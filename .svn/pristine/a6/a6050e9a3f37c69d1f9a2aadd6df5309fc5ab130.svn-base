<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
	<table style="width: 60%;">
		<tr><th>文件路径</th><th>文件大小</th><th>修改时间</th></tr>
	<%
		String toolsPath = "/tools/";
		File path = new File(request.getSession().getServletContext().getRealPath("/")+toolsPath);
		File[] files = path.listFiles();
		for(File file : files){
			String fileName = file.getName();
	%>
		<tr><th><a href="${rc.contextPath}/main/resource/download?filePath=<%=toolsPath+fileName %>&fileName=<%=fileName.substring(0, fileName.indexOf(".")) %>"><%=fileName %></a></th><td align="center"><%=file.length() %>&nbsp;bytes&nbsp;&nbsp;</td><td align="center"><%=new Date(file.lastModified()).toLocaleString() %></td></tr>
	<%
		}
	%>
	</table>
	
</body>
</html>