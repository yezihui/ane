<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>鲁班系统登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="css/login.css" />
<link rel="shortcut icon" href="css/favicon.ico" type="image/icon" />
<script type="text/javascript" src="js/jquery.min.1.11.3.js"></script>
<script type="text/javascript" src="js/jquery.public.min.js"></script>
<script type="text/javascript" src="js/jquery.tip.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<!--[if lt IE 9]>
	<script>
		$(function(){
		    $(".center").height($(window).height() - 120);
		});
	</script>
<![endif]-->
</head>

<body>
	<div class="top">
		<img src="img/login-logo.png" />
		<ul>
			<li>首页</li>
			<li>安能官网</li>
			<li>信息门户</li>
		</ul>
	</div>
	<!-- <form action="main" method="post" name="loginForm"	id="loginForm"> -->
	<div class="center">
		<ul>
			<li class="login-title">鲁班物流管理系统</li>
			<li><div>
					<img src="img/login-user.png" /><input id="username" name="username"
						placeholder="请输入用户名" type="text" value="">
				</div>
			</li>
			<li><div>
					<img src="img/login-pwd.png" /> <input id="password" name="password"
						placeholder="请输入密码" type="password" value="">
				</div>
			</li>
			<li><input type="button" class="login-btn" id="submit"
				value="登录" />
			</li>
			<!-- 							<li class="hint">错误提示</li> -->
		</ul>
	</div>
	<!-- 	</form> -->
	<div class="bottom">安能物流 版权所有</div>
</body>
</html>
