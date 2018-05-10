package controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AccountDto;
import entity.Sign;
import entity.User;
import service.ClassService;
import service.SignService;

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
	public Object initSignStudent(Integer page, Integer userId) {
		User user = new User();
		user.setUserId(userId);
		AccountDto<List<Sign>> accountDto = signService.getSignsByUser(page, user);
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "admin_get_signs.do", produces = {"application/json;charset=UTF8"})
	public Object adminGetSigns(Integer page) {
		AccountDto<List<Sign>> accountDto = signService.getSigns(page);
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "get_sign_code.do", produces = {"application/json;charset=UTF8"})
	public Object getSignCode() {
		Integer code = signService.getSignCode();
		return code;
	}
	
	@ResponseBody
	@RequestMapping(path = "get_course_by_class.do", produces = {"application/json;charset=UTF8"})
	public Object getCourseByClass(Integer classId) {
		AccountDto accountDto = ClassService.getCourseByClassId(classId);
		return accountDto.getData();
	}
}
