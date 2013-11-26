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
	}/*,
	releaseVersion : function(val){
		if(val < 1){
			$("#version").html("");
			$("#showRelease").css("display","none");
			$("#freleaseversionid").html("");
			sourceRelease.queryByExample(1);
			return;
		}
		_waiting._show();
		$.post("releaseVersion",{"fbranchid":$("#fbranchid").val(),"definition":$("#fdefinition").val()},function(data,status){
			if(status){
				var options = "";//<option value=''>==版本==</option>
				$.each(data,function(i,d){
					var o = d.fdesc +"[sep]"+ d.fversion +"[sep]"+ d.fcreatetime;
					options += "<option value='"+d.id+"' desc='"+o+"'>"+d.fversion+"</option>";
				});
				$("#showRelease").css("display","");
				$("#freleaseversionid").html(options);
				sourceRelease.queryByExample(1);
			}else{
				$.jBox.tip("数据加载失败",'error',{"timeout":3000});
			}
			_waiting._hide();
		},"json");
	},
	showVersionDesc : function(){
		var desc = $('#freleaseversionid').find("option:selected").attr("desc");
		if(desc){
			var sep = desc.split("[sep]");
			var html = "<div style='padding:5px;'><br>版本创建时间："+sep[2] + "<br><br>版本号："+sep[1] + "<br><br>版本描述："+sep[0] + "<br><br></div>";
			$.jBox(html,{"title":"版本描述信息","width":700});
		}else{
			$.jBox.tip("暂无发布版本信息","info");
		}
	},
	gotoStatus : function(){
		var rvid = $("#freleaseversionid").val();
		if(rvid){
			var url = '../nodeStatus/queryContractAdvResourceStatus?type=2&rvid='+rvid+'&bid='+$("#fbranchid").val();
			window.open(url,featurse="_blank");
		}else{
			$.jBox.tip("暂无发布版本信息","info");
		}
	}*//*,
	selectChannels : function(){
		var id = base.selectFirst();
		var fchannelsid = $("input[name='checkbox']:checked").attr("fchannelsid");
		if(id){
			if(fchannelsid){
				$.post("../source/loadChannels",{"fchannelsid":fchannelsid},function(data,status){
					if(status){
						var html = "<div><div><b>频道总数：</b>"+fchannelsid.split(',').length+"</div>";
						$.each(data,function(i,c){
							html += "<div style='float : left;width:120px;'><input type='hidden' tsid='"+c.ftsid+"' onid='"+c.fonid+"' name='cid' value='"+c.id+"'/>"+c.fname+"</div>";
						});
						html += "<div style='clear:both;'></div></div>";
						var kdialog=null;
						kdialog = KindEditor.dialog({
							width : 530,
							height : 400,
							title : '查看频道信息',
							body : '<div id="txt_source_div" style="padding:0px;height:100%;overflow:auto;overflow-x:hidden;margin-top:1px;margin-left:30px;">'+html+'</div>',
							shadowMode : true,
							closeBtn : {name : '关闭',click : function(e) {kdialog.remove();}},
							yesBtn : {name : '修改',
								click : function(e) {}
							},
							noBtn : {name : '取消',click : function(e) {kdialog.remove();}}
						});
					}else{
						$.jBox.tip('数据加载出错',"error");
					}
				},"json");
			}else{
				$.jBox.tip('该广告无频道相关信息',"info");
			}
		}else{
			$.jBox.tip('请选择广告信息',"info");
		}
	}*/
};