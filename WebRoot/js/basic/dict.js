$(function() {

	var id = $("#u");
	getGridView(); // 初始化显示数据列表
	// 数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	$(".ad-btn").click(function() {// 提交数据
		$("#py").empty();
		$("#id").val("");
		 $(".btn-div input[type='button']").attr("disabled",true).addClass("gbtn");//所有按钮不可操作
		 $(".add-btn").add(".sub-btn").add(".sel-btn").attr("disabled",false).removeClass("gbtn");//添加、提交按钮可操作
	});
	$(".sub-btn").click(function() { // 提交数据
		$.ajax({
			url : "{0}/basic/saveDict".format($.getRequestUrl()),
			type : "post",
			data : $(".condition-form").serialize(),
			dataType : "json",
			success : function(dataObj) {
				alert(dataObj.msg);
				if (dataObj.status == "SUCCESS") {
					getGridView();
					$("#shortName").empty();
				}
			},
			error : function() {
			}
		});
	});
	// 删除数据
	$(".del-btn").click(
			function() {
				$.ajax({
					url : "{0}/basic/delDict".format($.getRequestUrl()),
					type : "post",
					data : {
						"id" : $("#id").val()
					},
					dataType : "json",
					success : function(dataObj) {
						alert(dataObj.msg);
						if (dataObj.status == "SUCCESS") {
							getGridView(); // 数据列表刷新
							$(".info-form input").add(".info-form textarea")
									.add(".info-form select").attr("disabled",
											false); // 取消表单不可编辑状态
							$(".btn-div input[type='button']").attr("disabled",
									true).addClass("gbtn"); // 所有按钮不可操作
							$(".sub-btn").attr("disabled", false).removeClass(
									"gbtn"); // 提交按钮可操作
							$("#shortName").empty();
						}
					},
					error : function() {
					}
				});
			});
	// 获取拼音
	$("#name").change(
			function() {// 提交数据
				$.ajax({
					url : "{0}/site/getPy".format($.getRequestUrl()),
					type : "post",
					data : {
						"name" : $("#name").val()
					},
					dataType : "json",
					success : function(data) {
						$("#py").empty();
						$("#py").append(
								'<option value="' + data.msg + '">' + data.msg
										+ '</option>');
					},
					error : function() {
					}
				});
			});
	// 获取最大排序值
	
	
	/* {复选框 点击} */
	$("#pay").click(function() {
		if ("false" == ($("#pay").val())) {
			$("#pay").val("true");
		} else {
			$("#pay").val("false")
		}
	});
});

function getArbTypeInfo(id) {
	$.ajax({
		url : "{0}/arbType/getArbTypes".format($.getRequestUrl()),
		type : "post",
		dataType : "json",
		success : function(dataObj) {
			for ( var i = 0; i < dataList.length; i++) {
				$(".u li").append(
						'<li class="id" value="' + dataList[i].id + '">'
								+ dataList[i].name + '</li>');
			}
		}
	});
}
// 查询数据列表
function getGridView(pageNum) {
	$.ajax({
				url : "{0}/basic/getDictTypes".format($.getRequestUrl()),
				type : "post",
				dataType : "json",
				success : function(dataList) {
					$(".u ul").empty();
					for ( var i = 0; i < dataList.length; i++) {
						$(".u ul").append(
								'<li><input type="hidden" class="id" value="'
										+ dataList[i].id + '"/>'
										+ dataList[i].name + '</li>');
					}
					if (dataList.length == 0) { // 没有数据
						$(".data-count table").append(
										'<tr><td colspan="8" style="color:red;">没有查到数据</td></tr>');
					} else {
						// 数据表头和内容宽度适应
						$(".data-title tr th").each(
								function(index) {
									$(this).width($(".data-count table tr td").eq(
													index).width());
								});
						// 查看详情
						$(".u li").dblclick(
								function() {
									$.ajax({
										url : "{0}/basic/getDictById".format($.getRequestUrl()),
										type : "post",
										data : {
											"id" : $(this).find(".id").val()
										},
										dataType : "json",
										success : function(dataList) {
											console.log(dataList);
											$(".all-page").val(dataList.totalPage);//总页数
											$(".this-page").val(dataList.currentPage);//总记录数
											$(".data-count table").empty();//清空列表数据
											for(var i = 0; i < dataList.length; i++) {
												$(".data-count table").append('<tr><td><input type="hidden" class="userId" value="'
												+dataList[i].id+'"/>'+dataList[i].name 
												+'</td><td>'+(dataList[i].value||'')
												+'</td><td>'+(dataList[i].order||'')
												+'</td><td>'+(dataList[i].remark||'')
												+'</td><td>'+(dataList[i].creater ||'')
												+'</td><td>'+(dataList[i].created ||'')
												+'</td></tr>');
											}
											if(dataList.length==0){//没有数据
												$(".data-count table").append('<tr><td colspan="8" style="color:red;">没有查到数据</td></tr>');
											}else{
												//数据表头和内容宽度适应
												$(".data-title tr th").each(function(index){
													$(this).width($(".data-count table tr td").eq(index).width());
												});
												//查看详情
												$(".data-count tr").dblclick(function(){
													$.ajax({
														url:"{0}/basic/getInfoById".format($.getRequestUrl()),
														type:"post",
														data:{"userId":$(this).find(".userId").val()},
														dataType:"json",
														success:function(dataObj){
															for(i in dataObj) {//把json字符串赋值给详情界面表单
																$(".condition-form").find("[name='"+i+"']").val(dataObj[i]);
															}	
															$("#py").empty();
															$("#py").append(
																	'<option value="'
																		+dataObj["value"]+'">' + dataObj['value'] +
																	'</option>');
															$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",true);
															$(".type-tab li").eq(1).click();
															$(".add-btn").add(".reset-btn").add(".update-btn").add(".del-btn").add(".sel-btn").attr("disabled",false).removeClass("gbtn");//添加、提交按钮可操作
														},
														error:function(){}
													});
												});
											}
										},
										error : function() {
										}
									});
								});
					}
				},
				error : function() {

				}
			});
}