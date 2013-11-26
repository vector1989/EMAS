$(document).ready(function(){
	navTag("用户管理&gt;用户分组");
	group.load(1);
});
var group={
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
				group.bindGrid(data,limit);
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip("数据加载失败","error");; 
				_waiting._hide();
			}
		});
	},
	bindGrid : function(V,limit){
		var D = eval(V);
		var html = '';
		if(D.total > 0){
			$.each(D.source,function(i,obj){
				html += '<tr class="tr" name="tr'+i+'" id="tr'+i+'"><td><input type="checkbox" name="checkbox" id="checkbox'+i+'" value="'+obj.id+'"></td><td>'+obj.fname+'</td><td>'+(obj.ftype==0?"合同":(obj.ftype==1?"广告":(obj.ftype==3 ? "广告测试":"管理员")))+'</td></tr>';
			});
		}else{
			html = "<tr><td colspan='4' align='center'>暂无数据</td></tr>";
		}
		$("#dataGrid").html(html);
		check.addCk("dataGrid");
		$("#pager").pager({ pagenumber: D.page, pagecount: D.totalPage, buttonClickCallback: this.load, limitFun:"group.load",limit:limit,count:D.total});
	},
	edit : function(D,uri){
		var kdialog = null;
		if(!D){
			D = {"fname":"","id":"","ftype":1};
		}
		var select = "selected='selected'";
		var html = '<form id="form1">';
		html += '<br/><br/><label for="fname">分组名称：</label><input id="fname" name="fname" value="'+D.fname+'" required="required" type="text" placeholder="请输入用户分组名称"/>';
		html += '<br/><br/><label for="fname">分组类型：</label><select id="ftype" name="ftype" placeholder="请输入节点类型"><option value="0" '+(D.ftype==0?select:"")+'>合同</option><option value="1" '+(D.ftype==1?select:"")+'>广告</option><option value="3" '+(D.ftype==3?select:"")+'>广告测试</option><option value="2" '+(D.ftype==2?select:"")+'>管理员</option></select>';
		html += '<input id="id" name="id" value="'+D.id+'" type="hidden" /></form>';
		kdialog = KindEditor.dialog({
			width : 350,
			height : 250,
			title : '新建用户分组',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:30px;margin-left:30px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					if(!uri)
						uri = "insert";
					group.ajaxSubmit("form1",uri);
					group.load();
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
				group.edit(data,"update");
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip("数据加载失败","error");; 
				_waiting._hide();
			}
		});
	},
	deleteSource:function(){
		var id = base.selectValue();
		if(!id){
			alert("请选择要删除的数据");
			return false;
		}
		_waiting._show();
		$.ajax({
			url:"delete",
			type:"post",
			data:{"id":id},
			success:function(data){
				$.jBox.tip(data,'info');
				group.load();
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip(data,'error');
				_waiting._hide();
			}
		});
	}
};
var menu = {
	load : function(){
		var id= base.selectFirst();
		_waiting._show();
		if(id)
			$.ajax({
				url:"../menuUser/query",
				type:"post",
				data:{"fusergroupid":id},
				dataType:"json",
				success:function(data){
					menu.bindGrid(data);
					_waiting._hide();
				},
				error:function(data){
					$.jBox.tip("数据加载失败","error");; 
					_waiting._hide();
				}
			});
		else{
			$.jBox.tip("请选择用户分组！","info");
		}
	},
	bindGrid : function(data){
		var kdialog = null;
		var checkStr = "checked='checked'";
		var html = '<form id="form1">';
		$.each(data,function(i,o){
			if(o.fparentid == -1){
				html += "<div><fieldset><legend>" + o.fname + "</legend>";
				$.each(data,function(j,obj){
					if(o.id==obj.fparentid){
						html += "<div style='float:left;width:300px;font-size:15px;'><dt style='width:100px;float:left;'>"+obj.fname+": </dt>";
						html += " <b>r</b><input type='checkbox' line='line"+j+"' "+(obj.temp=='r'?checkStr:'')+" onclick='menu.lineChecked(this,this.checked)' class='menu' o='r' value='"+obj.id+":r'> ";
						html += " <b>w</b><input type='checkbox' line='line"+j+"' "+(obj.temp=='w'?checkStr:'')+" onclick='menu.lineChecked(this,this.checked)' class='menu' o='w' value='"+obj.id+":w'> ";
						html += " <b>c</b><input type='checkbox' line='line"+j+"' "+(obj.temp=='c'?checkStr:'')+" onclick='menu.lineChecked(this,this.checked)' class='menu' o='c' value='"+obj.id+":c'> ";
						html += " <b>s</b><input type='checkbox' line='line"+j+"' "+(obj.temp=='a'?checkStr:'')+" onclick='menu.lineChecked(this,this.checked)' class='menu' o='a' value='"+obj.id+":a'> </div>";
					}
				});
				html += "<div style='clear:both;'></div></div></fieldset>";
			}
			
		});
		html += '</form>';
		html += "<div>快捷选择：只读<input class='all' onclick='menu.ischecked(this.value,this.checked);' id='r' type='checkbox' value='r'>";
		html += "读写<input class='all' onclick='menu.ischecked(this.value,this.checked);' id='w' type='checkbox' value='w'>";
		html += "审核<input class='all' onclick='menu.ischecked(this.value,this.checked);' id='c' type='checkbox' value='c'>";
		html += "超级<input class='all' onclick='menu.ischecked(this.value,this.checked);' id='a' type='checkbox' value='a'></div>";
		html += '<b>注：r表示只读，w表示读写，c表示审核，s超级管理员</b><br/>';
		kdialog = KindEditor.dialog({
			width : 660,
			height : 550,
			title : '用户权限设置',
			body : '<div id="txt_source_div" style="padding:10px;overflow:auto;overflow-x:hidden;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}},
			yesBtn : {
				name : '保存',
				click : function(e) {
					var str= menu.getCheckedVal();
					var id= base.selectFirst();
					_waiting._show();
					if(id){
						$.ajax({
							url:"../menuUser/insert",
							type:"post",
							data:{"str":str,"fusergroupid":id},
							dataType:"json",
							success:function(data){
								$.jBox.tip('用户分组权限设置成功','success'); 
								kdialog.remove();
								_waiting._hide();
							},
							error:function(data){
								$.jBox.tip("数据加载失败","error");; 
								kdialog.remove();
								_waiting._hide();
							}
						});
					}else{
						$.jBox.tip('请选择菜单操作模式','info'); 
					}
				}
			}
		});
	},
	ischecked : function(val,c){
		$("input[class='all']").removeAttr("checked");
		
		if(c){
			$("input[class='menu']").removeAttr("checked");
			$("input[value='"+val+"']").attr("checked",true);
			$("input[o='"+val+"']").attr("checked",true);
		}else{
			$("input[value='"+val+"']").removeAttr("checked");
			$("input[o='"+val+"']").removeAttr("checked");
		}
	},
	lineChecked:function(o,c){
		var obj= $(o);
		var line = obj.attr("line");
		$("input[line="+line+"]").removeAttr("checked");
		if(c)
			obj.attr("checked",true);
		else
			obj.removeAttr("checked");
	},
	getCheckedVal : function(){
		var checkbox = $("input[class='menu']:checked");
		var val=new Array();
		checkbox.each(function(i){ //由于复选框一般选中的是多个,所以可以循环输出
			val.push($(this).val());
		});
		return val.join(",");
	}
};