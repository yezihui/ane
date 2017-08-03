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
<link rel="stylesheet" href="../css/basic/arbtype.css">
<%@ include file="../top.jsp"%>
<link rel="stylesheet" href="../js/validation/css/jquery.validation.css">
<script type="text/javascript" src="../js/validation/js/jquery.validation-zh_cn.js"></script>
<script type="text/javascript" src="../js/validation/js/jquery.validation.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="../js/main/dept.js"></script>
<title>仲裁类型</title>
<script type="text/javascript">
	$(function() {
		$('#info-form').validation();
	});
</script>
</head>

<body>
	<div class="type-div">
		<fieldset class="left">
			<div class="column">
				<span class="i-right" style="display: block;"></span> <span
					style="font-weight: bold;">部门展示</span>
			</div>
			<div >
				<ul class="u">
				</ul>
			</div>
		</fieldset>
		<fieldset class="right">
			<div class="column">
				<span class="i-left" style="display: block;"></span> <span
					style="font-weight: bold;">部门信息</span>
			</div>
			<div class="btn-div">
				<input type="button" class="add-btn" value="新增" /> <input
					type="button" class="gbtn update-btn" value="修改" /> <input
					type="button" class="gbtn del-btn" value="删除" /> <input
					type="button" id="sub-btn" class="gbtn sub-btn" value="保存" />
			</div>
			<form class="info-form" id="info-form">
				<div>
					<div class="input-div">
						<input type="text" name="deptId" class="infoId" style="display:none;" />
						<label>部门名称</label> <input type="text" name="deptName" id="name"
							rule="validate[required,minSize[1],maxSize[6]]" />
					</div>
					<div class="input-div">
					</div>
					<div class="input-div" style="width:408px;">
						<label>名称拼音</label> <select type="text" name="deptSpell" id="shortName" style="width:330px;">
						</select>
					</div>
					<div class="input-div">
					</div>
					<div class="input-div" >
						<label>自定义顺序</label> <input type="text" name="deptOrder" id="deptOrder"/>
					</div>
					<div class="input-div">
					</div>
					<div class="input-div" style="width:408px;">
						<label>网点类型</label> <select type="text" name="parentDeptId" style="width:330px;" >
							<option value="1">总部</option>
							<option value="2">分拨中心</option>
						</select>
					</div>
					<div class="input-div">
					</div>
					<div class="textarea-div" style="width:408px;">
						<label>部门描述</label>
						<textarea name="description" style="width:310px;"></textarea>
					</div>
				</div>
			</form>
		</fieldset>
	</div>
</body>

</html>