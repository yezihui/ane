<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../top.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>退件申请</title>
<link rel="stylesheet" href="../css/zxcl-info.css" />
<script src="../js/list-demo.js"></script>
<script type="text/javascript" src="../js/jquery.tip.min.js"></script>

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
		<input type="button" class="add-btn" value="新增" /> <input
			type="button"  id="save-btn" value="保存"  />
	</div>
	<div class="type-content">
		<div class="info windiv">
			<form class="info-form">
				<div class="condition">
					<div class="condition-title">
						<span>退件申请</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
							<label>运单号</label> <input type="text" name="ewbNo" id="ewbNo"></input>
						</div>
						<div class="input-div">
							<label>通知网点</label> <input type="text" name="receiveSiteName" id="" ></input>
						</div>
						<div class="input-div">
						</div>
						<div class="input-div">
						</div>
						<div class="input-div" style="width:520px;">
							<label>退件地址</label> <input type="text" style="width:402px;" name="fullAddress" id="" />
						</div>
						<div class="textarea-div" style="width:1175px;">
							<label>退件理由</label>
							<textarea name="returnReason" style="width:935px;" id=""></textarea>
						</div>
					</div>
					<div class="condition-title">
						<span>运单信息</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
							<label>寄件日期</label> <input type="text" name="sendTime" disabled="disabled"/>
						</div>
						<div class="input-div">
							<label>寄件网点</label> <input type="text" name="sendSiteName" disabled="disabled"/>
						</div>
						<div class="input-div">
							<label>目的网点</label> <input type="text" name="receiveSiteName" disabled="disabled"/>
						</div>
						<div class="input-div">
							<label>物品名称</label> <input type="text" name="produceName" disabled="disabled"/>
						</div>
						<div class="input-div">
							<label>件数</label> <input type="text" name="piece" disabled="disabled"/>
						</div>
						<div class="input-div">
							<label>实际重量</label> <input type="text" name="calcWeight" disabled="disabled"/>
						</div>
						<div class="input-div">
							<label>支付方式</label> <input type="text" name="payMode" disabled="disabled"/>
						</div>
						<div class="input-div">
							<label>签收时间</label> <input type="text" name="update" disabled="disabled"/>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>