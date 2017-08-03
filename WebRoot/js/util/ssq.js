$(function() {
	var id = $("#province");
	getProvince(id);
	$("#province").change( function(){
		var n = $("#province").val();
		id=$("#city");
		if(n!=0){
			getCity(id,n);
		}else{
			id.empty();
			$("#city").append('<option value="'
					+""+'">' + "———市———" +
			'</option>');
			$("#area").empty();
			$("#area").append('<option value="'
					+""+'">' + "———区———" +
			'</option>');
		}
	});
	$("#city").change( function(){
		var n = $("#city").val();
		id=$("#area");
		if(n!=0){
			getArea(id,n);
		}else{
			$("#area").empty();
			$("#area").append('<option value="'
					+""+'">' + "———区———" +
			'</option>');
		}
	});
});

function getProvince(id){
	$.ajax({
		url: "{0}/ssq/listProvice".format($.getRequestUrl()),
		type: "post",
		dataType: "text",
		success: function(data) {
			var dataObj = JSON.parse(data);
			id.append('<option value="'
					+""+'">' + "———省———" +
				'</option>');
			$("#city").append('<option value="'
					+""+'">' + "———市———" +
			'</option>');
			$("#area").append('<option value="'
					+""+'">' + "———区———" +
			'</option>');
			for(var i = 0; i < dataObj.length; i++) {
				id.append(
					'<option value="'
						+dataObj[i].provinceid+'">' + dataObj[i].province +
					'</option>');
			}
		}
	});
}

function getCity(id,pro){
	$.ajax({
		url: "{0}/ssq/listCity".format($.getRequestUrl()),
		type: "post",
		dataType: "text",
		data :{
			"pro" : pro
		} ,
		success: function(data) {
			var dataObj = JSON.parse(data);
			id.empty();
			$("#city").append('<option value="'
					+""+'">' + "———市———" +
			'</option>');
			$("#area").empty();
			$("#area").append('<option value="'
					+""+'">' + "———区———" +
			'</option>');
			for(var i = 0; i < dataObj.length; i++) {
				id.append(
					'<option value="'
						+dataObj[i].cityid+'">' + dataObj[i].city +
					'</option>');
			}
		}
	});
}

function getArea(id,city){
	$.ajax({
		url: "{0}/ssq/listArea".format($.getRequestUrl()),
		type: "post",
		dataType: "text",
		data :{
			"city" : city
		} ,
		success: function(data) {
			var dataObj = JSON.parse(data);
			id.empty();
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