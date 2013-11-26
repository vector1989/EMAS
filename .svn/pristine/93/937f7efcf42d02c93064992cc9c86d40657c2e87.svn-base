<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	label {width:108px;}
</style>
<jsp:include page="../css.jsp" />
<title>分公司数据库配置</title>
</head>
<body>
	<div class="content">
		<div class="nav" id="nav_logo"></div>
		<table class="bodyContent">
			<tr>
				<td style="vertical-align:top;">
					<div class="tools">
						<span>
						<c:if test="${(usermenu.freadonly=='w' || usermenu.freadonly=='a')}">
							<a class="a" href="javascript:void(0);" onclick="dbconfig.ajaxLoadById()">
								<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
								<b>添加&nbsp;</b>
							</a>
							<a class="a" href="javascript:void(0);" onclick="dbconfig.ajaxLoadById(1)">
								<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
								<b>修改&nbsp;</b>
							</a>
							<a class="a" href="javascript:void(0);" onclick="dbconfig.deleteSource()">
								<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
								<b>删除&nbsp;</b>
							</a>
						</c:if>
						</span>
					</div>
					<div id="data" class="data">
						<div class="opt">
							<form name="queryForm" id="queryForm" action="">
								<span style="padding: 3px 10px;"></span>
							</form>
						</div>
						<div class="table">
							<table border="0" rules="none" cellpadding="0" cellspacing="0">
								<tr>
									<th class="th"><input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked)" type="checkbox"></th>
									<!-- <th class="th">索引</th> -->
									<th class="th">服务器IP</th>
									<th class="th">数据库服务用户名</th>
									<th class="th">数据库服务密码</th>
									<th class="th">分公司</th>
								</tr>
								<tbody id="dataGrid">
			
								</tbody>
							</table>
						</div>
					</div>
					<div id="pager_bar"></div>
				</td>
			</tr>
		</table>
	</div>
<jsp:include page="../contentHead.jsp" />
<script type="text/javascript" src="${rc.contextPath}/js/self/dbconfig.js"></script>
</body>
</html>