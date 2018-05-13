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
			$("#school-select").val(data.data.school);
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

//获取省份
$("#modal-info-update-a").click(
	function(){
		$.ajax({
			url:"http://119.29.166.254:9090/api/provinces",
			type:"POST",
			dataType:"json",
			success: function(data){
				$("#province-select").empty();
				$("#province-select").append("<option value='' disabled selected style='display:none;'>请选择省份</option>  ");
			    $.each(data,function(index, item){
			    	var option = $("<option></option>").append(item.name);
					option.attr("value", item.id);
					option.appendTo("#province-select");
			    });
			},
			error:function () {
			    alert("网络错误");
			}
		});
});

//获取市
$("#province-select").change(
		function(){
			$.ajax({
			    url:"http://119.29.166.254:9090/api/province/getCitiesByProvinceId",
			    type:"POST",
			    dataType:"json",
			    data:{"id":$("#province-select").val()},
			    success: function(data){
			    	$("#city-select").empty();
			    	$("#city-select").append("<option value='' disabled selected style='display:none;'>请选择城市</option>");
				    $.each(data,function(index, item){
				    	var option = $("<option></option>").append(item);
						option.appendTo("#city-select");
				    });
			    },
			    error:function () {
			        alert("网络错误");
			    }
			});
	});

//获取学校
$("#city-select").change(
		function(){
			$.ajax({
			    url:"http://119.29.166.254:9090/api/university/getUniversityByCityName",
			    type:"POST",
			    dataType:"json",
			    data:{"name":$("#city-select").val()},
			    success: function(data){
			    	$("#school-select").empty();
			    	$("#school-select").append("<option value='' disabled selected style='display:none;'>请选择学校</option>");
				    $.each(data,function(index, item){
				    	var option = $("<option></option>").append(item.name);
				    	option.attr("value", item.id);
						option.appendTo("#school-select");
				    });
			    },
			    error:function () {
			        alert("网络错误");
			    }
			});
	});






