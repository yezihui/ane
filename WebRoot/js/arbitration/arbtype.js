$(function() {

	var id = $("#u");
	getGridView(); // 初始化显示数据列表
	// 数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	$(".add-btn").click(function() {// 提交数据
		$("#shortName").empty();
		getMaxOrder();
	});
	// $(".data-title").add(".data-count").add(".data-count
	// table").css("min-width","2000px");//表格列表超出设置
	$(".sub-btn").click(function() { // 提交数据
		$.ajax({
			url : "{0}/arbType/saveType".format($.getRequestUrl()),
			type : "post",
			data : $(".info-form").serialize(),
			dataType : "json",
			success : function(dataObj) {
				alert(dataObj.msg);
				if (dataObj.status == "SUCCESS") {
					if ($(".infoId").val() == "") { // 添加成功
						$(".this-page").val(1);
						getGridView();
					} else { // 修改成功
						getGridView();
					}
					$(".type-tab li").eq(0).click(); // 跳转到数据列表界面
					$("#shortName").empty();
					$(".info-form")[0].reset(); // 清空详细界面表单
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
					url : "{0}/arbType/delType".format($.getRequestUrl()),
					type : "post",
					data : {
						"id" : $(".infoId").val()
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
							$(".info-form")[0].reset(); // 清空详细界面表单
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
						$("#shortName").empty();
						$("#shortName").append(
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
				url : "{0}/arbType/getArbTypes".format($.getRequestUrl()),
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
						$(".data-count table")
								.append(
										'<tr><td colspan="8" style="color:red;">没有查到数据</td></tr>');
					} else {
						// 数据表头和内容宽度适应
						$(".data-title tr th").each(
								function(index) {
									$(this).width(
											$(".data-count table tr td").eq(
													index).width());
								});
						// 查看详情
						$(".u li").dblclick(
								function() {
									$.ajax({
										url : "{0}/arbType/getTypeById"
												.format($.getRequestUrl()),
										type : "post",
										data : {
											"id" : $(this).find(".id").val()
										},
										dataType : "json",
										success : function(dataObj) {
											for (i in dataObj) { // 把json字符串赋值给详情界面表单
												$(".info-form").find(
														"[name='" + i + "']")
														.val(dataObj[i]);
											}
											$("#shortName").empty();
											$("#shortName").append(
													'<option value="'
														+dataObj["pinyin"]+'">' + dataObj['pinyin'] +
													'</option>');
											if (dataObj.isPayFor == true) {
												$("[name=isPayFor]:checkbox").prop("checked", true);
												$("#pay").val("true");
											}else{
												  $("[name=isPayFor]:checkbox").prop("checked", false);
												$("#pay").val("false");
											}
											$(".info-form input").add(
													".info-form textarea").add(
													".info-form select").attr(
													"disabled", true);
											$(".update-btn").attr("disabled", false).removeClass(
											"gbtn"); // 提交按钮可操作
											$(".del-btn").attr("disabled", false).removeClass(
											"gbtn"); // 提交按钮可操作
											$(".type-tab li").eq(1).click();
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
function getMaxOrder() {
	$.ajax({
		url : "{0}/arbType/getDesc".format($.getRequestUrl()),
		type : "post",
		dataType : "json",
		success : function(data) {
			$("#order").val(data);
		},
		error : function() {
		}
	});
}