/**
 * 
 */
var baseJs = {
	/**
	 * 合同审核
	 */
	checkedContract:function(id,fparentid){
		if(!id){
			id= base.selectFirst();
			fparentid = $("input[name='checkbox']:checked").attr("parentid");
		}
		if(!id){
			$.jBox.tip("请选择要审核的的数据","info");
			return false;
		}
		var html = "<form id='form1'>";
		html += '<input type="hidden" name="fparentid" value="'+fparentid+'">';
		html += '<input type="hidden" name="fcontractid" value="'+id+'">';
		html += '<label for="fstatus" style="text-align:right;">审核状态：</label>通过<input name="fstatus" value="1" type="radio"/> 不通过<input name="fstatus" value="2" type="radio"/><font color="red" id="errStatusTip"></font>';
		html += '<br/><br/><textarea name="fremark" id="fremark" cols="56" rows="6"></textarea><font color="red" id="errTip"></font>';
		html += '<br/><br/><b>注：该操作成功后，任务自动提交到下一节点！你已完成该任务。</b></form>';
		var kdialog = null;
		kdialog = KindEditor.dialog({
			width : 450,
			title : '合同审核',
			body : '<div id="txt_source_div" style="overflow:auto;overflow-x:hidden;margin:15px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			noBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {name : '提交',click : function(e) {
					var fstatus = $("input[name='fstatus']:checked").val();
					if(!fstatus){
						$("#errStatusTip").html("请选择审核状态！");
						return;
					}else if (fstatus == 0 && !$("#fremark").val()) {
						$("#errTip").html("该值不能为空！");
						return;
					}else{
						baseJs.submitForm("contract/checked");
					}
				}
			}
		});
	},
	submitForm : function(url){
		var basePath = _waiting.getRootPath();
		_waiting._show();
		$("#form1").ajaxSubmit({
			url:basePath+"/main/"+url,
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.status == 1){
					$.jBox.tip('操作成功','success');
					window.location.reload();
				}else{
					$.jBox.tip(data.result,'info');
				}
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('操作失败','error'); 
				_waiting._hide();
			}
		});
	},
	checked : function(nsid,fparentid,type,key,value,nodeName){
		var html = "<form id='form1'>";
		html += '<input type="hidden" name="id" value="'+nsid+'">';
		html += '<input type="hidden" name="fparentid" value="'+fparentid+'">';
		html += '<input type="hidden" name="'+key+'" value="'+value+'">';
		html += '<input type="hidden" name="fnodetitle" value="'+nodeName+'">';
		html += '<label for="fstatus" style="text-align:right;">审核状态：</label>通过<input name="fstatus" value="1" type="radio"/> 不通过<input name="fstatus" value="2" type="radio"/><font color="red" id="errStatusTip"></font>';
		html += '<br/><br/><textarea name="fremark" id="fremark" cols="56" rows="6"></textarea><font color="red" id="errTip"></font>';
		html += '<br/><br/><b>注：该操作成功后，任务自动提交到下一节点！你已完成该任务。</b></form>';
		var kdialog = null;
		kdialog = KindEditor.dialog({
			width : 450,
			title : '审核',
			body : '<div id="txt_source_div" style="overflow:auto;overflow-x:hidden;margin:15px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			noBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {name : '提交',click : function(e) {
					var fstatus = $("input[name='fstatus']:checked").val();
					if(!fstatus){
						$("#errStatusTip").html("请选择审核状态！");
						return;
					}else if (fstatus == 2 && !$("#fremark").val()) {
						$("#errTip").html("该值不能为空！");
						return;
					}else{
						baseJs.submitForm(type+"/checked");
					}
				}
			}
		});
	},
	release : function(id,rvid,nodeName){
		var html = "<form id='form1'>";
		html += '<input type="hidden" name="nsid" value="'+id+'">';
		html += '<input type="hidden" name="freleaseversionid" value="'+rvid+'">';
		html += '<input type="hidden" name="fdesc" value="'+nodeName+'">';
		html += '<label for="fstatus" style="text-align:right;">发布至：</label>' + nodeName;
		html += '<br/><br/><textarea name="fremark" id="fremark" cols="56" rows="6"></textarea><font color="red" id="errTip"></font>';
		html += '<br/><br/><b>注：该操作成功后，任务自动提交到下一节点！你已完成该任务。</b></form>';
		var kdialog = null;
		kdialog = KindEditor.dialog({
			width : 450,
			title : '广告版本发布',
			body : '<div id="txt_source_div" style="padding:0px;overflow:auto;overflow-x:hidden;margin:15px;">'+html+'</div>',
			shadowMode : true,
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			noBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {name : '提交',click : function(e) {	
					if (!$("#fremark").val()) {
						$("#errTip").html("该值不能为空！");
						return;
					}else{
						baseJs.submitForm("sourceRelease/release");
					}
				}
			}
		});
	}
};