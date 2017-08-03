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
	<script src="../js/util/time.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>中心接单</title>
	<link rel="stylesheet" href="../css/zxcl-info.css" />
	<script src="../js/arbitration/order.js"></script>
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
			<input type="button" class="sel-btn" value="查询"/>
			<input type="button" class="order-btn" value="接单"/>
		</div>
		<ul class="type-tab">
			<li class="sel-tabtype">数据列表</li>
			<li>详细信息</li>
		</ul>
		<div class="type-content">
			<div class="list windiv" style="display:block;">
				<form class="condition-form">
					<div class="condition">
						<div class="condition-title">
							<img class="t-img" src="../img/shezhi.png"/>
							<span>查询条件</span>
							<img class="o-img" src="../img/arrowtop.png"/>
						</div>
						<div class="condition-div">
							<div class="condition-left">
								<input type="checkbox" class="chk" value="true" name="chk"/>
								<label>按运单号查询</label>
								<input type="button" class="empty" value="清空" style="margin-left:25px;" />
								<input type="text" name="id" class="infoId" style="display:none;" value="1"/>
								<textarea class="num-text" name="ewbNo"></textarea>
							</div>
							<div class="condition-right">
								<div class="day input-div">
									<label>天数</label>
									<div>
										<input type="radio" name="dayTime" value="1"/><span>1天</span>
										<input type="radio" name="dayTime" value="3" checked="checked"/><span>3天</span>
										<input type="radio" name="dayTime" value="7"/><span>7天</span>
										<input type="radio" name="dayTime" value="15"/><span>15天</span>
										<input type="radio" name="dayTime" value="30"/><span>1个月</span>
									</div>
								</div>
								<div class="input-div time">
									<label>时间</label>
									<input type="text" name="start" readonly="readonly" class="laydate-icon" onclick="laydate({format:'YYYY-MM-DD hh:mm:ss',istime:true})"/>
									<span>-</span>
									<input type="text" name="end" readonly="readonly" class="laydate-icon" onclick="laydate({format:'YYYY-MM-DD hh:mm:ss',istime:true})"/>
								</div>
								<div class="input-div">
									<label>申报类型</label>
									<select  id="type" name="applyType"></select>
								</div>
								<div class="input-div">
									<label>申报状态</label>
									<select name="status">
										<option value="1">已分中心</option>
									</select>
								</div>
								<div class="input-div">
									<label>申报网点</label>
									<input type="text" name="applySiteName"/>
									<input type="hidden" class="wangdian"/>
								</div>
							</div>
						</div>
					</div>
					<div class="data">
						<div class="data-width">
							<table class="data-title">
								<tr>
									<th><input type="checkbox" id="orderall"/></th>
									<th>运单编号</th>
									<th>申报网点</th>
									<th>申报类型</th>
									<th>申报状态</th>
									<th style="padding-right:16px;">申报时间</th>
								</tr>
							</table>
							<div class="data-count">
								<table>
								</table>
							</div>
						</div>
						<div class="page">
							<a href="javascript:void(0);">首页</a>
							<a href="javascript:void(0);">上一页</a>
							<span>当前第<input type="text" class="this-page" value="1" readonly="true" name="page"/>页、
							共<input type="text" class="all-page" value="1" readonly="true"/>页</span>
							<a href="javascript:void(0);">下一页</a>
							<a href="javascript:void(0);">尾页</a> 
						</div>
					</div>
				</form>
			</div>
			<div class="info windiv">
				<form class="info-form">
					<div>
						<div class="input-div" >
							<input type="text" name="id" class="infoId" style="display:none;" />
							<label>运单编号</label>
							<input type="text" name="ewbNo" class="eNo" id="ewbNo"/>
						</div>
						<div class="input-div">
							<label>寄件日期</label>
							<input type="text" name="sendTime" class="able"/>
						</div>
						<div class="input-div">
						</div>
						<div class="input-div">
						</div>
						<div class="input-div">
							<label>申报网点</label>
							<input type="text" name="applySiteName" class="able"/>
						</div>
						<div class="input-div">
							<label>责任网点</label>
							<select id="site" name="ownerSiteId" id="ownerSiteId"></select>
						</div>
						<div class="input-div">
							<label>申报类型</label>
							<select id="applyType" name="applyId" id="applyId"></select>
						</div>
						<div class="input-div">
						</div>
						<div class="input-div" >
							<label>索赔金额</label>
							<input type="text" name="amount" id="amount"/>
						</div>
						<div  class="input-div" >
							<label>投诉人名</label>
							<input type="text" name="complainant" id="complainant"/>
						</div>
						<div class="input-div">
							<label>诉人电话</label>
							<input type="text" name="complaintPhone" id="complaintPhone"/>
						</div>
						<div class="input-div">
							
						</div>
						<div class="input-div" >
							<label>业务类型</label>
							<input type="text" name="productType" class="able"/>
						</div>
						<div class="input-div">
							<label>签收状态</label>
							<input type="text" name="signName" class="able"/>
						</div>
						<div class="input-div">
							<label>件数</label>
							<input type="text" name="piece" class="able"/>
						</div>
						<div  class="input-div">
							<label>重量</label>
							<input type="text" name="calcWeight" class="able"/>
						</div>
						<div  class="input-div">
							<label>始发站点</label>
							<input type="text" name="beginStation" class="able"/>
						</div>
						<div  class="input-div">
							<label>目的站点</label>
							<input type="text" name="endStation" class="able"/>
						</div>
						<div class="input-div">
							<label>物品名称</label>
							<input type="text" name="productName" class="able"/>
						</div>
						<div class="input-div" >
							<label>包装类型</label>
							<input type="text" name="packType" class="able"/>
						</div>
						<div  class="input-div">
							<label>寄件人名</label>
							<input type="text" name="sender" class="able"/>
						</div>
						<div  class="input-div">
							<label>寄人电话</label>
							<input type="text" name="sendPhone" class="able"/>
						</div>
						<div  class="input-div" style="width:530px;">
							<label>寄件地址</label>
							<input type="text" style="width:410px;" name="sendAddress" class="able"/>
						</div>
						<div  class="input-div">
							<label>收件人名</label>
							<input type="text" name="recipient" class="able"/>
						</div>
						<div  class="input-div">
							<label>收件电话</label>
							<input type="text" name="recipientPhone" class="able"/>
						</div>
						<div  class="input-div" style="width:530px;">
							<label>收件地址</label>
							<input type="text" style="width:410px;" name="recipientAddress" class="able"/>
						</div>
						<div class="textarea-div" style="width:1175px;">
							<label>申报理由</label>
							<textarea name="applyReason" style="width:935px;" id="applyReason"></textarea>
						</div>
					</div>
				</form>
			</div>
			
		</div>
	</body>
</html>