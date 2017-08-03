<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <%@ include file="../top.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>客户签收</title>
	<link rel="stylesheet" href="../css/zxcl-info.css" />
	<script type="text/javascript" src="../js/jquery.tip.min.js"></script>
	<script src="../js/order/dsOrder.js"></script>
	<script src="../js/util/time.js"></script>
	<!--[if lt IE 9]>
		<script>
			$(function(){
			    $(".type-content").height($(window).height() - 80);
			});
		</script>
	<![endif]-->
	<style>
	.condition .condition-left textarea {
    height: 30px;
    width: 190px;!important
}
.info {
    background: #fff none repeat scroll 0 0;
    height: 130%;
    overflow: hidden;!important
}
	</style>
	</head>
	<body>
		<div class="btn-div">
			<input type="button" class="sign-btn" value="签收"/>
		</div>
		<div class="type-content">
			<div class="list windiv" style="display:block;">
			<form class="condition-form">
				<div class="condition">
					<div class="condition-title">
						<img class="t-img" src="../img/shezhi.png" /> <span>签收信息</span>
						<img class="o-img" src="../img/arrowtop.png" />
					</div>
					<div class="condition-div">
						<div class="condition-right">
							<div class="input-div">
								<label>输入该运单号</label> <input type="text" name="ewbNo" id="ewbNo" class="sig"/> 
							</div>
							<div class="input-div">
							</div>
							<div class="input-div">
							</div>
							<div class="input-div">
							</div>
							<div class="input-div">
								<label>签收类型</label> 
								<select name="signType" id="signType">
									<option value="0">正常签收</option>
									<option value="1">异常签收</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="data">
					<div class="data-width">
						<table class="data-title">
							<tr>
								<th style="padding-right:16px;">签收信息(请双击信息确认签收)</th>
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
			
		</div>
	</body>
</html>