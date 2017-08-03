$(function() {
	//conditionShowHide(); //查询条件事件
	var id = $("#type");
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
	//仲裁
	$(".info-btn").click(function(){//提交数据
		$.ajax({
			url:"{0}/arb/getAllInfo".format($.getRequestUrl()),
			type:"post",
			data:{"id":$(".infoId").val()},
			dataType:"json",
			success:function(dataObj){
				$(".data-count table").empty();
				$(".type-tab li").eq(2).click(); 
				$(".data-table").append(
						'<tr><th>处理人</th><th>上一状态</th><th>处理状态</th><th>处理原因</th>'
						+'<th style="padding-right:16px;">处理时间</th></tr>');
				if(typeof(dataObj.msg) != "undefined")
					alert(dataObj.msg);
				for(var i = 0; i < dataObj.length; i++) {
					console.log(dataObj[i].centerHandOne);
					if(typeof(dataObj[i].centerHandOne) != "undefined"){
						$(".data-table").append(
								'<tr><td>'+dataObj[i].hand
								+'</td><td colspan="4"><table><tr><td>'+dataObj[i].centerHandOne
								+'</td></tr><tr><td>'+dataObj[i].centerHandTwo
								+'</td></tr></table></td></tr>'
								+'<tr><td>'+dataObj[i].hand
								+'</td><td>'+dataObj[i].provStatus
								+'</td><td>'+dataObj[i].status
								+'</td><td>'+dataObj[i].reason
								+'</td><td>'+dataObj[i].created
								+'</td></tr>');
					}else if(typeof(dataObj[i].centerHandOne) == "undefined"){
						$(".data-table").append(
								'<tr><td>'+dataObj[i].hand
								+'</td><td>'+dataObj[i].provStatus
								+'</td><td>'+dataObj[i].status
								+'</td><td>'+dataObj[i].reason
								+'</td><td>'+dataObj[i].created
								+'</td></tr>');
					}
				}
				
			},
			error:function(){}
		});
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
					'<tr><td><input type="hidden" class="id" value="'
						+dataList.list[i].id+'"/>' + dataList.list[i].ewbNo +
					'</td><td>' + dataList.list[i].applySiteName +
					'</td><td>' + dataList.list[i].ownerSiteName +
					'</td><td>' + dataList.list[i].applyType +
					'</td><td>' + dataList.list[i].status +
					'</td><td>' + dataList.list[i].signName +
					'</td><td>' + dataList.list[i].handName +
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
							$(".infoId").val(dataObj["id"]);
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
			for(var i = 0; i < dataList.length; i++) {
				id.append(
					'<option value="'
						+dataList[i].id+'">' + dataList[i].name +
					'</option>');
			}
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