$(function(){
	$(".windiv").hide().eq(0).show();
	//数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	$("#province").change( function(){
		$("#x").remove();
	});
	$("#city").change( function(){
		$("#x1").remove();
	});
	$("#area").change( function(){
		$("#x2").remove();
	});
	$(".sub-btn").click(function(){//提交数据
			$.ajax({
				url:"{0}/site/save".format($.getRequestUrl()),
				type:"post",
				data:$(".info-form").serialize(),
				dataType:"text",
				success:function(data){
					var dataObj = JSON.parse(data);
					alert(dataObj.msg);
					$(".info-form")[0].reset();//清空详细界面表单
					$("#shortName").val();
				},
				error:function(){}
			}); 
	});
	
	$("#siteType").change(function(){
		if($("#siteType").val()==0||$("#siteType").val()==1){
			$("#orderId").empty();
			$("#orderId").append('<option value="'
					+0+'">' +"总部" +
					'</option>');
		}else {
			$.ajax({
				url:"{0}/site/getOrderSite".format($.getRequestUrl()),
				type:"post",
				data:{"type":$("#siteType").val()},
				dataType:"json",
				success:function(dataList){
					$("#orderId").empty();
					for(var i = 0; i < dataList.length; i++) {
						$("#orderId").append('<option value="'
								+dataList[i].id+'">' +dataList[i].name +
								'</option>');
					}
				},
				error:function(){}
			}); 
		}
	});
	
	$(".add-btn").click(function(){//提交数据
		getMaxOrder(); 
	});
	
	$("#name").change(function(){//提交数据
		$.ajax({
			url:"{0}/site/getPy".format($.getRequestUrl()),
			type:"post",
			data:{"name":$("#name").val()},
			dataType:"json",
			success:function(data){
				$("#shortName").empty();
				$("#shortName").append('<option value="'
						+data.msg+'">' +data.msg +
				'</option>');
			},
			error:function(){}
		}); 
	});
	
	//删除数据
	$(".del-btn").click(function(){
		$.ajax({
			url:"{0}/site/delSite".format($.getRequestUrl()),
			type:"post",
			data:{"id":$("#sid").val()},
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
		url:"{0}/site/getSites".format($.getRequestUrl()),
		type:"post",
		data:$(".condition-form").serialize(),
		dataType:"json",
		success:function(dataList){
			$(".all-page").val(dataList.totalPage);//总页数
			$(".this-page").val(dataList.currentPage);//总记录数
			$(".data-count table").empty();//清空列表数据
			for(var i = 0; i < dataList.list.length; i++) {
				$(".data-count table").append('<tr><td><input type="hidden" class="siteId" value="'
				+dataList.list[i].id+'"/>'+dataList.list[i].name
				+'</td><td>'+(dataList.list[i].shortName||'')
				+'</td><td>'+dataList.list[i].fullAddress
				+'</td><td>'+dataList.list[i].orderName
				+'</td><td>'+dataList.list[i].headName
				+'</td><td>'+dataList.list[i].headPhone
				+'</td></tr>');
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
						url:"{0}/site/getSiteById".format($.getRequestUrl()),
						type:"post",
						data:{"siteId":$(this).find(".siteId").val()},
						dataType:"json",
						success:function(dataObj){
							for(i in dataObj) {//把json字符串赋值给详情界面表单
								$(".info-form").find("[name='"+i+"']").val(dataObj[i]);
							}	
							$("#province").empty();
							$("#city").empty();
							$("#area").empty();
							$("#province").append('<option id='+"x"+' value="'
									+""+'">' +dataObj['province'] +
							'</option>');
							$("#city").append('<option id='+"x1"+' value="'
									+""+'">' +dataObj['city'] +
							'</option>');
							$("#area").append('<option id='+"x2"+' value="'
									+""+'">' +dataObj['region'] +
							'</option>');
							$("#shortName").append('<option id='+""+' value="'
									+""+'">' +dataObj['shortName'] +
							'</option>');
							$("#orderId").append('<option id='+""+' value="'
									+""+'">' +dataObj['siteOrder'] +
							'</option>');
							$(".info-form input").add(".info-form textarea").add(".info-form select").attr("disabled",true);
							$(".type-tab li").eq(1).click();
							$(".add-btn").add(".update-btn").add(".del-btn").add(".sel-btn").attr("disabled",false).removeClass("gbtn");//添加、提交按钮可操作
							getProvince1(dataObj['province'],dataObj['city']);
						},
						error:function(){}
					});
				});
			}
		},
		error:function(){}
	}); 
}

function getProvince1(name1,name2){
	$.ajax({
		url: "{0}/ssq/listProvice".format($.getRequestUrl()),
		type: "post",
		dataType: "text",
		success: function(data) {
			var dataObj = JSON.parse(data);
			$("#province").append('<option value="'
					+""+'">' + "———省———" +
				'</option>');
			for(var i = 0; i < dataObj.length; i++) {
				$("#province").append(
					'<option value="'
						+dataObj[i].provinceid+'">' + dataObj[i].province +
					'</option>');
			}
			for(var i = 0; i < dataObj.length; i++) {
				if(dataObj[i].province == name1){
					getCity1(name2,dataObj[i].provinceid);
				}
			}
		}
	});
}

function getCity1(name,pro){
	id=$("#city");
	$.ajax({
		url: "{0}/ssq/listCity".format($.getRequestUrl()),
		type: "post",
		dataType: "text",
		data :{
			"pro" : pro
		} ,
		success: function(data) {
			var dataObj = JSON.parse(data);
			$("#city").append('<option value="'
					+""+'">' + "———市———" +
			'</option>');
			for(var i = 0; i < dataObj.length; i++) {
				id.append(
					'<option value="'
						+dataObj[i].cityid+'">' + dataObj[i].city +
					'</option>');
			}
			for(var i = 0; i < dataObj.length; i++) {
				if(dataObj[i].city == name){
					getArea1(dataObj[i].cityid);
				}
			}
		}
	});
}

function getArea1(city){
	id=$("#area");
	$.ajax({
		url: "{0}/ssq/listArea".format($.getRequestUrl()),
		type: "post",
		dataType: "text",
		data :{
			"city" : city
		} ,
		success: function(data) {
			var dataObj = JSON.parse(data);
			$("#area").append('<option value="'
					+""+'">' + "———区———" +
			'</option>');
			for(var i = 0; i < dataObj.length; i++) {
				id.append(
					'<option value="'
						+dataObj[i].areaid+'">' + dataObj[i].area +
					'</option>');
			}
		}
	});
}
function getMaxOrder(){
	$.ajax({
		url:"{0}/site/getSiteOrder".format($.getRequestUrl()),
		type:"post",
		dataType:"json",
		success:function(data){
			$("#siteOrder").val(data);
		},
		error:function(){}
	});
}