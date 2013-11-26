$(document).ready(function(){
	navTag("系统管理&gt;分公司信息");
	branch.load(1);
});
var branch={
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
				branch.bindGrid(data,limit);
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
			$.each(D.source,function(i,obj){
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" rvid="'+obj.fguid+'|'+obj.fupdatetime+'" value="'+obj.id+'"></td>';
				html += '<td>'+obj.fcode+'</td><td>'+obj.fname+'</td><td>'+obj.faddress+'</td><td>'+obj.temp+'</td><td>'+(obj.fisspecialchannel == 1 ? "允许" : "不允许")+'</td></tr>';
			});
		}else{
			html = "<tr><td colspan='4' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"branch.load",limit:limit,count:D.total});
	},
	edit : function(D,uri){
		var kdialog = null;
		if(!D){
			D = {"fcode":"","fname":"","id":"","faddress":"","fisspecialchannel":""};
		}
		var checkbox = (D.fisspecialchannel == 1 ? "checked='checked'" : "");
		var html = '<form id="form1">';
		html += '<label for="fname">公司名称：</label><input id="fname" name="fname" value="'+D.fname+'" required="required" type="text" placeholder="请输入公司名称"/>';
		html += '<br/><br/><label for="fcode">公司编号：</label><input id="fcode" name="fcode" value="'+D.fcode+'" required="required" type="text" placeholder="请输入公司编号"/>';
		html += '<br/><br/><label for="fcode">公司地址：</label><input id="faddress" name="faddress" value="'+D.faddress+'" required="required" type="text" placeholder="请输入公司地址"/>';
		html += '<br/><br/><label for="fisspecialchannel">素材特殊通道：</label><input id="fisspecialchannel" name="fisspecialchannel" value="1" required="required" type="checkbox" '+checkbox+' placeholder="请选择是否为特殊通道"/>';
		html += '<input id="id" name="id" value="'+D.id+'" type="hidden" /></form>';
		kdialog = KindEditor.dialog({
			width : 350,
			height : 250,
			title : '新增分公司',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					if(!uri)
						uri = "insert";
					branch.ajaxSubmit("form1",uri);
					kdialog.remove();
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
				branch.load();
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('数据保存失败','error'); 
				_waiting._hide();
			}
		});
	},
	ajaxLoadById:function(){
		var id= base.selectFirst();
		_waiting._show();
		$.ajax({
			url:"selectByKey",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				branch.edit(data,"update");
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
		_waiting._show();
		if(!id){
			$.jBox.tip("请选择要删除的数据",'info');
			return false;
		}
		$.ajax({
			url:"delete",
			type:"post",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				$.jBox.tip('数据删除成功','suucess');
				branch.load();
			},
			error:function(data){
				$.jBox.tip('数据加载失败','error'); 
			}
		});
	},
	loadBranchAdvClassQuery:function(){
		var input = $("input[name='checkbox']:checked");
		var fbranchid = input.val();
		var rvid = input.attr("rvid");
		
		if(fbranchid){
			_waiting._show();
			$.post("branchAdvClassQuery",{"fbranchid":fbranchid,"rvid":rvid},function(data,status){
				if(status){
					branch.branchAdvClass(data);
					_waiting._hide();
				}else{
					$.jBox.tip('数据加载失败','error'); 
					_waiting._hide();
				}
			},"json");
		}else{
			$.jBox.tip("请选择分公司",'info');
		}
	},
	branchAdvClass:function(data){
		var html = "<style>label{width:120px;}</style><div >联动:<input name='linked' id='linked' type='checkbox' value='1' checked='checked'/></div><br/>";
		var advs = data.advs;
		var userAdvs = data.userAdvs;
		var checked = "checked='checked'";
		var hdListHtml = "";
		$.each(advs,function(i,a){
			var bool = true;
			if(a.fdefinition == "HD"){
				if(userAdvs){
					$.each(userAdvs,function(j,b){
						if(b.id == a.id){
							if(a.fpositionid == 7){
								hdListHtml = "<div style='width:150px; float:left;'><input name='fadvclassid' value='"+a.id+"|"+a.fdefinition+"' pos='"+a.fpositionid+"' onchange='branch.ischeckedLinked(this,"+a.fpositionid+")' type='checkbox' "+checked+"/><label>[" + a.ftype + "|" + a.fdefinition + "]</label></div>";
								bool = false;
								return;
							}else{
								html += "<div style='width:150px; float:left;'><input name='fadvclassid' value='"+a.id+"|"+a.fdefinition+"' pos='"+a.fpositionid+"' onchange='branch.ischeckedLinked(this,"+a.fpositionid+")' type='checkbox' "+checked+"/><label>[" + a.ftype + "|" + a.fdefinition + "]</label></div>";
								bool = false;
								return;
							}
						}
					});
				}
				if(bool){
					if(a.fpositionid == 7){
						hdListHtml = '<div style="width:150px; float:left"><input name="fadvclassid" value="'+a.id+'|'+a.fdefinition+'" pos="'+a.fpositionid+'" onchange="branch.ischeckedLinked(this,'+a.fpositionid+')" type="checkbox"/><label>[' + a.ftype + '|' + a.fdefinition + ']</label></div>';
					}else{
						html += '<div style="width:150px; float:left"><input name="fadvclassid" value="'+a.id+'|'+a.fdefinition+'" pos="'+a.fpositionid+'" onchange="branch.ischeckedLinked(this,'+a.fpositionid+')" type="checkbox"/><label>[' + a.ftype + '|' + a.fdefinition + ']</label></div>';
					}
				}
			}
		});
		html += hdListHtml + "<div class='clear'></div>";
		$.each(advs,function(i,a){
			var bool = true;
			if(a.fdefinition == "SD"){
				if(userAdvs){
					$.each(userAdvs,function(j,b){
						if(b.id == a.id){
							html += "<div style='width:150px; float:left;'><input name='fadvclassid' value='"+a.id+"|"+a.fdefinition+"' pos='"+a.fpositionid+"' onchange='branch.ischeckedLinked(this,"+a.fpositionid+")' type='checkbox' "+checked+"/><label>[" + a.ftype + "|" + a.fdefinition + "]</label></div>";
							bool = false;
							return;
						}
					});
				}
				if(bool){
					html += '<div style="width:150px; float:left"><input name="fadvclassid" pos="'+a.fpositionid+'" onchange="branch.ischeckedLinked(this,'+a.fpositionid+')" value="'+a.id+'|'+a.fdefinition+'" type="checkbox"/><label>[' + a.ftype + '|' + a.fdefinition + ']</label></div>';
				}
			}
		});
		
		
		kdialog = KindEditor.dialog({
//			width : 600,
//			height : 230,
			title : '分公司广告位设置',
			body : '<div id="txt_source_div" style="padding:0px;overflow:auto;overflow-x:hidden;margin:30px; margin-top:5px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}},
			previewBtn : {name : '预览',click : function(e) {
//				var K = new _dialog({"name":"加载中"});
//				K.dialog.showLoading("玩命加载中......");
			}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					var checkbox = $("input[name='fadvclassid']:checked");
					var val=new Array();
					checkbox.each(function(i){ //由于复选框一般选中的是多个,所以可以循环输出
						val.push($(this).val());
					});
					var advids = val.join(",");
					var input = $("input[name='checkbox']:checked");
					var fbranchid = input.val();
					var rvid = input.attr("rvid");
//					if(advids){
					$.post("branchAdvClassInsert",{"fbranchid":fbranchid,"advids":advids,"rvid":rvid},function(data,status){
						if(status){
							$.jBox.tip('数据保存成功','success');
							branch.load();
							kdialog.remove();
						}else{
							$.jBox.tip('数据保存失败','error'); 
						}
					},"json");
//					}else{
					/*	$.jBox.tip('请选择广告位','info'); 
					}*/
				}
			}
		});
	},
	ischeckedLinked : function(obj,pos){
		var isLinked = $("#linked").attr("checked");
		var ischecked = obj.checked;
		if(isLinked){
			if(ischecked){
				$("input[pos='"+pos+"']").attr("checked",true);
			}else{
				$("input[pos='"+pos+"']").removeAttr("checked");
			}
		}
	}
};