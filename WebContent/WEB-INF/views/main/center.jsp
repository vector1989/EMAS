<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery-1.8.2.js"></script>
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: hidden;
}
-->
</style>
<style>
.navPoint {
	COLOR: white;
	CURSOR: hand;
	FONT-FAMILY: Webdings;
	FONT-SIZE: 9pt
}
</style>
<script type="text/javascript">
	function switchSysBar() {
		var locate = location.href.replace('main/centerIndex', '');
		var ssrc = document.all("img1").src.replace(locate, '');
		if (ssrc == "images/main_18.gif") {
			document.all("img1").src = "../images/main_18_1.gif";
			document.all("frmTitle").style.display = "none";
		} else {
			document.all("img1").src = "../images/main_18.gif";
			document.all("frmTitle").style.display = "";
		}
	}
	 
	function SetCwinHeight() {
		var clientHeight = document.documentElement.clientHeight;
		document.getElementById("I2").height=clientHeight;
		document.getElementById("I1").height=clientHeight;
	}
	/* function treeSetHeight(){
		var height = (document.body.clientHeight-60);
		$("#tree").css("height",height + "px");
		height = (height - 35) + "px";
		var tabbox = $(".tab_box_div");
		if(tabbox.length > 0){
			tabbox.css("height",height+"px");
		}
	} */
	$(function() {
		SetCwinHeight();
	});
	$(window).resize(function() {
		SetCwinHeight();
	});
	/* 
	if (window.addEventListener){
		window.addEventListener("load", SetCwinHeight, false);
	}else if (window.attachEvent){
		window.attachEvent("onload", SetCwinHeight);
	}else{
		window.onload = SetCwinHeight;
	}
	window.onresize = SetCwinHeight; */
	
</script>

</head>

<body>
	<table width="100%" id="top" height="100%" border="0" cellpadding="0"
		cellspacing="0" style="table-layout: fixed;">
		<tr> 
			<td width="170" id=frmTitle noWrap name="fmTitle" align="center" valign="top" style="background:url('../images/main_16.gif') repeat-y;">
				<iframe name="I1" id="I1" height="100%" width="180" onload="SetCwinHeight()" src="leftIndex" border="0" frameborder="0" scrolling="auto"> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。</iframe>
			</td>
			<td width="8" valign="middle" background="../images/main_12.gif" onclick="switchSysBar()">
				<span class="navPoint">
					<img src="../images/main_18.gif" name="img1" width=8 height=52 id=img1>
				</span>
			</td>
			<td align="center" valign="top" id="main">
				<iframe name="I2" id="I2" height="100%" onload="SetCwinHeight()" width="100%" border="0" frameborder="0" scrolling="no" style="overflow-x: hidden;" src=""> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。</iframe>
			</td>
			<td width="4" align="center" valign="top"
				background="../images/main_20.gif"></td>
		</tr>
	</table>
</body>
</html>
