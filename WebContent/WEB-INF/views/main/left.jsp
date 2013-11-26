<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jbox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jbox/i18n/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/menu.js"></script>
<link rel="stylesheet" href="${rc.contextPath}/js/jbox/Skins/Gray/jbox.css" type="text/css"></link>
<title>Insert title here</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px;
}

a:link {
	font-size: 12px;
	color: #000000;
	text-decoration: none;
}

a:visited {
	font-size: 12px;
	color: #000000;
	text-decoration: none;
}

a:hover {
	font-size: 12px;
	color: #00CCFF;
	text-decoration: none;
}

.STYLE3 {
	font-size: 12px;
	color: #033d61;
}

-->
.menu_title a {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #ffffff;
	POSITION: relative;
	TOP: 2px
}

.menu_title2 a {
	FONT-WEIGHT: bold;
	LEFT: 3px;
	COLOR: #FFCC00;
	POSITION: relative;
	TOP: 2px
}

.t_column {
	background-image: url("../images/main_47.gif");
	padding-left: 30px;
	font-weight: bold;
}

.c_column {
	background-image: url("../images/main_51.gif");
	padding-left: 15px;
	font-size: 12px;
}

.e_column {
	height: 3px;
	vertical-align: top;
	background-image: url("../images/main_52.gif");
}

ul {
	list-style: none;
	text-align: left;
	padding-left: 5px;
	width: 151px;
	margin: 0px;
	line-height: 20px;
}

ul li a {
	width: 80px;
}
</style>
</head>
<body>
	<script>
	var he = document.body.clientHeight - 105;
	document.write("<div id=tt style=height:"+he+";overflow:hidden>");
</script>
	<div style="width: 165px; height: 100%; text-align: center;">
		<div class="STYLE1" style="color: #ffffff; width: 100%; padding-top: 6px; height: 23px; background-image: url('../images/main_40.png');">管理菜单</div>
		<div id="menuColumn"></div>
	</div>
	<script type="text/javascript">
	function $$(id){
		return document.getElementById(id);
	}
	function showsubmenu(sid) {
		var submenu = document.getElementsByName("submenu"+sid);
		for(var i=0;i<submenu.length;i++){
			if(submenu[i].style.display=='')
				submenu[i].style.display="none";
			else
				submenu[i].style.display="";
		}
	}
</script>
</body>
</html>