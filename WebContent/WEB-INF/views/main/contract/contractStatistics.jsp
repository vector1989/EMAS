<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery.form.js" charset="utf-8"></script>
<script type="text/javascript" src="${rc.contextPath}/js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/utils/loading.js"></script>
<link href="${rc.contextPath}/style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	$(".li_div").bind("click",function(){
		 $("#box_left").find('div').removeClass();
		 $("#box_left").find('div').addClass('li_div');
		 $(this).removeClass();
		 $(this).addClass('liClick');
		 $("#box").find(".box_right").css("display","none");
		 $("#"+$(this).attr("id")+"_statistics").css("display","block");
		 if($(this).attr("id") == "contract"){
			 statisType('year');
		 }else{
			 statisType('company');
		 }
	 });
	$(".li_div:first").addClass('liClick');
	$.ajax({
		url:'getBranch',
		type:'post',
		dataType:'json',
		success:function(data){
			var html = '<option value="0">请选择....</option>';
			$.each(data.branchs,function(i,branch){
				html += '<option value="'+ branch.id +'">'+ branch.fname +'</option>';
			});
			$("select[name='branchid']").html(html);
		},
		error:function(msg){
			$.growlUI("分公司数据加载失败！");
		}
	});
	statisType('year');
});	

function statisType(statisType){
	$("div[class='select_term']").css("display","none");
	$("#"+statisType+"Type").css("display","");
	$("#statisType").val(statisType);
}

