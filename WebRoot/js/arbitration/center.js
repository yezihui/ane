$(function() {
	var id = $("#type");
	id.append('<option>全部</option>')
	getArbTypeInfo(id);
	getArbTypeInfo($("#applyType"));
	getAllSite();
	//getGridView(); //初始化显示数据列表
	//数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	//$(".data-title").add(".data-count").add(".data-count table").css("min-width","2000px");//表格列表超出设置
	//清空
	$(".empty").click(function(){
		$(".num-text").val("");//清空文本域
	});
	$(".chk").click(function(){
		if ("false" == ($("#chk").val())) {
			$("#chk").val("true");
		} else {
			$("#chk").val("false")
		}
	});
	//获取最大排序值
	$(".cs").click(function(){
		getArbTypeInfo($("#applyType"));
	});
	
	//提交数据
	$(".order-btn").click(function(){//提交数据
		var obj = document.getElementsByName("oid");
		var orderData = '';
		for(var i = 0; i < obj.length; i++) {
			if(obj[i].checked)
				orderData += obj[i].value + ',';
		}
		if(orderData.length != 0) {
			orderData=orderData.substring(0, orderData.length - 1);
		}
		var arrayData = {
			"orderData": orderData
		};
		$.ajax({
			url:"{0}/arbOrder/updateArbInfo".format($.getRequestUrl()),
			type:"post",
			data:arrayData,
			dataType:"json",
			success:function(dataObj){
				alert(dataObj.msg);
				getGridView();
			},
			error:function(){}
		}); 
	});
	$("#orderall").click(function() {
		$("input[name='oid']").prop("checked", this.checked);
	});
	//仲裁
	$(".true-btn").click(function(){//提交数据
		var obj = document.getElementsByName("oid");
		var orderData = '';
		for(var i = 0; i < obj.length; i++) {
			if(obj[i].checked){
				orderData += obj[i].value + '';break;
			}
		}
		if(orderData=='')
			alert("请选中数据进行仲裁");
		else{
			findById(orderData);
			$(".arbitrationId").val(orderData);
			$(".type-tab li").eq(2).click(); 
		}
	});
	$(".re-btn").click(function(){//提交数据
		$.ajax({
			url:"{0}/arbCenter/saveCenter".format($.getRequestUrl()),
			type:"post",
			data:$(".handle-form").serialize(),
			dataType:"json",
			success:function(dataObj){
				alert(dataObj.msg);
				if(dataObj.status=="SUCCESS"){
					if($(".infoId").val()==""){//添加成功
						$(".this-page").val(1);
						getGridView();
					}else{//修改成功
						getGridView();
					}
					$(".type-tab li").eq(0).click();//跳转到数据列表界面
					$(".info-form")[0].reset();//清空详细界面表单
					$(".handle-form")[0].reset();//清空详细界面表单
				}
			},
			error:function(){}
		}); 
	});
	$(".return-btn").click(function(){
		var obj = document.getElementsByName("oid");
		var orderData = '';
		for(var i = 0; i < obj.length; i++) {
			if(obj[i].checked){
				orderData += obj[i].value + ',';
			}
		}
		if(orderData.length != 0) {
			orderData=orderData.substring(0, orderData.length - 1);
		}
		var arrayData = {
			"orderData": orderData
		};
		$.ajax({
			url:"{0}/arbOrder/returnArbInfo".format($.getRequestUrl()),
			type:"post",
			data:arrayData,
			dataType:"json",
			success:function(dataObj){
				alert(dataObj.msg);
				getGridView();
			},
			error:function(){}
		}); 
	});
	$(".re-btn").click(function(){
		$(".handle-form")[0].reset();//清空详细界面表单
	});
});

