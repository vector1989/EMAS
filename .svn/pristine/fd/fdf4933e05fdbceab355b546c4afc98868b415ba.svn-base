<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>广告位管理</title>
<jsp:include page="../css.jsp" />
</head>
<body>
	<div class="content">
		<div class="nav">
			<img alt="" src="${rc.contextPath}/images/hi.gif">广告管理&gt;广告位管理
		</div>
		<div class="tools">
			<span>
				<a class="${(usermenu.freadonly=='w' || usermenu.freadonly=='a') ? 'a' : 'a1'}" href="javascript:void(0);" onclick="${(usermenu.freadonly=='w' || usermenu.freadonly=='a') ? 'adv.ajaxLoadById()' : ''}">
					<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
					<b>添加&nbsp;</b>
				</a>
				<a class="${(usermenu.freadonly=='w' || usermenu.freadonly=='a') ? 'a' : 'a1'}" href="javascript:void(0);" onclick="${(usermenu.freadonly=='w' || usermenu.freadonly=='a') ? 'adv.ajaxLoadById(1)' : ''}">
					<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
					<b>修改&nbsp;</b>
				</a>
				<a class="${(usermenu.freadonly=='w' || usermenu.freadonly=='a') ? 'a' : 'a1'}" href="javascript:void(0);" onclick="${(usermenu.freadonly=='w' || usermenu.freadonly=='a') ? 'adv.deleteSource()' : ''}">
					<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
					<b>删除&nbsp;</b>
				</a>
				<a class="${(usermenu.freadonly=='c' || usermenu.freadonly=='a') ? 'a' : 'a1'}" href="javascript:void(0);" onclick="${(usermenu.freadonly=='w' || usermenu.freadonly=='a') ? 'adv.checked(1)' : ''}">
					<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
					<b>通过审核&nbsp;</b>
				</a>
				<a class="${(usermenu.freadonly=='c' || usermenu.freadonly=='a') ? 'a' : 'a1'}" href="javascript:void(0);" onclick="${(usermenu.freadonly=='w' || usermenu.freadonly=='a') ? 'adv.checked(2)' : ''}">
					<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
					<b>不通过审核&nbsp;</b>
				</a>
				<%-- <a class="a" href="javascript:void(0);" onclick="">
					<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
					<b>详细信息&nbsp;</b>
				</a> --%>
			</span>
		</div>
		<div id="data">
			<div class="opt">
				<form name="queryForm" id="queryForm" action="">
					<span style="padding: 3px 10px;">
						<!-- <label>全选</label> -->
						<c:if test="${USERLOGIN.fbranchid==1}">
							<label for="fbranchid">公司：</label>
							<select id="fbranchid" name="fbranchid" onchange="adv.load()">
								<c:forEach items="${branchs}" var="branch">
									<option value="${branch.id}">${branch.fname}</option>
								</c:forEach>
							</select>
						</c:if>
						
						<label for="fchecked">审核：</label>
						<select id="fchecked" name="fchecked" onchange="adv.load()">
							<option value="">==请选择==</option>
							<option value="0">未审核</option>
							<option value="1">已通过审核</option>
							<option value="2">未通过审核</option>
						</select>
						<!-- 
						&nbsp;&nbsp;列出： 从 <input class="Wdate" id="queryStartTime" name="queryStartTime" onClick="WdatePicker()" style="width: 100px;" type="text">
						至 <input class="Wdate" id="queryEndTime" name="queryEndTime" onClick="WdatePicker()" style="width: 100px;" type="text">
						&nbsp;关键词: <input name="queryKeyWord" id="queryKeyWord" style="width: 110px;" type="text">
						<input class="inputButton" name="submitbutton" value="查询" id="submitbutton" type="button"> -->
					</span>
				</form>
			</div>
			<div class="table">
				<table border="0" rules="none" cellpadding="0" cellspacing="0">
					<tr>
						<td class="th"><input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked)" type="checkbox"></td>
						<th class="th">索引</th>
						<th class="th">广告位</th>
						<th class="th">广告类型</th>
						<th class="th">解析度</th>
						<th class="th">审核</th>
						<th class="th">创建人</th>
						<th class="th">创建时间</th>
						<th class="th">分公司</th>
					</tr>
					<tbody id="dataGrid">
						
					</tbody>
				</table>
			</div>
		</div>
		<div id="pager_bar"></div>
	</div>
<jsp:include page="../contentHead.jsp" />
<script type="text/javascript" src="${rc.contextPath}/js/self/adv.js"></script>
</body>
</html>