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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>人员分配比率</title>
<link rel="stylesheet" href="../css/zxcl-info.css" />
<script src="../js/arbitration/assign.js"></script>
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
		<input type="button" class="sel-btn" value="查询" /> <input
			type="button" class="add-btn" value="新增" /> <input
			type="button" class="gbtn update-btn" value="修改" disabled="disabled" />
			 <input type="button"
			class="gbtn del-btn" value="删除" disabled="disabled" /> 
		<input type="button" class="gbtn sub-btn" value="保存"
			disabled="disabled" />
	</div>
	<ul class="type-tab">
		<li class="sel-tabtype">数据列表</li>
		<li>详细信息</li>
		<!-- <li>中心差错处理</li> -->
	</ul>
	<div class="type-content">
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
							<label>仲裁中心</label><select name="center"><option value="1">安能物流</option></select>
						</div>
						<div class="input-div">
							<label>仲裁类型</label><select name="types" id="type"></select>
						</div>
						<div class="input-div">
							<label>申报方所属省份</label><select name="applyAddresses" id="applyAddresses"></select>
						</div>
						<div class="input-div">
							<label>责任方所属省份</label><select name="ownerAddresses" id="ownerAddresses"></select>
						</div>
						</div>
					</div>
				</div>
				<div class="data">
					<div class="data-width">
						<table class="data-title">
							<tr>
								<th>编号</th>
								<th>仲裁人员</th>
								<th>申报方所属省份</th>
								<th>仲裁类型</th>
								<th>仲裁比例</th>
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
		<div class="info windiv">
			<form class="info-form">
				<div>
					<div class="input-div">
						<input type="text" name="id" class="infoId" style="display:none;" />
						<label>仲裁中心</label><select name="center" class="center"><option value="1">安能物流</option></select>
					</div>
					<div class="input-div">
						<label>仲裁人员</label> <select name="ownerId" id="ownerId"><option></option></select>
					</div>
					<div class="input-div">
						<label>仲裁方式</label> <select name="ownerType" class="ownerType"><option></option><option value="1">申报方所属省份</option><option value="2">责任方所属省份</option></select>
					</div>
					<div class="input-div">
						<label>分配比率(%)</label> <input type="text" name="rate" class="rate"/>
					</div>
					<div class="textarea-div">
						<label>仲裁省份</label>
						<div  class="textarea-div" style="width:166px; height:100px; overflow:auto; border:1px solid #c9c9c9;background: #fbfbfb none repeat scroll 0 0;">
					    	<ul class="regionlist">
					    		<li>
					    			<input type="checkbox" name="applyAddresses"  value="0" style="width:20px;" id="regionall"/> 全国
						    	</li>
					    		
						    </ul>
						</div>
					</div>
					<div class="textarea-div">
						<label>仲裁类型</label>
						<div  class="textarea-div" style="width:166px; height:100px; overflow:auto; border:1px solid #c9c9c9;background: #fbfbfb none repeat scroll 0 0;">
					    	<ul class="typelist">
					    		<li>
					    			<input type="checkbox" name="applyType"  value="0" style="width:20px;" id="typeall"/> 全部
						    	</li>
					    		
						    </ul>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>