//查询数据列表
function getGridView(pageNum) {
	$.ajax({
		url: "{0}/arbDeclare/getArbtrations".format($.getRequestUrl()),
		type: "post",
		data: $(".condition-form").serialize(),
		dataType: "json",
		success: function(dataList) {
			$(".all-page").val(dataList.totalPage);
			$(".this-page").val(dataList.currentPage);
			$(".data-count table").empty();
			if(typeof(dataList.msg) != "undefined")
				alert(dataList.msg);
			for(var i = 0; i < dataList.list.length; i++) {
				$(".data-count table").append(
					'<tr><td><input type="checkbox" name="oid" value="'+dataList.list[i].id+'" /></td><td><input type="hidden" class="id" value="'
						+dataList.list[i].id+'"/>' + dataList.list[i].ewbNo +
					'</td><td>' + dataList.list[i].applySiteName +
					'</td><td>' + dataList.list[i].applyType +
					'</td><td>' + dataList.list[i].status +
					'</td><td>' + dataList.list[i].created +
					'</td><td>' + dataList.list[i].applySingName +
					'</td><td>' + dataList.list[i].productName +
					'</td><td>' + dataList.list[i].packType +
					'</td></tr>');
			}
			//数据表头和内容宽度适应
			$(".data-title tr th").each(function(index) {
				$(this).width($(".data-count table tr td").eq(index).width());
			});
			if(dataList.list.length==0){//没有数据
				$(".data-count table").append('<tr><td colspan="8" style="color:red;">没有查到数据</td></tr>');
			}else{
				//数据表头和内容宽度适应
				$(".data-title tr th").each(function(index){
					$(this).width($(".data-count table tr td").eq(index).width());
				});
				//查看详情
				$(".data-count tr").dblclick(function(){
					$.ajax({
						url:"{0}/arbDeclare/getArbInfo".format($.getRequestUrl()),
						type:"post",
						data:{"id":$(this).find(".id").val()},
						dataType:"json",
						success:function(dataObj){
							for(i in dataObj) {//把json字符串赋值给详情界面表单
								$(".info-form").find("[name='"+i+"']").val(dataObj[i]);
							}	
							$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",true);
							$(".type-tab li").eq(1).click();
						},
						error:function(){}
					});
				});
			}
		},
		error: function() {

		}
	});
}
function getArbTypeInfo(id){
	$.ajax({
		url: "{0}/arbType/getArbTypes".format($.getRequestUrl()),
		type: "post",
		dataType: "json",
		success: function(dataList) {
			id.empty();
			id.append('<option></option>');
			for(var i = 0; i < dataList.length; i++) {
				id.append(
					'<option value="'
						+dataList[i].id+'">' + dataList[i].name +
					'</option>');
			}
		}
	});
}
function getDesc(type){
	$.ajax({
		url: "{0}/arbType/getDesc".format($.getRequestUrl()),
		type: "post",
		data:{type:type},
		dataType: "json",
		success: function(data) {
			$("#desc").val(data);
		}
	});
}
function getAllSite(){
	$.ajax({
		url: "{0}/site/getAllSites".format($.getRequestUrl()),
		type: "post",
		dataType: "json",
		success: function(dataList) {
			$("#site").empty();
			$("#site").append('<option></option>');
			for(var i = 0; i < dataList.list.length; i++) {
				$("#site").append(
					'<option value="'
						+dataList.list[i].id+'">' + dataList.list[i].name +
					'</option>');
			}
		}
	});
}
function getAllOwner(applyType){
	$.ajax({
		url: "{0}/arbOwner/getAllOwners".format($.getRequestUrl()),
		type: "post",
		data:{"applyType":applyType},
		dataType: "json",
		success: function(dataList) {
			$("#applyId").empty();
			$("#applyId").append('<option></option>');
			for(var i = 0; i < dataList.length; i++) {
				$("#applyId").append(
					'<option value="'
						+dataList[i].id+'">' + dataList[i].name +
					'</option>');
			}
		}
	});
}
function findById(id){
	$.ajax({
		url:"{0}/arbDeclare/getArbInfo".format($.getRequestUrl()),
		type:"post",
		data:{"id":id},
		dataType:"json",
		success:function(dataObj){
			getAllOwner(dataObj.applyType);
			$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",true);
			$(".handle-form").find("[name=joinOrganization]").val(dataObj.ownerSiteName);
			$(".handle-form").find("[name=joinOrganization1]").val(dataObj.applySiteName);
		},
		error:function(){}
	});
}