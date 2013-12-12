<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
html,body {
	margin: 0px;
	padding: 0px;
	overflow-x: hidden;//去掉横条
}
</style>
<jsp:include page="../css.jsp" />
<title>合同编辑</title>
</head>
<body>
	<div class="nav" id="nav_logo">
	</div>
	<div class="tools">
		<form id="addContractForm" action="" method="post" enctype="multipart/form-data">
			<span>
			<input type="file" name="file" id="file" style="display: none;" onchange="updateWord(this.value);">
			<a class="a" href="javascript:void(0);" onclick="javascript:$('#file').click()">
					<img src="${rc.contextPath }/images/a.gif" width="20" height="20">
					<b>导入现有WORD&nbsp;</b>
			</a>
			<a class="a" href="javascript:void(0);" onclick="saveContract()" id="modify">
				<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
					<b>保存&nbsp;</b>
			</a> <%-- <a class="${usermenu.freadonly=='w' || usermenu.freadonly=='a' ? 'a' : 'a1'}" href="javascript:void(0);" onclick="${usermenu.freadonly=='w' || usermenu.freadonly=='a' ? 'closeContract()' : ''}">
					<img src="${rc.contextPath }/images/d.gif" width="20" height="20">
					<b>关闭&nbsp;</b>
				</a> --%>
			</span>
		</form>
	</div>
	<div style="width: 100%; overflow: auto;">
		<form id="newContract" name="newContract" style="width: 100%;">
			<table id="contractTable" style="width: 100%">
				<tr>
					<td><label for="fguid">合同编号：</label><input type="text"
						class="text" id="fguid" name="fguid" value="${contract.fguid }"
						placeholder="请输入合同编号" /></td>
					<td><label for="ftitle">合同名称：</label><input type="text"
						class="text" id="ftitle" name="ftitle" value="${contract.ftitle }"
						placeholder="请输入合同名称" /></td>
					<td><label for="fadvname">广&nbsp;告&nbsp;商：</label><input
						type="text" class="text" id="fadvname" name="fadvname"
						value="${contract.fadvname }" placeholder="请输入合同广告商" /></td>
				</tr>
				<tr>
					<td><label for="fadvlevel">广告级别：</label> <select
						id="fadvlevel" name="fadvlevel" style="width: 155px">
							<option value="A"
								${contract.fadvlevel =='A'?'selected="selected"':''}>A</option>
							<option value="B"
								${contract.fadvlevel =='B'?'selected="selected"':''}>B</option>
							<option value="C"
								${contract.fadvlevel =='C'?'selected="selected"':''}>C</option>
					</select></td>
					<td><label for="fagent">代&nbsp;理&nbsp;商：</label><input
						type="text" class="text" id="fagent" name="fagent"
						value="${contract.fagent }" placeholder="请输入合同代理商" /></td>
					<td><label for="fprice">单&nbsp;&nbsp;&nbsp;&nbsp;价：</label><input
						type="text" class="text" id="fprice" name="fprice"
						value="${contract.fprice }" placeholder="请输入整数单价" /></td>
				</tr>
				<tr>
					<td><label for="fdiscount">折&nbsp;&nbsp;&nbsp;&nbsp;扣：</label><input
						type="text" class="text" id="fdiscount" name="fdiscount"
						value="${contract.fdiscount }" placeholder="格式为0.XX或1" /></td>
					<td><label for="fpay">付款方式：</label><input type="text"
						class="text" id="payway" name="fpayway"
						value="${contract.fpayway }" placeholder="请输入付款方式" /></td>
					<td><label for="fcontactname">联&nbsp;系&nbsp;人：</label><input
						type="text" class="text" id="fcontactname" name="fcontactname"
						value="${contract.fcontactname }" placeholder="请输入联系人姓名" /></td>
				</tr>
				<tr>
					<td><label for="fcontacttel">联系电话：</label><input type="text"
						class="text" id="fcontacttel" name="fcontacttel"
						value="${contract.fcontacttel }" placeholder="格式为手机或区号-电话" /></td>
					<td><label for="fstarttime">起始日期：</label><input class="Wdate"
						id="fstarttime" name="fstarttime" onClick="WdatePicker()"
						value="${contract.fstarttime }" readonly="readonly"
						style="width: 155px;" type="text" placeholder="请输入起始日期"></td>
					<td><label for="fendtime">终止日期：</label><input class="Wdate"
						id="fendtime" name="fendtime" onClick="WdatePicker()"
						value="${contract.fendtime }" readonly="readonly"
						style="width: 155px;" type="text" placeholder="请输入终止日期"></td>
				</tr>
				<tr>
					<td colspan="3"><label for="quickSelect">快速选择广告位：</label> <label>标清</label><input
						type="checkbox" class="text" id="quickSelect1" name="quickSelect"
						value="SD" onchange="isChecked(1)" /> <label>高清</label><input
						type="checkbox" class="text" id="quickSelect2" name="quickSelect"
						value="HD" onchange="isChecked(2)" /> <input type="button"
						class="text" value="清空" onclick="clearSelect();" /></td>
				</tr>
				<tr>
					<td colspan="3"><label for="fadvid">广&nbsp;告&nbsp;位:</label>
						<div style="width: 100%; padding-left: 50px;">
							<!-- 高清  -->
							<c:forEach var="adv" items="${advs}" varStatus="vs">
								<c:if test="${adv.fdefinition=='HD'}">
									<c:if test="${adv.fpositionid==7}">
										<c:set value="${adv}" var="hdListAdv"/>
									</c:if>
									<c:if test="${adv.fpositionid != 7}">
										<c:set var="bool" value="true" />
										<c:if test="${not empty conadvs and bool}">
											<c:forEach var="conadv" items="${conadvs}" varStatus="s">
												<c:if test="${bool}">
													<c:if test="${not empty hdListAdv && hdListAdv.id == conadv.fadvid}">
														<c:set value="1" var="hdListAdvChecked"/>
													</c:if>
													<c:if test="${conadv.fadvid ==adv.id}">
														<div style="float: left; width: 150px;">
															<input type="checkbox" name="fadvid" value="${adv.id }" attr="${adv.fdefinition}" checked="checked">
															<label for="fadvid">${adv.ftype}|${adv.fdefinition=="HD"?"高清":"标清"}</label>
														</div>
														<c:set var="bool" value="false" />
													</c:if>
													<c:if test="${s['last'] and bool}">
														<div style="float: left; width: 150px;">
															<input type="checkbox" name="fadvid" value="${adv.id }" attr="${adv.fdefinition}">
															<label for="fadvid">${adv.ftype}|${adv.fdefinition=="HD"?"高清":"标清"}</label>
														</div>
														<c:set var="bool" value="false" />
													</c:if>
												</c:if>
											</c:forEach>
										</c:if>
										<c:if test="${empty conadvs}">
											<div style="float: left; width: 150px;">
												<input type="checkbox" name="fadvid" value="${adv.id }" attr="${adv.fdefinition}">
												<label for="fadvid">${adv.ftype}|${adv.fdefinition=="HD"?"高清":"标清"}</label>
											</div>
										</c:if>
									</c:if>
								</c:if>
							</c:forEach>
							<c:if test="${not empty hdListAdv}">
								<div style="float: left; width: 150px;">
									<input type="checkbox" name="fadvid" value="${hdListAdv.id }" attr="${hdListAdv.fdefinition}" ${not empty hdListAdvChecked ? "checked='checked'" : ""}>
									<label for="fadvid">${hdListAdv.ftype}|${hdListAdv.fdefinition=="HD"?"高清":"标清"}</label>
								</div>
							</c:if>
							<div style="clear: both;"></div>
							<!-- 标清 -->
							<!-- <br/> -->
							<c:forEach var="adv" items="${advs}" varStatus="vs">
								<c:if test="${adv.fdefinition=='SD' && adv.fpositionid != 7}">
									<c:set var="bool" value="true" />
									<c:if test="${not empty conadvs and bool}">
										<c:forEach var="conadv" items="${conadvs}" varStatus="s">
											<c:if test="${bool}">
												<c:if test="${conadv.fadvid ==adv.id}">
													<div style="float: left; width: 150px;">
														<input type="checkbox" name="fadvid" value="${adv.id }" attr="${adv.fdefinition}" checked="checked">
														<label for="fadvid">${adv.ftype}|${adv.fdefinition=="HD"?"高清":"标清"}</label>
													</div>
													<c:set var="bool" value="false" />
												</c:if>
												<c:if test="${s['last'] and bool}">
													<div style="float: left; width: 150px;">
														<input type="checkbox" name="fadvid" value="${adv.id }"	attr="${adv.fdefinition}">
														<label for="fadvid">${adv.ftype}|${adv.fdefinition=="HD"?"高清":"标清"}</label>
													</div>
													<c:set var="bool" value="false" />
												</c:if>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${empty conadvs}">
										<div style="float: left; width: 150px;">
											<input type="checkbox" name="fadvid" value="${adv.id }" attr="${adv.fdefinition}">
											<label for="fadvid">${adv.ftype}|${adv.fdefinition=="HD"?"高清":"标清"}</label>
										</div>
									</c:if>
								</c:if>
							</c:forEach>
							<font color='color' id="erroradvid"></font>
						</div></td>
				</tr>
			</table>
			<input type="hidden" id="ffiletitle" name="ffiletitle" value="${contract.ffiletitle }" />
			<input type="hidden" name="id" id="id" value="${contract.id }" />
		</form>
		<div align="center">
			<textarea id="editor_id" name="content" style="width: 90%; height: 300;">${contract.fcontent}</textarea>
		</div>
	</div>
