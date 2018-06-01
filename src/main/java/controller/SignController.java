package controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AccountDto;
import entity.Clazz;
import entity.Sign;
import entity.User;
import service.ClassService;
import service.SignService;
import utils.ContextUtil;

@Controller
public class SignController {
	
	@Resource
	private SignService signService;
	
	@Resource
	private ClassService ClassService;
	
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
	
	
	@ResponseBody
	@RequestMapping(path = "init_sign_student.do", produces = {"application/json;charset=UTF8"})
	public Object initSignStudent(Integer page, HttpSession session) {
		User user = new User();
		user.setUserId((Integer)((Map)session.getAttribute("user")).get("user_id"));
		AccountDto<List<Sign>> accountDto = signService.getSignsByUser(page, user);
		return accountDto.getData();
	}
	
	
	@ResponseBody
	@RequestMapping(path = "get_sign_code.do", produces = {"application/json;charset=UTF8"})
	public Object getSignCode(HttpSession session) {
		Integer code = signService.getSignCode();
		ContextUtil.putSignCode((Integer)((Map)session.getAttribute("user")).get("user_id"), code);
		return code;
	}
	
	@ResponseBody
	@RequestMapping(path = "get_course_by_class.do", produces = {"application/json;charset=UTF8"})
	public Object getCourseByClass(Integer classId) {
		AccountDto accountDto = ClassService.getCourseByClassId(classId);
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "admin_get_signs.do", produces = {"application/json;charset=UTF8"})
	public Object adminGetSigns(Integer page, Integer classId, Integer courseId) {
		AccountDto accountDto = null;
		Clazz clazz = new Clazz();
		clazz.setClassId(classId);
		if(classId == 0 && courseId == 0) {
			accountDto = signService.getSigns(page);
		}
		else if (classId != 0 && courseId == 0) {
			accountDto = signService.getClassSigns(page, clazz);
		}
		else if (classId == 0 && courseId != 0) {
			accountDto = signService.getStudentSignsByCourseId(page, courseId);
		}
		else if (classId != 0 && courseId != 0) {
			accountDto = signService.getSignsByCouseIdAndClassId(page, courseId, classId);
		}
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "teacher_get_signs.do", produces = {"application/json;charset=UTF8"})
	public Object teacherGetSigns(Integer page, Integer classId, Integer courseId, HttpSession session) {
		AccountDto accountDto = null;
		Integer userId = (Integer)((Map)session.getAttribute("user")).get("user_id");
		Clazz clazz = new Clazz();
		clazz.setClassId(classId);
		if(classId == 0 && courseId == 0) {
			accountDto = signService.getSignsByUserId(page, userId);
		}
		else if (classId != 0 && courseId == 0) {
			accountDto = signService.getStudentSignsByClass(page, clazz);
		}
		else if (classId == 0 && courseId != 0) {
			accountDto = signService.getSignsByCourseIdAndHisClassId(page, userId, courseId);
		}
		else if (classId != 0 && courseId != 0) {
			accountDto = signService.getSignsByCouseIdAndClassId(page, courseId, classId);
		}
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "student_sign.do", produces = {"application/json;charset=UTF8"})
	public Object studentSign(HttpSession session, Integer inputCode, String reason) {
		Integer realCode = ContextUtil.getSignCode(1);
		Integer classId = ((List<Clazz>)session.getAttribute("hisClasses")).get(0).getClassId();
		User user = new User();
		user.setUserId((Integer)((Map)session.getAttribute("user")).get("user_id"));
		AccountDto accountDto = signService.sign(user, inputCode, realCode, classId, reason);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "teacher_sign.do", produces = {"application/json;charset=UTF8"})
	public Object teacherSign(HttpSession session, String reason) {
		Integer classId = ((List<Clazz>)session.getAttribute("hisClasses")).get(0).getClassId();
		User user = new User();
		user.setUserId((Integer)((Map)session.getAttribute("user")).get("user_id"));
		AccountDto accountDto = signService.sign(user, classId, reason);
		return accountDto;
	}
}
