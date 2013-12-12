$(document).ready(function(){
	navTag("广告管理&gt;时间段编辑");
	timePeriod.load();
});
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
function beforeClick(treeId, treeNode, clickFlag) {
	if(treeNode.positionid == -3){
		$("#_tools").css("visibility","hidden");
	}else{
		$("#_tools").css("visibility","visible");
	}
	if(treeNode.isParent){
		if(!treeNode.click){
			$.jBox.tip('该广告为不支持时间段',"info"); 
			return false;
		}
		if(!treeNode.disabled){
			$.jBox.tip('请至省公司修改，无法在分公司着修改',"info"); 
			return false;
		}
	}
	if(!treeNode.isParent){
		if(!treeNode.disabled){
			$.jBox.tip('请至省公司修改，无法在分公司着修改',"info"); 
			return false;
		}
	}
	return true;
}
function onClick(event, treeId, treeNode, clickFlag) {
	if(!treeNode.isParent){
		var times = treeNode.name;
		var timeArrs = times.split("-");
		$("#fstarttime").val(timeArrs[0]);
		$("#fendtime").val(timeArrs[1]);
	}else{
		$("#fstarttime").val("00:00");
		$("#fendtime").val("23:59");
	}
}
var timePeriod={
	load : function(){
		_waiting._show();
		$.ajax({
			url:"query?"+$("#queryForm").serialize(),
			type:"post",
			dataType:"json",
			success:function(d){
				$.fn.zTree.init($("#tree"), setting, d);
				_waiting._hide();
			},
			error:function(data){
				$.jBox.tip('数据加载失败',"error"); 
				_waiting._hide();
			}
		});
	},
	insertOrUpdate : function(opt){
		var node = base.getSelectedTreeNode();
		if(node == null){
			$.jBox.tip('请选择广告位！',"info"); 
			return;
		}
		//提交参数
		var branchid = $("#fbranchid").val();
		if(node.isParent){
			if(!node.click){
				$.jBox.tip('该广告位不支持时间段',"info"); 
				return;
			}
		}
		var aid = node.advid;
		var tid = node.timeperiodid;
		var dd = {"fadvclassid":aid,"id":tid,"fbranchid":branchid,"adv":node.positionid};
		dd.fdefinition = $("#fdefinition").val();
		dd.freleaseversionid = node.rvid;
		dd.fpositionid = node.positionid;
		dd.fversionadvid = node.vaid;

		var fstarttime = $("#fstarttime").val();
		var fendtime = $("#fendtime").val();
		if(!(fstarttime == "00:00" && fendtime == "23:59")){
			var st = fstarttime.split(":");
			var t1 = parseInt(st[0]) * 60 + parseInt(st[1]);
			var et = fendtime.split(":");
			var t2 = parseInt(et[0]) * 60 + parseInt(et[1]);
			
			if(t1 >= t2){
				$.jBox.tip("开始时间必须小于结束时间","info");
				return;
			}
		}
		var uri = "insert";
		if(opt) uri = "update";
		this.ajaxSubmit("form1", uri, dd);
	},
	ajaxSubmit : function(form,uri,d){
		_waiting._show();
		$("#"+form).ajaxSubmit({
			url:uri,
			type:'post',
			data : d,
			success:function(data){
				if(data == -1){
					$.jBox.tip('时间段重复，请检查并重新输入','info');
				}else{
					timePeriod.load();
					$.jBox.tip('数据保存成功','success');
				}
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('数据保存失败','error'); 
				_waiting._hide();
			}
		});
	},
	deleteSource : function(){
		var node = base.getSelectedTreeNode();
		if(node == null){
			$.jBox.tip('请选中广告位时间段！',"info"); 
			return;
		}
		if(node.isParent){
			$.jBox.tip('请选中时间段！',"info"); 
			return;
		}
		_waiting._show();
		$.ajax({
			url:"delete",
			type:"post",
			data:{"id":node.timeperiodid},
			success:function(data){
				$.jBox.tip(data,"info");
				_waiting._hide();
				timePeriod.load();
			},
			error:function(data){
				_waiting._hide();
				$.jBox.tip(data,"error"); 
			}
		});
	}
};