<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源</title>
<jsp:include page="../css.jsp" />
<style type="text/css">
.bodyContent{height:88%;}
</style>
</head>
<body>
	<div class="content">
		<div class="nav" id="nav_logo"></div>
		<div >
			<span id="li1" onclick="changeBox(1,2);channels.load()" class="li li_on">频道信息</span>
			<span id="li2" onclick="changeBox(2,1);channels.ajaxLoadIncFile()" class="li">INC文件</span>
			<div style="clear:both;"></div>
		</div>
		<table class="bodyContent">
			<tr>
				<td style="vertical-align:top;">
					<div id="tab_box_div1" class="tab_box_div">
						<div class="tools" style="margin-top: -1px;">
							<span>
								<c:if test="${usermenu.freadonly=='w' || usermenu.freadonly=='a'}">
									<a class="a" href="javascript:void(0);" onclick="channels.ajaxLoadById(1)">
										<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
										<b>添加&nbsp;</b>
									</a>
									<a class="a" href="javascript:void(0);" onclick="channels.ajaxLoadById(2)">
										<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
										<b>批量添加&nbsp;</b>
									</a>
									<a class="a" href="javascript:void(0);" onclick="channels.ajaxLoadById()">
										<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
										<b>修改&nbsp;</b>
									</a>
									<a class="a" href="javascript:void(0);" onclick="channels.deleteSource()">
										<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
										<b>删除&nbsp;</b>
									</a>
									<a class="a" href="javascript:void(0);" onclick="channels.loadLevel()">
										<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
										<b>频道分组&nbsp;</b>
									</a>
									<a class="a" href="javascript:void(0);" onclick="channels.batchGroup()">
										<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
										<b title="以省公司为标准，批量对频道分组">频道批量分组&nbsp;</b>
									</a>
									<a class="a" href="${rc.contextPath}/main/tools" target="_black">
										<img src="${rc.contextPath }/images/priority_3.gif" width="20" height="20">
										<b>工具下载&nbsp;</b>
									</a>
								</c:if>
							</span>
						</div>
						<div id="data" class="data">
							<div class="opt">
								<form name="queryForm" id="queryForm" action="">
									<span style="padding: 3px 10px;">
										<c:if test="${USERLOGIN.fbranchid==1}">
											<label for="fbranchid">公司：</label>
											<select name="fbranchid" id="fbranchid" onchange="channels.load(1);">
												<c:forEach items="${branchs}" var="branch">
													<option value="${branch.id}">${branch.fname}</option>
												</c:forEach>
											</select>
										</c:if>
									</span>
								</form>
							</div>
							<div class="table">
								<table border="0" rules="none" cellpadding="0" cellspacing="0">
									<tr>
										<th class="th"><input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked)" type="checkbox"></th>
										<th class="th">索引</th>
										<th class="th">业务号</th>
										<th class="th">频道名称</th>
										<th class="th">传输流号</th>
										<th class="th">网络号</th>
										<th class="th">分组</th>
										<th class="th">分公司</th>
									</tr>
									<tbody id="dataGrid">
				
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div id="tab_box_div2" class="tab_box_div" style="display:none;">
						<div class="tools" style="margin-top: -1px;">
							<span>
								<c:if test="${usermenu.freadonly=='w' || usermenu.freadonly=='a'}">
									<a class="a" href="javascript:void(0);" onclick="channels.uploadIncFile()">
										<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
										<b>上传频道inc文件&nbsp;</b>
									</a>
									<a class="a" href="javascript:void(0);" onclick="channels.deleteIncFile()">
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
										<c:if test="${USERLOGIN.fbranchid==1}">
											<label for="fbranchid">公司：</label>
											<select name="fbranchid" id="branchid" onchange="channels.ajaxLoadIncFile();">
												<option value="0">===选择分公司===</option>
												<c:forEach items="${branchs}" var="branch">
													<option value="${branch.id}">${branch.fname}</option>
												</c:forEach>
											</select>
										</c:if>
										<label for="fstatus">状态：</label>
											<select name="fstatus" id="fstatus" onchange="channels.ajaxLoadIncFile();">
												<option value="">===选择编辑状态===</option>
												<option value="0">未编辑</option>
												<option value="1">已编辑</option>
											</select>
									</span>
								</form>
							</div>
							<div class="table">
								<table border="0" rules="none" cellpadding="0" cellspacing="0">
									<tr>
										<th class="th"><input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked)" type="checkbox"></th>
										<th class="th">文件名</th>
										<th class="th">版本</th>
										<th class="th">上传时间</th>
										<th class="th">上传用户</th>
										<th class="th">状态</th>
										<th class="th">分公司</th>
										<th class="th"></th>
									</tr>
									<tbody id="dataGrid_">
				
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div id="pager_bar"></div>
				</td>
			</tr>
		</table>
	</div>
<jsp:include page="../contentHead.jsp" />
<script type="text/javascript" src="${rc.contextPath}/js/self/channels.js"></script>
<script type="text/javascript">
function changeBox(id,di){
	if(id == 1)
		$("#navText").html("广告管理&gt;频道编辑");
	else
		$("#navText").html("广告管理&gt;频道编辑&gt;INC文件管理");
	$("#pager").html("");
	$("#dataGrid_").html("");
	$("#dataGrid").html("");
	
	$("#li"+id).removeClass();
	$("#li"+di).removeClass();
	$("#li"+id).addClass('li li_on');
	$("#tab_box_div" + id).show();
	$("#li"+di).addClass('li');
	$("#tab_box_div" + di).hide();
}
</script>
</body>
</html>