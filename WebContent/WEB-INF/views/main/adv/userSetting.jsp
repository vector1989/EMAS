<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户设置</title>
<jsp:include page="../css.jsp" />
<jsp:include page="../contentHead.jsp" />
<style type="text/css">
<!--
body,html{height:100%;}
*{margin:0;padding:0;list-style:none;}
#mainBox{width:100%; padding-top: 3px;}
.right_top{border:0px solid #c1dcee;height:auto !important;min-height:30px;padding:5px;vertical-align:middle;line-height:30px;}

.tools{margin: 5px; font-style: normal; font-size: 14px; height:25px; text-align: center;}
.tools{text-decoration: none;height: 24px;margin: 0 5px 0 0;background: url('${rc.contextPath}/images/btnout_bg_left.gif') no-repeat left center;float: left;_display: inline;-moz-user-select:none;}
.tools img {margin: 2px 0px 4px 4px;_display: inline;float: left; border: 0px;}
.tools b {cursor:pointer;	white-space: nowrap;color: #666;font-weight: normal;height: 24px;padding:0 3px 0 3px;line-height: 24px;background: url('${rc.contextPath}/images/btnout_bg_right.gif') no-repeat right center;display:inline-block;+zoom: 1;+display: inline;}
.tools:hover {text-decoration: none;background: url('${rc.contextPath}/images/btnover_bg_left.gif') no-repeat left center;}
.tools:hover b {color: #147;background: url('${rc.contextPath}/images/btnover_bg_right.gif') no-repeat right center;}

.table{border-collapse:collapse;border:none;}
.table td{border:solid #c1dcee 1px;  font-size:15px;padding:3px;}
.trHead{text-align:center;height:22px;background-image: url('${rc.contextPath}/images/td_bg.png');background-repeat: repeat-x;}

.tr td{font-size:13px;text-align:center;}
.tr_hover td{font-size:13px;text-align:center;}
.tr:hover{background-color: #D8F79D}
.tr{background-color: #ffffff}
.tr_hover{background-color: #D8F79D}

.li{border:1px solid #ccc;background:#EEEEEE;font-size:14px;cursor:pointer;width:100px;height:25px;verticle-align:middle;line-height:25px;text-align:center;margin-left:10px;}
.li_on{background:#ffffff;border-bottom-width: 0px;}

.tab_box_div{border:1px solid #ccc;background:#ffffff;margin-top:-2px;padding-top:10px;border-bottom-width: 0px;border-left-width: 0px;border-right-width: 0px;margin-top: -4px;}

.input_text{width:100%;font-size:16px;background-color:#ffffff;padding:3px;}
input{width: 90%}
-->
</style>
</head>
<body>
<div class="nav" id="nav_logo"></div>
<div id="mainBox">
	<div >
		<span id="li1" onclick="changeBox(1,2)" class="li li_on">个人信息</span>
		<span id="li2" onclick="changeBox(2,1)" class="li">修改密码</span>
		<div class="clear"></div>
		</div>
		<table class="bodyContent">
			<tr>
				<td style="vertical-align:top;">
					<div id="tab_box_div1" class="tab_box_div">
						<div id="data" class="data">
							<div class="table">
								<form action="" name="modifyUser" id="userInfoForm">
									<table class="table" width="98%" style="margin-left:10px;">
										<tr>
											<td width="20%" class="trHead">姓名</td>
											<td><input name="fname" value="${USERLOGIN.fname}"  style="border:0px; border-bottom: 1px #ccc solid;"/><input name="id" value="${USERLOGIN.id}" type="hidden"/></td>
										</tr>
										<tr>
											<td class="trHead">用户名</td>
											<td><input name="fusername" value="${USERLOGIN.fusername}"  style="border:0px; border-bottom: 1px #ccc solid;"/></td>
										</tr>
										<tr>
											<td class="trHead">用户分组</td>
											<td><input value="${USERLOGIN.fguid}" disabled="disabled"  style="border:0px; border-bottom: 1px #ccc solid;"/></td>
										</tr>
										<tr>
											<td  class="trHead">部门</td>
											<td ><input name="fdepart" value="${USERLOGIN.fdepart}" type="text"  style="border:0px; border-bottom: 1px #ccc solid;"/></td>
										</tr>
										<tr>
											<td  class="trHead">职位</td>
											<td ><input name="fpost" value="${USERLOGIN.fpost}" type="text"  style="border:0px; border-bottom: 1px #ccc solid;"/></td>
										</tr>
										<tr>
											<td  class="trHead">公司</td>
											<td ><input value="${USERLOGIN.branch}" type="text" style="border:0px; border-bottom: 1px #ccc solid;"/></td>
										</tr>
										<tr>
											<td></td>
											<td class="right_top">
												<a href="javascript:void(0)" onclick="save('update','userInfoForm')"  class="tools" ><b>修改</b></a>
											</td>
										</tr>
									</table>
								</form>
							</div>
						</div>
					</div>
					<div id="tab_box_div2" class="tab_box_div" style="display:none;">
						<div id="data" class="data">
							<div class="table">
								<form action="" name="modifyPsswd" id="modifyPasswd">
									<table class="table" width="98%" style="margin-right:10px;">
										<tr>
											<td width="20%" class="trHead">原密码</td>
											<td><input name="oldPass" value="" type="password" onchange="check('${USERLOGIN.fpassword}',this.value,'old')" onfocus="tip('old');" id="oldPass"><span id="old"></span><input name="id" value="${USERLOGIN.id}" type="hidden"/></td>
										</tr>
										<tr>
											<td class="trHead">新密码</td>
											<td><input name="newPasswd" id="newPasswd" value="" type="password" onfocus="tip('new1');"><span id="new1"></span></td>
										</tr>
										<tr>
											<td class="trHead">确认新密码</td>
											<td><input name="fpassword" id="fpassword" value="" type="password" onchange="checkFpasswd(this.value)" onfocus="tip('new2');"><span id="new2"></span></td>
										</tr>
										<tr>
											<td></td>
											<td class="right_top">
												<a href="javascript:void(0)" onclick="save('update','userInfoForm','id')" class="tools" ><b>修改</b></a>
												<!-- <a href="javascript:void(0)" class="tools"><b>重置</b></a> -->
											</td>
										</tr>
									</table>
								</form>
							</div>
						</div>
					</div>
					<div id="pager_bar"></div>
				</td>
			</tr>
		</table>
	</div>
<jsp:include page="../contentHead.jsp" />
<script type="text/javascript">
navTag("用户管理&gt;个人信息");
function changeBox(id,di){
	$("#li"+id).removeClass();
	$("#li"+di).removeClass();
	$("#li"+id).addClass('li li_on');
	$("#tab_box_div" + id).show();
	$("#li"+di).addClass('li');
	$("#tab_box_div" + di).hide();
}
/**
 * 保存修改数据
 */
function save(url,id,pass){
	url = "../user/" + url;
	if(pass){
		if(checked(id))
			url += "?fpassword="+$("#fpassword").val();
		else
			return;
	}
	$("#"+id).ajaxSubmit({
		url:url,
		type:'post',
		dataType:'json',
		success:function(data){
			$.jBox.tip('数据修改成功');
			top.location.href="../logout";
		},
		error:function(msg){
			$.jBox.tip('数据出错');
		}
	});
}
/**
 * 提交前验证
 */
function checked(id){
	if($("#oldPass").val() != "${USERLOGIN.fpassword}"){
		$("#oldPass").focus();
		$("#"+id).html("您输入的密码与原始密码不匹配");
		$("#"+id).css("color","red");
		return false;
	}
	if($("#newPasswd").val() ==""){
		$("#newPasswd").focus();
		$("#new1").html("新密码不能为空！");
		$("#new1").css("color","red");
		return false;
	}
	if($("#fpassword").val() == ""){
		$("#fpassword").focus();
		$("#new2").html("确认密码不能为空！");
		$("#new2").css("color","red");
		return false;
	}
	if($("#fpassword").val() != $("#newPasswd").val()){
		$("#fpassword").focus();
		$("#new2").html("新密码与确认密码不匹配！");
		$("#new2").css("color","red");
		return false;
	}
	return true;
}
//检查输入的密码与原始密码是否匹配
function check(passwd,value,id){
	if(passwd != value){
		$("#oldPass").focus();
		$("#"+id).html("您输入的密码与原始密码不匹配");
		$("#"+id).css("color","red");
	}
}
function tip(id){
	$("#"+id).html('');
}
//确认密码
function checkFpasswd(value,id){
	var new1 = $("#newPasswd").val();
	if(new1 == ""){
		$("#newPasswd").focus();
		$("#new1").html("新密码不能为空！");
		$("#new1").css("color","red");
	}
	if(value == ""){
		$("#new2").html("确认密码不能为空！");
		$("#new2").css("color","red");
		$("#fpassword").focus();
	}
	if(new1 != value){
		$("#new2").html("新密码与确认密码不匹配！");
		$("#new2").css("color","red");
		$("#fpassword").focus();
	}
}
</script>
</body>
</html>