$(document).ready(function(){
	navTag("广告管理&gt;流程配置");
	node.load(1);
});
var node={
	load : function(page){
		$("#AllCheck").attr("checked",false);
		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}
		var limit = base.getGlobalLimit();
		_waiting._show();
		$.post("query",{"page":page,"limit":limit},function(data,status){
			if(status){
				node.bindGrid(data,limit);
			}else{
				$.jBox.tip("数据加载失败","error");
			}
			_waiting._hide();
		},"json");
	},
	bindGrid : function(D,limit){
		var html = '';
		if(D.source.length > 0){
			$.each(D.source,function(i,obj){
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+obj.id+'"></td><td>'+obj.fname+'</td><td>'+(obj.fisprovincecompany==1?"省公司":"分公司")+'</td><td>'+obj.temp+'</td><td>'+(obj.ftype==0?"合同":(obj.ftype==2?"广告测试":"广告"))+'</td><td>'+(obj.fischecked==0?"编辑":obj.fischecked==1?"审核":"发布")+'</td></tr>';
			});
		}else{
			html = "<tr><td colspan='4' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"node.load",limit:limit,count:D.total});
	},
	edit : function(data,opt){
		var D = data.node;
		var kdialog = null;
		if(!D)
			D = {"fname":"","id":"","ftype":"0","fischecked":"0","fusergroupid":"","fisprovincecompany":""};
		var select = "selected='selected'";
		var html = '<form id="form1">';
		html += '<label for="fname">节点名称：</label><input id="fname" name="fname" value="'+D.fname+'" required="required" type="text" placeholder="请输入节点名称"/>';
		html += '<br/><br/><label for="ftype">节点类型：</label><select id="ftype" name="ftype" placeholder="请选择节点类型"><option value="0" '+(D.ftype==0?select:"")+'>合同</option><option value="1" '+(D.ftype==1?select:"")+'>广告</option><option value="2" '+(D.ftype==2?select:"")+'>广告测试</option></select>';
		html += '<br/><br/><label for="fischecked">节点分类：</label><select id="fischecked" name="fischecked" placeholder="请选择节点分类"><option value="0" '+(D.fischecked==0?select:"")+'>编辑</option><option value="1" '+(D.fischecked==1?select:"")+'>审核</option><option value="2" '+(D.fischecked==2?select:"")+'>发布</option></select>';
		html += '<br/><br/><label for="fusergroupid">用户分组：</label><select id="fusergroupid" name="fusergroupid" required="required" placeholder="请选择用户分组">';
		$.each(data.usergroups,function(i,d){
			html += '<option value="'+d.id+'" '+(d.id == D.fusergroupid ? select : "")+'>'+d.fname+'</option>';
		});
		html += '</select>';
		var checked = "checked='checked'";
		html += '<br/><br/><label for="fisprovincecompany">省公司操作：</label><input name="fisprovincecompany" type="checkbox" value="1" '+(D.fisprovincecompany==1?checked:"")+'>';
		html += '<input id="id" name="id" value="'+D.id+'" type="hidden" /></form>';
		kdialog = KindEditor.dialog({
			width : 450,
			height : 350,
			title : '编辑控制流程节点',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					var uri = "insert";
					if(opt)
						uri = "update";
					if(base.formIsNull()){
						node.ajaxSubmit("form1",uri);
						kdialog.remove();
					}
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
				$.jBox.tip("数据保存成功","success");
				_waiting._hide();
				node.load();
			},
			error:function(msg){
				$.jBox.tip("数据保存失败","error");
				_waiting._hide();
			}
		});
	},
	ajaxLoadById:function(opt){
		var id = "";
		if(opt){
			id = base.selectFirst();
			if(!id){
				$.jBox.tip("请选择需修改的数据","info");
				return;
			}
		}
		_waiting._show();
		$.post("selectByKey",{"id":id},function(data,status){
			if(status){
				node.edit(data,opt);
			}else{
				$.jBox.tip("数据加载失败","error");
			}
			_waiting._hide();
		},"json");
	},
	deleteSource:function(){
		var id = base.selectValue();
		if(!id){
			$.jBox.tip("请选择要删除的数据","error");
			return false;
		}
		_waiting._show();
		$.post("delete",{"id":id},function(data,status){
			if(status){
				$.jBox.tip(data,"success");
				node.load();
			}else{
				$.jBox.tip("数据加载失败","error");
			}
			_waiting._hide();
		});
	}
};