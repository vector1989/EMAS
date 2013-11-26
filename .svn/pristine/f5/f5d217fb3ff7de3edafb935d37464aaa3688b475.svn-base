<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>频道分组</title>
<jsp:include page="../css.jsp" />
</head>
<body>
	<div class="content">
		<div class="nav" id="nav_logo"></div>
		<table class="bodyContent">
			<tr>
				<td style="vertical-align:top;">
					<div class="tools">
						<span>
							<c:if test="${usermenu.freadonly=='w' || usermenu.freadonly=='a'}">
								<a class="a" href="javascript:void(0);" onclick="cgroup.ajaxLoadById()">
									<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
									<b>添加&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" onclick="cgroup.ajaxLoadById(1)">
									<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
									<b>修改&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" onclick="cgroup.deleteSource()">
									<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
									<b>删除&nbsp;</b>
								</a>
							</c:if>
						</span>
					</div>
					
					<div id="data" class="data">
						<div class="opt">
							<form name="queryForm" id="queryForm" action="">
								<span style="padding: 3px 10px;">
								</span>
							</form>
						</div>
						<div class="table">
							<table border="0" rules="none" cellpadding="0" cellspacing="0">
								<tr>
									<th class="th"><input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked)" type="checkbox"></th>
									<th class="th">分组名称</th>
									<th class="th">公司</th>
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
<script type="text/javascript" src="${rc.contextPath}/js/self/channelGroup.js"></script>
</body>
</html>