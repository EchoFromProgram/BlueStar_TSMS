package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScoreController {

	@RequestMapping(path = "score_admin.do", produces = {"application/json;charset=UTF8"})
	public String signAdmin() {
		return "score_admin";
	}
	
	@RequestMapping(path = "score_student.do", produces = {"application/json;charset=UTF8"})
	public String signStudent() {
		return "score_student";
	}
	
	@RequestMapping(path = "score_teacher.do", produces = {"application/json;charset=UTF8"})
	public String signTeacher() {
		return "score_teacher";
	}
}
