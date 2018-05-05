package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignController {
	
	@RequestMapping(path = "sign_admin.do", produces = {"application/json;charset=UTF8"})
	public String signAdmin() {
		return "sign_admin";
	}
	
	@RequestMapping(path = "sign_student.do", produces = {"application/json;charset=UTF8"})
	public String signStudent() {
		return "sign_student";
	}
	
	@RequestMapping(path = "sign_teacher.do", produces = {"application/json;charset=UTF8"})
	public String signTeacher() {
		return "sign_teacher";
	}
}