function gennerationStatisticsChart(chartType){
	var statisType = "statisType="+$("#statisType").val();//统计方式
	var branchid = "";
	if($("#statisType").val()!="company"){
		branchid += "&branchid="+$("#"+$("#statisType").val()+"branchid").val();//分公司id
	}
	var startYear = "&startYear="+$("#"+$("#statisType").val()+"startYear").val();//开始年份
	var endYear = "&endYear="+$("#"+$("#statisType").val()+"endYear").val();//结束年份
	var chooseYear = "&chooseYear="+$("#chooseYear").val();//选择年份
	var startMonth = "&startMonth="+$("#startMonth").val();//开始月份
	var endMonth = "&endMonth="+$("#endMonth").val();//结束月份
	var ctype = "&chartType="+chartType;//生成方式
	var src = '<c:url value="gennerationStatisticsChart?'+ statisType+branchid+startYear+endYear+chooseYear+startMonth+endMonth+ctype +'" />';
	if($("#statisType").val()!="company"&&$("#statisType").val()!="adv"){
		$("#contractStatistics_img").attr('src',src);
	}else{
		$("#financeStatistics_img").attr('src',src);
	}
}
</script>
<style type="text/css">
<!--
*{margin:0;padding:0;}
body,html{height:100%;}
#box{width:100%;height:100%;padding-left:5px;}
#box_left{width:10%;height:100%;float:left;}
.li_div{position:relative;z-index:2000px;width:100%;height:30px;vertical-align: middle;line-height:30px;text-align:center;border:1px solid #ccc;background-color:#D5D5D5;cursor:pointer;margin-top:7px;font-size:12px;}
.li_div:hover{background-color:#F7F7F7;}
.liClick{position:relative;z-index:2000px;width:100%;height:30px;vertical-align: middle;line-height:30px;text-align:center;border:1px solid #ccc;background-color:#ffffff;margin-top:7px;font-size:12px;border-right-width: 0;}

.box_right{
	float:left;
	width:89%;
	height:100%;
	border:0;
	border-left:1px solid #ccc;
	background-color:#ffffff;
	padding-left:8px;
	z-index:20px;
	margin-left:-1px;
}

.seStyle{border:1px solid #425f99;width:200px;height:25px;vertical-align:middle;line-height:25px;text-align:center;cursor: pointer;color:#09387F;font-family: '宋体';}
.tr{background-color:#add2da;text-align:center;}
.tr2:hover{background-color:#FDF5E6;}
.imgBtn{color:#05080C;background-image: url("${rc.contextPath}/images/botton_125.png");width:235px;height:59px;border:0px;cursor:pointer;}
.imgBtn:hover{color:#05080C;background-image: url("${rc.contextPath}/images/botton_126.png");width:235px;height:59px;border:0px;cursor:pointer;}	
.select_term{width:99%;height:35px;vertical-align: middle;line-height:30px;display:table-cell;}
#fixed_div{width:100px;height:200px;position:fixed;right:0;top:20px; }
#fixed_div img{cursor:pointer;}
-->
</style>
</head>
<body>
<div style="height:97%;width:99%;">
	<div id="box">
		<input id="statisType" name="statisType" type="hidden" />
		<div id="box_left">
			<div id="contract" class="li_div">合同统计</div>
			<div id="finance" class="li_div">金融统计</div>
		</div>
		<div id="contract_statistics" class="box_right">
		<div class="tools">
			<a class="a" href="javascript:void(0);" onclick="statisType('year')">
				<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
				<b>按年份统计&nbsp;</b>
			</a>
			<a class="a" href="javascript:void(0);" onclick="statisType('month')">
				<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
				<b>按月份统计&nbsp;</b>
			</a>
		</div>
		<div class="select_term" id="yearType">
			<form id="yearForm" action="gennerationStatisticsChart">
				分公司：<select id="yearbranchid" name="branchid" style="border:1px solid #ccc;background:#ffffff;width:100px;font-size:15px;">
				</select>
				开始年份：<input style="width: 70px" type="text" class="Wdate" id="yearstartYear" name="yearstartYear" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
				截止年份：<input style="width: 70px" type="text" class="Wdate" id="yearendYear" name="yearendYear" onclick="WdatePicker({dateFmt:'yyyy',minDate:'#F{$dp.$D(\'yearstartYear\')}'})" readonly="readonly" />
				<input name="submitbutton" value="数据统计" id="submitbutton" type="button" onclick="gennerationStatisticsChart('bar')">
			</form>
		</div>
		<div class="select_term" id="monthType">
			<form id="monthForm" action="gennerationStatisticsChart">
				分公司：<select id="monthbranchid" name="branchid" style="border:1px solid #ccc;background:#ffffff;width:100px;font-size:15px;">
				</select>
				选择年份：<input style="width: 70px" type="text" class="Wdate" id="chooseYear" name="chooseYear" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
				开始月份：<input style="width: 50px" type="text" class="Wdate" id="startMonth" name="startMonth" onclick="WdatePicker({dateFmt:'MM'})" readonly="readonly" />
				截止月份：<input style="width: 50px" type="text" class="Wdate" id="endMonth" name="endMonth" onclick="WdatePicker({dateFmt:'MM',minDate:'#F{$dp.$D(\'startMonth\')}'})" readonly="readonly" />
				<input name="submitbutton" value="数据统计" id="submitbutton" type="button" onclick="gennerationStatisticsChart('bar')">
			</form>
		</div>
		<img id="contractStatistics_img" src="<c:url value="gennerationStatisticsChart?statisType=year&chartType=bar" />" onerror="this.src = '${rc.contextPath }/images/preload.gif'"/>
		</div>
		<div id="finance_statistics" style="display:none;" class="box_right">
			<div class="tools">
				<a class="a" href="javascript:void(0);" onclick="statisType('company')">
					<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
					<b>按分公司统计&nbsp;</b>
				</a>
				<a class="a" href="javascript:void(0);" onclick="statisType('adv')">
					<img src="${rc.contextPath }/images/m.gif" width="20" height="20">
					<b>按广告位统计&nbsp;</b>
				</a>
			</div>
			<div class="select_term" id="companyType">
				<form id="companyForm" action="gennerationStatisticsChart">
					开始年份：<input style="width: 70px" type="text" class="Wdate" id="companystartYear" name="companystartYear" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
					截止年份：<input style="width: 70px" type="text" class="Wdate" id="companyendYear" name="companyendYear" onclick="WdatePicker({dateFmt:'yyyy',minDate:'#F{$dp.$D(\'companystartYear\')}'})" readonly="readonly" />
					<input name="submitbutton" value="数据统计" id="submitbutton" type="button" onclick="gennerationStatisticsChart('bar')">
				</form>
			</div>
			<div class="select_term" id="advType">
				<form id="advForm" action="gennerationStatisticsChart">
					分公司：<select id="advbranchid" name="branchid" style="border:1px solid #ccc;background:#ffffff;width:100px;font-size:15px;">
					</select>
					开始年份：<input style="width: 70px" type="text" class="Wdate" id="advstartYear" name="advstartYear" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
					截止年份：<input style="width: 70px" type="text" class="Wdate" id="advendYear" name="advendYear" onclick="WdatePicker({dateFmt:'yyyy',minDate:'#F{$dp.$D(\'advstartYear\')}'})" readonly="readonly" />
					<input name="submitbutton" value="数据统计" id="submitbutton" type="button" onclick="gennerationStatisticsChart('bar')">
				</form>
			</div>
			<img id="financeStatistics_img" src="<c:url value="gennerationStatisticsChart?statisType=company&chartType=bar" />" onerror="this.src = '${rc.contextPath }/images/preload.gif'"/>
		</div>
		<div id="fixed_div">
			<img title="柱状图" src="${rc.contextPath }/images/bar.png" onclick="gennerationStatisticsChart('bar')" /><Br/><Br/>
			<img title="饼状图" src="${rc.contextPath }/images/Colourful_Chart_002.png" onclick="gennerationStatisticsChart('pie')"/>
		</div>
		<div style="clear:both;"></div>
	</div>
</div>	
<br/>
</body>
</html>