$(function() {
	// 提交数据
	$(".audit-btn").click(
			function() {// 提交数据
				$.ajax({
					url : "{0}/claim/updateAuditCalim".format($.getRequestUrl()),
					type : "post",
					data : {"id":$(".infoId").val()},
					dataType : "json",
					success : function(dataObj) {
						alert(dataObj.msg);
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
	
	// 提交数据
	$(".noaudit-btn").click(
			function() {// 提交数据
				$.ajax({
					url : "{0}/claim/updatenoAuditCalim".format($.getRequestUrl()),
					type : "post",
					data : {"id":$(this).find(".infoId").val()},
					dataType : "json",
					success : function(dataObj) {
						alert(dataObj.msg);
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
