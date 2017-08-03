$(function() {
	var id = $("#type");
	id.append('<option value="">全部</option>')
	getArbTypeInfo(id);
	getArbTypeInfo($("#applyType"));
	//getGridView(); //初始化显示数据列表
	//数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	$(".eNo").keydown(function(e){
		if(e.which == 13) {
			getClaimInfo();
		}
	});
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
	//获取最大排序值
	$(".cs").click(function(){
		getArbTypeInfo($("#applyType"));
	});
	$(".update-btn").click(function(){
		$(".eNo").add(".able").attr("disabled",true);//添加、提交按钮可操作
	});
	
	//提交数据
	$(".sub-btn").click(function(){//提交数据
		if ($("#ewbNo").val() == "") {
			$.msgTip($("#ewbNo"), '运单号不得为空');
		}else{
			$.ajax({
				url:"{0}/claim/saveCalimInfo".format($.getRequestUrl()),
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
						$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",true);
						$(".add-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
						$(".sel-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
						$(".type-tab li").eq(0).click();//跳转到数据列表界面
						$(".info-form")[0].reset();//清空详细界面表单
					}
				},
				error:function(){}
			}); 
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
		url: "{0}/claim/getClaims".format($.getRequestUrl()),
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
						+dataList.list[i].id+'"/>' + dataList.list[i].ewbNo +
					'</td><td>' + dataList.list[i].amount +
					'</td><td>' + dataList.list[i].claimName +
					'</td><td>' + dataList.list[i].claimPhone +
					'</td><td>' + dataList.list[i].applyState +
					'</td><td>' + dataList.list[i].applyReason +
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
						url:"{0}/claim/getCalimInfo".format($.getRequestUrl()),
						type:"post",
						data:{"id":$(this).find(".id").val()},
						dataType:"json",
						success:function(dataObj){
							$(".infoId").val($(this).find(".id").val());
							for(i in dataObj) {//把json字符串赋值给详情界面表单
								$(".info-form").find("[name='"+i+"']").val(dataObj[i]);
							}	
							$("#ownerSiteId").empty();
							$("#ownerSiteId").append('<option value="'
								+dataObj.ownerSiteId+'">' + dataObj.ownerSiteName +
								'</option>');
							$("#applyId").empty();
							$(".type-tab li").eq(1).click();
							$("#applyId").append('<option value="'
								+dataObj.applyId+'">' + dataObj.applyType +
								'</option>');
							$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",true);
							$(".update-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
							$(".del-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
							$(".yesconfirm-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
							$(".noconfirm-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
							$(".audit-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
							$(".noaudit-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
							$(".con-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
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
		url: "{0}/arbType/getArbTypesByPay".format($.getRequestUrl()),
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

function getClaimInfo(){
	if ($("#ewbNo").val() == "") {
		$.msgTip($("#ewbNo"), '运单号不得为空');
	} else {
		$.ajax({
			url : "{0}/ewb/arbInfo".format($.getRequestUrl()),
			type : "post",
			data:{"ewbNo":$(".eNo").val()},
			dataType : "json",
			success : function(data) {
				if (typeof(data.msg) != "undefined"){
					alert(data.msg);
				}
				for(i in data) {//把json字符串赋值给详情界面表单
					$(".info-form").find("[name='"+i+"']").val(data[i]);
					if(i!="ewbNo")
						$(".info-form").find("[name='"+i+"']").attr("disabled","true");
				}	
			},
			error : function(dataObj) {
				alert(dataObj.msg);
			}
		});
	}
}