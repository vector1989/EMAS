<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传资源</title>
<style type="text/css">
/**上传文件*/
.fu_list {width: 100%;background: #ebebeb;font-size: 12px;}

.fu_list td {padding: 5px;line-height: 20px;background-color: #fff;}

.fu_list table {width: 100%;border: 1px solid #ebebeb;}

.fu_list thead td {background-color: #f4f4f4;}

.fu_list b {font-size: 14px;}
/*file容器样式*/
a.files {width: 90px;height: 30px;overflow: hidden;display: block;border: 1px solid #BEBEBE;background: url(../../images/fu_btn.gif) left top no-repeat;text-decoration: none;}

a.files:hover {background-color: #FFFFEE;background-position: 0 -30px;}
/*file设为透明，并覆盖整个触发面*/
a.files input {margin-left: -350px;font-size: 30px;cursor: pointer;filter: alpha(opacity = 0);opacity: 0;}
/*取消点击时的虚线框*/
a.files,a.files input {outline: none; /*ff*/hide-focus: expression(this.hideFocus = true); /*ie*/}
</style>
<script type="text/javascript" src="${rc.contextPath }/js/jquery/jquery-1.8.2.js" charset="utf-8"></script>
<%-- <script type="text/javascript" src="${rc.contextPath}/js/datepicker/WdatePicker.js"></script>--%>
<script type="text/javascript" src="${rc.contextPath }/js/jquery/jquery.blockUI.js" charset="utf-8"></script>
<%-- <script type="text/javascript" src="${rc.contextPath }/js/utils/jscolor.js" charset="utf-8"></script>
 --%>
</head>
<body>
	<form id="uploadForm" action="upload" method="post">
		<table border="0" cellspacing="1" class="fu_list">
			<thead>
				<tr>
					<td colspan="2"><b>上传图片资源</b></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="right" width="15%" style="line-height: 35px;">添加文件：</td>
					<td>
						<%-- <a href="${(usermenu.freadonly=='w'||usermenu.freadonly=='wc')?'javascript:void(0);':''}" class="${(usermenu.freadonly=='w'||usermenu.freadonly=='wc')?'files':''}" id="${(usermenu.freadonly=='w'||usermenu.freadonly=='wc')?'idFile':''}"></a> --%>
						<!-- <input class="files" id="idFile" onchange="" type="file" multiple="true"> -->
						<a href="javascript:void(0)" class="files" id="idFile"></a>
						<img id="idProcess" style="display: none;" src="${rc.contextPath}/images/loading.gif" />
					</td>
				</tr>
				<tr>
					<td align="right" width="15%" style="line-height: 35px;">资源类型：</td>
					<td>
						<select name="ftype" onchange="changeType(this.value);">
							<option value="0">图片资源&nbsp;&nbsp;&nbsp;</option>
							<option value="3">视频I帧&nbsp;&nbsp;&nbsp;</option>
							<option value="10">视频资源&nbsp;&nbsp;&nbsp;</option>
							<!-- <option value="1">文本资源&nbsp;&nbsp;&nbsp;</option> -->
							<!-- <option value="2">实时音视频&nbsp;&nbsp;&nbsp;</option> -->
						</select>
					</td>
				</tr>
				<tr>
					<td align="right" width="15%" style="line-height: 35px;">广告类型：</td>
					<td>
						<select name="fadvtype" id="fadvtype">
							<c:forEach var="adv" items="${advs}">
								<option value="${adv.fpositionid}">${adv.ftype}&nbsp;&nbsp;&nbsp;</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right" width="15%" style="line-height: 35px;">解析度：</td>
					<td>
						<select name="fdefinition"><option value="HD">高清&nbsp;</option><option value="SD">标清&nbsp;&nbsp;</option></select>
					</td>
				</tr>
				<!-- <tr id="vedio" style="display: none;">
					<td align="right" width="15%" style="line-height: 35px;">文件编码格式：</td>
					<td><select name="ffileformat">
							<option value="0">mpg1、mpeg2编码&nbsp;</option>
							<option value="1">H.264编码&nbsp;&nbsp;&nbsp;</option>
					</select></td>
				</tr> -->
			<tbody id="text" style="display: none;">
				<tr>
					<td align="right" width="15%" style="line-height: 35px;">滚动类型：</td>
					<td><select name="fscrolltype">
							<option value="0">不滚动&nbsp;&nbsp;&nbsp;</option>
							<option value="1">从右至左&nbsp;&nbsp;&nbsp;</option>
							<option value="2">从左至右&nbsp;&nbsp;&nbsp;</option>
							<option value="3">从上到下&nbsp;&nbsp;&nbsp;</option>
							<option value="4">从下到上&nbsp;&nbsp;&nbsp;</option>
					</select></td>
				</tr>
				<tr>
					<td align="right" width="15%" style="line-height: 35px;">滚动速度：</td>
					<td><input name="fspeed" value="" /></td>
				</tr>
				<tr>
					<td align="right" width="15%" style="line-height: 35px;">背景颜色：</td>
					<td><input name="fbackcolor" class="color" value="66ff00" /></td>
				</tr>
				<tr>
					<td align="right" width="15%" style="line-height: 35px;">文字颜色：</td>
					<td><input name="ffontcolor" class="color" value="66ff00" /></td>
				</tr>
			</tbody>
			<tr>
				<td colspan="2">
					<table border="0" cellspacing="0">
						<thead>
							<tr>
								<td>待上传文件列表</td>
								<td width="100"></td>
							</tr>
						</thead>
						<tbody id="idFileList"></tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="color: gray">温馨提示：最多可同时上传 <b
					id="idLimit" style="color: red;"></b> 个文件，只允许上传 <b id="idExt"></b>
					文件。
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center" id="idMsg"><input type="button"
					value="开始上传" onclick="upload()" id="idBtnupload"
					disabled="disabled" /> &nbsp;&nbsp;&nbsp; <input type="button"
					value="全部取消" id="idBtndel" disabled="disabled" /></td>
			</tr>
			</tbody>
		</table>
	</form>
</body>
<script type="text/javascript">
changeType(0);
function changeType(value){
	var html = "";
	switch (value) {
	case '10':
		html = '<option value="2">开机视频&nbsp;&nbsp;&nbsp;</option>';
		break;
	case '3':
		html = '<option value="1">开机画面&nbsp;&nbsp;&nbsp;</option>';
		html += '<option value="9">数字音频背景&nbsp;&nbsp;&nbsp;</option>';
		break;
	default:
		html = '<option value="3">主菜单广告&nbsp;&nbsp;&nbsp;</option>';
		html += '<option value="4">信息条广告&nbsp;&nbsp;&nbsp;</option>';
		html += '<option value="5">节目列表&nbsp;&nbsp;&nbsp;</option>';
		html += '<option value="6">喜爱列表&nbsp;&nbsp;&nbsp;</option>';
		html += '<option value="7">高清列表&nbsp;&nbsp;&nbsp;</option>';
		html += '<option value="8">分类列表&nbsp;&nbsp;&nbsp;</option>';
		html += '<option value="10">节目指南&nbsp;&nbsp;&nbsp;</option>';
		html += '<option value="11">NVOD&nbsp;&nbsp;&nbsp;</option>';
		html += '<option value="12">音量条广告&nbsp;&nbsp;&nbsp;</option>';
		break;
	}
	$("#fadvtype").html(html);;
}


var isIE = (document.all) ? true : false;

var browser=navigator.appName;
var b_version=navigator.appVersion;
var version=parseFloat(b_version);


if (browser == "Microsoft Internet Explorer" && navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE9.0") {
    isIE = false;
}
if (browser == "Microsoft Internet Explorer" && version > 4) {
    isIE = false;
}
var $$ = function (id) {
    return "string" == typeof id ? document.getElementById(id) : id;
};

var Class = {
  create: function() {
    return function() {
      this.initialize.apply(this, arguments);
    };
  }
};

var Extend = function(destination, source) {
	for (var property in source) {
		destination[property] = source[property];
	}
};

var Bind = function(object, fun) {
	return function() {
		return fun.apply(object, arguments);
	};
};

var Each = function(list, fun){
	for (var i = 0, len = list.length; i < len; i++) { fun(list[i], i);}
};

var image = ["jpg"];
//文件上传类
var FileUpload = Class.create();
FileUpload.prototype = {
  //表单对象，文件控件存放空间
  initialize: function(form, folder, options) {
	this.Form = $$(form);//表单
	this.Folder = $$(folder);//文件控件存放空间
	this.Files = [];//文件集合
	
	this.SetOptions(options);
	
	this.FileName = this.options.FileName;
	this._FrameName = this.options.FrameName;
	this.Limit = this.options.Limit;
	this.Distinct = !!this.options.Distinct;
	this.ExtIn = this.options.ExtIn;
	this.ExtOut = this.options.ExtOut;
	
	this.onIniFile = this.options.onIniFile;
	this.onEmpty = this.options.onEmpty;
	this.onNotExtIn = this.options.onNotExtIn;
	this.onExtOut = this.options.onExtOut;
	this.onLimite = this.options.onLimite;
	this.onSame = this.options.onSame;
	this.onFail = this.options.onFail;
	this.onIni = this.options.onIni;
	
	if(!this._FrameName){
		//为每个实例创建不同的iframe
		this._FrameName = "uploadFrame_" + Math.floor(Math.random() * 1000);
		//ie不能修改iframe的name
		var oFrame = isIE ? document.createElement("<iframe name='" + this._FrameName + "'>") : document.createElement("iframe");
		//为ff设置name
		oFrame.name = this._FrameName;
		oFrame.style.display = "none";
		//在ie文档未加载完用appendChild会报错
		document.body.insertBefore(oFrame, document.body.childNodes[0]);
	}
	
	//设置form属性，关键是target要指向iframe
	this.Form.target = this._FrameName;
	this.Form.method = "post";
	//注意ie的form没有enctype属性，要用encoding
	this.Form.encoding = "multipart/form-data";

	//整理一次
	this.Ini();
  },
  //设置默认属性
  SetOptions: function(options) {
    this.options = {//默认值
		FileName:	"file",//文件上传控件的name，配合后台使用
		FrameName:	"",//iframe的name，要自定义iframe的话这里设置name
		onIniFile:	function(){},//整理文件时执行(其中参数是file对象)
		onEmpty:	function(){},//文件空值时执行
		Limit:		0,//文件数限制，0为不限制
		onLimite:	function(){},//超过文件数限制时执行
		Distinct:	true,//是否不允许相同文件
		onSame:		function(){},//有相同文件时执行
		ExtIn:		["jpg","m2v","mpg2"],//允许后缀名
		onNotExtIn:	function(){},//不是允许后缀名时执行
		ExtOut:		[],//禁止后缀名，当设置了ExtIn则ExtOut无效
		onExtOut:	function(){},//是禁止后缀名时执行
		onFail:		function(){},//文件不通过检测时执行(其中参数是file对象)
		onIni:		function(){}//重置时执行
    };
    Extend(this.options, options || {});
  },
  //整理空间
  Ini: function() {
	//整理文件集合
	this.Files = [];
	//整理文件空间，把有值的file放入文件集合
	Each(this.Folder.getElementsByTagName("input"), Bind(this, function(o){
		if(o.type == "file"){ o.value && this.Files.push(o); this.onIniFile(o); }
	}));
	//插入一个新的file
	var file = document.createElement("input");
	file.name = this.FileName; file.type = "file"; file.multiple="true"; file.onchange = Bind(this, function(){this.Check(file); this.Ini(); });
	this.Folder.appendChild(file);
	//执行附加程序
	this.onIni();
  },
  //检测file对象
  Check: function(file) {
	//检测变量
	var bCheck = true;
	//空值、文件数限制、后缀名、相同文件检测
	if(!file.value){
		bCheck = false; this.onEmpty();
	} else if(this.Limit && this.Files.length >= this.Limit){
		bCheck = false; this.onLimite();
	} else if(!!this.ExtIn.length && !RegExp("\.(" + this.ExtIn.join("|") + ")$$", "i").test(file.value)){
		//检测是否允许后缀名
		bCheck = false; this.onNotExtIn();
	} else if(!!this.ExtOut.length && RegExp("\.(" + this.ExtOut.join("|") + ")$$", "i").test(file.value)) {
		//检测是否禁止后缀名
		bCheck = false; this.onExtOut();
	} else if(!!this.Distinct) {
		Each(this.Files, function(o){ if(o.value == file.value){ bCheck = false; }});
		if(!bCheck){ this.onSame(); }
	}
	//没有通过检测
	!bCheck && this.onFail(file);
  },
  //删除指定file
  Delete: function(file) {
	//移除指定file
	this.Folder.removeChild(file); this.Ini();
  },
  //删除全部file
  Clear: function() {
	//清空文件空间
	Each(this.Files, Bind(this, function(o){ this.Folder.removeChild(o); })); this.Ini();
  }
};

var fu = new FileUpload("uploadForm", "idFile", { 
	onIniFile: function(file){ file.value ? file.style.display = "none" : this.Folder.removeChild(file); },
	onEmpty: function(){ alert("请选择一个文件"); },
	onLimite: function(){ alert("超过上传限制"); },
	onSame: function(){ alert("已经有相同文件"); },
	onNotExtIn:	function(){ alert("只允许上传" + this.ExtIn.join("，") + "文件"); },
	onFail: function(file){ this.Folder.removeChild(file); },
	onIni: function(){
		//显示文件列表
		var arrRows = [];
		if(this.Files.length){
			var oThis = this;
			Each(this.Files, function(o){
				var a = document.createElement("a"); a.innerHTML = "取消"; a.href = "?";
				a.onclick = function(){ oThis.Delete(o); return false; };
				var val = o.value;
				var name = val.substring(val.lastIndexOf("//")+1, val.length);
				/* for(var i = 0; i <image.length;i++){
					if(suffix == image[i]){
						val = '<img src="'+val+'" width="60" height="50">';
					}
				} */
				arrRows.push([name, a]);
			});
		} else { arrRows.push(["<font color='gray'>没有添加文件</font>", "&nbsp;"]); }
		AddList(arrRows);
		//设置按钮
		$$("idBtnupload").disabled = $$("idBtndel").disabled = this.Files.length <= 0;
	}
});

function upload(){
	//显示文件列表
	var arrRows = [];
	Each(fu.Files, function(o){ arrRows.push([o.value, "&nbsp;"]); });
	AddList(arrRows);
	
	fu.Folder.style.display = "none";
	$$("idProcess").style.display = "";
	$$("idMsg").innerHTML = "正在上传文件到服务器中，请稍候……<br />有可能因为网络问题，出现程序长时间无响应，请点击“<a href='?'><font color='red'>取消</font></a>”重新上传文件";
	
	fu.Form.submit();
};

//用来添加文件列表的函数
function AddList(rows){
	//根据数组来添加列表
	var FileList = $$("idFileList"), oFragment = document.createDocumentFragment();
	//用文档碎片保存列表
	Each(rows, function(cells){
		var row = document.createElement("tr");
		Each(cells, function(o){
			var cell = document.createElement("td");
			if(typeof o == "string"){ cell.innerHTML = o;}else{ cell.appendChild(o);}
			row.appendChild(cell);
		});
		oFragment.appendChild(row);
	});
	//ie的table不支持innerHTML所以这样清空table
	while(FileList.hasChildNodes()){ FileList.removeChild(FileList.firstChild); }
	FileList.appendChild(oFragment);
}

$$("idLimit").innerHTML = fu.Limit == 0 ? "任意多" : fu.Limit;

$$("idExt").innerHTML = fu.ExtIn.join("，");

$$("idBtndel").onclick = function(){ fu.Clear(); };

//在后台通过window.parent来访问主页面的函数
function Finish(msg){ 
	msg=msg.replace(/<br[^>]*>/ig,"\n").replace(/&nbsp;/ig," ");
	//alert(msg); 
	//location.href = location.href;
	//createTable();
	$$("idMsg").innerHTML = '<input type="button" value="开始上传" id="idBtnupload" onclick="upload()" disabled="disabled" />&nbsp;&nbsp;&nbsp;<input type="button" value="全部取消" id="idBtndel" disabled="disabled" />';
	fu.Folder.style.display = "";
	$$("idProcess").style.display = "none";
	fu.Clear();
	$.growlUI(msg); 
}

</script>
</html>
