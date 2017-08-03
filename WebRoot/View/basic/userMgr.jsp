<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../top.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Demo</title>
<link rel="stylesheet" href="../css/zxcl-info.css" />
<link rel="stylesheet" href="../js/validation/css/jquery.validation.css">
<script type="text/javascript" src="../js/validation/js/jquery.validation-zh_cn.js"></script>
<script type="text/javascript" src="../js/validation/js/jquery.validation.min.js"></script>
<script type="text/javascript" src="../js/jquery.tip.min.js"></script>
<script src="../js/selectQuery.js"></script>
<script src="../js/main/user.js"></script>
<!--[if lt IE 9]>
		<script>
			$(function(){
			    $(".type-content").height($(window).height() - 80);
			});
		</script>
	<![endif]-->
<script type="text/javascript">
	$(function() {
		$('#info-form').validation();
		
	});
</script>
</head>
<body>
	<div class="btn-div">
		<input type="button" class="sel-btn" value="查询" /> <input
			type="button" class="add-btn cs" value="新增" /> <input type="button"
			class="gbtn update-btn" value="修改" disabled="disabled" /> <input type="button"
			class="gbtn reset-btn" value="密码重置" disabled="disabled" /><input
			type="button" class="gbtn del-btn" value="删除" disabled="disabled" />
		<input type="button" id="sub-btn" class="gbtn sub-btn" value="保存"
			disabled="disabled" />
	</div>
	<ul class="type-tab">
		<li class="sel-tabtype">数据列表</li>
		<li class="l">详细信息</li>
	</ul>
	<div class="type-content">
		<div class="list windiv" style="display:block;">
			<form class="condition-form">
				<div class="condition">
					<div class="condition-title">
						<img class="t-img" src="../img/shezhi.png" /> <span>查询条件</span>
						<img class="o-img" src="../img/arrowtop.png" />
					</div>
					<div class="condition-div">
						<div class="condition-right">
							<div class="input-div">
								<label>用户名称</label> <input type="text" name="userName" /> <input
									type="hidden" class="wangdian" />
							</div>
							<div class="input-div">
								<label>用户类型</label><select id="type" name="userType"><option
										value="0">普通用户</option>
										<option value="1">管理员</option>
								</select>
							</div>
							<div class="input-div">
								
							</div>
							<div class="input-div">
								
							</div>
							<div class="input-div">
								<label>员工编号</label> <input type="text" name="employeeId" /> 
							</div>
							<div class="input-div">
								<label>员工姓名</label> <input type="text" name="employeeName" id="employeeName" />
							</div>
							<div class="input-div">
								<label>部门名称</label> <input type="text" name="siteName" /> <input
									type="hidden" class="wangdian" />
							</div>
						</div>
					</div>
				</div>
				<div class="data">
					<div class="data-width">
						<table class="data-title">
							<tr>
								<th>用户名称</th>
								<th>员工编号</th>
								<th>员工姓名</th>
								<th>所属网点</th>
								<th>用户类型</th>
								<th>备注</th>
								<th>创建时间</th>
								<th>创建人</th>
								<th>修改人</th>
								<th style="padding-right:16px;">修改时间</th>
							</tr>
						</table>
						<div class="data-count">
							<table>
							</table>
						</div>
					</div>
					<div class="page">
						<a href="javascript:void(0);">首页</a> <a href="javascript:void(0);">上一页</a>
						<span>当前第<input type="text" class="this-page" value="1"
							readonly="true" name="page" />页、 共<input type="text"
							class="all-page" value="1" readonly="true" />页</span> <a
							href="javascript:void(0);">下一页</a> <a href="javascript:void(0);">尾页</a>
					</div>
				</div>
			</form>
		</div>
		<div class="info windiv">
			<form class="info-form" id="info-form">
				<div>
					<div class="input-div" style="width:520px;">
						<input type="text" name="userId" id="uId" style="display:none;" />
						<label>用户名称</label> <input type="text" name="userName" id="uName" style="width:402px;"
						rule="validate[required,minSize[1],maxSize[8]]" />
					</div>
					<div class="input-div" style="width:520px;">
					</div>
					<div class="input-div" style="width:520px;">
						<label>员工名称</label> 
						<select  name="employeeId" id="eId" style="width:424px;"
						rule="validate[required]" >
						</select>
					</div>
					<div class="input-div" style="width:520px;">
					</div>
					<div class="input-div" style="width:520px;">
						<label>用户类型</label><select id="userType" name="userType" style="width:424px;"><option
										value="0">普通用户</option>
										<option value="1">管理员</option>
								</select>
					</div>
					<div class="input-div" style="width:520px;">
					</div>
					<div class="input-div" style="width:520px;">
						<label>用户密码</label> <input type="text" name="userPwd" id="userPwd" style="width:402px;"
						rule="validate[required,minSize[1],maxSize[12]]" />
					</div>
					<div class="input-div" style="width:520px;">
					</div>
					<div class="input-div" style="width:520px;">
						<label>备注</label> <input type="text" style="width:402px;" name="remark"/>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>