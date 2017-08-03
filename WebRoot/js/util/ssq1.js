$(function() {
	var id = $("#p");
	getP(id);
	$("#p").change( function(){
		var n = $("#p").val();
		id=$("#c");
		if(n!=0){
			getC(id,n);
		}else{
			id.empty();
			$("#c").append('<option value="'
					+""+'">' + "———市———" +
			'</option>');
			$("#a").empty();
			$("#a").append('<option value="'
					+""+'">' + "———区———" +
			'</option>');
		}
	});
	$("#c").change( function(){
		var n = $("#c").val();
		id=$("#a");
		if(n!=0){
			getA(id,n);
		}else{
			$("#a").empty();
			$("#a").append('<option value="'
					+""+'">' + "———区———" +
			'</option>');
		}
	});
});

function getP(id){
	$.ajax({
		url: "{0}/ssq/listProvice".format($.getRequestUrl()),
		type: "post",
		dataType: "text",
		success: function(data) {
			var dataObj = JSON.parse(data);
			id.append('<option value="'
					+""+'">' + "———省———" +
				'</option>');
			$("#c").append('<option value="'
					+""+'">' + "———市———" +
			'</option>');
			$("#a").append('<option value="'
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

function getC(id,pro){
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
			$("#c").append('<option value="'
					+""+'">' + "———市———" +
			'</option>');
			$("#a").empty();
			$("#a").append('<option value="'
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

function getA(id,city){
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
			$("#a").append('<option value="'
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