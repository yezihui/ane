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
<title>责任类型</title>
<script src="../js/selectQuery.js"></script>
<link rel="stylesheet" href="../css/zxcl-info.css" />
<%@ include file="../top.jsp"%>
<link rel="stylesheet" href="../js/validation/css/jquery.validation.css">
<script type="text/javascript" src="../js/validation/js/jquery.validation-zh_cn.js"></script>
<script type="text/javascript" src="../js/validation/js/jquery.validation.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../js/arbitration/owner.js"></script>
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
		<!-- <li>中心差错处理</li> -->
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
								<label>申报类型</label><select id="type" name="arbitrationType"><option
										value="0">全部</option>
								</select>
							</div>
							<div class="input-div">
								<label>责任类型</label> <input type="text" name="name" /> <input
									type="hidden" class="wangdian" />
							</div>
						</div>
					</div>
				</div>
				<div class="data">
					<div class="data-width">
						<table class="data-title">
							<tr>
								<th>责任类型编号</th>
								<th>责任类型名称</th>
								<th>责任类型拼音</th>
								<th>自定义顺序</th>
								<th style="padding-right:16px;">仲裁类型</th>
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
				<div>
					<div class="input-div">
						<input type="text" name="id" class="infoId" style="display:none;" />
						<label>责任类型编号</label> <input type="text" name="code" />
					</div>
					<div class="input-div">
						<label>自定义顺序</label> <input type="text" name="order" id="order" />
					</div>
					
					<div class="input-div">
					</div>
					<div class="input-div">
					</div>
					<div class="input-div">
						<label>责任类型名称</label> <input type="text" name="name" id="name" />
					</div>
					<div class="input-div" style="width:288px;">
						<label>名称拼音</label> <select type="text" name="letters" id="shortName" style="width:200px;">
						</select>
					</div>
					<div class="input-div">
						<label>所属类型</label> <select name="arbitrationType" id="typeName"></select>
					</div>
					<div class="textarea-div">
						<label>类型描述</label>
						<textarea name="remark" style="width: 942px;"></textarea>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>