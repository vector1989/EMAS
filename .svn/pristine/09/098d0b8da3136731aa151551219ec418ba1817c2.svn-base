<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	label {width:108px;}
</style>
<title>在播广告</title>
<jsp:include page="../css.jsp" />
<link rel="stylesheet" href="${rc.contextPath}/style/ztree/zTreeStyle.css" type="text/css" />
</head>
<body>
	<div class="content">
		<div class="nav" id="nav_logo"></div><table class="bodyContent">
			<tr>
				<td id="tree_div" style="vertical-align:top;">
					<ul id="tree" class="ztree"></ul>
				</td>
				<td style="vertical-align:top;">
					<div class="tools">
						<span>
							<%-- <a class="a" href="javascript:void(0);"  onclick="source.selectChannels()">
								<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
								<b>查看频道信息&nbsp;</b>
							</a> --%>
							<a class="a" id="showChannel" href="javascript:void(0);"  onclick="source.selectChannels()">
								<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
								<b>查看频道信息&nbsp;</b>
							</a>
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
									<c:if test="${USERLOGIN.fbranchid==1}">
										<label for="fbranchid">分公司：</label>
										<select name="fbranchid" id="fbranchid" onchange="source.createTree();">
											<c:forEach var="b" items="${branchs}">
												<option value="${b.id}" ${b.id == USERLOGIN.fbranchid?'selected="selected"':''}>${b.fname}</option>
											</c:forEach>
										</select>
									</c:if>
									<label for="fdefinition">解析度:</label>
									<select name="fdefinition" id="fdefinition" onchange="source.createTree()" >
										<option value="HD">高清</option>
										<option value="SD">标清</option>
									</select>
									<input name="isusing" id="isusing" value="1" type="hidden"/>
									<input name="fstatus" id="fstatus" value="1" type="hidden"/>
									&nbsp;&nbsp;列出： 从 <input class="Wdate" id="queryStartTime" name="queryStartTime" onClick="WdatePicker()" style="width: 100px;" type="text">
									至 <input class="Wdate" id="queryEndTime" name="queryEndTime" onClick="WdatePicker()" style="width: 100px;" type="text">
									&nbsp;关键词: <input name="queryKeyWord" id="queryKeyWord" style="width: 110px;" type="text">
									<input class="inputButton" name="submitbutton" value="查询" onclick="sourceRelease.queryByExample()" type="button">
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
<script type="text/javascript" src="${rc.contextPath}/js/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/sourceBase.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/sourceRelease.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/preview.js"></script>
<script type="text/javascript">navTag("广告管理&gt;正播广告");</script>
</body>
</html>