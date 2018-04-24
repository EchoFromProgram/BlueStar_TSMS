package controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AccountDto;
import entity.User;
import enums.LoginStatus;
import service.AccountService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Resource(name="accountServiceImpl")
	AccountService accountService;
	
	@ResponseBody
	@RequestMapping(path = "loginCheck.do", produces = {"application/json;charset=UTF8"})
	public Object loginCheck(User user, HttpSession session)
	{
		System.out.println(user);
		AccountDto accountDto = accountService.login(user);
		if(((LoginStatus)accountDto.getStatus()).getCode() == 0)
		{
			System.out.println("-----------------");
			
			session.setAttribute("user", ((Map)accountDto.getData()).get("user"));
			session.setAttribute("hisPower", ((Map)accountDto.getData()).get("hisPowers"));
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
	@RequestMapping(path = "getSessionHisPower.do", produces = {"application/json;charset=UTF8"})
	public Object getSessionHisPower(HttpSession session) {
		// TODO html访问session， 
		return session.getAttribute("user");
	}
}
