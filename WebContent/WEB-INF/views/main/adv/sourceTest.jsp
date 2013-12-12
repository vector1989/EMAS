<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>广告测试</title>
</head>
<jsp:include page="../css.jsp" />
<link rel="stylesheet" href="${rc.contextPath}/style/ztree/zTreeStyle.css" type="text/css" />
<body>
	<div class="content">
		<div class="nav" id="nav_logo"></div>
		<table class="bodyContent">
			<tr>
				<td id="tree_div" style="vertical-align:top;">
					<ul id="tree" class="ztree"></ul>
				</td>
				<td style="vertical-align:top;">
					<div class="tools">
						<span>
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
										<label for="fbranchid">公司：</label>
										<select name="fbranchid" id="fbranchid" onchange="source.loadBranchRelease();">
											<c:forEach items="${branchs}" var="b">
												<option value="${b.id}">${b.fname}</option>
											</c:forEach>
										</select>
									</c:if>
									<label for="fdefinition">解析度:</label>
									<select name="fdefinition" id="fdefinition" onchange="source.loadBranchRelease()" >
										<option value="HD">高清</option>
										<option value="SD">标清</option>
									</select>
									<label for="fstatus">状态：</label>
									<c:if test="${USERLOGIN.fbranchid==1}">
										<c:if test="${USERLOGIN.id == 1}">
											<select name="fstatus" id="fstatus" onchange="source.loadBranchRelease();">
												<option value="3">省公司待测试</option>
												<option value="4">分公司待测试</option>
											</select>
										</c:if>
										<c:if test="${USERLOGIN.id != 1}">
											<input name="fstatus" value="3" type="hidden"/>
										</c:if>
									</c:if>
									<c:if test="${USERLOGIN.fbranchid != 1}">
										<input name="fstatus" value="4" type="hidden"/>
									</c:if>
										<label for="freleaseversionid">广告版本：</label>
										<select name="freleaseversion" id="freleaseversionid" onchange="source.createTree();">
											<c:forEach items="${rvs}" var="rv">
												<option value="${rv.id}" label="${rv.fisfinishededit}">${rv.fversion}</option>
											</c:forEach>
										</select>
										<input type="button" id="versiondesc" style="display:none" value="查看版本描述信息" onclick="source.showVersionDesc();"/>
										<input type="button" id="rvNode" style="display:none" value="版本流程" onclick="source.gotoStatus();"/>
									<br/>
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
<script type="text/javascript" src="${rc.contextPath}/js/self/source.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/sourceBase.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/preview.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	navTag("广告管理&gt;待测试广告");
	source.createTree();
});
</script>
</body>
</html>