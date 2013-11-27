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
		var desc = $("#fdesc").val();
		if(!desc){
			$.jBox.tip("请填入版本描述信息","info");
			$("#desctip").html("请填入版本描述信息");
			return false;
		}else{
			$("#desctip").html("");
			return true;
		}
	},
	release:function(){
		var version = $("#freleaseversionid");
		var versionNo = version.find("option:selected").text();
		if(!versionNo){
			$.jBox.tip("暂无待发布广告版本信息","info");
			return;
		}
		var html = "<form id='form1'>版本："+versionNo;
		html += "<textarea name='fdesc' id='fdesc' cols='50' rows='5'></textarea><font color='red' id='desctip'>&nbsp;</font>";
		html += "</form>";
		var kdialog = null;
		kdialog = KindEditor.dialog({
			title : '广告发布',
			body : '<div id="txt_source_div" style="padding:30px;padding-top:40px;overflow:auto;overflow-x:hidden;">'+html+'</div>',
			closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
			yesBtn : {name : '发布',click : function(e) {
				
				if(!sourceRelease.checkNull()){
					return false;
				}
				
				var data = {"fbranchid":$("#fbranchid").val(),"freleaseversionid":version.val(),"fdesc":$("#fdesc").val()};
				_waiting._show();
				$.post("release",data,function(d,status){
						var data = eval("("+d+")");
						if(status){
							var s = data.status;
							var success = "error";
							if(s)
								success = "success";
							$.jBox.tip(data.result,success,{"timeout":3000});
							_waiting._hide();
							var treeNode = base.getSelectedTreeNode();
							source.queryByExample(1,treeNode);
						}else{
							$.jBox.tip("发布失败",'error',{"timeout":3000});
							_waiting._hide();
						}
					});
					kdialog.remove();
				}
			},
			noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
		});
		
		return false;
	}
};