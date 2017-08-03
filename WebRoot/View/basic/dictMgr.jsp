<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" href="../css/arbitration/arbtype.css" />
<%@ include file="../top.jsp"%>
<link rel="stylesheet" href="../js/validation/css/jquery.validation.css">
<script type="text/javascript" src="../js/validation/js/jquery.validation-zh_cn.js"></script>
<script type="text/javascript" src="../js/validation/js/jquery.validation.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../js/basic/dict.js"></script>
<title>仲裁类型</title>
<script type="text/javascript">
	$(function() {
		$('#info-form').validation();
	});
</script>
<style>
	
.type-div .left{
				float:left;
				width: 220px;
				border:1px solid #C9C9C9;
				margin:10px;
				min-height:610px;
				background:#FFF;
				overflow-y:auto;
				overflow:hidden;!important
			}
	</style>
</head>

<body>
	<div class="type-div">
		<fieldset class="left">
			<div class="column">
				<span class="i-right" style="display: block;"></span> <span
					style="font-weight: bold;">问题类型</span>
			</div>
			<div class="u">
				<ul>
				</ul>
			</div>
		</fieldset>
		<fieldset class="right">
			<div class="column">
				<span class="i-left" style="display: block;"></span> <span
					style="font-weight: bold;">问题管理</span>
			</div>
			<div class="btn-div">
				<input type="button" class="ad-btn" value="新增" /> <input
					type="button" class=" update-btn" value="修改" /> <input
					type="button" class=" del-btn" value="删除" /> <input
					type="button" id="sub-btn" class=" sub-btn" value="保存" />
			</div>
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
								<label>字典名称</label> <input type="text" name="name" id="name"/>
								<input type="text" name="typeId" hidden="hidden"/>
								<input type="text" name="id" id="id" hidden="hidden"/>
							</div>
							<div class="input-div">
								<label>字典拼音</label> <select type="text" name="value" id="py"  /> 
							</div>
							<div class="input-div">
								<label>字典备注</label> <input type="text" name="remark1" /> 
							</div>
							<div class="input-div">
								<label>字典备注</label> <input type="text" name="remark" /> 
							</div>
						</div>
					</div>
				</div>
				<div class="data">
					<div class="data-width">
						<table class="data-title">
							<tr>
								<th>字典名称</th>
								<th>字典简拼</th>
								<th>自定义顺序</th>
								<th>字典备注</th>
								<th>创建人</th>
								<th style="padding-right:16px;">创建时间</th>
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
		</fieldset>
	</div>
</body>

</html>