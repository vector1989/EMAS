$(document).ready(function(){
	navTag("系统管理&gt;数据库配置");
	dbconfig.load(1);
});
var dbconfig={
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
				dbconfig.bindGrid(data,limit);
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据加载失败','error'); 
				_waiting._hide();
			}
		});
	},
	bindGrid : function(V,limit){
		var D = eval(V);
		var html = '';
		if(D.total > 0){
			$.each(D.source,function(i,obj){//<td>'+obj.id+'</td>
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+obj.id+'"></td><td>'+obj.fip+'</td><td>'+obj.fuser+'</td><td>'+obj.fpasswd+'</td><td>'+(obj.temp != "" ? obj.temp : "<font color='red'>省公司测试</font>")+'</td></tr>';
			});
		}else{
			html = "<tr><td colspan='6' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"dbconfig.load",limit:limit,count:D.total});
	},
	isProvTest : function(obj){
		console.debug(obj.checked);
		if(obj.checked){
			$("#branch").css("display","none");
		}else{
			$("#branch").css("display","");
		}
	},
	edit : function(v,uri){
		var kdialog = null;
		var D = v.config;
		if(!D){
			D = {"fuser":"root","fip":"","id":"","fpasswd":"a1s2d3","fbranchid":""};
		}
		var branchHtml = "";
		var selected = "selected='selected'";
		$.each(v.branchs,function(i,b){
			branchHtml += "<option value='"+b.id+"' "+(b.id==D.fbranchid?selected:'')+">"+b.fname+"</option>";
		});
		var html = '<style>label{width:105px;}</style><form id="form1">';
		html += '<br/><label for="fip">服务器IP地址：</label><input id="fip" name="fip" value="'+D.fip+'" required="required" type="text" placeholder="请输入服务器ip地址"/>';
		html += '<br/><br/><label for="fuser">数据库服务用户：</label><input id="fuser" name="fuser" value="'+D.fuser+'" required="required" type="text" placeholder="请输入数据库服务用户名"/>';
		html += '<br/><br/><label for="fpasswd">数据库服务密码：</label><input id="fpasswd" name="fpasswd" value="'+D.fpasswd+'" required="required" type="text" placeholder="请输入数据库服务密码"/>';
		html += '<br/><br/><label for="ftest">省公司测试：</label><input id="ftest" name="ftest" onchange="dbconfig.isProvTest(this);" value="1" '+(D.fbranchid==-1?"checked='checcked'":"")+' type="checkbox" placeholder="省公司测试地址"/>';
		html += '<span id="branch"'+(D.fbranchid==-1?"style='display:none;'":"")+'><br/><br/><label for="fbranchid">分公司：</label><select id="fbrancheid" name="fbranchid" required="required">'+branchHtml+'</select></span>';
		html += '<input id="id" name="id" value="'+D.id+'" type="hidden" /></form>';
		kdialog = KindEditor.dialog({
			width : 450,
			height : 330,
			title : '分公司数据库配置',
			body : '<div id="txt_source_div" style="padding:0px;overflow:auto;overflow-x:hidden;padding:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					if(!base.formIsNull("form1")){
						return;
					}
					dbconfig.ajaxSubmit("form1",uri,kdialog);
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
	},
	ajaxSubmit:function(form,uri,K){
		_waiting._show();
		$("#"+form).ajaxSubmit({
			url:uri,
			type:'post',
			success:function(data){
				if(data == -1){
					$.jBox.tip('该分公司已存在数据库配置信息','error');
				}else{
					$.jBox.tip('数据保存成功','success');
					K.remove();
					dbconfig.load();
				}
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('数据保存失败','error'); 
				_waiting._hide();
			}
		});
	},
	ajaxLoadById:function(cmd){
		var uri = "insert";
		if(cmd) uri = "update";
		var id= base.selectFirst();
		_waiting._show();
		$.ajax({
			url:"selectByKey",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				dbconfig.edit(data,uri);
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据加载失败','error'); 
				_waiting._hide();
			}
		});
	},
	deleteSource:function(){
		var id = base.selectValue();
		if(!id){
			$.jBox.tip("请选择要删除的数据",'info');
			return false;
		}
		_waiting._show();
		$.ajax({
			url:"delete",
			type:"post",
			data:{"id":id},
			success:function(data){
				$.jBox.tip(data,'info');
				dbconfig.load();
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据删除失败','error'); 
				_waiting._hide();
			}
		});
	},
	loadBranchAdvClassQuery:function(){
		_waiting._show();
		var fbranchid = base.selectFirst();
		$.post("branchAdvClassQuery",{"fbranchid":fbranchid},function(data,status){
			if(status){
				dbconfig.branchAdvClass(data);
			}else{
				$.jBox.tip('数据加载失败','error'); 
			}
			_waiting._hide();
		},"json");
	},
	branchAdvClass:function(data){
		var html = "";
		var advclasses = data.advclasses;
		var bas = data.bas;
		var checked = "checked='checked'";
		$.each(advclasses,function(i,a){
			var bool = true;
			$.each(bas,function(j,b){
				if(b.fadvclassid == a.id){
					html += "<div style='width:150px; float:left;'>"+a.ftype + '<input name="fadvclassid" value="'+a.id+'" type="checkbox" '+checked+'/></div>';
					bool = false;
					return;
				}
			});
			if(bool){
				html += "<div style='width:150px; float:left'>"+a.ftype + '<input name="fadvclassid" value="'+a.id+'" type="checkbox"/></div>';
			}
		});
		kdialog = KindEditor.dialog({
			width : 500,
			height : 230,
			title : '分公司广告位设置',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:10px;margin-left:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					var checkbox = $("input[name='fadvclassid']:checked");
					var val=new Array();
					checkbox.each(function(i){ //由于复选框一般选中的是多个,所以可以循环输出
						val.push($(this).val());
					});
					var advids = val.join(",");
					var fbranchid= base.selectFirst();
					if(advids){
						$.post("branchAdvClassInsert",{"fbranchid":fbranchid,"advids":advids},function(data,status){
							if(status){
								$.jBox.tip('数据保存成功','success');
								dbconfig.load();
								kdialog.remove();
							}else{
								$.jBox.tip('数据保存失败','error'); 
							}
						},"json");
					}else{
						$.jBox.tip('请选择广告位','info'); 
					}
				}
			}
		});
	}
};