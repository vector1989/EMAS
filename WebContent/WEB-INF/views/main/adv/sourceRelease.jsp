<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>分公司数据库配置</title>
<jsp:include page="../css.jsp" />
<link rel="stylesheet" href="${rc.contextPath}/style/ztree/zTreeStyle.css" type="text/css" />
<style type="text/css">
	label {width:108px;}
</style>
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
					<div class="tools">
						<span>
							<c:if test="${(usermenu.freadonly=='c' || usermenu.freadonly=='a')}">
								<span id="tools">
									<a class="a" id="releaseA" href="javascript:void(0);" onclick="sourceRelease.release()">
										<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
										<b>发布&nbsp;</b>
									</a>
								</span>
							</c:if>
							<a class="a" href="javascript:void(0);" id="showChannel" onclick="source.selectChannels()">
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
									<select name="fdefinition" id="fdefinition" onchange="source.loadBranchRelease();" >
										<option value="HD">高清</option>
										<option value="SD">标清</option>
									</select>
									<label for="fstatus">状态：</label>
									<select name="fstatus" id="fstatus" onchange="source.loadBranchRelease();">
										<option value="0">待发布</option>
										<option value="1">已发布</option>
										<option value="2">未通过</option>
									</select>
									<label for="freleaseversionid">待发布版本：</label>
									<select name="freleaseversion" id="freleaseversionid" onchange="source.createTree();">
										<c:forEach items="${rvs}" var="rv">
											<option value="${rv.id}" label="${rv.fisfinishededit}">${rv.fversion}</option>
										</c:forEach>
									</select>
									<input type="button" id="versiondesc" style="display:none" value="查看版本描述信息" onclick="source.showVersionDesc();"/>
									<input type="button" value="版本流程" onclick="source.gotoStatus();"/>
									<br/>
									<!-- &nbsp;&nbsp;列出： 从 <input class="Wdate" id="queryStartTime" readonly="readonly" name="queryStartTime" onClick="WdatePicker()" style="width: 90px;" type="text">
									至 <input class="Wdate" id="queryEndTime" name="queryEndTime" readonly="readonly" onClick="WdatePicker()" style="width: 90px;" type="text">
									&nbsp;关键词: <input name="queryKeyWord" id="queryKeyWord" style="width: 110px;" type="text">
									<input class="inputButton" name="submitbutton" value="查询" id="submitbutton" onclick="source.queryByExample()" type="button">
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
<script type="text/javascript" src="${rc.contextPath}/js/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/sourceRelease.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/sourceBase.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/preview.js"></script>
<script type="text/javascript">
	navTag("广告管理&gt;广告发布");
</script>
</body>
</html>