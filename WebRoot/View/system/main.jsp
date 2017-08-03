<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>鲁班系统</title>
    <!-- head top file end -->
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/main.css" />
    <!-- head top file end -->
    <script src="js/jquery.min.1.11.3.js"></script>
    <!-- mean tab js -->
    <script src="js/jquery.index.min.js"></script>
	<!--[if lt IE 9]>
		<script>
			$(function(){
			    $(".left").add(".right").height($(window).height() - 80);
				$(".right").width($(window).width() - 240);
				$(".right-tab>div").width($(window).width() - 308);
				$(".right-count iframe").height($(window).height() - 112);
			});	
		</script>
	<![endif]-->
  </head>
   <body>
		<div class="top">
			<image src="img/logo.png"/>
			<span>鲁班系统</span>
			<div>
				<span>登录员工：【${sessionScope.loginUserName }】 登录网点：【${sessionScope.loginSiteName }】</span>
				<a href="View/login.jsp">退出</a>
			</div>
		</div>
		<div class="left">
			<ul class="menu-ul">
			   
				<li>
					<div>
						<img class="a-img" src="img/shezhi.png"/>
						<span>基础数据管理</span>
					</div>
					<ul>
						<li class="a-title"  url="basic/siteMgr">
							<span>网点管理</span>
						</li>
						<li class="a-title"  url="basic/deptMgr">
							<span>部门查询</span>
						</li>
						<li class="a-title"  url="basic/employeeMgr">
							<span>员工管理</span>
						</li>
						<li class="a-title"  url="basic/userMgr">
							<span>用户管理</span>
						</li>
						<li class="a-title"  url="basic/dictMgr">
							<span>数据字典</span>
						</li>
					</ul>
				</li>
				 <li>
					<div>
						<img class="a-img" src="img/shezhi.png"/>
						<span>运单管理</span>
					</div>
					<ul>
						<li class="a-title"  url="order/follow">
							<span>快件跟踪</span>
						</li>
						<li class="a-title"  url="order/siteOrderList">
							<span>客户下单</span>
						</li>
						<li class="a-title"  url="order/orderOrder">
							<span>网点接单</span>
						</li>
						<li class="a-title"  url="order/sendOrder">
							<span>网点发件</span>
						</li>
						<li class="a-title"  url="order/receiveOrder">
							<span>网点到件</span>
						</li>
						<li class="a-title"  url="order/dispatch">
							<span>网点派送</span>
						</li>
						<li class="a-title"  url="order/sign">
							<span>客户签收</span>
						</li>
						<li class="a-title"  url="return/apply">
							<span>退件申请</span>
						</li>
						<li class="a-title"  url="return/confirm">
							<span>退件管理</span>
						</li>
					</ul>
					
				</li>
				<li>
					<div>
						<img class="a-img" src="img/shezhi.png"/>
						<span>仲裁</span>
					</div>
					<ul>
						<li class="a-title"  url="arb/type">
							<span>仲裁类型</span>
						</li>
						<li class="a-title" url="arb/owner">
							<span>责任类型</span>
						</li>
						<li class="a-title" url="arb/assign">
							<span>人员分配比率</span>
						</li>
						<li class="a-title"  url="arb/declare">
							<span>网点申报</span>
						</li>
						<li class="a-title"  url="arb/apply">
							<span>申报查询</span>
						</li>
						<li class="a-title" url="arb/arbOrder">
							<span>中心接单</span>
						</li>
						<li class="a-title" url="arb/center">
							<span>中心处理</span>
						</li>
						<li class="a-title" url="arb/audit">
							<span>中心审核</span>
						</li>
						<li class="a-title"  url="arb/search">
							<span>仲裁查询</span>
						</li>
					</ul>
				</li>
				<li>
					<div>
						<img class="a-img" src="img/shezhi.png"/>
						<span>理赔</span>
					</div>
					<ul>
						<li class="a-title"  url="claim/declare">
							<span>网点理赔申报</span>
						</li>
						<li class="a-title" url="claim/search">
							<span>理赔查询</span>
						</li>
						<li class="a-title" url="claim/apply">
							<span>理赔中心处理</span>
						</li>
						<li class="a-title"  url="claim/audit">
							<span>理赔审核</span>
						</li>
						<li class="a-title" url="claim/confirm">
							<span>理赔确认</span>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="right">
			<div class="right-tab">
				<image style="border-left:none;" src="img/tableft.png" title="左移动"/>
				<div>
					<ul class="tab-title">
						<li class="sel-tabtitle">首页</li>
					</ul>
				</div>
				<image style="border-right:none;" src="img/tabright.png" title="右移动"/>
			</div>
			<div class="right-count">
				<iframe frameborder="no" src="list"></iframe>
			</div>
		</div>
		<div class="bottom">Copyright © 2015-2025 上海安能聚创供应链管理有限公司. All rights reserved.</div>
	</body>
</html>
