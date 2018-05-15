$(function(){
	$.ajax({
	    url:"student_get_score.do",
	    type:"POST",
	    dataType:"json",
	    data:{"page":1, "userId":$.cookie('userData')},
	    success: function(data){
	        console.log(data);
	    },
	    error:function () {
	        alert("网络错误");
	    }
	});
});