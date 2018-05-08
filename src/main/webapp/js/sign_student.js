	$(function(){
		$.ajax({
	        type: "POST",
	        url: "init_sign_student.do",
	        dataType: "json",
	        data:{"page":"1", "userId":$.cookie('userData')},
	        success: function(data){
	        	console.log(data);
	        	$.each(data.list ,function(i, item){
	        	$("#sign-student-tr").append(
	        			'<tr><td>' + item.classId + '</td>'
	        			+ '<td>' + item.userId + '</td>'
	        			+ '<td>' + "java课程" + '</td>'
	        			+ '<td>' + dateFormat(new Date(item.date)) + '</td>'
	        			+ '<td>' + item.status + '</td>'
	        			+ '<td>' + item.reason + '</td></tr>'
	        			);
	        	});
	        },
	        error:function () {
	            alert("网络错误");
	        }
	    });
	});