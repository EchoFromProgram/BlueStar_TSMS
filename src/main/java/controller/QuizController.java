package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuizController {

	@RequestMapping(path = "quiz_admin.do", produces = {"application/json;charset=UTF8"})
	public String quizAdmin() {
		return "quiz_admin";
	}
	
	@RequestMapping(path = "quiz_student.do", produces = {"application/json;charset=UTF8"})
	public String quizStudent() {
		return "quiz_student";
	}
	
	@RequestMapping(path = "quiz_teacher.do", produces = {"application/json;charset=UTF8"})
	public String quizTeacher() {
		return "quiz_teacher";
	}
}
