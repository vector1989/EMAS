$(document).ready(function(){
	navTag("用户管理&gt;用户编辑");
	user.load(1);
});
var user={
	load : function(page){
		$("#AllCheck").attr("checked",false);
		if(!page){
			page = $("li[class='page-number pgCurrent']").text();
		}
		var limit = base.getGlobalLimit();
		_waiting._show();
		$.ajax({
			url:"query",
			type:"post",
			data:{"page":page,"limit":limit},
			dataType:"json",
			success:function(data){
				user.bindGrid(data,limit);
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip("数据加载失败","error");; 
				_waiting._hide();
			}
		});
	},
	bindGrid : function(D,limit){
		var html = '';
		if(D.total > 0){
			$.each(D.source,function(i,obj){
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+obj.id+'"></td><td>'+obj.fname+'</td><td>'+obj.fusername+'</td><td>'+obj.branch+'</td><td>'+obj.usergroup+'</td><td>'+obj.fdepart+'</td><td>'+obj.fpost+'</td></tr>';
			});
		}else{
			html = "<tr><td colspan='8' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"user.load",limit:limit,count:D.total});
	},
	edit : function(data,uri){
		var kdialog = null;
		
		var branchs = data.branchs;
		var groups = data.groups;
		var D = data.user;
		
		if(!D){
			D = {"fusername":"","fname":"","id":"","fbranchid":"","fusergroupid":"","fdepart":"","fpost":"","fcompany":"","fpassword":"123456"};
		}
		var regHtml = '';
		var select = 'selected="selected"';
		if(branchs)
			$.each(branchs,function(i,r){
				regHtml += '<option value="'+r.id+'" '+(r.id==D.fbranchid?select:"")+'>'+r.fname+'</option>';
			});
		var grouHtml = '';
		$.each(groups,function(i,g){
			grouHtml += '<option value="'+g.id+'" '+(g.id==D.fusergroupid?select:"")+'>'+g.fname+'</option>';
		});
		var html = '<form id="form1">';
		html += '<br/><br/><label for="fname">姓名：</label><input id="fname" name="fname" value="'+D.fname+'" required="required" type="text" placeholder="请输入姓名"/>';
		html += '<br/><br/><label for="fusername">用户名：</label><input id="fusername" name="fusername" value="'+D.fusername+'" required="required" type="text" placeholder="请输入用户名"/>';
		html += '<br/><br/><label for="fpassword">密码：</label><input id="fpassword" name="fpassword" value="'+D.fpassword+'" required="required" type="password" placeholder="请输入密码"/>默认密码为：123456';
		if(branchs)
			html += '<br/><br/><label for="fbranchid">公司：</label><select id="fbranchid" name="fbranchid"><option value="">==请选择==</option>'+regHtml+'</select>';
		html += '<br/><br/><label for="fusergroupid">用户组：</label><select id="fusergroupid" required="required" name="fusergroupid"><option value="">==请选择==</option>'+grouHtml+'</select>';
		html += '<br/><br/><label for="fdepart">部门：</label><input id="fdepart" name="fdepart" value="'+D.fdepart+'" type="text" placeholder="请输入部门"/>';
		html += '<br/><br/><label for="fpost">职位：</label><input id="fpost" name="fpost" value="'+D.fpost+'" type="text" placeholder="请输入职位"/>';
		html += '<input id="id" name="id" value="'+D.id+'" type="hidden" /></form>';
		kdialog = KindEditor.dialog({
			width : 450,
			height : 430,
			title : '新增用户',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:0px;margin-left:55px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					if(base.formIsNull("form1")){
						if(!uri)
							uri = "insert";
						user.ajaxSubmit("form1",uri);
						user.load();
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
				$.jBox.tip('数据保存成功','success');
				user.load();
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('数据保存失败'); 
				_waiting._hide();
			}
		});
	},
	ajaxLoadById:function(opt,id){
		//var id= null;
		var uri = "insert";
		if(opt){
			if(id==null){ id = base.selectFirst(); }
			if(id) uri = "update";
		}
		_waiting._show();
		$.post("selectByKey",{"id":id},function(data,status){
			if(status=="success"){
				user.edit(data,uri);
			}else
				$.jBox.tip("数据加载失败","error");; 
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
			if(status=="success"){
				$.jBox.tip('数据删除成功','success');
				user.load();
			}else
				$.jBox.tip("数据加载失败","error");; 
			_waiting._hide();
		},"json");
	}
};