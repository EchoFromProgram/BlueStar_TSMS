//获取签到密码
$("#modal-pub").click(function(){
		$.ajax({
			url:"get_sign_code.do",
			type:"POST",
			success: function(data){
				console.log(data);
				$("#sign-code-display").html(data);
			},
			error:function () {
	            alert("网络错误");
	        }
		});
});

var Courses;
//获取课程列表
$(function(){
		$.ajax({
			url:"get_courses.do",
			type:"POST",
			dataType:"json",
			success: function(data){
				Courses = data;
				
				$("#which-stage").append('<option value="0">全部课程</option>');
				$.each(Courses,function(index, item){
				    var option = $("<option></option>").append(item.course);
				    option.attr("value", item.courseId);
				    option.appendTo("#which-stage");
				});
			},
			error:function () {
	            alert("网络错误");
	        }
		});
});
	
//获取班级列表
$(function(){
	$.ajax({
		url:"getSessionHisClasses.do",
		type:"POST",
		dataType:"json",
		success: function(data){
			$("#which-class").empty();
			$("#which-class").append('<option value="0">全部班级</option>');
		    $.each(data,function(index, item){
		        var option = $("<option></option>").append(item.className);
		        option.attr("value", item.classId);
		        option.appendTo("#which-class");
		    });
		    $("#which-class").change(function(){
	        	getCourseByClass(this.value)
	        });
		},
		error:function () {
            alert("网络错误");
        }
	});
});

//根据班级获取课程
function getCourseByClass(classId){
	$.ajax({
		url:"get_course_by_class.do",
		type:"POST",
		dataType:"json",
		data:{"classId":classId},
		success: function(data){
			$("#which-stage").val(data.courseId);
		},
		error:function () {
            alert("网络错误");
        }
	});
}

//载入页面默认显示所有签到信息
function getAllSign(){
	$.ajax({
		url:"",
		type:"POST",
		data:{"classId":$("#which-class").val(), "stageId":$("#which-stage").val()},
		dataType:"json",
		success: function(data){
			console.log(data);
		},
		error:function () {
            alert("网络错误");
        }
	});
};


//点击查询按钮获取相应的签到信息
$("#submit-which-need").click(function(){
	alert($("#which-stage").val());
	alert($("#which-class").val());
});

	