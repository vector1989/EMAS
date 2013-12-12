var setting = {
	view: {
		showLine: true,
		showIcon: true,
		selectedMulti: false,
		dblClickExpand: false,
		fontCss: getFont,
		nameIsHTML: true
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback: {
		beforeClick : beforeClick,
		onClick: onClick
	}
};
function beforeClick(treeId, treeNode, clickFlag) {
	$("#showChannel").css("display","none");
	var pos = treeNode.positionid;
	if(pos == 4){
		$("#showChannel").css("display","");
	}
	return true;
}
/**
 * 
 * @param event
 * @param treeId
 * @param treeNode
 * @param clickFlag
 */
function onClick(event, treeId, treeNode, clickFlag) {
	source.queryByExample(1,treeNode);
}
function getFont(treeId, node) {
	var font = {'font-weight':'bold',"color":"red"};
	if(node.disabled){
		font = {};
	}
	if(node.root){
		node.icon = "../../images/1_open.png";
		font = {'font-weight':'bold','color':'blue',"font-size":"20px"};
	}
	return font;
}
$(document).ready(function(){
	source.createTree();
});
var sourceRelease={
	checkSourceByTimeId : function(){
		var posids = [-3,1,2,3,5,6,7,8,10,11,12];
		for(var i=1,len = posids.length;i <= len;i++){
			var cbox = $("input[name='checkbox'][pos="+posids[i]+"]:checked");
			var tids = [];
			$.each(cbox,function(b){
				tids.push($(b).attr("tid"));
			});
			if(tids.length > 0){
				//数组排序
				var sortTids = tids.sort();
				for(var i = 0,len = sortTids.length-1;i<len;i++){
					if(sortTids[i] == sortTids[i+1]){
						return true;
					}
				}
			}
		}
		return false;
	},
	checkNull : function(){
		var desc = $("#fremark").val();
		if(!desc){
			var tip = "";
			if(!desc){
				tip = "请填入版本描述信息";
			}
			$("#desctip").html(tip);
			return false;
		}else{
			$("#desctip").html("");
			return true;
		}
	},
	release:function(){
		var version = $("#freleaseversionid").find("option:selected");
		var versionNo = version.text();
		var label = version.attr("label");
		if(!versionNo){
			$.jBox.tip("暂无待发布广告版本信息","info");
			return;
		}
		var remark = sourceRelease.toStatus(label);
		var html = "<form id='form1'><label style='width:70px;'>版本：</label>"+versionNo;
		html += "<br/><label style='width:70px;'>版本状态：</label>" + remark;
		html += "<input type='hidden' name='fisfinishededit' value='"+label+"' />";
//		html += "<br/><label style='width:70px;'>发布至：</label>"+(label=="1"?"省公司测试":"正式播出");
		//html += "<br/><label style='width:70px;'>发布至：</label><input type='radio' "+(label=="1"?checked:"")+" name='fisfinishededit' value='1' />省公司测试<input type='radio' name='fisfinishededit' "+(label=="3"?checked:"")+" value='3' />分公司测试<input type='radio' name='fisfinishededit' "+(label=="5"?checked:"")+" value='5' />正式播出发布";
		html += "<br/><br/><textarea name='fremark' id='fremark' cols='50' rows='5'></textarea><font color='red' id='desctip'>&nbsp;</font>";
		html += "<input name='freleaseversionid' value='"+version.val()+"' type='hidden'>";
		html += "<input name='fdesc' value='成功"+remark+"' type='hidden'>";
		html += "</form>";
		var kdialog = null;
		kdialog = KindEditor.dialog({
			title : '广告发布',
			body : '<div id="txt_source_div" style="padding:20px;padding-top:30px;overflow:auto;overflow-x:hidden;">'+html+'</div>',
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {name : '发布',click : function(e) {
				if(!sourceRelease.checkNull()){
					return false;
				}
				_waiting._show();
				$.post("release?"+$("#form1").serialize(),{},function(data,status){
						if(status){
							var s = data.status;
							var success = "error";
							if(s)
								success = "success";
							$.jBox.tip(data.result,success,{"timeout":3000});
							_waiting._hide();
							source.loadBranchRelease();
						}else{
							$.jBox.tip("发布失败",'error',{"timeout":3000});
							_waiting._hide();
						}
					},"json");
					kdialog.remove();
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
		
		return false;
	},
	toStatus : function(s){
		var html = "";
		var s1 = parseInt(s);
		switch (s1) {
			case 0:
				html = "未编辑";
				break;
			case 1:
				html = "发布至省公司测试";
				break;
			case 2:
				html = "省公司测试中";
				break;
			case 3:
				html = "分公司测试中";
				break;
			case 4:
				html = "正式发布广告至分公司播出";
				break;
		}
		return html;
	}
};