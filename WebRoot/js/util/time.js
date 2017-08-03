$(function() {
	var sysdate = getNowFormatDate;
	$('input[name="end"]').val(sysdate);
	var sysdat = getNFormatDate(3);
	$('input[name="start"]').val(sysdat);
	//单选框改变事件
	$('input:radio[name="dayTime"]').change( function(){
		var n = $("input[name='dayTime']:checked").val()
		var sysdate = getNowFormatDate;
		$('input[name="end"]').val(sysdate);
		var sysdat = getNFormatDate(n);
		$('input[name="start"]').val(sysdat);
	});
});

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate
            + " " + 23 + seperator2 + 59
            + seperator2 + 59;
    return currentdate;
}

function getNFormatDate(n) {
    var d = new Date().getTime()-n*86400000;
    var date = new Date(d);
    var seperator1 = "-";
    var seperator2 = ":";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate
            + " " + "00" + seperator2 + "00"
            + seperator2 + "00";
    return currentdate;
}

