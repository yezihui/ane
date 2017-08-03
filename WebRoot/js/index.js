$(function() {
	$(document).keydown(function(e){
		if(e.which == 13) {
			login();
		}
	});
	$("#submit").click(function() { // 提交数据
		login();
	});
	
	
});

function login(){
	var username = $('input[name=username]'),
	password = $('input[name=password]');
	if ($(username).val() == "") {
		$.msgTip(username, '用户名不得为空');
	} else if ($(password).val() == "") {
		$.msgTip(password, '密码不得为空');
	}else {
		$.ajax({
			url : "{0}/user/load".format($.getRequestUrl()),
			type : "post",
			data :{
				"userName" : $(username).val(),
				"userPwd" : $(password).val()
			} ,
			dataType : "json",
			success : function(data) {
				if(data.status=="SUCCESS" ){
					alert(data.msg);
					window.location.href = "View/system/main.jsp";
				}else{
					alert("登录失败~~");
					window.location.href = "View/login.jsp";
				}
				
			},
			error : function() {
			}
		});
	}
} 
