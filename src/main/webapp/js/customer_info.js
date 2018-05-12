$(function(){
	$.ajax({
		url:"get_customer_info.do",
		data:{"infoId":$.cookie("infoId")},
		dataTpye:"json",
		type:"POST",
		success: function(data){
			$("#name-display").html($.cookie("name"));
			$("#id-display").html(data.data.identityNum);
			$("#school-display").html(data.data.school);
			$("#grade-display").html(data.data.gradeMajor);
			$("#qq-display").html(data.data.qq);
			$("#tel-display").html(data.data.telephone);
			$("#mail-display").html(data.data.email);
			
			$("#info-update-ID-input").val(data.data.identityNum);
			$("#school-select").html(data.data.school);
			$("#info-update-class").val(data.data.gradeMajor);
			$("#info-update-qq").val(data.data.qq);
			$("#info-update-tel").val(data.data.telephone);
			$("#info-update-mail").val(data.data.email);
		},
		error:function () {
            alert("网络错误");
        }
	});
});

function setCustomerInfo(){
	$.ajax({
		url:"update_staff_info.do",
		type:"POST",
		data:{
			"infoId":$.cookie("infoId"),
			"identityNum":$("#info-update-ID-input").val(),
			"school":$("#school-select").html(),
			"gradeMajor":$("#info-update-class").val(),
			"qq":$("#info-update-qq").val(),
			"telephone":$("#info-update-tel").val(),
			"email":$("#info-update-mail").val()
			},
		success: function(data){
			alert(data.info);
		},
		error:function () {
            alert("网络错误");
        }
	});

};