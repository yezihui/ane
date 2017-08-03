$(function() {
	$(".yesconfirm-btn").click(
			function() {// 提交数据
				$(".id").val($(".infoId").val());
				$("#claimAmount").val($("#amount").val());
				$("#amount").val($("#amount").val());
				$("#claimAmount").prop("disable", true);
				$("#amount").prop("disable", true);
				$(".yesConfirm").prop("hidden", false);
				$(".noConfirm").prop("hidden", true);
				$(".btn-div input[type='button']").attr("disabled", true)
						.addClass("gbtn");// 所有按钮不可操作
				$(".sel-btn").attr("disabled", false).removeClass("gbtn");// 提交按钮可操作
				$(".yesconfirm-btn").attr("disabled", false)
						.removeClass("gbtn");// 提交按钮可操作
			});
	$(".noconfirm-btn")
			.click(
					function() {// 提交数据
						$(".id").val($(".infoId").val());
						$(".yesConfirm").prop("hidden", true);
						$(".noConfirm").prop("hidden", false);
						$(".btn-div input[type='button']").attr("disabled",
								true).addClass("gbtn");// 所有按钮不可操作
						$(".sel-btn").attr("disabled", false).removeClass(
								"gbtn");// 提交按钮可操作
						$(".noconfirm-btn").attr("disabled", false)
								.removeClass("gbtn");// 提交按钮可操作
					});
	// 提交数据
	$(".return-btn").click(
			function() {// 提交数据
				$.ajax({
					url : "{0}/claim/saveCalimDetailInfo".format($.getRequestUrl()),
					type : "post",
					data : $(".handle-form").serialize(),
					dataType : "json",
					success : function(dataObj) {
						alert(dataObj.msg);
						$(".type-tab li").eq(0).click();
					},
					error : function() {
					}
				});

			});
	$(".return1-btn").click(
			function() {// 提交数据
				$.ajax({
					url : "{0}/claim/saveCalimInfo".format($.getRequestUrl()),
					type : "post",
					data : $(".handle-form").serialize(),
					dataType : "json",
					success : function(dataObj) {
						alert(dataObj.msg);
						$(".type-tab li").eq(0).click();
					},
					error : function() {
					}
				});

			});
});
