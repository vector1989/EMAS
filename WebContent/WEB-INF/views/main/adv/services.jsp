<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../contentHead.jsp" />
<script type="text/javascript" src="${rc.contextPath}/js/self/services.js"></script>
<title>资源</title>
</head>
<body>
	<div class="content">
		<div class="nav"></div>
		<table class="bodyContent">
			<tr>
				<td style="vertical-align:top;">
					<div class="tools">
						<span>
							<a class="a" href="javascript:void(0);" id="delete" onclick="service.edit()">
								<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
								<b>添加&nbsp;</b>
							</a>
							<a class="a" href="javascript:void(0);" id="delete" onclick="service.ajaxLoadById()">
								<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
								<b>修改&nbsp;</b>
							</a>
							<a class="a" href="javascript:void(0);" id="delete" onclick="service.deleteSource()">
								<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
								<b>删除&nbsp;</b>
							</a>
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
									<td class="th"><input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked)" type="checkbox"></td>
									<!-- <th class="th">索引</th> -->
									<th class="th">业务名称</th>
									<th class="th">业务号</th>
									<th class="th">频道名称</th>
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
</body>
</html>