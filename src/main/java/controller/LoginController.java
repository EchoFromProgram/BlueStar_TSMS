package controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AccountDto;
import entity.User;
import service.AccountService;
import service.InitService;

@Controller
public class LoginController {
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private InitService InitService;
	
	@ResponseBody
	@RequestMapping(path = "loginCheck.do", produces = {"application/json;charset=UTF8"})
	public Object loginCheck(User user, HttpSession session)
	{
		System.out.println(user);
		AccountDto accountDto = accountService.login(user);
		if(accountDto.getCode() == 0)
		{
			System.out.println("-----------------");
			
			session.setAttribute("user", ((Map)accountDto.getData()).get("user"));
			session.setAttribute("hisPowers", ((Map)accountDto.getData()).get("hisPowers"));
			session.setAttribute("hisClasses", ((Map)accountDto.getData()).get("hisClasses"));
		}
		return accountDto;
	}
	
	@RequestMapping(path = "index.do", produces = {"application/json;charset=UTF8"})
	public String loginSuccess() {
		return "index";
	}
	
	@RequestMapping(path = "login.do", produces = {"application/json;charset=UTF8"})
	public String login() {
		return "Login";
	}
	
	@ResponseBody
	@RequestMapping(path = "getSessionUser.do", produces = {"application/json;charset=UTF8"})
	public Object getSessionUser(HttpSession session) {
		// TODO html访问session， 
		return session.getAttribute("user");
	}
	
	@ResponseBody
	@RequestMapping(path = "getSessionHisPowers.do", produces = {"application/json;charset=UTF8"})
	public Object getSessionHisPower(HttpSession session) {
		// TODO html访问session， 
		return session.getAttribute("hisPowers");
	}
	
	@ResponseBody
	@RequestMapping(path = "getSessionHisClasses.do", produces = {"application/json;charset=UTF8"})
	public Object getSessionHisClasses(HttpSession session) {
		// TODO html访问session， 
		return session.getAttribute("hisClasses");
	}
	
	@ResponseBody
	@RequestMapping(path = "getPowerTable.do", produces = {"application/json;charset=UTF8"})
	public Object getPowerTable(HttpSession session) {
		//返回权限表
		//TO DO 这里需要直接返回，和上面的hisPower方法合并
		return session.getServletContext().getAttribute("powerMap");
	}
	
	@ResponseBody
	@RequestMapping(path = "get_courses.do", produces = {"application/json;charset=UTF8"})
	public Object getCourses(HttpSession session) {
		//返回课程表
		return session.getServletContext().getAttribute("Courses");
	}
}
