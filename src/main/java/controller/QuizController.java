package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuizController {

	@RequestMapping(path = "quiz_admin.do", produces = {"application/json;charset=UTF8"})
	public String signAdmin() {
		return "quiz_admin";
	}
	
	@RequestMapping(path = "quiz_student.do", produces = {"application/json;charset=UTF8"})
	public String signStudent() {
		return "quiz_student";
	}
	
	@RequestMapping(path = "quiz_teacher.do", produces = {"application/json;charset=UTF8"})
	public String signTeacher() {
		return "quiz_teacher";
	}
}
