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
	public Object getTeacherAllNotice(Integer page, Integer userId) {
		User user = new User();
		user.setUserId(userId);
		AccountDto accountDto = noticeService.getNotices(page, user);
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "student_get_all_notice.do", produces = {"application/json;charset=UTF8"})
	public Object getStudentAllNotice(Integer page, Integer classId) {
		AccountDto accountDto = noticeService.getNotices(page, classId);
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "admin_get_all_notice.do", produces = {"application/json;charset=UTF8"})
	public Object getAdminAllNotice(Integer page) {
		AccountDto accountDto = noticeService.getNotices(page);
		return accountDto.getData();
	}
	
	/**
	 * 提供给教师和管理员，传相应的通知id然后删除通知
	 * 
	 * @param noticeId 
	 * @return accountDto
	 */
	@ResponseBody
	@RequestMapping(path = "delete_notice.do", produces = {"application/json;charset=UTF8"})
	public Object deleteNotice(Integer noticeId) {
		AccountDto accountDto = noticeService.deleteNoticeDetailAndNotice(noticeId);
		return accountDto;
	}
}
