package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.User;
import service.AccountService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Resource(name="accountServiceImpl")
	AccountService accountService;
	
	@ResponseBody
	@RequestMapping(path = "loginCheck.do", produces = {"application/json;charset=UTF8"})
	public Object loginCheck(User user)
	{
		System.out.println(user);
		return accountService.login(user);
	}
	
	@RequestMapping(path = "index.do", produces = {"application/json;charset=UTF8"})
	public String loginSuccess() {
		return "index";
	}
	
	@RequestMapping(path = "login.do", produces = {"application/json;charset=UTF8"})
	public String login() {
		return "Login";
	}
}
