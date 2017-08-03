$(function(){
	$(".windiv").hide().eq(0).show();
	//删除数据
	$("#orderall").click(function() {
		$("input[name='oid']").prop("checked", this.checked);
	});
	//save
	$(".confirm-btn").click(function(){//提交数据
		var obj = document.getElementsByName("oid");
		var orderData = '';
		for(var i = 0; i < obj.length; i++) {
			if(obj[i].checked)
				orderData += obj[i].value + ',';
		}
		if(orderData.length != 0) {
			orderData=orderData.substring(0, orderData.length - 1);
		}
		var arrayData = {
			"orderData": orderData
		};
		$.ajax({
			url:"{0}/return/confirm".format($.getRequestUrl()),
			type:"post",
			data:arrayData,
			dataType:"json",
			success:function(dataObj){
				alert(dataObj.msg);
				getGridView();
				$(".info-form")[0].reset();//清空详细界面表单
				$(".save-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
			},
			error:function(){}
		}); 
		
	});
	
	
	
});

//查询数据列表
function getGridView(){
	$.ajax({
		url:"{0}/return/getRetreats".format($.getRequestUrl()),
		type:"post",
		data:$(".condition-form").serialize(),
		dataType:"json",
		success:function(dataList){
			$(".all-page").val(dataList.totalPage);//总页数
			$(".this-page").val(dataList.currentPage);//总记录数
			$(".data-count table").empty();//清空列表数据
			for(var i = 0; i < dataList.length; i++) {
				$(".data-count table").append('<tr><td><input type="checkbox" name="oid" value="'
				+dataList[i].id+'" /></td><input type="hidden" class="ewbNo" value="'
				+dataList[i].ewbNo+'"/>'
				+'</td><td>'+dataList[i].ewbNo
				+'</td><td>'+dataList[i].acceptStatus
				+'</td><td>'+dataList[i].applySiteName
				+'</td><td>'+dataList[i].applyTime
				+'</td><td>'+(dataList[i].msgSiteName||'')
				+'</td><td>'+(dataList[i].confirmSiteName||'')
				+'</td><td>'+(dataList[i].confirmBy||'')
				+'</td><td>'+(dataList[i].confirmTime||'')+'</td></tr>');
			}
			if(dataList.length==0){//没有数据
				$(".data-count table").append('<tr><td colspan="8" style="color:red;">没有查到数据</td></tr>');
			}else{
				//数据表头和内容宽度适应
				$(".data-title tr th").each(function(index){
					$(this).width($(".data-count table tr td").eq(index).width());
				});
				//查看详情
				$(".data-count tr").dblclick(function(){
					$.ajax({
						url : "{0}/return/getEwbInfo".format($.getRequestUrl()),
						type : "post",
						data : {"ewbNo":$(".ewbNo").val()},
						dataType : "json",
						success : function(dataObj) {
							if (typeof(dataObj.msg) != "undefined"){
								alert(dataObj.msg);
							}else{
								$(".type-tab li").eq(1).click();
								for(i in dataObj) {//把json字符串赋值给详情界面表单
									$(".info-form").find("[name='"+i+"']").val(dataObj[i]);
								}
							}
						},
						error : function(dataObj) {
							alert(dataObj.msg);
						}
					});
				});
			}
		},
		error:function(){}
	}); 
}
