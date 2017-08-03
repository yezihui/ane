$(function() {
	//数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	$("#ewbNo").keydown(function(e){
		if(e.which == 13) {
			getNextSite();
		}
	});
	//提交数据
	$(".sub-btn").click(function() {// 提交数据
		if ($("#ewbNo").val() == "") {
			$.msgTip($("#ewbNo"), '运单号不得为空');
		}else if ($("#siteName").val() == "") {
			$.msgTip($("#siteName"), '上一网点不得为空');
		} else {
			$.ajax({
				url : "{0}/receive/receiveOrder".format($.getRequestUrl()),
				type : "post",
				data : $(".condition-form").serialize(),
				dataType : "json",
				success : function(dataObj) {
					var s = "运单["+dataObj.ewbNo+"]寄件网点是["+dataObj.sendSiteName+"],目的网点是["+dataObj.receiveSiteName+
					"],即将到达网点["+dataObj.loginSiteName+"]";
					$(".data-count table").append('<tr><td>'+s+'</td></tr>');
					//数据表头和内容宽度适应
					$(".data-title tr th").each(function(index) {
						$(this).width($(".data-count table tr td").eq(index).width());
					});
					if(dataObj.length==0){//没有数据
						$(".data-count table").empty();
						$(".data-count table").append('<tr><td colspan="8" style="color:red;">没有查到数据</td></tr>');
					}else{
						//数据表头和内容宽度适应
						$(".data-title tr th").each(function(index){
							$(this).width($(".data-count table tr td").eq(index).width());
						});
						//查看详情
						$(".data-count tr").dblclick(function(){
							$.ajax({
								url:"{0}/receive/upload".format($.getRequestUrl()),
								type:"post",
								data:{
									"ewbNo":dataObj.ewbNo,
									"siteName":$("#siteName").val()
								},
								dataType:"json",
								success:function(data){
									alert(data);
									$(".data-count table").empty();
									$("#siteName").val("");
									$("#ewbNo").val("");
									$(".type-tab li").eq(1).click();
								},
								error:function(){}
							});
						});
					}
				},
				error : function(dataObj) {
					alert(dataObj.msg);
				}
			});
		}
	});
	
});

//查询数据列表
function getNextSite(){
	$.ajax({
		url: "{0}/receive/getProvSite".format($.getRequestUrl()),
		type: "post",
		data:{
			"ewbNo":$("#ewbNo").val()
		},
		dataType: "json",
		success: function(data) {
			$("#siteName").val(data.name);
			if (typeof(data.msg) != "undefined"){
				alert(data.msg);
			}
		},
		error : function(dataObj) {
			alert(dataObj.msg);
		}
	});
}
