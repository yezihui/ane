$(function() {
	getGridView();
	$(".sub-btn").click(function() {// 提交数据
		$.ajax({
			url : "{0}/dept/saveDept".format($.getRequestUrl()),
			type : "post",
			data : $(".info-form").serialize(),
			dataType : "json",
			success : function(dataObj) {
				alert(dataObj.msg);
				$("#shortName").empty();
				$(".info-form")[0].reset();// 清空详细界面表单
				$(".update-btn").attr("disabled", false).removeClass("gbtn");// 提交按钮可操作
				$(".del-btn").attr("disabled", false).removeClass("gbtn");// 提交按钮可操作
				getGridView();
			},
			error : function() {
			}
		});
	});
	$(".add-btn").click(function() {// 提交数据
		getMaxOrder();
	});
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
	// 删除数据
	$(".del-btn").click(
			function() {
				$.ajax({
					url : "{0}/dept/delDept".format($.getRequestUrl()),
					type : "post",
					data : {
						"id" : $(".infoId").val()
					},
					dataType : "json",
					success : function(dataObj) {
						alert(dataObj.msg);
						if (dataObj.status == "SUCCESS") {
							getGridView();// 数据列表刷新
							$(".info-form input").add(".info-form textarea")
									.add(".info-form select").attr("disabled",
											false);// 取消表单不可编辑状态
							$(".btn-div input[type='button']").attr("disabled",
									true).addClass("gbtn");// 所有按钮不可操作
							$(".add-btn").attr("disabled", false).removeClass(
									"gbtn");// 提交按钮可操作
							$(".info-form")[0].reset();// 清空详细界面表单
							$("#shortName").empty();
						}
					},
					error : function() {
					}
				});
			});
});

// 查询数据列表
function getGridView() {
	$.ajax({
		url : "{0}/dept/getDepts".format($.getRequestUrl()),
		type : "post",
		dataType : "json",
		success : function(dataList) {
			$(".u").empty();
			for ( var i = 0; i < dataList.length; i++) {
				$(".u").append(
					'<li><input class="userId" type="hidden" value="'+ dataList[i].deptId
						+'"/><font class="font-class">'
								+ dataList[i].deptName + '</font></li><br/>');
			}
			$(".u li").dblclick(function(){
				$.ajax({
					url:"{0}/basic/getDeptById".format($.getRequestUrl()),
					type:"post",
					data:{"id":$(this).find(".userId").val()},
					dataType:"json",
					success:function(dataObj){
						for(i in dataObj) {//把json字符串赋值给详情界面表单
							$(".info-form").find("[name='"+i+"']").val(dataObj[i]);
						}
						$("#shortName").empty();
						$("#shortName").append(
								'<option value="'
									+dataObj["deptSpell"]+'">' + dataObj['deptSpell'] +
								'</option>');
						$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",true);
						$(".add-btn").add(".reset-btn").add(".update-btn").add(".del-btn").add(".sel-btn").attr("disabled",false).removeClass("gbtn");//添加、提交按钮可操作
					},
					error:function(){}
				});
			});
		},
		error : function() {
		}
	});
}

function getMaxOrder() {
	$.ajax({
		url : "{0}/dept/getDeptOrder".format($.getRequestUrl()),
		type : "post",
		dataType : "json",
		success : function(data) {
			$("#deptOrder").val(data);
		},
		error : function() {
		}
	});
}
