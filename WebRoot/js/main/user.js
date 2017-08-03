$(function(){
	$(".windiv").hide().eq(0).show();
	//数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	$(".sub-btn").click(function(){//提交数据
		$.ajax({
			url:"{0}/user/save".format($.getRequestUrl()),
			type:"post",
			data:$(".info-form").serialize(),
			dataType:"text",
			success:function(data){
				var dataObj = JSON.parse(data);
				alert(dataObj.msg);
			},
			error:function(){}
		}); 
	});
	$("#uName").blur(function(){
		getUserName($("#uName").val());
	});
	$(".update-btn").click(function(){
		getAllEmployee();
	});
	$(".reset-btn").click(function(){
		$.ajax({
			url:"{0}/user/updatePsw".format($.getRequestUrl()),
			type:"post",
			data:{"id":$("#uId").val()},
			dataType:"json",
			success:function(dataObj){
				alert(dataObj.msg);
				if(dataObj.status=="SUCCESS"){
					getGridView();//数据列表刷新
					$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",false);//取消表单不可编辑状态
					$(".btn-div input[type='button']").attr("disabled",true).addClass("gbtn");//所有按钮不可操作
					$(".sel-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
					$(".add-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
					$(".sel-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
					$(".update-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
					$(".info-form").find("[name='userPwd']").val(888888);
				}
			},
			error:function(){}
		}); 
	});
	$(".add-btn").click(function(){//新增数据
		$("#eId").empty();
		$("#eId").append('<option value="'
				+0+'">' + "" +
		'</option>');
		getAllEmployee();
	});
	
	//删除数据
	$(".del-btn").click(function(){
		$.ajax({
			url:"{0}/user/delUser".format($.getRequestUrl()),
			type:"post",
			data:{"id":$("#uId").val()},
			dataType:"json",
			success:function(dataObj){
				alert(dataObj.msg);
				if(dataObj.status=="SUCCESS"){
					getGridView();//数据列表刷新
					$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",false);//取消表单不可编辑状态
					$(".btn-div input[type='button']").attr("disabled",true).addClass("gbtn");//所有按钮不可操作
					$(".sel-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
					$(".add-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
					$(".info-form")[0].reset();//清空详细界面表单
				}
			},
			error:function(){}
		}); 
	});
	
});

//查询数据列表
function getGridView(){
	$.ajax({
		url:"{0}/user/getUsers".format($.getRequestUrl()),
		type:"post",
		data:$(".condition-form").serialize(),
		dataType:"json",
		success:function(dataList){
			$(".all-page").val(dataList.totalPage);//总页数
			$(".this-page").val(dataList.currentPage);//总记录数
			$(".data-count table").empty();//清空列表数据
			for(var i = 0; i < dataList.list.length; i++) {
				$(".data-count table").append('<tr><td><input type="hidden" class="userId" value="'
				+dataList.list[i].userId+'"/>'+dataList.list[i].userName 
				+'</td><td>'+(dataList.list[i].employeeId||'')
				+'</td><td>'+(dataList.list[i].employeeName||'')
				+'</td><td>'+(dataList.list[i].siteName ||'')
				+'</td><td>'+(dataList.list[i].userTypeName ||'')
				+'</td><td>'+(dataList.list[i].remark     ||'')
				+'</td><td>'+(dataList.list[i].createdTime||'') 
				+'</td><td>'+(dataList.list[i].createdBy  ||'')
				+'</td><td>'+(dataList.list[i].modifiedTime||'')
				+'</td><td>'+(dataList.list[i].modifiedBy ||'')+'</td></tr>');
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
						url:"{0}/user/getUserById".format($.getRequestUrl()),
						type:"post",
						data:{"userId":$(this).find(".userId").val()},
						dataType:"json",
						success:function(dataObj){
							for(i in dataObj) {//把json字符串赋值给详情界面表单
								$(".info-form").find("[name='"+i+"']").val(dataObj[i]);
							}	
							$("#eId").empty();
							$("#eId").append(
									'<option value="'
										+dataObj["employeeId"]+'">' + dataObj['employeeName'] +
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

function getAllEmployee(){
	$.ajax({
		url:"{0}/emp/getAllEmps".format($.getRequestUrl()),
		type:"post",
		dataType:"json",
		success:function(dataObj){
			for(var i = 0; i < dataObj.length; i++) {
				$("#eId").append(
					'<option value="'
						+dataObj[i].id+'">' + dataObj[i].name +
					'</option>');
			}
		}
	});
}

function getUserName(name){
	$.ajax({
		url:"{0}/user/getUserName".format($.getRequestUrl()),
		type:"post",
		data:{"id":$("#uId").val(),
			"name":name},
		dataType:"json",
		success:function(dataObj){
			if(dataObj.msg==0){
				return true;
			}else{
				$.msgTip($("#uName"), '用户名重复'); 
				return false;
			}
		}
	});
}