$(document).ready(function(){
	navTag("系统管理&gt;频道分组");
	cgroup.load(1);
});
var cgroup={
	load : function(page){
		$("#AllCheck").attr("checked",false);
		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}
		var limit = base.getGlobalLimit();
		_waiting._show();
		$.post("query",{"page":page,"limit":limit},function(data,status){
			if(status){
				cgroup.bindGrid(data,limit);
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
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+obj.id+'"></td><td>'+obj.fname+'</td><td>'+(obj.fisprovincecompany==1 ? "省公司":"分公司")+'</td></tr>';
			});
		}else{
			html = "<tr><td colspan='3' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"cgroup.load",limit:limit,count:D.total});
	},
	edit : function(data,opt){
		var D = data.cgroup;
		var kdialog = null;
		if(!D)
			D = {"fname":"","id":"","fisprovincecompany":"0"};
		var html = '<form id="form1">';
		var checkbox = "checked='checked'";
		html += '<label for="fname">频点分组名称：</label><input id="fname" name="fname" value="'+D.fname+'" required="required" type="text" placeholder="请输入节点名称"/>';
		html += '<br/><br/><label for="fisprovincecompany">省公司：</label><input type="checkbox" value="1" name="fisprovincecompany" '+(D.fisprovincecompany=="1"?checkbox:"")+'>';
		html += '<input id="id" name="id" value="'+D.id+'" type="hidden" /></form>';
		kdialog = KindEditor.dialog({
			width : 450,
			height : 200,
			title : '编辑频点分组',
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
						cgroup.ajaxSubmit("form1",uri);
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
				cgroup.load();
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
				cgroup.edit(data,opt);
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
				cgroup.load();
			}else{
				$.jBox.tip("数据加载失败","error");
			}
			_waiting._hide();
		});
	}
};