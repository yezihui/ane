$(function() {
	//conditionShowHide(50); // 查询条件事件
	$("#applyAddresses").append(
	'<option></option><option value="0">全国</option>');
	$("#ownerAddresses").append(
	'<option></option><option value="0">全国</option>');
	$("#type").append('<option value="0">全部</option>');
	getGridView(); // 初始化显示数据列表
	getArbTypeInfo(); // 初始化获取仲裁类型
	getRegionInfo(); // 初始化获取省份信息
	getEmployeeInfo(); // 初始化获取仲裁部门
	// 数据表头和内容宽度适应
	$(".data-title tr th").each(function(index) {
		$(this).width($(".data-count table tr td").eq(index).width());
	});
	// $(".data-title").add(".data-count").add(".data-count
	// table").css("min-width","2000px");//表格列表超出设置
	
	
	$(".add-btn").click(function() { // 提交数据
		$(".ownerType").empty();
		$(".ownerType").append('<option value="0"></option>');
		$(".ownerType").append('<option value="1">申报方所属省份</option>');
		$(".ownerType").append('<option value="2">责任方所属省份</option>');
		getRegionInfo();
		getArbTypeInfo();
	});
	
	$(".update-btn").click(function() { // 提交数据
		$(".ownerType").empty();
		$(".ownerType").append('<option value="0"></option>');
		$(".ownerType").append('<option value="1">申报方所属省份</option>');
		$(".ownerType").append('<option value="2">责任方所属省份</option>');
	});
	// 提交数据
	$(".sub-btn").click(function() { // 提交数据
		var obj = document.getElementsByName("applyAddresses");
		var addressData = '';
		for(var i = 0; i < obj.length; i++) {
			if(obj[i].checked)
				addressData += obj[i].value + ',';
		}
		var obj = document.getElementsByName("applyType");
		var typeData = '';
		for(var i = 0; i < obj.length; i++) {
			if(obj[i].checked)
				typeData += obj[i].value + ',';
		}
		if(addressData.length != 0) {
			addressData=addressData.substring(0, addressData.length - 1);
		}
		if(typeData.length != 0) {
			typeData=typeData.substring(0, typeData.length - 1);
		}
		var arrayData = {
			"id": $(".infoId").val(),
			"center": $(".center").val(),
			"ownerId": $("#ownerId").val(),
			"ownerType": $(".ownerType").val(),
			"rate": $(".rate").val(),
			"applyAddresses": addressData,
			"applyType": typeData
		};
		$.ajax({
			url: "{0}/arbAssign/saveAssignInfo".format($.getRequestUrl()),
			type: "post",
			data: arrayData,
			dataType: "json",
			success: function(dataObj) {
				alert(dataObj.msg);
				if(dataObj.status == "SUCCESS") {
					if($(".infoId").val() == "") { // 添加成功
						$(".this-page").val(1);
						getGridView();
					} else { // 修改成功
						getGridView();
					}
					$(".type-tab li").eq(0).click(); // 跳转到数据列表界面
					$(".info-form")[0].reset(); // 清空详细界面表单
					$(".sel-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
					$(".del-btn").attr("disabled",true);//提交按钮可操作
				}
			},
			error: function() {}
		});
	});
	//删除数据
	$(".del-btn").click(function(){
		$.ajax({
			url:"{0}/arbAssign/delAssign".format($.getRequestUrl()),
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
});

function getGridView() {
	$.ajax({
			url: "{0}/arbAssign/getAssignInfo".format($.getRequestUrl()),
			type: "post",
			data: $(".condition-form").serialize(),
			dataType: "json",
			success: function(dataList) {
				$(".all-page").val(dataList.totalPage);
				$(".this-page").val(dataList.currentPage);
				$(".data-count table").empty();
				for(var i = 0; i < dataList.list.length; i++) {
					$(".data-count table").append(
						'<tr><td><input type="hidden" class="id" value="' +
						dataList.list[i].id + '"/>' +
						dataList.list[i].id + '</td><td>' +
						dataList.list[i].orderName +
						'</td><td>' +
						dataList.list[i].applyProvince +
						'</td><td>' +
						dataList.list[i].typeName +
						'</td><td>' +
						dataList.list[i].rate +
						'</td><td>' +
						dataList.list[i].created +
						'</td></tr>');
				}
				// 数据表头和内容宽度适应
				$(".data-title tr th").each(
					function(index) {
						$(this).width(
							$(".data-count table tr td").eq(index)
							.width());
					});
				if(dataList.list.length == 0) { // 没有数据
					$(".data-count table")
						.append(
							'<tr><td colspan="8" style="color:red;">没有查到数据</td></tr>');
				} else {
					// 数据表头和内容宽度适应
					$(".data-title tr th").each(
						function(index) {
							$(this).width(
								$(".data-count table tr td").eq(
									index).width());
						});
					// 查看详情
					$(".data-count tr").dblclick(
						function() {
							$.ajax({
								url: "{0}/arbAssign/getAssign"
									.format($.getRequestUrl()),
								type: "post",
								data: {
									"id": $(this).find(".id").val()
								},
								dataType: "json",
								success: function(dataObj) {
									for(i in dataObj) { // 把json字符串赋值给详情界面表单
										$(".info-form").find(
												"[name='" + i + "']")
											.val(dataObj[i]);
									}
									if(dataObj["applyAddresses"]!=null){
										$(".ownerType").empty();
										$(".ownerType").append('<option value="1">申报方所属省份</option>');
									}else{
										$(".ownerType").empty();
										$(".ownerType").append('<option value="2">责任方所属省份</option>');
									}
									getRegion(dataObj["applyAddresses"]);
									getArbType(dataObj["types"]);
									$(".info-form input").add(
										".info-form textarea").add(
										".info-form select").attr(
										"disabled", true);
									$(".type-tab li").eq(1).click();
									$(".update-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
									$(".del-btn").attr("disabled",false).removeClass("gbtn");//提交按钮可操作
								},
								error: function() {}
							});
						});
				}
			},
			error: function() {

			}
		});
}
// 查询数据列表
function getArbTypeInfo() {
	$.ajax({
		url: "{0}/arbType/getArbTypes".format($.getRequestUrl()),
		type: "post",
		dataType: "json",
		success: function(dataList) {
			$(".typelist").empty()
			$(".typelist").append(
					'<li> <input type = "checkbox" id="typeall"  class="at" name = "applyType" value = "' +
					0 + '"style = "width:20px;"/ > ' +
					"全部" + '</li>');
			for(var i = 0; i < dataList.length; i++) {
				// 添加查询 仲裁类型
				$("#type").append(
					'<option value="' + dataList[i].id + '">' +
					dataList[i].name + '</option>');
				// 添加详细 仲裁类型
				$(".typelist").append(
					'<li> <input type = "checkbox" class="at" name = "applyType" value = "' +
					dataList[i].id + '"style = "width:20px;"/ > ' +
					dataList[i].name + '</li>');
			}
			$("#typeall").click(function() {
				$("input[name='applyType']").prop("checked", this.checked);
			});
		}
	});
}

function getArbType(str) {
	$.ajax({
		url: "{0}/arbType/getArbTypes".format($.getRequestUrl()),
		type: "post",
		dataType: "json",
		success: function(dataList) {
			if(str!=0){
				$(".typelist").empty();
				$(".typelist").append(
						'<li> <input type = "checkbox" id="typeall" disabled="disabled"  class="at" name = "applyType" value = "' +
						0 + '"style = "width:20px;"/ > ' +
						"全部" + '</li>');
				for(var i = 0; i < dataList.length; i++) {
					// 添加详细 仲裁类型
					var arr=new Array(); 
				    arr=str.split(',');//注split可以用字符或字符串分割  
					// 添加详细
				    var flag=false;
					for(var j=0;j<arr.length;j++){
						if(arr[j]==dataList[i].id){
							flag=true;
						}
					}
					if(flag==true){
						$(".typelist").append(
								'<li> <input type = "checkbox" disabled="disabled" checked="checked" class="at" name = "applyType" value = "' +
								dataList[i].id + '"style = "width:20px;"/ > ' +
								dataList[i].name + '</li>');
					}else{
						$(".typelist").append(
								'<li> <input type = "checkbox" disabled="disabled" class="at" name = "applyType" value = "' +
								dataList[i].id + '"style = "width:20px;"/ > ' +
								dataList[i].name + '</li>');
					}
				}
			}else{
				$(".typelist").empty();
				$(".typelist").append(
						'<li> <input type = "checkbox" id="typeall" disabled="disabled"  checked="checked"  class="at" name = "applyType" value = "' +
						0 + '"style = "width:20px;"/ > ' +
						"全部" + '</li>');
				for(var i = 0; i < dataList.length; i++) {
					$(".typelist").append(
						'<li> <input type = "checkbox" disabled="disabled" checked="checked" class="at" name = "applyType" value = "' +
						dataList[i].id + '"style = "width:20px;"/ > ' +
						dataList[i].name + '</li>');
				}
			}
			$("#typeall").click(function() {
				$("input[name='applyType']").prop("checked", this.checked);
			});
		}
	});
}

function getRegionInfo() {
	$.ajax({
		url: "{0}/ssq/listProvice".format($.getRequestUrl()),
		type: "post",
		dataType: "json",
		success: function(dataList) {
			$(".regionlist").empty();
			$(".regionlist").append(
					'<li> <input type = "checkbox"  name = "applyAddresses" id="regionall" value = "' +
					0 + '"style = "width:20px;"/ > ' +
					"全国" + '</li>');
			for(var i = 0; i < dataList.length; i++) {
				// 添加身份信息
				$("#applyAddresses").append(
					'<option value="' + dataList[i].provinceid + '">' +
					dataList[i].province + '</option>');
				$("#ownerAddresses").append(
					'<option value="' + dataList[i].provinceid + '">' +
					dataList[i].province + '</option>');
				// 添加详细
				$(".regionlist").append(
					'<li> <input type = "checkbox" name = "applyAddresses" value = "' +
					dataList[i].provinceid + '"style = "width:20px;"/ > ' +
					dataList[i].province + '</li>');
			}
			$("#regionall").click(function() {
				$("input[name='applyAddresses']").prop("checked", this.checked);
			});
		}
	});
}
function getRegion(str) {
	$.ajax({
		url: "{0}/ssq/listProvice".format($.getRequestUrl()),
		type: "post",
		dataType: "json",
		success: function(dataList) {
			if(str!=0){
				$(".regionlist").empty();
				var arr=new Array(); 
			    arr=str.split(',');//注split可以用字符或字符串分割  
			    $(".regionlist").append('')
			    $(".regionlist").append(
								'<li> <input type = "checkbox" disabled="disabled" name = "applyAddresses" id="regionall" value = "' +
								0 + '"style = "width:20px;"/ > ' +
								"全国" + '</li>');
				for(var i = 0; i < dataList.length; i++) {
					// 添加详细
					var flag=false;
					for(var j=0;j<arr.length;j++){
						if(arr[j]==dataList[i].provinceid){
							flag=true;
						}
					}
					if(flag){
						$(".regionlist").append(
								'<li> <input type = "checkbox" disabled="disabled" checked="checked" name = "applyAddresses" value = "' +
								dataList[i].provinceid + '"style = "width:20px;"/ > ' +
								dataList[i].province + '</li>');
					}else{
						$(".regionlist").append(
								'<li> <input type = "checkbox" disabled="disabled" name = "applyAddresses" value = "' +
								dataList[i].provinceid + '"style = "width:20px;"/ > ' +
								dataList[i].province + '</li>');
					}
				}
			}else{
				$(".regionlist").empty();
				var arr=new Array(); 
			    arr=str.split(',');//注split可以用字符或字符串分割  
			    $(".regionlist").append('')
			    $(".regionlist").append(
								'<li> <input type = "checkbox" disabled="disabled" checked="checked" name = "applyAddresses" id="regionall" value = "' +
								0 + '"style = "width:20px;"/ > ' +
								"全国" + '</li>');
				for(var i = 0; i < dataList.length; i++) {
					$(".regionlist").append(
						'<li> <input type = "checkbox" disabled="disabled" checked="checked" name = "applyAddresses" value = "' +
						dataList[i].provinceid + '"style = "width:20px;"/ > ' +
						dataList[i].province + '</li>');
					
				}
			}
			$("#regionall").click(function() {
				$("input[name='applyAddresses']").prop("checked", this.checked);
			});
		}
	});
}

function getEmployeeInfo() {
	$.ajax({
		url: "{0}/emp/getEmpByDept".format($.getRequestUrl()),
		type: "post",
		data:{"deptId":1},
		dataType: "json",
		success: function(dataList) {
			for(var i = 0; i < dataList.length; i++) {
				$("#ownerId").append(
					'<option value="' + dataList[i].id + '">' +
					dataList[i].name + '</option>');
			}
		}
	});
}