<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的任务</title>
<jsp:include page="../css.jsp" />
<style type="text/css">
.bodyContent{height:88%;}
</style>
</head>
<body>
	<div class="content">
		<div class="nav" id="nav_logo"></div>
		<div >
			<span id="li1" onclick="changeBox(1,2)" class="li li_on">我的任务</span>
			<span id="li2" onclick="changeBox(2,1);" class="li">我操作的</span>
			<div class="clear"></div>
		</div>
		<input id="nid" value="${node.id}" type="hidden"/>
		<table class="bodyContent">
			<tr>
				<td style="vertical-align:top;">
					<div id="tab_box_div1" class="tab_box_div">
						<div class="tools" style="margin-top: -1px;">
							<span>
								<c:if test="${(node.ftype==1 && node.fischecked==0) && (usermenu.freadonly=='w' || usermenu.freadonly=='a')}">
									<span>请至广告编辑页面进行广告编辑</span>
								</c:if>
								<c:if test="${(node.ftype==1 && node.fischecked==2) && (usermenu.freadonly=='w' || usermenu.freadonly=='a')}">
									<span>请至广告发布页面进行广告发布</span>
								</c:if>
							</span>
						</div>
						<div id="data" class="data">
							<div class="opt">
								<form name="queryForm" id="queryForm" action="">
									<span style="padding: 3px 10px;">
										<c:if test="${USERLOGIN.fbranchid==1}">
											<label for="fbranchid">公司：</label>
											<select name="fbranchid" id="fbranchid" onchange="myTask.load(1);">
												<option value="">===分公司===</option>
												<c:forEach items="${branchs}" var="b">
													<option value="${b.id}">${b.fname}</option>
												</c:forEach>
											</select>
										</c:if>
										<c:if test="${node.ftype < 2}">
											<label for="fisvalid">状态：</label>
											<select name="fisvalid" id="fisvalid" onchange="myTask.load(1);">
												<option value="1">正常提交</option>
												<option value="0">流程回退</option>
											</select>
											<span id="totalOther"></span>
										</c:if>
									<!-- 	&nbsp;<label for="check"> 全选</label><input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked,'div','img_div')" type="checkbox"> -->
										<!-- &nbsp;&nbsp;列出： 从 <input class="Wdate" id="queryStartTime" readonly="readonly" name="queryStartTime" onClick="WdatePicker()" style="width: 90px;" type="text">
										至 <input class="Wdate" id="queryEndTime" name="queryEndTime" readonly="readonly" onClick="WdatePicker()" style="width: 90px;" type="text">
										&nbsp;关键词: <input name="queryKeyWord" id="queryKeyWord" style="width: 110px;" type="text">
										<input class="inputButton" name="submitbutton" value="查询" id="submitbutton" onclick="myTask.load(1)" type="button"> -->
									</span>
								</form>
							</div>
							<div class="table">
								<table border="0" rules="none" cellpadding="0" cellspacing="0">
									<tbody id="dataGrid">
										
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div id="tab_box_div2" class="tab_box_div" style="display:none;">
						<div id="data" class="data">
							<div class="opt">
								<form name="queryForm" id="queryForm1" action="">
									<span style="padding: 3px 10px;">
										<c:if test="${USERLOGIN.fbranchid==1}">
											<label for="fbranchid">公司：</label>
											<select name="fbranchid" id="fbranchid" onchange="myTask.ajaxIOperateInfo(1);">
												<c:forEach items="${branchs}" var="b">
													<option value="${b.id}">${b.fname}</option>
												</c:forEach>
											</select>
										</c:if>
										<!-- &nbsp;<label for="check"> 全选</label><input class="inputCheckbox" name="check" id="AllCheck" onchange="selectAllBox(this.checked,'div','img_div')" type="checkbox">
										&nbsp;&nbsp;列出： 从 <input class="Wdate" id="queryStartTime" readonly="readonly" name="queryStartTime" onClick="WdatePicker()" style="width: 90px;" type="text">
										至 <input class="Wdate" id="queryEndTime" name="queryEndTime" readonly="readonly" onClick="WdatePicker()" style="width: 90px;" type="text">
										&nbsp;关键词: <input name="queryKeyWord" id="queryKeyWord" style="width: 110px;" type="text">
										<input class="inputButton" name="submitbutton" value="查询" id="submitbutton" onclick="myTask.load()" type="button">
									 -->
									</span>
								</form>
							</div>
							<div class="table">
								<div id="dataGrid_"></div>
							</div>
						</div>
					</div>
					<div id="pager_bar"></div>
				</td>
			</tr>
		</table>
	</div>
<jsp:include page="../contentHead.jsp" />
<script type="text/javascript" src="${rc.contextPath}/js/self/myTask.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/source.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/self/preview.js"></script>
<script type="text/javascript">
function changeBox(id,di){
	if(id == 1){
		myTask.load(1,0);
		$("#navText").html("广告管理&gt;我的任务");
	} else{
		$("#navText").html("广告管理&gt;我的任务&gt;我操作的广告");
		myTask.ajaxIOperateInfo();
	}
		
	$("#pager").html("");
	$("#dataGrid_").html("");
	$("#dataGrid").html("");
	
	$("#li"+id).removeClass();
	$("#li"+di).removeClass();
	$("#li"+di).addClass('li');
	$("#li"+id).addClass('li li_on');
	$("#tab_box_div" + di).hide();
	$("#tab_box_div" + id).show();
}
</script>
</body>
</html>