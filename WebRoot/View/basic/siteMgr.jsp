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
<script src="../js/util/ssq.js"></script>
<script src="../js/basic/site.js"></script>
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
								<label>网点编号</label><input type="text" name="id" />
							</div>
							<div class="input-div">
								<label>网点名称</label> <input type="text" name="name" /> <input
									type="hidden" class="wangdian" />
							</div>
							<div class="input-div">
								<label>所属网点</label><input type="text" name="orderId" /><input
									type="hidden" class="wangdian" />
							</div>
						</div>
					</div>
				</div>
				<div class="data">
					<div class="data-width">
						<table class="data-title">
							<tr>
								<th>网点名称</th>
								<th>网点拼音</th>
								<th>所在地</th>
								<th>所属网点</th>
								<th>经理名称</th>
								<th style="padding-right:16px;">经理电话</th>
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
							<label>网点名称</label> <input type="text" name="name" id="name"></input>
						</div>
						<div class="input-div">
							<label>网点拼音</label> <select name="shortName" id="shortName">
							</select>
						</div>
						
						<div class="input-div">
							<label>网点类型</label> <select name="siteType" id="siteType">
								<option value="0">总部</option>
								<option value="1">一级分拨中心</option>
								<option value="2">二级分拨中心</option>
								<option value="3">直营网点</option>
							</select>
						</div>
						<div class="input-div">
							 <input type="hidden" name="id" id="sid"></input>
						</div>
						<div class="input-div">
							
						</div>
						<div class="input-div">
							<label>网点状态</label> <select name="siteState" id="siteState">
								<option value="0">正常</option>
								<option value="1">暂停</option>
								<option value="2">时效</option>
							</select>
						</div>
						<div class="input-div">
							<label>网点顺序</label> <input type="text" name="siteOrder" id="siteOrder"></input>
						</div>
						<div class="input-div">
							<label>所属网点</label> <select name="orderId" id="orderId"><option value="0">总部</option></select>
						</div>
					</div>
					<div class="condition-title">
						<span>地址信息</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
							<label>所属国家</label> <select name="country" id="country">
							<option value="0">国家</option>
							</select>
						</div>
						<div class="input-div">
							<label>所属省份</label> <select name="province" id="province"></select>
						</div>
						<div class="input-div">
							<label>所属城市</label> <select name="city" id="city"></select>
						</div>
						<div class="input-div">
							<label>所属区域</label> <select name="region" id="area"></select>
						</div>
						<div class="input-div" style="width:1050px;">
							<label>详细地址</label> <input type="text" style="width:932px;" name="address"/>
						</div>
					</div>
					<div class="condition-title">
						<span>财务信息和联系信息</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
							<label>经理名称</label> <input type="text" name="headName" id="headName"></input>
						</div>
						<div class="input-div">
							<label>经理电话</label> <input type="text" name="headPhone" id="headPhone"></input>
						</div>
						<div class="input-div">
							<label>开户行</label> <input type="text" name="bank" id="bank"></input>
						</div>
						<div class="input-div">
							<label>开户名</label> <input type="text" name="bankName" id="bankName"></input>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>