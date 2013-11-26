<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
<meta charset="UTF-8" />
<title>创建广告</title>
<script type="text/javascript" src="${rc.contextPath}/js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery.form.js" charset="utf-8"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery.pager.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery.drag.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/ui.core.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/ui.tabs.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/advEdit.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/utils/kindeditor-min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/utils/baseUtil.js"></script>
<link href="${rc.contextPath}/style/main.css" rel="stylesheet" type="text/css" />
<link href="${rc.contextPath}/js/themes/default/default.css" rel="stylesheet" type="text/css" />
<link href="${rc.contextPath}/style/ui.tabs.css" type="text/css" media="print, projection, screen" rel="stylesheet">
<link href="${rc.contextPath}/style/Pager.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function setHeight(){
	var height = 200;
	if(document.all){
		height = document.documentElement.scrollHeight;
	}else
		height = document.body.scrollHeight;
	height -= 63;
	//alert(height);
	document.getElementById("p_sheet").style.height =height + "px";
	document.getElementById("workspace").style.height=(height-5) + "px";
}

 if (window.addEventListener)
	window.addEventListener("load", setHeight, false);
else if (window.attachEvent)
	window.attachEvent("onload", setHeight);
else
	window.onload = setHeight;
window.onresize = setHeight;
</script>
<style type="text/css">
html body div {margin: 0px;padding: 0px;}

#e_work {height: 100%;}
.clear{clear:both;}
#tab {height: 100%;}

#workspace {position:relative;padding: 0px;margin: 0px;font: normal 14px tahoma;overflow: auto;	height: 99%;}

#p_sheet {width: 200px;	border: 1px solid #000;	float: left;height: 99%;}

#property {line-height: 30px;}

#property input {width: 120px;vertical-align: top;}
/* #property span{border-bottom:1px solid #ccc; vertical-align: middle;} */
#property #proName {width: 60px;display: -moz-inline-box;height: 23px;display: inline-block;font-weight: normal;font-size: 12px;}

#sheet_title {background-image: url("../../images/main_47.gif");font-weight: bolder;text-align: center;}

