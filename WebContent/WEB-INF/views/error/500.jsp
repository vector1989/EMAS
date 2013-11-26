<%@ page language="java" isErrorPage="true" import="java.util.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>出错信息提示</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<center>
		<div
			style="margin: 0 auto; border: 5px #ccc solid; width: 500px; height: auto !important; min-height: 150px; position: static; margin-top: 200px;">
			<div
				style="background: #0066FF; display: block; height: 50px; vertical-align: middle; line-height: 50px; font-weight: bold; color: #ffffff">
				提示：Oh! No ! 页面出错了...(error:500)</div>
			<p></p>
			<span>错误原因：<%=exception.getClass().getSimpleName()+":"+exception.getMessage() %></span>
		</div>
	</center>
</body>
</html>
