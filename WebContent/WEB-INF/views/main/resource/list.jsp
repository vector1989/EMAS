<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源</title>
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
							<c:if test="${(usermenu.freadonly=='w' || usermenu.freadonly=='a')}">
								<a class="a" href="javascript:void(0);" onclick="upload.ajaxLoadData()">
									<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
									<b>添加&nbsp;</b>
								</a>
							</c:if>
							<%-- <c:if test="${(usermenu.freadonly=='a')}">
								<a class="a" href="javascript:void(0);" id="delete" onclick="deleteLog()">
									<img src="${rc.contextPath }/images/s.gif" width="20" height="20">
									<b>删除&nbsp;</b>
								</a>
							</c:if> --%>
							<a class="a" href="javascript:void(0);" onclick="base.showResourceUsed()">
								<img src="${rc.contextPath }/images/s.gif" width="20" height="20">
								<b>素材使用情况&nbsp;</b>
							</a>
						</span>
					</div>
					<div id="data" class="data">
						<div class="opt">
							<form name="queryForm" id="queryForm" action="">
								<span style="padding: 3px 10px;">
									<input name="ftype" id="ftype" value="${ftype}" type="hidden"/>
									<c:if test="${USERLOGIN.fbranchid==1}">
										<label for="fbranchid">公司：</label>
										<select name="fbranchid" id="fbranchid" onchange="Resource.loadAdv(this.value);Resource.show()">
											<c:forEach items="${branchs}" var="b">
												<option value="${b.id}">${b.fname}</option>
											</c:forEach>
										</select>
									</c:if>
									<label for="fadvclassid">广告位：</label>
									<select id="fadvclassid" name="fadvclassid" onchange="Resource.show(1)">
										<option value="">==请选择==</option>
										<c:forEach var="adv" items="${advs}">
											<option value="${adv.id}">${adv.ftype}|${adv.fdefinition}</option>
										</c:forEach>
									</select>
									<!-- &nbsp;&nbsp;列出：
									从 <input class="Wdate" id="queryStartTime" name="queryStartTime" onClick="WdatePicker()" style="width: 100px;" type="text">
									至 <input class="Wdate" id="queryEndTime" name="queryEndTime" onClick="WdatePicker()" style="width: 100px;" type="text">
									&nbsp;关键词: <input name="queryKeyWord" id="queryKeyWord" style="width: 110px;" type="text">
									<input class="inputButton" name="submitbutton" value="查询" id="submitbutton" type="button">
									 -->
								</span>
							</form>
						</div>
						<div class="table" id="dataGrid"></div>
					</div>
					<div id="pager_bar"></div>
				</td>
			</tr>
		</table>
	</div>
<jsp:include page="../contentHead.jsp" />
<script type="text/javascript" src="${rc.contextPath}/js/self/uploadResource.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/resource.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/preview.js"></script>
</body>
</html>