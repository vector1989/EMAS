$(document).ready(function(){
	userNode.load(1);
});
var userNode={
	load : function(page){
		$("#AllCheck").attr("checked",false);
		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}
		var limit = base.getGlobalLimit();
		_waiting._show();
		$.post("query",{"page":page,"limit":limit,"fbranchid":$("#fbranchId").val()},function(data,status){
			if(status){
				userNode.bindGrid(data,limit);
			}else{
				$.jBox.tip('数据加载失败',"error"); 
			}
			_waiting._hide();
		},"json");
	},
	bindGrid : function(D,limit){
		var html = '';
		var source = D.source;
		if(source && source.length>0){
			$.each(D.source,function(i,obj){
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+obj.id+'" order="'+obj.forder+'"></td><td>'+obj.node+'</td><td>'+obj.branch+'</td><td>'+obj.userName+'</td><td id="td'+i+'">'+obj.forder+'</td><td>'+(obj.ftype==0?"合同":"广告")+'</td></tr>';
			});
		}else{
			html = "<tr><td colspan='6' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"userNode.load",limit:limit,count:D.total});
	},
	edit : function(D,uri){
		var kdialog = null;
		var source = D.userNode;
		if(!source){ source = {"fname":"","id":"","fuserid":"","fnodeid":"","fbranchid":""}; }
		var select = "selected='selected'";
		var branchHtml="";
		var fbranchid = $("#fbranchId").val();
		$.each(D.branchs,function(i,b){
			branchHtml += '<option value="'+b.id+'" '+(b.id==fbranchid?select:"")+'>'+b.fname+'</option>';
		});
		var nodeHtml = "";
		$.each(D.nodes,function(i,n){
			nodeHtml += '<option value="'+n.id+'" '+(n.id==source.fnodeid?select:"")+'>'+n.fname+'</option>';
		});
		var groupHtml = "";
		$.each(D.usergroups,function(i,g){
			groupHtml += '<option value="'+g.id+'" '+(g.id==source.fuserid?select:"")+'>'+g.fname+'</option>';
		});
		var html = '<form id="form1">';
		html += '<label for="fbranchid">分公司名称：</label><select name="fbranchid" required="required">'+branchHtml+'</select>';
		html += '<br/><br/><label for="fnodeid">节点名称：</label><select name="fnodeid" required="required">'+nodeHtml+'</select>';
		html += '<br/><br/><label for="fusergroupid">用户分组：</label><select name="fusergroupid" required="required">'+groupHtml+'</select>';
		html += '<input id="id" name="id" value="'+source.id+'" type="hidden" /></form>';
		kdialog = KindEditor.dialog({
			width : 350,
			height : 250,
			title : '编辑分公司流程管理',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin:50px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					userNode.ajaxSubmit("form1",uri);
					kdialog.remove();
					userNode.load();
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
	},
	ajaxSubmit:function(form,uri){
		_waiting._show();
		$("#"+form).ajaxSubmit({
			url:uri,
			type:'post',
			dataType:'json',
			success:function(data){
				$.jBox.tip('数据保存成功','success');
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('数据保存失败','error'); 
				_waiting._hide();
			}
		});
	},
	ajaxLoadById:function(opt){
		var id= base.selectFirst();
		var uri = "insert";
		if(opt) uri = "update";
		_waiting._show();
		$.post("selectByKey",{"id":id},function(data,status){
			if(status){
				userNode.edit(data,uri);
			}else{
				$.jBox.tip('数据加载失败',"error"); 
			}
			_waiting._hide();
		},"json");
	},
	deleteSource:function(){
		var id = base.selectValue();
		if(!id){
			$.jBox.tip("请选择要删除的数据",'info');
			return false;
		}
		_waiting._show();
		$.post("delete",{"id":id},function(data,status){
			if(status){
				$.jBox.tip(data,'info');
				userNode.load();
			}else{
				$.jBox.tip('数据加载失败',"error"); 
			}
			_waiting._hide();
		},"text");
	}
};

//排序
var order = {
	moveUp : function(){
		var inputObj = $("input[name='checkbox']:checked");
		//排序值
		var order = inputObj.attr("order");
		if(order){
			var orderInt = parseInt(order);
			var orderDown = orderInt-1;
			inputObj.attr("order",orderDown);
			//设置表格显示的值
			var td = inputObj.attr("id").replace("checkbox","td");
			$("#"+td).text(orderDown);
			var obj = inputObj.attr("id").replace("checkbox","tr");
			var onthis = $("#"+obj);
			var getup = onthis.prev();
			var upTd = getup.attr("id").replace("tr","td");
			$("#"+upTd).text(order);
			getup.attr("order",order);
			$(getup).before(onthis);
		}
	},
	moveDown : function(){
		var inputObj = $("input[name='checkbox']:checked");
		//排序值
		var order = inputObj.attr("order");
		var orderInt = parseInt(order);
		var orderUp = orderInt+1;
		inputObj.attr("order",orderUp);
		//设置表格显示的值
		var td = inputObj.attr("id").replace("checkbox","td");
		$("#"+td).text(orderUp);
		var obj = inputObj.attr("id").replace("checkbox","tr");
		var onthis = $("#"+obj);
		var getdown = onthis.next();
		var upTd = getdown.attr("id").replace("tr","td");
		$("#"+upTd).text(order);
		getdown.attr("order",order);
		$(getdown).after(onthis);
	}
};