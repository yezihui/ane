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
<script src="../js/arbitration/arbtype.js"></script>
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
				<input type="button" class="add-btn" value="新增" /> <input
					type="button" class=" update-btn" value="修改" /> <input
					type="button" class=" del-btn" value="删除" /> <input
					type="button" id="sub-btn" class=" sub-btn" value="保存" />
			</div>
			<form class="info-form" id="info-form">
				<div>
					<div class="input-div">
						<input type="text" name="id" class="infoId" style="display:none;" />
						<label>仲裁类型编号</label> <input type="text" name="code"
							rule="validate[required,minSize[1],maxSize[4]]" />
					</div>
					<div class="input-div">
					</div>
					<div class="input-div">
						<label>仲裁类型名称</label> <input type="text" name="name" id="name"
							rule="validate[required,minSize[1],maxSize[6]]" />
					</div>
					<div class="input-div">
					</div>
					<div class="input-div" style="width:408px;">
						<label>名称拼音</label> <select type="text" name="pinyin" id="shortName" style="width:330px;">
						</select>
					</div>
					<div class="input-div">
					</div>
					<div class="input-div">
						<label>自定义顺序</label> <input type="text" name="order" id="order"/>
					</div>
					<div class="input-div">
					</div>
					<div class="other-check">
						<div>
							<input type="checkbox" id="pay" name="isPayFor" value="false" /><span>是否理赔</span>
						</div>
					</div>
					<div class="textarea-div">
						<label>类型描述</label>
						<textarea name="description"></textarea>
					</div>

				</div>
			</form>
		</fieldset>
	</div>
</body>

</html>