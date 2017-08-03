<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <%@ include file="../top.jsp"%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>退件确认</title>
	<link rel="stylesheet" href="../css/zxcl-info.css" />
	<script src="../js/basic/mgr.js"></script>
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
    height: 70px;
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
			<input type="button" class="confirm-btn" value="确认"/>
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
								<div class="input-div">
								</div>
								<div class="input-div">
								</div>
								<div class="input-div">
									<label>申请网点</label>
									<input type="text" name="applySiteName"/>
								</div>
								<div class="input-div">
									<label>受理类型</label> 
									<select name="acceptStatus" id="acceptStatus">
										<option value="0">未受理</option>
										<option value="1">已受理</option>
									</select>
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
									<th>受理状态</th>
									<th>申请网点</th>
									<th>申请时间 </th>
									<th>通知网点</th>
									<th>受理网点</th>
									<th>受理人</th>
									<th style="padding-right:16px;">受理时间</th>
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
							<span>当前第&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;页、
							共&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;页</span>
							<a href="javascript:void(0);">下一页</a>
							<a href="javascript:void(0);">尾页</a> 
						</div>
					</div>
				</form>
			</div>
			<div class="info windiv">
				<form class="info-form">
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
	</body>
</html>