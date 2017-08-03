<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../top.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>下单</title>
<link rel="stylesheet" href="../css/zxcl-info.css" />
<script src="../js/selectQuery.js"></script>
<script src="../js/list-demo.js"></script>
<script src="../js/selectQuery.js"></script>
<script src="../js/util/dict.js"></script>
<script type="text/javascript" src="../js/jquery.tip.min.js"></script>
<script src="../js/util/ssq.js"></script>
<script src="../js/util/ssq1.js"></script>
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
			type="button"  id="sub-btn" value="保存"  />
	</div>
	<div class="type-content">
		<div class="info windiv">
			<form class="info-form">
				<div class="condition">
					<div class="condition-title">
						<span>运单信息</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
							<label>产品类型</label> <select name="produceTypeId" id="typeName"></select>
						</div>
						<div class="input-div">
							<label>服务方式</label> <select name="serviceTypeId" id="serviceType"></select>
						</div>
						<div class="input-div">
							<label>寄件日期</label> <input type="text" name="sendDate" id="sendDate" class="laydate-icon" onclick="laydate({format:'YYYY-MM-DD hh:mm:ss',istime:true})"/>
						</div>
					</div>
					<div class="condition-title">
						<span>寄件信息</span>
					</div>
					<div class="condition-div">
						<div class="input-div">
							<label>省份</label> <select name="p" id="p"></select>
						</div>
						<div class="input-div">
							<label>城市</label> <select name="c" id="c"></select>
						</div>
						<div class="input-div">
							<label>区县</label> <select name="a" id="a"></select>
						</div>
						<div class="input-div">
						</div>
						<div class="input-div">
							<label>寄件人</label> <input type="text" name="sendCustomer"/>
						</div>
						<div class="input-div">
							<label>寄件手机</label> <input type="text" name="sendPhone"/>
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
							<label>省份</label> <select name="province" id="province"></select>
						</div>
						<div class="input-div">
							<label>城市</label> <select name="city" id="city"></select>
						</div>
						<div class="input-div">
							<label>区县</label> <select name="area" id="area"></select>
						</div>
						<div class="input-div">
						</div>
						<div class="input-div">
							<label>收件人</label> <input type="text" name="receiveCustomer"/>
						</div>
						<div class="input-div">
							<label>收件手机</label> <input type="text" name="receivePhone"/>
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
							<label>支付类型</label> <select name="payModeId" id="payType"></select>
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
	</div>
</body>
</html>