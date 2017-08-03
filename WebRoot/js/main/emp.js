$(function() {
	$(".windiv").hide().eq(0).show();
	getAlDepts();
	// 数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	$(".sub-btn").click(function() {// 提交数据
		$.ajax({
			url : "{0}/emp/save".format($.getRequestUrl()),
			type : "post",
			data : $(".info-form").serialize(),
			dataType : "text",
			success : function(data) {
				var dataObj = JSON.parse(data);
				alert(dataObj.msg);
				$(".windiv").hide().eq(0).show();
				getGridView();
			},
			error : function() {
			}
		});
	});

	$(".add-btn").click(function() {// 新增数据
		$("#sid").val(null);
		$("#shortName").empty();
		getAllDepts();
	});

	$(".update-btn").click(function() {
		getAllDepts();
	});
	
	$("#name").change(function() {// 提交数据
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
					url : "{0}/emp/delEmp".format($.getRequestUrl()),
					type : "post",
					data : {
						"id" : $("#sId").val()
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
							$(".sel-btn").attr("disabled", false).removeClass(
									"gbtn");// 提交按钮可操作
							$(".add-btn").attr("disabled", false).removeClass(
									"gbtn");// 提交按钮可操作
							$("#sid").val(null);
							$("#shortName").empty();
							$(".info-form")[0].reset();// 清空详细界面表单
							$(".windiv").hide().eq(0).show();
						}
					},
					error : function() {
					}
				});
			});

});

//查询数据列表
function getGridView(){
	$.ajax({
		url:"{0}/emp/getEmps".format($.getRequestUrl()),
		type:"post",
		data:$(".condition-form").serialize(),
		dataType:"json",
		success:function(dataList){
			$(".all-page").val(dataList.totalPage);//总页数
			$(".this-page").val(dataList.currentPage);//总记录数
			$(".data-count table").empty();//清空列表数据
			for(var i = 0; i < dataList.list.length; i++) {
				$(".data-count table").append('<tr><td><input type="hidden" class="userId" value="'
				+dataList.list[i].id+'"/>'+dataList.list[i].name 
				+'</td><td>'+(dataList.list[i].pinyin||'')
				+'</td><td>'+(dataList.list[i].sex||'')
				+'</td><td>'+(dataList.list[i].phone  ||'')
				+'</td><td>'+(dataList.list[i].entryTime  ||'')
				+'</td><td>'+(dataList.list[i].siteName ||'')
				+'</td><td>'+(dataList.list[i].position ||'')
				+'</td><td>'+(dataList.list[i].created||'') 
				+'</td><td>'+(dataList.list[i].creater  ||'')
				+'</td><td>'+(dataList.list[i].updated||'')
				+'</td><td>'+(dataList.list[i].updater ||'')+'</td></tr>');
			}
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
						url:"{0}/emp/getEmpById".format($.getRequestUrl()),
						type:"post",
						data:{"userId":$(this).find(".userId").val()},
						dataType:"json",
						success:function(dataObj){
							for(i in dataObj) {//把json字符串赋值给详情界面表单
								$(".info-form").find("[name='"+i+"']").val(dataObj[i]);
							}	
							$(".info-form").find("[name='eTime']").val(dataObj['entryTime']);
							$("#shortName").empty();
							$("#shortName").append(
									'<option value="'
										+dataObj["pinyin"]+'">' + dataObj['pinyin'] +
									'</option>');
							$("#sex").empty();
							$("#sex").append(
									'<option value="'
										+dataObj["sex"]+'">' + dataObj['sex'] +
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
		error:function(){}
	}); 
}

function getAllDepts(){
	$.ajax({
		url : "{0}/dept/getDepts".format($.getRequestUrl()),
		type : "post",
		dataType : "json",
		success : function(dataList) {
			$("#siteId").empty();
			for ( var i = 0; i < dataList.length; i++) {
				$("#siteId").append('<option value="'
						+dataList[i].deptId+'">' + dataList[i].deptName +
						'</option>');
			}
		},
		error : function() {
		}
	});
}
function getAlDepts(){
	$.ajax({
		url : "{0}/dept/getDepts".format($.getRequestUrl()),
		type : "post",
		dataType : "json",
		success : function(dataList) {
			$("#SiteId").empty();
			$("#SiteId").append('<option value="'
					+""+'">' + "" +
					'</option>');
			for ( var i = 0; i < dataList.length; i++) {
				$("#SiteId").append('<option value="'
						+dataList[i].deptId+'">' + dataList[i].deptName +
						'</option>');
			}
		},
		error : function() {
		}
	});
}