//载入页面的时候 获取信息
$(function(){
	loadInfo();
});

function loadInfo(){
	$.ajax({
		url:"get_staff_info.do",
		dataTpye:"json",
		type:"POST",
		success: function(data){
			$("#name-display").html($("#username").text());
			$("#id-display").html(data.data.identityNum);
			$("#resume-display").html(data.data.resume);
			$("#qq-display").html(data.data.qq);
			$("#tel-display").html(data.data.telephone);
			$("#mail-display").html(data.data.email);
			
			$("#info-update-ID-input").val(data.data.identityNum);
			$("#info-update-intro-input").val(data.data.resume);
			$("#info-update-qq-input").val(data.data.qq);
			$("#info-update-qq-tel").val(data.data.telephone);
			$("#info-update-mail-input").val(data.data.email);
		},
		error:function () {
            alert("网络错误");
        }
	});
}

//设置信息（这里需要设置同步 async: false,）
function setStaffInfo(){
	$.ajax({
		url:"update_staff_info.do",
		type:"POST",
		async: false,
		data:{
			"identityNum":$("#info-update-ID-input").val(),
			"resume":$("#info-update-intro-input").val(),
			"qq":$("#info-update-qq-input").val(),
			"telephone":$("#info-update-qq-tel").val(),
			"email":$("#info-update-mail-input").val()
			},
		success: function(data){
			alert(data.info);
			window.location.reload();
		},
		error:function () {
            alert("员工信息更新失败");
        }
	});
	return false;
};