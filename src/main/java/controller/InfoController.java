package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InfoController {

	@RequestMapping(path = "customer_info.do", produces = {"application/json;charset=UTF8"})
	public String signStudent() {
		return "customer_info";
	}
	
	@RequestMapping(path = "staff_info.do", produces = {"application/json;charset=UTF8"})
	public String signTeacher() {
		return "staff_info";
	}
}
