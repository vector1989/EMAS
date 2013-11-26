<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>资源</title>
<jsp:include page="../css.jsp" />
<link rel="stylesheet" href="${rc.contextPath}/style/ztree/zTreeStyle.css" type="text/css">
</head>
<body>
	<div class="content">
		<div class="nav" id="nav_logo"></div>
		<table class="bodyContent">
			<tr>
				<td id="tree_div" style="vertical-align:top;">
					<ul id="tree" class="ztree"></ul>
				</td>
				<td style="vertical-align:top;">
					<div class="tools" id="_tools">
						<c:if test="${(usermenu.freadonly=='w' || usermenu.freadonly=='a')}">
							<span>
								<a class="a" href="javascript:void(0);" id="delete" onclick="timePeriod.insertOrUpdate()">
									<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
									<b>添加&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" id="delete" onclick="timePeriod.insertOrUpdate(1)">
									<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
									<b>修改&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" id="delete" onclick="timePeriod.deleteSource()">
									<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
									<b>删除&nbsp;</b>
								</a>
							</span>
						</c:if>
					</div>
					<div id="data" class="data_edit">
						<div class="opt">
							<form name="queryForm" id="queryForm" action="">
								<span style="padding: 3px 10px;">
									<!-- <label for="check"> 全选</label><input class="inputCheckbox" name="check" id="AllCheck" onchange="base.selectAll()" type="checkbox">
									 -->
									<c:if test="${USERLOGIN.fbranchid==1}">
										<label for="adv">公司：</label>
										<select id="fbranchid" onchange="base.loadBranchAdv(this.value);timePeriod.load();">
											<c:forEach items="${branchs}" var="branch">
												<option value="${branch.id}">${branch.fname}</option>
											</c:forEach>
										</select>
									</c:if>
									<label for="fdefinition">选择广告解析度:</label>
									<select name="fdefinition" id="fdefinition" onchange="timePeriod.load()" >
										<option value="HD">高清</option>
										<option value="SD">标清</option>
									</select>
									<input name="isusing" id="isusing" value="1" type="hidden"/>
								</span>
							</form>
						</div>
						<div class="table" style="border: 0px solid #ccc; margin: 30px; width: 90%">
							<form id="form1">
								<br />
								<br />
								<label for="fstarttime">开始时间：</label>
								<input id="fstarttime" name="fstarttime" value="00:00" required="required" type="text" class="Wdate"
									onClick="WdatePicker({dateFmt:'HH:mm'})" placeholder="请输入开始时间" readonly="readonly"/>
								<br />
								<br />
								<label for="fendtime">结束时间：</label>
								<input id="fendtime" name="fendtime" value="23:59" required="required" type="text" class="Wdate" 
									onClick="WdatePicker({dateFmt:'HH:mm'})" placeholder="请输入结束时间" readonly="readonly"/>
							</form>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</div>
<%-- <script type="text/javascript" src="${rc.contextPath}/js/ztree/jquery-1.4.4.min.js"></script> --%>
<jsp:include page="../contentHead.jsp" />
<script type="text/javascript" src="${rc.contextPath}/js/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/timePeriod.js"></script>
</body>
</html>