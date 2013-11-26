<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh_cn" class="no-js">
<head>
<meta charset="UTF-8" />
<title>EPG广告分级管理系统</title>
<link rel="shortcut icon" href="${rc.contextPath}/images/epg.ico">
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/style/login.css" />
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/style/style.css" />
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/style/animate-custom.css" />
</head>
<body>
	<div class="container">
		<header>
			<h1>
				<span>EPG广告分级管理系统</span>
			</h1>
		</header>
		<section>
			<div id="container_demo">
				<div id="wrapper">
					<div id="login" class="animate form">
						<form action="${rc.contextPath}/" autocomplete="on" method="post">
							<h1>Log in</h1>
							<p>
								<label for="username" class="uname" data-icon="u">用户名</label>
								<input id="username" name="fusername" required="required" type="text" placeholder="请输入用户名" />
							</p>
							<p>
								<label for="password" class="youpasswd" data-icon="p">密码</label>
								<input id="password" name="fpassword" required="required" type="password" placeholder="请输入密码" />
							</p>
							<p style="color: red; text-align: center;">${error}</p>
							<p class="login button">
								<input type="submit" value="Login" />
							</p>
						</form>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>