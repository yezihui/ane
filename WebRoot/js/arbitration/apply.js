$(function() {
	//conditionShowHide(100); //查询条件事件
	var id = $("#type");
	id.append('<option>全部</option>')
	getArbTypeInfo(id);
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
	$(".cover").click(function(){
		if ("false" == ($("#cover").val())) {
			$("#cover").val("true");
		} else {
			$("#cover").val("false")
		}
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
			for(var i = 0; i < dataList.list.length; i++) {
				$(".data-count table").append(
					'<tr><td><input type="hidden" class="id" value="'
						+dataList.list[i].id+'"/>' + dataList.list[i].orderId +
					'</td><td>' + dataList.list[i].applySiteName +
					'</td><td>' + dataList.list[i].applyType +
					'</td><td>' + dataList.list[i].status +
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
			id.empty();
			id.append('<option></option>');
			for(var i = 0; i < dataList.length; i++) {
				id.append(
					'<option value="'
						+dataList[i].name+'">' + dataList[i].name +
					'</option>');
			}
		}
	});
}
function getDesc(type){
	$.ajax({
		url: "{0}/arbType/getDesc".format($.getRequestUrl()),
		type: "post",
		data:{type:type},
		dataType: "json",
		success: function(data) {
			$("#desc").val(data);
		}
	});
}