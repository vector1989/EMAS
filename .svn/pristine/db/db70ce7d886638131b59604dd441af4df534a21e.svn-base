//去掉左右空格
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,//
        "d+": this.getDate(), //
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), //
        "s+": this.getSeconds(), //
        "q+": Math.floor((this.getMonth() + 3) / 3), //
        "S": this.getMilliseconds() //
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    	if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    		return fmt;
   /*
	 * var time1 = new Date().Format("yyyy-MM-dd"); 
	 * var time2 = new Date().Format("yyyy-MM-dd HH:mm:ss");
	 */
};
/**
 * 获取当前时间
 * @returns
 */
function getCurrentDate(){
	return new Date().Format("yyyy-MM-dd");
}
var base = {
	selectTd : function(i){
		$("#AllCheck").removeAttr("checked");
		$("#checkboxdiv"+i).attr("checked","true");
		$(".tr").each(function(j){
			var checkboxi= $(this).attr("id").replace("trdiv","checkboxdiv");
			var checkboxObj = $("#"+checkboxi);
			if(checkboxObj.attr("checked")){
				$(this).css("backgroundColor","#D8F79D");
			}else{
				$(this).css("backgroundColor","");
			}
		});
		
	},
	selectAll:function(){
		var trs = $("tr[class=tr]");// 所有tr
		var allCheck = $("#AllCheck");
		var checkbox = $("input[name='checkbox']");
		if(allCheck.attr("checked")){
			checkbox.attr("checked","true");
			trs.css("backgroundColor","#D8F79D");
		}else{
			checkbox.removeAttr("checked");
			trs.css("backgroundColor","");
		}
	},
	selectValue:function(opt,n){// 返回选中项的值
		if(!opt){opt = ",";}
		if(!n){n="checkbox";}
		var checkbox = $("input[name='"+n+"']:checked");
		var val=new Array();
		checkbox.each(function(i){ // 由于复选框一般选中的是多个,所以可以循环输出
			val.push($(this).val());
		});
		return val.join(opt);
	},
	selectFirst:function(){// 获取选中的第一项
		return $("input[name='checkbox']:checked").val();
	},
    obj2Str : function(o){
    	var str = '';
    	for (var i in o){
    		if(i != 'fcreatetime'){
	    		var v = o[i];
	    		str += "\'"+i+"\':\'"+v + "\',";
    		}
    	}
    	return str.substr(0,str.length-1).trim();
	},
	getQueryString : function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	},
	formIsNull:function(frm){
		/**
		 * 表单验证
		 * 
		 * @param frm
		 * @returns {Boolean}
		 */
		if(!frm) frm = "form1";
		frm = "#" + frm;
		$("font[name='tips']").remove();
		var bool = true;
		var input = $(frm).find("[required='required']");
		var length = input.length;
		for(var i=0;i<length;i++){
			var obj = input[i];
			if(!obj.value){
				$(obj).after("<font color='color' name='tips'>[该值不允许为空]</font>");
				bool = false;
			}
		}
		return bool;
	},
	loadBranchAdv : function(branchid){
		_waiting._show();
		$.post("../time/loadBranchAdv",{"branchid":branchid},function(data,status){
			if(status){
				var advHtml= "<option value='' positionid='' definition=''>===选择合同-广告位===</option>";
				$.each(data,function(i,adv){
					advHtml += '<option value="'+adv.id+'" positionid='+adv.fpositionid+' definition='+adv.fdefinition+'>'+adv.ftype+"|"+adv.fdefinition+'</option>';
				});
				$("#fadvid").html(advHtml);
			}else{
				$.growlUI('数据加载失败'); 
			}
			_waiting._hide();
		},"json");
	},
	getGlobalLimit : function(){
		var limit = $("#limit").val();
		if(!limit)
			limit = $("#global_limit",window.parent.parent.frames["topFrame"].document).val();
		if (limit)
			return limit;
		else
			return 10;
	},
	getSelectedTreeNode : function(){
		var node = null;
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length > 0){
			node = nodes[0];
		}
		return node;
	},
	showResourceUsed : function(id){
		var param = {"rid":id};
		if(id == null){
			var obj = $('input:radio[name="checkbox"]:checked');
			if(!obj.length){
				$.jBox.tip("请选择要查看的素材或广告",'info');
				return;
			}
			id = obj.val();
			var issource = obj.attr("isSource");
			
			if(issource == 1){
				param = {"sid":id};
			}else{
				param.rid = id;
			}
		}
		var basePath = _waiting.getRootPath();
		$.ajax({
			url:basePath+"/main/resource/queryResourceUsed",
			type:'post',
			data:param,
			dataType:'json',
			success:function(data){
				console.info(data);
				if(data.length > 0){
					var html = "<table border='0' cellpadding='0' cellspacing='0' width='100%' style='border:1 #ccc solid;' rules='all'>";
					html += "<tr><th width='15%'>版本号</th><th width='8%'>分公司</th><th width='8%'>解析度</th><th width='10%'>状态</th><th width='20%'>版本创建时间</th><th width='45%'>版本描述</th></tr>";
					$.each(data,function(i,d){
						html += "<tr><td>"+d.fversion+"</td><td>"+d.branch+"</td><td>"+d.fdefinition+"</td><td>"+(d.fstatus==0?'待发布':(d.fstatus==1?'已发布':'失效'))+"</td><td>"+d.fcreatetime+"</td><td>"+(d.fdesc == undefined ? '' : d.fdesc)+"</td></tr>";
					});
					html += "</table>";
					$.jBox(html,{"title":"素材使用情况","width":900,"height":400,buttons:{"关闭":0}});
				}else{
					$.jBox.tip("该素材暂未被使用","info");
				}
				_waiting._hide();
			},
			error:function(msg){
				$.jBox.tip('数据加载失败','error'); 
				_waiting._hide();
			}
		});
	}
};
/**
 * 末尾字符检测
 * 
 * @param str
 * @param endStrArr
 */
function endWith(str,endStrArr){
    for(var i=0,len = endStrArr.length;i<len;i++){
        var tmp = endStrArr[i];
        if(str.length - tmp.length<0) return false;

        if(str.substring(str.length-tmp.length)==tmp){
            return true;
        }
    }
    return false;
}

function getBrowser(){
	var browser = $.browser;
	if (browser.msie) {
		alert("this is msie"+browser.version);
	} else if (browser.safari) {
		alert("this is safari!");
	} else if (browser.mozilla) {
		alert("this is mozilla!");
	} else if (browser.opera) {
		alert("this is opera");
	} else {
		alert("i don't konw!");
	}
}
/**
 * 设置树高度
 */
function treeSetHeight(){
	var bc = document.body.clientHeight;
	var height = (bc-60);
	if(height > 0){
		$("#tree").css("height",height + "px");
		height = (height - 35);
		var tabbox = $(".tab_box_div");
		if(tabbox.length > 0){
			tabbox.css("height",height+"px");
		}else{
			$(".data").css("height",height + "px");
		}
	}
}
$(function() {
	treeSetHeight();
});
$(window).resize(function() {
	treeSetHeight();
});