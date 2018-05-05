package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InfoController {

	@RequestMapping(path = "customer_info.do", produces = {"application/json;charset=UTF8"})
	public String customerInfo() {
		return "customer_info";
	}
	
	@RequestMapping(path = "staff_info.do", produces = {"application/json;charset=UTF8"})
	public String staffInfo() {
		return "staff_info";
	}
}