<jsp:include page="../contentHead.jsp" />
<script type="text/javascript" src="${rc.contextPath}/js/utils/zh_CN.js"></script>
<script type="text/javascript">
	navTag("合同管理 &gt; 合同编辑");
	var editor;
	var filePath;
	var fileName;
	KindEditor.ready(function(K) {
		editor = K.create('#editor_id', {
			filterMode : false
		});
	});

	function updateWord(value) {
		var regExp = /^.*\.doc$/;
		if (!regExp.test(value)) {
			alert("请选择doc文件！");
		} else {
			_waiting._show();
			$("#addContractForm").ajaxSubmit(
					{
						url : "addContract",
						type : "post",
						dataType : "json",
						success : function(data) {
							filePath = data.filePath;
							fileName = data.fileName;
							fileOriginName = data.fileOriginName;
							$("#ftitle").val(
									fileOriginName.substr(0, fileOriginName
											.indexOf(".doc")));
							$("#ffiletitle").val(data.fileName);
							editor.html(htmldecode(data.wordHtml));
							_waiting._hide();
						}
					});
		}
	}

	function htmldecode(str) {
		str = str.replace(/&nbsp;/gi, ' ');
		str = str.replace(/&lt;/gi, '<');
		str = str.replace(/&gt;/gi, '>');
		str = str.replace(/&amp;/gi, '&');
		return str;
	}

	function saveContract() {
		var advloc = selectValue();
		if (checkForm()) {
			var data = {
				"fcontent" : editor.html(),
				"advloc" : advloc
			};
			_waiting._show();
			$("#newContract").ajaxSubmit({
				url : "saveContract",
				data : data,
				type : "post",
				dataType : "json",
				success : function(data) {
					console.info(data);
					if (null != data.errormsg) {
						$.jBox.tip(data.errormsg);
					} else {
						if (null != data.contract) {
							$("#contractid").val(data.contract.id);
							$("#ftitle").val(data.contract.ftitle);
							$("#ffiletitle").val(data.contract.ffiletitle);
						}
						//self.location.href="${rc.contextPath}/main/contract/listIndex";
						window.history.back();
					}
					//alert(data.msg);
					_waiting._hide();
				},
				error : function(data) {
					_waiting._hide();
				}
			});
		}
	}

	function closeContract() {
		if (confirm("请确认是否保存编辑文档，确认关闭？")) {
			window.close();
		}
	}

	function checkForm() {
		$("font[name='tips']").remove();
		var bool = true;
		var input = $("#contractTable").find(":input[type=text]");
		var length = input.length;
		for ( var i = 0; i < length; i++) {
			var obj = input[i];
			if (obj.type == "text" && obj.value.length == 0) {
				$(obj)
						.after(
								"<font color='color' name='tips'>[该值不允许为空]</font>");
				bool = false;
			} else if (obj.name == "fcontacttel") {
				var regExp = /^(\d{0,4}-)?\d{3,11}$/;
				if (!regExp.test(obj.value)) {
					$(obj).after(
							"<font color='color' name='tips'>[号码格式错误]</font>");
					bool = false;
				}
			} else if (obj.name == "fprice") {
				var regExp = /^\d+$/;
				if (!regExp.test(obj.value)) {
					$(obj).after(
							"<font color='color' name='tips'>[单价格式错误]</font>");
					bool = false;
				}
			} else if (obj.name == "fdiscount") {
				var regExp = /^0\.\d+|1$/;
				if (!regExp.test(obj.value)) {
					$(obj).after(
							"<font color='color' name='tips'>[折扣格式错误]</font>");
					bool = false;
				}
			}
		}
		if (!$("input[name='fadvid']:checked").length) {
			$("#erroradvid").html("[请选择广告位]");
			bool = false;
		}
		var starttime = $("#fstarttime").val();
		var endtime = $("#fendtime").val();
		if (starttime && endtime) {
			var d1 = toDate(starttime);
			var d2 = toDate(endtime);
			if (d1 > d2) {
				$("#fstarttime")
						.after(
								"<font color='color' name='tips'>[起始日期不能大于终止日期]</font>");
				$("#fendtime")
						.after(
								"<font color='color' name='tips'>[起始日期不能大于终止日期]</font>");
			}
		}
		return bool;
	}
	function selectValue() {//返回选中项的值
		var checkbox = $("input[name='fadvid']:checked");
		var val = new Array();
		checkbox.each(function(i) { //由于复选框一般选中的是多个,所以可以循环输出
			val.push($(this).val() + "|" + $(this).attr("attr"));
		});
		return val.join(",");
	}
	//
	function toDate(str) {
		var sd = str.split("-");
		return new Date(sd[0], sd[1], sd[2]);
	}
	//是否选中
	function isChecked(id) {
		if (id == 1) {
			var s1c = $("#quickSelect1").attr("checked");
			if (s1c) {
				$("#quickSelect2").attr("checked", false);
				$("input[type='checkbox'][attr='SD']").attr("checked", "true");
				$("input[type='checkbox'][attr='HD']").attr("checked", false);
			}
		} else {
			var s2c = $("#quickSelect2").attr("checked");
			if (s2c) {
				$("#quickSelect1").attr("checked", false);
				$("input[type='checkbox'][attr='HD']").attr("checked", true);
				$("input[type='checkbox'][attr='SD']").attr("checked", false);
			}
		}
	}
	function clearSelect() {
		$("#quickSelect1").attr("checked", false);
		$("#quickSelect2").attr("checked", false);
		$("input[name='fadvid']").attr("checked", false);
	}
</script>
</body>
</html>