<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <%@ include file="../top.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>网点发件</title>
	<link rel="stylesheet" href="../css/zxcl-info.css" />
	<script type="text/javascript" src="../js/jquery.tip.min.js"></script>
	<script src="../js/order/srOrder.js"></script>
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
			<input type="button" class="sub-btn" value="发件"/>
		</div>
		<div class="type-content">
			<div class="list windiv" style="display:block;">
			<form class="condition-form">
				<div class="condition">
					<div class="condition-title">
						<img class="t-img" src="../img/shezhi.png" /> <span>发件信息</span>
						<img class="o-img" src="../img/arrowtop.png" />
					</div>
					<div class="condition-div">
						<div class="condition-right">
							<div class="input-div">
								<label>输入该运单号</label> <input type="text" name="ewbNo" id="ewbNo" /> 
							</div>
							<div class="input-div">
							</div>
							<div class="input-div">
							</div>
							<div class="input-div">
								
							</div>
							<div class="input-div">
								<label>输入下一网点</label> <input type="text" name="siteName" id="siteName"/> 
							</div>
						</div>
					</div>
				</div>
				<div class="data">
					<div class="data-width">
						<table class="data-title">
							<tr>
								<th style="padding-right:16px;">发件信息(请双击信息上传)</th>
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