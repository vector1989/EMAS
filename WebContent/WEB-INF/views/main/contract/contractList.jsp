<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>合同</title>
</head>
<jsp:include page="../css.jsp" />
<body>
	<div class="content">
		<div class="nav" id="nav_logo"> </div>
		<table class="bodyContent">
			<tr>
				<td style="vertical-align:top;">
					<div class="tools">
						<span>
							<c:if test="${usermenu.freadonly=='w' || usermenu.freadonly=='a'}">
								<a class="a" href="javascript:void(0)" id="add" onclick="con.edit()" attr="addContractIndex" target="I2">
									<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
									<b>添加&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" src="addContractIndex" onclick="con.edit(1)" id="modify" target="I2">
									<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
									<b>修改&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" onclick="con.deleteContract()">
									<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
									<b>删除&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" id="download" onclick="con.download()">
									<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
									<b>下载&nbsp;</b>
								</a>
								<input type="file" name="file" id="file" style="display: none;" onchange="con.uploadPic(this.value);">
								<a class="a" href="javascript:void(0);" onclick="javascript:$('#file').click()">
									<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
									<b>上传合同图片&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" id="preview" onclick="con.preview()">
									<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
									<b>预览合同图片&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" id="previewContract" onclick="con.previewContract()">
									<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
									<b>合同预览&nbsp;</b>
								</a>
							</c:if>
							<c:if test="${usermenu.freadonly=='c' || usermenu.freadonly=='a'}">
								<a class="a" href="javascript:void(0);" id="checkedContract" onclick="baseJs.checkedContract()">
									<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
									<b>合同审核&nbsp;</b>
								</a>
							</c:if>
							<c:if test="${usermenu.freadonly=='w' || usermenu.freadonly=='a'}">
								<a class="a" href="javascript:void(0);" onclick="con.uploadRes()">
									<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
									<b>素材添加&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" onclick="con.previewResource()">
									<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
									<b>素材预览&nbsp;</b>
								</a>
								<a class="a" href="javascript:void(0);" onclick="con.loadAdvAndResourceByContract()">
									<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
									<b>合同素材&nbsp;</b>
								</a>
							</c:if>
						</span>
					</div>
					<div class="clear"></div>
					<div id="data" class="data">
						<div class="opt">
							<form name="queryForm" id="queryForm" action="">
								<span style="padding: 3px 10px;">
									<c:if test="${USERLOGIN.fbranchid==1}">
										<label for="fbranchid">公司：</label>
										<select name="fbranchid" id="fbranchid" onchange="con.load(1);">
											<c:forEach items="${branchs}" var="b">
												<option value="${b.id}">${b.fname}</option>
											</c:forEach>
										</select>
									</c:if>
									&nbsp;&nbsp;列出： 从 <input class="Wdate" id="queryStartTime" name="queryStartTime" onClick="WdatePicker()" style="width: 100px;" type="text">
									至 <input class="Wdate" id="queryEndTime" name="queryEndTime" onClick="WdatePicker({minDate:'#F{$dp.$D(\'queryStartTime\')}'})" style="width: 100px;" type="text">
									&nbsp;合同名称关键词: <input name="queryKeyWord" id="queryKeyWord" style="width: 110px;" type="text">
									<input class="inputButton" name="submitbutton" value="查询" id="submitbutton" type="button" onclick="con.load()">
									总金：<input id="sumPrice" name="sumPrice" readonly="readonly" style="border: hidden;"/>
								</span>
							</form>
						</div>
						<div class="table">
							<table border="0" rules="none" cellpadding="0" cellspacing="0">
								<tr>
									<td class="th"></td><!-- <input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked)" type="checkbox"> -->
									<!-- <th class="th">序号</th> -->
									<th class="th">合同编号</th>
									<th class="th">合同名称</th>
									<th class="th">广告商</th>
									<th class="th">代理商</th>
									<th class="th">开始时间|结束时间</th>
									<th class="th">创建人</th>
									<th class="th">联系人|电话</th>
									<th class="th">单价</th>
									<th class="th">折扣</th>
									<th class="th">付款方式</th>
									<th class="th">审核</th>
									<th class="th"></th>
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
<script type="text/javascript" src="${rc.contextPath}/js/self/contract.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/preview.js"></script>
</body>
</html>