#eProject {width: 100%;height: 100%;}
#element{height:100%;overflow-y:auto;}
ul{list-style-type:none; width:100%; padding: 0px;}
ul li .elementImg{font-size: 20px; width: 120px;}
.safearea {z-index: 2;background: #ccc;border: 40px solid #114a11;margin:5px;}
.page_title{width:100%;height:50px; border: 1px solid #114a11;}
#logo{float:left; border-right:1px solid #eee;height:100%;}
#adv_tools{vertical-align: middle;height:100%;padding-top:13px;}
.move{position:absolute;left: 45px; top:45px;z-index:3;background: #ccc;border: 1px solid #ccc;}
select{width: 120px;vertical-align: top; height:26px;}
</style>
</head>
<body onload="init()">
	<input name="fremark" value="${source.fremark}" type="hidden"/>
	<div class="page_title">
		<div id="logo"><img alt="" src="${rc.contextPath}/images/logo.png"></div>
		<div id="adv_tools">
			<%-- <span>广告位：</span>
			<span>
				<input id="fpositionid" name="fpositionid" value="" readonly="readonly" class="input"/>
				<select id="fpositionid" name="fpositionid">
					<c:forEach var="advc" items="${advs}">
						<option value="${advc.id}" ${advc.id==sourceExpand.fadvid ? 'selected="selected"' : ''}>${advc.ftype}&nbsp;&nbsp;&nbsp;</option>
					</c:forEach>
				</select>
			</span>
			<span>解析度：</span>
			<span>
				<select id="fdefinition" name="fdefinition">
					<option value="HD" ${sourceExpand.fdefinition=='HD' ? 'selected="selected"' : ''}>高清&nbsp;&nbsp;&nbsp;</option>
					<option value="SD" ${sourceExpand.fdefinition=='SD' ? 'selected="selected"' : ''}>标清&nbsp;&nbsp;&nbsp;</option>
				</select>
			</span> --%>
			<span><input value="新建广告" type="button" onclick="Adv.newAdv();"/></span>
			<span><input value="保存广告" type="button" onclick="Adv.ajaxSubmit();"/></span>
			<span><input value="预览" type="button" onclick="Adv.preview();"/></span>
		</div>
		<div class="clear"></div>
	</div>
	<div id="e_work">
		<div id="p_sheet">
			<div id="tab">
				<ul>
					<li><a href="#property" onclick=""><span>属性</span></a></li>
					<li><a href="#element" onclick=""><span>元素</span></a></li>
				</ul>
				<div id="property">
					<form action="" method="post" id="form1">
						<input name="id" value="${source.id}" type="hidden"/>
						<input name="fadvid" value="" type="hidden"/>
						<span id='proName'>屏幕宽度</span><span><input name='fscreenwidth' value='1280' onchange='Adv.setAdvDesign("advDesign","width",this.value)'/></span>
						<span id='proName'>屏幕高度</span><span><input name='fscreenheight' value='720' onchange='Adv.setAdvDesign("advDesign","height",this.value)'/></span>
						<span id='proName'>广告类型</span><span><input name='ftype' value="${source.ftype}" readonly='readonly'/></span>
						<span id='proName'>解析度</span><span><input id="fdefinition" name="fdefinition" value="${source.fdefinition}" readonly="readonly"/></span>
						<!-- <span id='proName'>广告名称</span><span><input name='advname' id="advname" onfocus="Adv.setFocusAdvName(this.value);" onchange="Adv.setAdvName(this.value);"  required="required" value="未命名" type="text" placeholder="请输入广告名称" /></span> -->
						<span id='proName'>基本信息</span>
						<select id="fadvbaseid" name="fadvbaseid" onchange="Adv.setAdvProperty(this.id);">
							<c:forEach var="base" items="${bases}">
								<option attr="${base.temp}" value="${base.id}" ${base.id==source.fadvbaseid?"selected='selected'":''} >${base.fadvbrand} | ${base.fadvlevel}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
							</c:forEach>
						</select>
						<span id='proName'>广告时间段</span>
						<select id="ftimeperiodid" name="ftimeperiodid"></select>
						<br/>
						<span id='proName'>资源名称</span><span><input name='fname' value='${source.fname}' style='width:70px;' id='fname' readonly='readonly'/><input value='浏览' type='button' onclick='Adv.showResource();' style='width:50px;'></span>
						<span id='proName'>资源类型</span><span><input name='felementtype' value='${source.felementtype}' id='eleType' readonly='readonly'/></span>
						<span id='proName'>宽(px)</span><span><input name='fwidth' value='${source.fwidth}' id='fwidth' onchange='Adv.setAdvDesignPosition("designImg","width",this.value,"img")'/></span>
						<span id='proName'>高(px)</span><span><input name='fheight' value='${source.fheight}' id='fheight' onchange='Adv.setAdvDesignPosition("designImg","height",this.value,"img")'/></span>
						<span id='proName'>左(px)</span><span><input name='fleft' value='${source.fleft}' id='x' onchange='Adv.setAdvDesignPosition("designImg","left",this.value)'/></span>
						<span id='proName'>上(px)</span><span><input name='ftop' value='${source.ftop}' id='y' onchange='Adv.setAdvDesignPosition("designImg","top",this.value)'/></span>
					</form>
				</div>
				<div id="element">暂无信息</div>
			</div>
		</div>
		<div id="workspace">
			<div id="eProject">
				<c:if test="${source != null}">
					${source.frawdata}
					<%-- <div class="safearea" id="advDesign" style="width: ${adv.fdefinition == 'HD'?1280:640}px;height: ${adv.fdefinition == 'HD'?720:526}px; opacity: 1; cursor: default;"></div> --%>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>