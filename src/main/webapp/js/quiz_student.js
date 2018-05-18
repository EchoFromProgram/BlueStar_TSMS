$(function(){
	$.ajax({
	    url:"get_quiz_question.do",
	    type:"POST",
	    dataType:"json",
	    success: function(data){
	    	$("#quiz-answer").empty();
	    	$.each(data.questions,function(index, item){
	    		$("#quiz-answer").append('<div class="form-group">'+
	    				'<div class="form-control question-title">'+
	    				item.question +
	    				'</div>'+
	    				'<textarea placeholder="请填写问卷内容" class="form-control question-detail answer-box" required=""></textarea>'+
	    				'</div>'
	    				);
	    	});
	    },
	    error:function () {
	        alert("网络错误");
	    }
	});
});


function submitQuiz(){
	var answers = new Array();
	$.each($(".answer-box"), function(index, item){
		answers.push($(item).val());
	});
	console.log(answers);
	$.ajax({
	    url:"write_quiz.do",
	    type:"POST",
	    dataType:"json",
	    data:{
	    	"userId":$.cookie('userData'),
	    	"answers":answers
	    },
	    traditional: true,
	    success: function(data){
	        alert(data.info);
	    },
	    error:function () {
	        alert("网络错误");
	    }
	});

	return false;
}