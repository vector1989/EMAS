<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE jsp:include PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.raised{background:transparent; float: left; margin: 10px;}
.raised h1,.raised p{margin:0 10px;}
.raised h1{font-size:1.5em;color:#fff;}
.raised p{padding-bottom:0.5em;}
.raised .b1,.raised .b2,.raised .b3,.raised .b4,.raised .b1b,.raised .b2b,.raised .b3b,.raised .b4b{display:block;overflow:hidden;font-size:1px;}
.raised .b1,.raised .b2,.raised .b3,.raised .b1b,.raised .b2b,.raised .b3b{height:1px;}
.raised .b2{background:#ccc;border-left:1px solid #fff;border-right:1px solid #eee;}
.raised .b3{background:#ccc;border-left:1px solid #fff;border-right:1px solid #ddd;}
.raised .b4{background:#ccc;border-left:1px solid #fff;border-right:1px solid #aaa;}
.raised .b4b{background:#ccc;border-left:1px solid #eee;border-right:1px solid #999;}
.raised .b3b{background:#ccc;border-left:1px solid #ddd;border-right:1px solid #999;}
.raised .b2b{background:#ccc;border-left:1px solid #aaa;border-right:1px solid #999;}
.raised .b1{margin:0 5px;background:#fff;}
.raised .b2, .raised .b2b{margin:0 3px;border-width:0 2px;}
.raised .b3, .raised .b3b{margin:0 2px;}
.raised .b4, .raised .b4b{height:2px; margin:0 1px;}
.raised .b1b{margin:0 5px; background:#999;}
.raised .boxcontentRed{display:block;background:red;border-left:1px solid #fff;border-right:1px solid #999;height: 100px; width:200px; vertical-align: middle; text-align: center;padding: 3px;display: table-cell;}
.raised .boxcontentBlue{display:block;background:blue;border-left:1px solid #fff;border-right:1px solid #999;height: 100px; width:200px; vertical-align: middle; text-align: center;padding: 3px;display: table-cell;}
.raised .boxcontent{display:block;background:#ccc;border-left:1px solid #fff;border-right:1px solid #999;height: 100px; width:200px; vertical-align: middle; text-align: center;padding: 3px;display: table-cell;}
.raised .raisedcontent{display:block;background:#ccc;border-left:1px solid #fff;border-right:1px solid #999;height: 50px; vertical-align: middle; text-align: left;padding: 3px;}
</style>
<title>监控</title>
<%
	List<String> nodes = new ArrayList<String>();
	for(int i=0;i<61;i++){
		nodes.add("公司---" +i);
	}
	request.setAttribute("nodes", nodes);
%>
</head>
<body>
	<div class="content" class="table" style="margin-left: auto;margin-right: auto;">
		<div class="nav" id="nav_logo">监控</div>
		<div id="data">
			<div>
				<c:forEach var="n" items="${nodes}" varStatus="statusIndex">
					<div class="raised">
						<b class="b1"></b><b class="b2"></b><b class="b3"></b><b class="b4"></b>
						<div class="${statusIndex.index%6==0?'boxcontent':(statusIndex.index%7==0?'boxcontentRed':'boxcontentBlue')}">
							<h1><a title="详细信息">${n}</a></h1>
						</div>
						<b class="b4b"></b><b class="b3b"></b><b class="b2b"></b><b class="b1b"></b>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>