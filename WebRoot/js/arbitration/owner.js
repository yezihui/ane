$(function(){
	//conditionShowHide(50);//查询条件事件
	var id = $("#type");
	getArbTypeInfo(id);
	//getGridView();//初始化显示数据列表
	//数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	//$(".data-title").add(".data-count").add(".data-count table").css("min-width","2000px");//表格列表超出设置
	$(".sub-btn").click(function(){//提交数据
		$.ajax({
			url:"{0}/arbOwner/saveOwner".format($.getRequestUrl()),
			type:"post",
			data:$(".info-form").serialize(),
			dataType:"json",
			success:function(dataObj){
				alert(dataObj.msg);
				if(dataObj.status=="SUCCESS"){
					if($(".infoId").val()==""){//添加成功
						$(".this-page").val(1);
						getGridView();
					}else{//修改成功
						getGridView();
					}
					$(".type-tab li").eq(0).click();//跳转到数据列表界面
					$(".info-form")[0].reset();//清空详细界面表单
				}
			},
			error:function(){}
		}); 
	});
	//删除数据
	$(".del-btn").click(function(){
		$.ajax({
			url:"{0}/arbOwner/delOwner".format($.getRequestUrl()),
			type:"post",
			data:{"id":$(".infoId").val()},
			dataType:"json",
			success:function(dataObj){
				alert(dataObj.msg);
				if(dataObj.status=="SUCCESS"){
					getGridView();//数据列表刷新
					$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",false);//取消表单不可编辑状态
					$(".btn-div input[type='button']").attr("disabled",true).addClass("gbtn");//所有按钮不可操作
					$(".sub-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
					$(".info-form")[0].reset();//清空详细界面表单
				}
			},
			error:function(){}
		}); 
	});
	//获取拼音
	$(".add-btn").click(function(){
		getMaxOrder();
	});
	$(".update-btn").click(function(){
		var id = $("#typeName");
		getArbTypeInfo1(id);
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

});

//查询数据列表
function getGridView(pageNum) {
	$.ajax({
		url: "{0}/arbOwner/getArbtrations".format($.getRequestUrl()),
		type: "post",
		data: $(".condition-form").serialize(),
		dataType: "json",
		success: function(dataList) {
			$(".all-page").val(dataList.totalPage);
			$(".this-page").val(dataList.currentPage);
			$(".data-count table").empty();
			for(var i = 0; i < dataList.list.length; i++) {
				$(".data-count table").append(
					'<tr><td><input type="hidden" class="id" value="'
						+dataList.list[i].id+'"/>' + dataList.list[i].id +
					'</td><td>' + (dataList.list[i].name||'') +
					'</td><td>' + (dataList.list[i].letters||'') +
					'</td><td>' + (dataList.list[i].order||'') +
					'</td><td>' + (dataList.list[i].typeName||'') +
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
						url:"{0}/arbOwner/getOwnerById".format($.getRequestUrl()),
						type:"post",
						data:{"id":$(this).find(".id").val()},
						dataType:"json",
						success:function(dataObj){
							for(i in dataObj) {//把json字符串赋值给详情界面表单
								$(".info-form").find("[name='"+i+"']").val(dataObj[i]);
							}	
							$("#shortName").empty();
							$("#shortName").append(
									'<option value="'
										+dataObj["letters"]+'">' + dataObj['letters'] +
									'</option>');
							$("#typeName").empty();
							$("#typeName").append(
									'<option value="'
										+dataObj["arbitrationType"]+'">' + dataObj['typeName'] +
									'</option>');
							$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",true);
							$(".type-tab li").eq(1).click();
							$(".update-btn").attr("disabled", false).removeClass(
							"gbtn"); // 提交按钮可操作
							$(".del-btn").attr("disabled", false).removeClass(
							"gbtn"); // 提交按钮可操作
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
function getMaxOrder(){
	$.ajax({
		url: "{0}/arbOwner/getMaxOrder".format($.getRequestUrl()),
		type: "post",
		dataType: "json",
		success: function(dataObj) {
			for(i in dataObj) {//把json字符串赋值给详情界面表单
				$(".info-form").find("[name='"+i+"']").val(dataObj[i]);
			}
			var id = $("#typeName");
			
			getArbTypeInfo1(id);
		}
	});
}

function getArbTypeInfo1(id){
	$.ajax({
		url: "{0}/arbType/getArbTypes".format($.getRequestUrl()),
		type: "post",
		dataType: "json",
		success: function(dataList) {
			id.empty();
			for(var i = 0; i < dataList.length; i++) {
				id.append(
					'<option value="'
						+dataList[i].id+'">' + dataList[i].name +
					'</option>');
			}
		}
	});
}
