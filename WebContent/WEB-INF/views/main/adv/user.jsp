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
						<c:if test="${(usermenu.freadonly=='w' || usermenu.freadonly=='a')}">
							<span>
								<a class="a" href="javascript:void(0);" onclick="user.ajaxLoadById(0)">
									<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
									<b>添加&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" onclick="user.ajaxLoadById(1)">
									<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
									<b>修改&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" onclick="user.deleteSource()">
									<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
									<b>删除&nbsp;</b>
								</a>
							</span>
						</c:if>
					</div>
					
					<div id="data" class="data">
						<div class="opt">
							<form name="queryForm" id="queryForm" action="">
								<span style="padding: 3px 10px;">
								<!-- <label><input class="inputCheckbox" name="check" id="AllCheck" onchange="base.selectAll()" type="checkbox"> 全选</label>
								 -->		&nbsp;&nbsp;<!-- 列出： 从 <input class="Wdate" id="queryStartTime" name="queryStartTime" onClick="WdatePicker()" style="width: 100px;" type="text">
									  至 <input class="Wdate" id="queryEndTime" name="queryEndTime" onClick="WdatePicker()" style="width: 100px;" type="text">
									&nbsp;关键词: <input name="queryKeyWord" id="queryKeyWord" style="width: 110px;" type="text"><input class="inputButton" name="submitbutton" value="查询" id="submitbutton" type="button"> -->
								</span>
							</form>
						</div>
						<div class="table">
							<table border="0" rules="none" cellpadding="0" cellspacing="0">
								<tr>
									<td class="th"><input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked)" type="checkbox"></td>
									<!-- <th class="th">索引</th> -->
									<th class="th">姓名</th>
									<th class="th">用户名</th>
									<th class="th">公司</th>
									<th class="th">用户组</th>
									<th class="th">部门</th>
									<th class="th">职位</th>
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
<script type="text/javascript" src="${rc.contextPath}/js/self/user.js"></script>
</body>
</html>