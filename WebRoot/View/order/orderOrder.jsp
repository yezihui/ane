<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <%@ include file="../top.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>网点接单</title>
	<link rel="stylesheet" href="../css/zxcl-info.css" />
	<script src="../js/order/order.js"></script>
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
								<textarea class="num-text" name="orderId"></textarea>
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
								</div>
								<div class="input-div time">
									<label>时间</label>
									<input type="text" id="start" name="start" readonly="readonly" class="laydate-icon" onclick="laydate({format:'YYYY-MM-DD hh:mm:ss',istime:true})"/>
									<span>-</span>
									<input type="text" id="end" name="end" readonly="readonly" class="laydate-icon" onclick="laydate({format:'YYYY-MM-DD hh:mm:ss',istime:true})"/>
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
									<th>产品类型</th>
									<th>服务方式</th>
									<th>寄件日期 </th>
									<th>寄件人</th>
									<th>寄件地址</th>
									<th>收件人</th>
									<th>收件地址</th>
									<th>物品名称</th>
									<th>实际重量</th>
									<th>体积</th>
									<th>支付方式</th>
									<th style="padding-right:16px;">运费</th>
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
					<div class="condition">
					<div class="condition-title">
						<span>运单信息</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
							<label>产品类型</label> <input type="text" name="produceType" id="typeName"></input>
						</div>
						<div class="input-div">
							<label>服务方式</label> <input type="text" name="serviceType" id="serviceType"></input>
						</div>
						<div class="input-div">
							<label>寄件日期</label> <input type="text" name="sendDate" id="sendDate" />
						</div>
					</div>
					<div class="condition-title">
						<span>寄件信息</span>
					</div>
					<div class="condition-div">
						
						<div class="input-div">
							<label>寄件人</label> <input type="text" name="sendCustomer"/>
						</div>
						<div class="input-div">
							<label>寄件手机</label> <input type="text" name="sendPhone"/>
						</div>
						<div class="input-div">
						</div>
						<div class="input-div">
						</div>
						<div class="input-div" style="width:520px;">
							<label>寄件地址</label> <input type="text" style="width:402px;" name="sendAddress" id="sendAddress" />
						</div>
					</div>
					<div class="condition-title">
						<span>收件信息</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
							<label>收件人</label> <input type="text" name="receiveCustomer"/>
						</div>
						<div class="input-div">
							<label>收件手机</label> <input type="text" name="receivePhone"/>
						</div>
						<div class="input-div">
						</div>
						<div class="input-div">
						</div>
						<div class="input-div" style="width:520px;">
							<label>收件地址</label> <input type="text" style="width:402px;" name="receiveAddress"/>
						</div>
					</div>
					<div class="condition-title">
						<span>货物信息</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
							<label>物品名称</label> <input type="text" name="produceName"/>
						</div>
						<div class="input-div">
							<label>包装类型</label> <input type="text" name="pickName"/>
						</div>
						<div class="input-div">
						</div>
						<div class="input-div">
						</div>
						<div class="input-div">
							<label>实际重量</label> <input type="text" name="calcWeight"/>
						</div>
						<div class="input-div">
							<label>实际体积</label> <input type="text" name="vol"/>
						</div>
						<div class="input-div">
							<label>总件数</label> <input type="text" name="piece"/>
						</div>
					</div>
					<div class="condition-title">
						<span>收费信息</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
							<label>支付类型</label> <input type="text"  name="payMode" id="payType"></input>
						</div>
						<div class="input-div">
							<label>运费</label> <input type="text" name="freight"/>
						</div>
						<div class="input-div">
							<label>总金额</label> <input type="text" name="amount"/>
						</div>
					</div>
					<div class="condition-title">
						<span>备注</span>
					</div>
					<div class="condition-div">
						<div class="input-div" style="width:790px;">
							<label>备注</label> <input type="text" style="width:672px;" name="remark"/>
						</div>
					</div>
				</form>
			</div>
			
		</div>
	</body>
</html>