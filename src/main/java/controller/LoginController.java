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
	
	/***
	 * 登陆验证
	 * 如果信息正确，将信息，权限和班级放入session
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "loginCheck.do", produces = {"application/json;charset=UTF8"})
	public Object loginCheck(User user, HttpSession session)
	{
		AccountDto accountDto = accountService.login(user);
		if(accountDto.getCode() == 0)
		{
			
			session.setAttribute("user", ((Map)accountDto.getData()).get("user"));
			session.setAttribute("hisPowers", ((Map)accountDto.getData()).get("hisPowers"));
			session.setAttribute("hisClasses", ((Map)accountDto.getData()).get("hisClasses"));
		}
		return accountDto;
	}
	
	//转跳首页
	@RequestMapping(path = "index.do", produces = {"application/json;charset=UTF8"})
	public String loginSuccess() {
		return "index";
	}
	
	//转跳登陆界面
	@RequestMapping(path = "login.do", produces = {"application/json;charset=UTF8"})
	public String login() {
		return "Login";
	}
	
	//session获取信息(现在通过直接在session传入controller，无需传到前台，此方法基本不会用到)
	@ResponseBody
	@RequestMapping(path = "getSessionUser.do", produces = {"application/json;charset=UTF8"})
	public Object getSessionUser(HttpSession session) {
		// TODO html访问session， 
		return session.getAttribute("user");
	}
	
	//session获取权限(现在通过直接在session传入controller，无需传到前台，此方法基本不会用到)
	@ResponseBody
	@RequestMapping(path = "getSessionHisPowers.do", produces = {"application/json;charset=UTF8"})
	public Object getSessionHisPower(HttpSession session) {
		// TODO html访问session， 
		return session.getAttribute("hisPowers");
	}
	
	//session获取班级(现在通过直接在session传入controller，无需传到前台，此方法基本不会用到)
	@ResponseBody
	@RequestMapping(path = "getSessionHisClasses.do", produces = {"application/json;charset=UTF8"})
	public Object getSessionHisClasses(HttpSession session) {
		// TODO html访问session， 
		return session.getAttribute("hisClasses");
	}
	
	@ResponseBody
	@RequestMapping(path = "getPowerTable.do")
	public Object getPowerTable(HttpSession session) {
		//返回权限表
		//TO DO 这里需要直接返回，和上面的hisPower方法合并
		return session.getServletContext().getAttribute("powerMap");
	}
	
	//从全局获取课表
	@ResponseBody
	@RequestMapping(path = "get_courses.do", produces = {"application/json;charset=UTF8"})
	public Object getCourses(HttpSession session) {
		//返回课程表
		return session.getServletContext().getAttribute("Courses");
	}
}
