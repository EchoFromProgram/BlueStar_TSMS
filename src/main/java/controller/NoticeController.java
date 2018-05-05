package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NoticeController {
	@RequestMapping(path = "notice_admin.do", produces = {"application/json;charset=UTF8"})
	public String noticeAdmin() {
		return "notice_admin";
	}
	
	@RequestMapping(path = "notice_student.do", produces = {"application/json;charset=UTF8"})
	public String noticeStudent() {
		return "notice_student";
	}
	
	@RequestMapping(path = "notice_teacher.do", produces = {"application/json;charset=UTF8"})
	public String noticeTeacher() {
		return "notice_teacher";
	}
}
