package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManageController {

	@RequestMapping(path = "user_manage.do", produces = {"application/json;charset=UTF8"})
	public String signStudent() {
		return "user_manage";
	}
	
	@RequestMapping(path = "role_manage.do", produces = {"application/json;charset=UTF8"})
	public String signTeacher() {
		return "role_manage";
	}
}
