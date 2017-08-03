$(function() {
	// 提交数据
	$(".con-btn").click(
			function() {// 提交数据
				alert($(".infoId").val());
				$.ajax({
					url : "{0}/claim/updateconAuditCalim".format($.getRequestUrl()),
					type : "post",
					data : {"id":$(".infoId").val()},
					dataType : "json",
					success : function(dataObj) {
						if (dataObj.status == "SUCCESS") {
							if ($(".infoId").val() == "") {// 添加成功
								$(".this-page").val(1);
								getGridView();
							} else {// 修改成功
								getGridView();
							}
							$(".info-form input").add(".info-form textarea")
									.add(".info-form select").attr("disabled",
											true);
							$(".type-tab li").eq(0).click();// 跳转到数据列表界面
							$(".info-form")[0].reset();// 清空详细界面表单
						}
					},
					error : function() {
					}
				});
			});
	
});
