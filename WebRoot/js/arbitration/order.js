$(function() {
	var id = $("#type");
	id.append('<option>全部</option>')
	getArbTypeInfo(id);
	getArbTypeInfo($("#applyType"));
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
	//删除数据
	$("#orderall").click(function() {
		$("input[name='oid']").prop("checked", this.checked);
	});
});

function getGridView(pageNum) {
	$.ajax({
		url: "{0}/arbDeclare/getArbtrations".format($.getRequestUrl()),
		type: "post",
		data: $(".condition-form").serialize(),
		dataType: "json",
		success: function(dataList) {
			if(typeof(dataList.msg) != "undefined")
				alert(dataList.msg);
			$(".all-page").val(dataList.totalPage);
			$(".this-page").val(dataList.currentPage);
			$(".data-count table").empty();
			for(var i = 0; i < dataList.list.length; i++) {
				$(".data-count table").append(
					'<tr><td><input type="checkbox" name="oid" value="'+dataList.list[i].id+'" /></td><td><input type="hidden" class="id" value="'
						+dataList.list[i].id+'"/>' + dataList.list[i].ewbNo +
					'</td><td>' + dataList.list[i].applySiteName +
					'</td><td>' + dataList.list[i].applyType +
					'</td><td>' + dataList.list[i].status +
					'</td><td>' + dataList.list[i].created +
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
							$("#ownerSiteId").empty();
							$("#ownerSiteId").append('<option value="'
								+dataObj.ownerSiteId+'">' + dataObj.ownerSiteName +
								'</option>');
							$("#applyId").empty();
							$(".type-tab li").eq(1).click();
							$("#applyId").append('<option value="'
								+dataObj.applyId+'">' + dataObj.applyType +
								'</option>');
							$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",true);
							$(".update-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
							$(".del-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
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
						+dataList[i].name+'">' + dataList[i].name +
					'</option>');
			}
		}
	});
}
