<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../contentHead.jsp" />
<script type="text/javascript" src="${rc.contextPath}/js/self/broadcast.js"></script>
<style type="text/css">
a {font-size: 12px;}
</style>
<title>审核流程</title>
<jsp:include page="../css.jsp" />
</head>
<body onload='navTag("系统管理&gt;播发服务器状态")'>
	<div class="content">
		<div class="nav" id="nav_logo"></div>
		<div id="data"><div class="opt">
				<form name="queryForm" id="queryForm" action="">
					<span style="padding: 3px 10px;">
						<c:if test="${not empty branchs}">
							<label for="fbranchid">公司：</label>
							<select id="fbranchid" name="fbranchid" onchange="broadcast.load()">
								<c:forEach items="${branchs}" var="branch">
									<option value="${branch.id}">${branch.fname}</option>
								</c:forEach>
							</select>
						</c:if>
					</span>
				</form>
			</div>
			<div class="table" style="text-align: center; font-size: 2em;">
				<div>该公司播发服务器当前状态：<span id="status" style="color: red;">暂停播发</span></div>
				<c:if test="${(usermenu.freadonly !='r')}">
					<a href="javascript:void(0);" id="play" onclick="broadcast.edit(0);"><img id="playImg" src="${rc.contextPath}/images/play.png"></a>
					<a href="javascript:void(0);" id="pause" onclick="broadcast.edit(1);"><img id="pauseImg" src="${rc.contextPath}/images/pause_focus.png"></a>
				</c:if>
				<c:if test="${(usermenu.freadonly=='r')}">
					<a id="play"><img id="playImg" src="${rc.contextPath}/images/play.png"></a>
					<a id="pause"><img id="pauseImg" src="${rc.contextPath}/images/pause_focus.png"></a>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>