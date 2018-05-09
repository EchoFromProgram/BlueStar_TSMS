package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AccountDto;
import entity.User;
import service.NoticeService;

@Controller
public class NoticeController {
	
	@Resource
	private NoticeService noticeService;
	
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
	
	@ResponseBody
	@RequestMapping(path = "teacher_get_all_notice.do", produces = {"application/json;charset=UTF8"})
	public Object getAllNotice(Integer page, Integer userId) {
		User user = new User();
		user.setUserId(userId);
		AccountDto accountDto = noticeService.getNotices(page, user);
		return accountDto.getData();
	}
}
