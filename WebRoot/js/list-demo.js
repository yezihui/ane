$(function(){
	$(".windiv").hide().eq(0).show();
	$("#ewbNo").keydown(function(e){
		if(e.which == 13) {
			getEwbInfo();
		}
	});
	$("#sub-btn").click(function(){//提交数据
		if ($("#typeName").val() == "") {
			$.msgTip($("#typeName"), '产品类型不得为空'); 
		} else if ($("#serviceType").val() == "") {
			$.msgTip($("#serviceType"), '服务方式不得为空'); 
		} else if ($("#sendDate").val() == "") {
			$.msgTip($("#sendDate"), '寄件日期不得为空'); 
		} else if ($("#p").val() == "") {
			$.msgTip($("#p"), '寄件省份不得为空'); 
		} else if ($("#c").val() == "") {
			$.msgTip($("#c"), '寄件城市不得为空'); 
		} else if ($("#a").val() == "") {
			$.msgTip($("#a"), '寄件区域不得为空'); 
		} else if ($("#sendCustomer").val() == "") {
			$.msgTip($("#sendCustomer"), '寄件人不得为空'); 
		} else if ($("#sendPhone").val() == "") {
			$.msgTip($("#sendPhone"), '寄件电话不得为空'); 
		} else if ($("#sendAddress").val() == "") {
			$.msgTip($("#sendAddress"), '寄件详细地址不得为空'); 
		} else if ($("#province").val() == "") {
			$.msgTip($("#province"), '收件省份不得为空');
		} else{
			$.ajax({
				url:"{0}/order/save".format($.getRequestUrl()),
				type:"post",
				data:$(".info-form").serialize(),
				dataType:"text",
				success:function(data){
					var dataObj = JSON.parse(data);
					alert(dataObj.msg);
					$(".info-form")[0].reset();//清空详细界面表单
					$(".sub-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
				},
				error:function(){}
			}); 
		}
	});
	//save
	$("#save-btn").click(function(){//提交数据
		if ($("#ewbNo").val() == "") {
			$.msgTip($("#ewbNo"), '运单号不得为空'); 
		}else {
			$.ajax({
				url:"{0}/return/save".format($.getRequestUrl()),
				type:"post",
				data:$(".info-form").serialize(),
				dataType:"text",
				success:function(data){
					var dataObj = JSON.parse(data);
					alert(dataObj.msg);
					$(".info-form")[0].reset();//清空详细界面表单
					$(".save-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
				},
				error:function(){}
			}); 
		}
	});
	//添加关联组织
	$(".add-btn").click(function(){
		$(".info-form")[0].reset();//清空详细界面表单
		$(".sub-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
		alert(2);
	});
	//删除数据
	$(".del-btn").click(function(){
		$.ajax({
			url:"{0}/demo/delCompany".format($.getRequestUrl()),
			type:"post",
			data:{"id":$(".infoId").val()},
			dataType:"json",
			success:function(data){
				var dataObj = JSON.parse(data);
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
	
});

//查询数据列表
function getGridView(){
	$.ajax({
		url:"{0}/demo/getCompanys".format($.getRequestUrl()),
		type:"post",
		data:$(".condition-form").serialize(),
		dataType:"json",
		success:function(data){
			var dataList = JSON.parse(data);
			$(".all-page").val(dataList.totalPage);//总页数
			$(".this-page").val(dataList.currentPage);//总记录数
			$(".data-count table").empty();//清空列表数据
			for(var i = 0; i < dataList.list.length; i++) {
				$(".data-count table").append('<tr><td><input type="hidden" class="siteId" value="'
				+dataList.list[i].id+'"/>'+dataList.list[i].name
				+'</td><td>'+dataList.list[i].shortName
				+'</td><td>'+dataList.list[i].type
				+'</td><td>'+dataList.list[i].country
				+'</td><td>'+dataList.list[i].province
				+'</td><td>'+dataList.list[i].city
				+'</td><td>'+dataList.list[i].region
				+'</td><td>'+dataList.list[i].street+'</td></tr>');
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
					alert(1);
					$.ajax({
						url:"{0}/demo/getCompanyById".format($.getRequestUrl()),
						type:"post",
						data:{"siteId":$(this).find(".siteId").val()},
						dataType:"json",
						success:function(data){
							var dataObj = JSON.parse(data);
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
		error:function(){}
	}); 
}

function getEwbInfo(){
	if ($("#ewbNo").val() == "") {
		$.msgTip($("#ewbNo"), '运单号不得为空');
	} else {
		$.ajax({
			url : "{0}/return/getEwbInfo".format($.getRequestUrl()),
			type : "post",
			data : {"ewbNo":$("#ewbNo").val()},
			dataType : "json",
			success : function(dataObj) {
				if (typeof(dataObj.msg) != "undefined"){
					alert(dataObj.msg);
				}else{
					for(i in dataObj) {//把json字符串赋值给详情界面表单
						$(".info-form").find("[name='"+i+"']").val(dataObj[i]);
					}
				}
				
			},
			error : function(dataObj) {
				alert(dataObj.msg);
			}
		});
	}
}
