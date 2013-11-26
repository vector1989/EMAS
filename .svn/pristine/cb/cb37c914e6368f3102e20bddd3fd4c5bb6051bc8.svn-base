<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
<script type="text/javascript" src="${rc.contextPath}/js/utils/version.js"></script>
<script type="text/javascript">
function setMD()
{
	var date = new Date();
	var Y = date.getFullYear();
	var M = (date.getMonth() + 1) < 10 ? "" + (date.getMonth() + 1) : date.getMonth() + 1;
    var D = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var H = date.getHours();
    H = H > 9 ? H : "0" + H;
    var m = date.getMinutes();
    m = m > 9 ? m : "0" + m;
    var S = date.getSeconds();
    S = S > 9 ? S : "0" + S;
    var DD = date.getDay();
    switch (DD) {
	case 1:
		DD = "一";
		break;
	case 2:
		DD = "二";
		break;
	case 3:
		DD = "三";
		break;
	case 4:
		DD = "四";
		break;
	case 5:
		DD = "五";
		break;
	case 6:
		DD = "六";
		break;
	case 0:
		DD = "日";
		break;
	}
	document.getElementById("MD").innerHTML = "&nbsp;&nbsp;Copyright &copy;&nbsp;"+version.year+"&nbsp;&nbsp;"+version.v+"&nbsp;&nbsp;"+version.company+"&nbsp;&nbsp; All Rights Reserved &nbsp;&nbsp;&nbsp;&nbsp;今天是："+Y+"年"+ M +"月"+D+"日  "+H+":"+m +":"+S+"&nbsp;&nbsp;星期" + DD;
}
setInterval("setMD()",1000);
</script>
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
	color: #147233;
}
-->
</style>
</head>

<body onload="setMD();">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="23" background="../images/main_25.gif"><table
					width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="181" height="23" background="../images/main_24.gif"></td>
						<td><div align="right" class="STYLE1" id="MD"></div></td>
						<td width="25"><img src="../images/main_27.gif" width="25" height="23" /></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>