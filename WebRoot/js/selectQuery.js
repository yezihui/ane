var e,page;

/**
 * 模糊查询
 * @param e //按键值
 * @param thisEmt  //当前文本框
 * @param objVal  //id的赋值对象
 */
function selectQuery(e,thisEmt,objVal){
	page=1;
	clearTimeout(e);
	var thisVal=$(thisEmt).val().replace(/(^\s+)|(\s+$)/g,"");
	if(e.keyCode<48 || (e.keyCode>57 && e.keyCode<65) || (e.keyCode>90 && e.keyCode<96) || e.keyCode>105){
		if(e.keyCode!=8){
			return false;
		}
	}
	$(".vagueWin").remove();
	if(thisVal==""){
		$("."+objVal).val("");
		return false;
	}
	e=setTimeout(function(){
		$.ajax({
			type : "POST",
			async : false,
			url : "/ane56-customer-web/demo/getCompanys",
			data : {'page' :page},
			dataType : "json",
			success : function(data) {
				var dataList = JSON.parse(data);
				var htm="";
				for (var i = 0; i < 5; i++) {
					htm+='<tr><input type="hidden" value="'+dataList.list[i].name+'"/><td>'+
					dataList.list[i].province+'</td><td>'+
					dataList.list[i].region+'</td><td>'+
					dataList.list[i].mobilePhone+'</td></tr>';
				}
				drawTable(htm,thisEmt,objVal,dataList.totalPage);
			}
		});
	},1000);
}

/**
 * 绘制数据列表
 * @param htm  
 * @param thisEmt  //当前文本框
 * @param objVal  //id的赋值对象
 */
function drawTable(htm,thisEmt,objVal,totalPage){
	htm='<div class="vagueWin"><input type="hidden" value="1" class="keySelected"/><input type="hidden" value="'+totalPage+'" class="totalPage"/>'
	+'<table cellspacing="0"><tr><th>员工编号</th><th>员工名称</th><th>员工拼音</th></tr>'+htm+
	'<tr><th colspan="3"><a href="javascript:void(0);" class="sq-lpage">上一页</a><a href="javascript:void(0);" class="sq-npage">下一页</a></th><tr/>'+
	'</table><div>';			
	$("body").append(htm);
	$(".vagueWin tr").eq(1).addClass("selectedVagueTr");
	$(".vagueWin").css("top",$(thisEmt).offset().top+32);
	var thisRight=$(document).width()-$(thisEmt).offset().left;
	if(thisRight<$(".vagueWin").width()){
		var imgRight=$(".vagueWin").width()-$(".vagueWin img").width()-20;
		$(".vagueWin").css("right",thisRight-$(thisEmt).width()-20);
	}else{
		$(".vagueWin").css("left",$(thisEmt).offset().left);
	}
	$(".vagueWin tr").unbind("click").click(function(){
		var trNum=$(this).index();
		if(trNum>0 && trNum<6){
			trClick(trNum,thisEmt,objVal);
		}
	});
	$(".vagueWin a").unbind("click").click(function(){
		if($(this).attr("class")=="sq-npage" && page<$(".totalPage").val()){
			page=page+1;
			pageSelect();
		}else if($(this).attr("class")=="sq-lpage" && page>1){
			page=page-1;
			pageSelect();
		}
	});
	$(document).unbind("click").click(function(e){//隐藏结果框
		var target = $(e.target);
		if(target.closest(".vagueWin").length == 0){
			$(thisEmt).val("");
			$("."+objVal).val("");
			$(".vagueWin").remove();
		}
	});
	keyClick(thisEmt,objVal);
}


function pageSelect(){
	$.ajax({
		type : "POST",
		async : false,
		url : "/ane56-customer-web/demo/getCompanys",
		data : {'page' :page},
		dataType : "json",
		success : function(data) {
			var dataList = JSON.parse(data);
			for (var i = 0; i < 5; i++) {
				$(".vagueWin table tr").eq(i+1).find("input").val(dataList.list[i].name);
				$(".vagueWin table tr").eq(i+1).find("td").eq(0).html(dataList.list[i].province);
				$(".vagueWin table tr").eq(i+1).find("td").eq(1).html(dataList.list[i].region);
				$(".vagueWin table tr").eq(i+1).find("td").eq(2).html(dataList.list[i].mobilePhone);
			}
		}
	});
}

/**
 * 表格行单击事件
 * @param i
 * @param thisEmt
 * @param objVal
 */
function trClick(i,thisEmt,objVal){
	$(document).unbind("click");
	$(thisEmt).val($(".vagueWin tr").eq(i).find("td").eq(1).html());
	$("."+objVal).val($(".vagueWin tr").eq(i).find("input").val());
	$(".vagueWin").remove();
}


/**
 * 键盘控制事件
 * @param thisEmt
 * @param objVal
 */
function keyClick(thisEmt,objVal){
	$(document).unbind("keydown").keydown(function(e){
		e.returnValue=false;
		var keySelected=$(".keySelected").val();
		if($(".vagueWin").length>0){
			switch (e.keyCode) {
			case 13:
				trClick(keySelected,thisEmt,objVal);
				break;
			/*case 37:
				$(".sq-lpage").click();
				$(".keySelected").val(1);
				$(".vagueWin tr").removeClass("selectedVagueTr").eq(1).addClass("selectedVagueTr");
				break;
			case 39:
				e.returnvalue=false;
				$(".sq-npage").click();
				$(".keySelected").val(1);
				$(".vagueWin tr").removeClass("selectedVagueTr").eq(1).addClass("selectedVagueTr");
				break;*/
			case 38:
				if(keySelected>1){
					$(".keySelected").val(parseInt(keySelected)-1);
					$(".vagueWin tr").removeClass("selectedVagueTr").eq($(".keySelected").val()).addClass("selectedVagueTr");
				}
				break;
			case 40:
				if(keySelected<$(".vagueWin tr").length-3){
					$(".keySelected").val(parseInt(keySelected)+1);
					$(".vagueWin tr").removeClass("selectedVagueTr").eq($(".keySelected").val()).addClass("selectedVagueTr");
				}
				break;
			default:
				break;
			}
		}
	});
}