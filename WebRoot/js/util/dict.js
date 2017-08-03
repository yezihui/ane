$(function() {
	var id = $("#typeName");
	getDictType(id,3);
	id = $("#serviceType");
	getDictType(id,4);
	id = $("#payType");
	getDictType(id,5);
});

function getDictType(id,n){
	$.ajax({
		url: "{0}/dict/findByTypeId".format($.getRequestUrl()),
		type: "post",
		data :{
			"dateType" : n
		} ,
		dataType: "text",
		success: function(data) {
			var dataObj = JSON.parse(data);
			id.append('<option value="'
					+""+'">' + "" +
				'</option>');
			for(var i = 0; i < dataObj.length; i++) {
				id.append(
					'<option value="'
						+dataObj[i].id+'">' + dataObj[i].name +
					'</option>');
			}
		}
	});
}