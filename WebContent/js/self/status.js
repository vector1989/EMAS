var taskStatus={
	show : function(id){
		var content = $("#" +id).text();
		$.jBox(content,{title:"详细信息","top":"10%","width":800,"height":500});
	},
	showOrHide : function(id){
		var a = $("#a"+id);
		var div = $("#id"+id);
		var display = div.css("display");
		if("none" == display){
			div.css("display","");
			a.text("关闭");
		}else{
			div.css("display","none");
			a.text("打开");
		}
	},
	checked : function(nid,carid,caid,contractid,sIndex,sid,opt){
		var s = $("#status"+sIndex).text();
		var r = $("#remark"+sIndex).text();
		if(!s) s = "";
		if(!r) r = "";
		if(!sid) sid = "";
		var select = "selected='selected'";
		var html = "<form id='form1'>";
		html += "<input name='fnodeid' value='"+nid+"' type='hidden'>";
		if(caid){
			html += "<input name='fcontractadvresourceid' value='"+carid+"' type='hidden'>";
			html += "<input name='id' value='"+sid+"' type='hidden'>";
			html += "<input name='fcontractadvid' value='"+caid+"' type='hidden'>";
			html += "<input name='fcontractid' value='"+contractid+"' type='hidden'>";
		}else{
			html += "<input name='freleaseversionid' value='"+carid+"' type='hidden'>";
		}
		html += '<label for="fstatus">审核状态：</label><select name="fstatus" id="fstatus"><option value="1" '+(s==1 ? select : '')+'>通过</option><option value="2" '+(s==2 ? select : '')+'>不通过</option></select>';
		html += '<br/><br/><textarea name="fremark" cols="56" rows="6">'+r+'</textarea></form>';
		var kdialog = null;
		kdialog = KindEditor.dialog({
			width : 450,
			height : 240,
			title : '广告审核',
			body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin:15px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			noBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {name : '提交',click : function(e) {taskStatus.submitForm(opt);}}
		});
	},
	submitForm : function(opt){
		var uri = "insert";
		if(opt) uri = "update";
		$("#form1").ajaxSubmit({
			url:uri,
			type:'post',
			dataType:'json',
			success:function(data){
				$.jBox.tip('操作成功','success');
//				window.location.reload();
				parent.window.location.reload();
				window.close();
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('操作失败','error'); 
				_waiting._hide();
			}
		});
	}
};