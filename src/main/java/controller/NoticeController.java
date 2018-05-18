package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AccountDto;
import entity.Clazz;
import entity.Notice;
import entity.NoticeDetail;
import entity.User;
import service.NoticeService;

import java.util.List;
import java.util.Map;

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
	public Object getTeacherAllNotice(Integer page, HttpSession session) {
		User user = new User();
		user.setUserId((Integer)((Map)session.getAttribute("user")).get("user_id"));
		AccountDto accountDto = noticeService.getNotices(page, user);
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "student_get_all_notice.do", produces = {"application/json;charset=UTF8"})
	public Object getStudentAllNotice(Integer page, HttpSession session) {
		AccountDto accountDto = noticeService.getNotices(page, ((List<Clazz>)session.getAttribute("hisClasses")).get(0).getClassId());
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "admin_get_all_notice.do", produces = {"application/json;charset=UTF8"})
	public Object getAdminAllNotice(Integer page) {
		AccountDto accountDto = noticeService.getAllNoticeDetails(page);
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
	
	@ResponseBody
	@RequestMapping(path = "update_notice.do", produces = {"application/json;charset=UTF8"})
	public Object updateNotice(NoticeDetail noticeDetail) {
		AccountDto accountDto = noticeService.updateNoticeDetailByNoticeDetailId(noticeDetail);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "get_one_notice.do", produces = {"application/json;charset=UTF8"})
	public Object getOneNotice(Integer noticeId) {
		AccountDto accountDto = noticeService.getNoticeDetail(noticeId);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "add_notice.do", produces = {"application/json;charset=UTF8"})
	public Object addNotice(Integer classId, String title, String content, HttpSession session) {
		Notice notice = new Notice();
		notice.setClassId(classId);
		notice.setUserId((Integer)((Map)session.getAttribute("user")).get("user_id"));
		NoticeDetail noticeDetail = new NoticeDetail();
		noticeDetail.setTitle(title);
		noticeDetail.setContent(content);
		AccountDto accountDto = noticeService.insertNotice(notice, noticeDetail);
		
		return accountDto;
	}
}
