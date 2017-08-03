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
<script src="../js/selectQuery.js"></script>
<script src="../js/main/emp.js"></script>
<!--[if lt IE 9]>
		<script>
			$(function(){
			    $(".type-content").height($(window).height() - 80);
			});
		</script>
	<![endif]-->

</head>
<body>
	<div class="btn-div">
		<input type="button" class="sel-btn" value="查询" /> <input
			type="button" class="add-btn cs" value="新增" /> <input type="button"
			class="gbtn update-btn" value="修改" disabled="disabled" /> <input
			type="button" class="gbtn del-btn" value="删除" disabled="disabled" />
		<input type="button" class="gbtn sub-btn" value="保存"
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
								<label>员工编号</label><input type="text" name="id" value=""/>
							</div>
							<div class="input-div">
								<label>员工姓名</label> <input type="text" name="name" value="" /> 
							</div>
							<div class="input-div">
								<label>所属网点</label><select type="text" name="siteId" id="SiteId"></select>
							</div>
						</div>
					</div>
				</div>
				<div class="data">
					<div class="data-width">
						<table class="data-title">
							<tr>
								<th>员工名称</th>
								<th>员工拼音</th>
								<th>员工性别</th>
								<th>联系方式</th>
								<th>入职时间</th>
								<th>所属部门</th>
								<th>职位</th>
								<th>创建时间</th>
								<th>创建人</th>
								<th>修改时间</th>
								<th style="padding-right:16px;">修改人</th>
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
			<form class="info-form">
				<div class="condition">
					<div class="condition-title">
						<span>基本信息</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
						 	<input type="hidden" name="id" id="sid"></input>
							<label>员工名称</label> <input type="text" name="name" id="name"></input>
						</div>
						<div class="input-div">
							<label>员工拼音</label> <select name="pinyin" id="shortName">
							</select>
						</div>
						<div class="input-div">
							<label>性别</label> <select name="sex" id="sex">
							<option value="男">男</option>
							<option value="女">女</option>
							</select>
						</div>
						<div class="input-div">
						</div>
						<div class="input-div">
							<label>入职时间</label> <input type="text" name="eTime" id="entryTime" class="laydate-icon" onclick="laydate({format:'YYYY-MM-DD hh:mm:ss',istime:true})"/>
						</div>
						<div class="input-div">
							<label>联系方式</label> <input type="text" name="phone" id="phone"></input>
						</div>
						<div class="input-div">
							<label>所属部门</label> <select name="siteId" id="siteId">
							</select>
						</div>
						<div class="input-div">
							<label>所在职位</label>  <input type="text" name="position" id="position"></input>
						</div>
						<div class="textarea-div" style="width:1048px;">
							<label>员工描述</label>
							<textarea name="description" style="width:930px;"></textarea>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>