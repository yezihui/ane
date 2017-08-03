$(function() {
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
	
	//全选
	$("#orderall").click(function() {
		$("input[name='oid']").prop("checked", this.checked);
	});
	//提交数据
	$(".order-btn").click(function(){//提交数据
		var obj = document.getElementsByName("oid");
		var orderData = '';
		for(var i = 0; i < obj.length; i++) {
			if(obj[i].checked)
				orderData += obj[i].value + ',';
		}
		if(orderData==''){
			alert("请勾选数据！")
		}else{
			if(orderData.length != 0) {
				orderData=orderData.substring(0, orderData.length - 1);
			}
			var arrayData = {
				"orderData": orderData
			};
			$.ajax({
				url:"{0}/order/order".format($.getRequestUrl()),
				type:"post",
				data:arrayData,
				dataType:"json",
				success:function(data){
					alert(data.msg);
					getGridView();
				},
				error:function(){}
			}); 
		}
	});
	
});

//查询数据列表
function getGridView(pageNum) {
	$.ajax({
		url: "{0}/order/getOrders".format($.getRequestUrl()),
		type: "post",
		data:$(".condition-form").serialize(),
		dataType: "json",
		success: function(dataList) {
			$(".all-page").val(dataList.totalPage);
			$(".this-page").val(dataList.currentPage);
			$(".data-count table").empty();
			for(var i = 0; i < dataList.list.length; i++) {
				if(typeof(dataList.list[i].ewbNo)=="undefined"){
					$(".data-count table").append(
						'<tr><td><input type="checkbox" name="oid" value="'+dataList.list[i].orderId+
						'" /></td><td><input type="hidden" class="id" value="'
							+dataList.list[i].orderId+'"/>' + (dataList.list[i].ewbNo||'') +
						'</td><td>' + dataList.list[i].produceType +
						'</td><td>' + dataList.list[i].serviceType +
						'</td><td>' + dataList.list[i].sendTime +
						'</td><td>' + dataList.list[i].sendCustomer +
						'</td><td>' + dataList.list[i].sendAddress +
						'</td><td>' + dataList.list[i].receiveCustomer +
						'</td><td>' + (dataList.list[i].receiveAddress||'') +
						'</td><td>' + dataList.list[i].produceName +
						'</td><td>' + dataList.list[i].calcWeight +
						'</td><td>' + dataList.list[i].vol +
						'</td><td>' + dataList.list[i].payMode +
						'</td><td>' + dataList.list[i].amount +
						'</td></tr>');
				}else{
					$(".data-count table").append(
							'<tr><td></td><td><input type="hidden" class="id" value="'
								+dataList.list[i].orderId+'"/>' + (dataList.list[i].ewbNo||'') +
							'</td><td>' + dataList.list[i].produceType +
							'</td><td>' + dataList.list[i].serviceType +
							'</td><td>' + dataList.list[i].sendTime +
							'</td><td>' + dataList.list[i].sendCustomer +
							'</td><td>' + dataList.list[i].sendAddress +
							'</td><td>' + dataList.list[i].receiveCustomer +
							'</td><td>' + dataList.list[i].sendAddress +
							'</td><td>' + dataList.list[i].produceName +
							'</td><td>' + dataList.list[i].calcWeight +
							'</td><td>' + dataList.list[i].vol +
							'</td><td>' + dataList.list[i].payMode +
							'</td><td>' + dataList.list[i].amount +
							'</td></tr>');
				}
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
						url:"{0}/order/getOrderInfo".format($.getRequestUrl()),
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
function getDesc(type){
	$.ajax({
		url: "{0}/arbType/getDesc".format($.getRequestUrl()),
		type: "post",
		data:{type:type},
		dataType: "json",
		success: function(data) {
			var data = JSON.parse(data);
			$("#desc").val(data);
		}
	});
}
