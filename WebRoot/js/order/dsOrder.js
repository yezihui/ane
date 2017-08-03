$(function() {
	//数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	$(".dis").keydown(function(e){
		if(e.which == 13) {
			getNextSite();
		}
	});
	$(".sig").keydown(function(e){
		if(e.which == 13) {
			getSignSite();
		}
	});
	$(".fol").keydown(function(e){
		if(e.which == 13) {
			getFollowSite();
		}
	});
	//提交数据
	$(".follow-btn").click(function() {// 提交数据
		getFollowSite();
	});
	//提交数据
	$(".dispatch-btn").click(function() {// 提交数据
		getNextSite();
	});
	//提交数据
	$(".sign-btn").click(function() {// 提交数据
		getSignSite();
	});
});

//查询数据列表
function getNextSite(){
	if ($("#ewbNo").val() == "") {
		$.msgTip($("#ewbNo"), '运单号不得为空');
	} else {
		$.ajax({
			url : "{0}/dispatch/dispatchOrder".format($.getRequestUrl()),
			type : "post",
			data : $(".condition-form").serialize(),
			dataType : "json",
			success : function(dataObj) {
				if (typeof(data.msg) != "undefined"){
					alert(data.msg);
				}
				var s = "运单["+dataObj.ewbNo+"]寄件网点是["+dataObj.sendSiteName+"],目的网点是["+dataObj.receiveSiteName+
				"],即将派送到客户["+dataObj.loginSiteName+"]";
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
							url:"{0}/dispatch/upload".format($.getRequestUrl()),
							type:"post",
							data:{
								"ewbNo":dataObj.ewbNo,
							},
							dataType:"json",
							success:function(data){
								alert(data);
								if (typeof(data.msg) != "undefined"){
									alert(data.msg);
								}
								$(".data-count table").empty();
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
}
//查询数据列表
function getSignSite(){
	if ($("#ewbNo").val() == "") {
		$.msgTip($("#ewbNo"), '运单号不得为空');
	} else {
		$.ajax({
			url : "{0}/sign/signOrder".format($.getRequestUrl()),
			type : "post",
			data : $(".condition-form").serialize(),
			dataType : "json",
			success : function(dataObj) {
				if (typeof(dataObj.msg) != "undefined"){
					alert(dataObj.msg);
				}else{
					if(dataObj.sendSiteId==0){
						var s = "运单["+dataObj.ewbNo+"]寄件网点是["+dataObj.sendSiteName+"],目的网点是["+dataObj.receiveSiteName+
						"],即将被客户["+dataObj.loginSiteName+"]签收";
						$(".data-count table").empty();
						$(".data-count table").append('<tr><td>'+s+'</td></tr>');
					}else{
						var s = "运单["+dataObj.ewbNo+"]寄件网点是["+dataObj.sendSiteName+"],目的网点是["+dataObj.receiveSiteName+
						"],即将被网点异常签收";
						$(".data-count table").empty();
						$(".data-count table").append('<tr><td>'+s+'</td></tr>');
					}
				}
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
							url:"{0}/sign/upload".format($.getRequestUrl()),
							type:"post",
							data:{
								"ewbNo":dataObj.ewbNo,
								"signType":dataObj.sendSiteId,
							},
							dataType:"json",
							success:function(data){
								alert(data);
								if (typeof(data.msg) != "undefined"){
									alert(data.msg);
								}
								$(".data-count table").empty();
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
}
//查询数据列表
function getFollowSite(){
	if ($("#ewbNo").val() == "") {
		$.msgTip($("#ewbNo"), '运单号不得为空');
	} else {
		$.ajax({
			url : "{0}/follow/followOrder".format($.getRequestUrl()),
			type : "post",
			data : $(".condition-form").serialize(),
			dataType : "json",
			success : function(dataObj) {
				if (typeof(dataObj.msg) != "undefined"){
					alert(dataObj.msg);
				}else{
					if(dataObj.length==0){//没有数据
						$(".data-count table").empty();
						$(".data-count table").append('<tr><td colspan="8" style="color:red;">没有查到数据</td></tr>');
					}else{
						//数据表头和内容宽度适应
						$(".data-count table").empty();
						for(var i = dataObj.list.length-1; i >= 0; i--) {
							if(dataObj.list[i].state==0) {
								$(".data-count table").append('<tr><td>'+dataObj.list[i].createTime+'</td><td colspan="7">网点[<font style="color:red;">'+dataObj.list[i].cName+'</font>]的取件员[<font style="color:red;">'+dataObj.list[i].cName+'</font>]从客户[<font style="color:red;">'+dataObj.list[i].topSite+'</font>]手中取件</td></tr>');
							}else if(dataObj.list[i].state==1) {
								$(".data-count table").append('<tr><td>'+dataObj.list[i].createTime+'</td><td colspan="7">运单在网点[<font style="color:red;">'+dataObj.list[i].topSite+'</font>]正发往网点[<font style="color:red;">'+dataObj.list[i].nextSite+'</font>],扫描员是[<font style="color:red;">'+dataObj.list[i].cName+'</font>]</td></tr>');
							}else if(dataObj.list[i].state==2) {
								$(".data-count table").append('<tr><td>'+dataObj.list[i].createTime+'</td><td colspan="7">运单到达网点[<font style="color:red;">'+dataObj.list[i].nextSite+'</font>] 上一站是网点[<font style="color:red;">'+dataObj.list[i].topSite+'</font>] 扫描员是[<font style="color:red;">'+dataObj.list[i].cName+'</font>]</td></tr>');
							}else if(dataObj.list[i].state==3) {
								$(".data-count table").append('<tr><td>'+dataObj.list[i].createTime+'</td><td colspan="7">网点[<font style="color:red;">'+dataObj.list[i].topSite+'</font>]的[<font style="color:red;">'+dataObj.list[i].cName+'</font>]正在派送,收件人是[<font style="color:red;">'+dataObj.list[i].nextSite+'</font>]</td></tr>');
							}else if(dataObj.list[i].state==4) {
								$(".data-count table").append('<tr><td>'+dataObj.list[i].createTime+'</td><td colspan="7">运单由收件人[<font style="color:red;">'+dataObj.list[i].nextSite+'</font>]从网点[<font style="color:red;">'+dataObj.list[i].topSite+'</font>]签收,签收人是[<font style="color:red;">'+dataObj.list[i].cName+'</font>]</td></tr>');
							}else if(dataObj.list[i].state==5) {
								$(".data-count table").append('<tr><td>'+dataObj.list[i].createTime+'</td><td colspan="7">运单由网点[<font style="color:red;">'+dataObj.list[i].topSite+'</font>]进行异常签收</td></tr>');
							}
						}
						//数据表头和内容宽度适应
						$(".data-title tr th").each(function(index) {
							$(this).width($(".data-count table tr td").eq(index).width());
						});
					}
				}
			},
			error : function(dataObj) {
				alert(dataObj.msg);
			}
		});
	}
}
