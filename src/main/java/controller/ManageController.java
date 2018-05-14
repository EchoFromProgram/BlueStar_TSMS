package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import dto.AccountDto;
import entity.User;
import service.AccountService;

@Controller
public class ManageController {

	@Resource
	private AccountService accountService;
	
	@RequestMapping(path = "user_manage.do", produces = {"application/json;charset=UTF8"})
	public String userManage() {
		return "user_manage";
	}
	
	@RequestMapping(path = "role_manage.do", produces = {"application/json;charset=UTF8"})
	public String roleManage() {
		return "role_manage";
	}
	
	@ResponseBody
	@RequestMapping(path = "insert_user.do", produces = {"application/json;charset=UTF8"})
	public Object insertUser(User user) {
		AccountDto accountDto = accountService.createAccount(user);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "get_all_accounts.do", produces = {"application/json;charset=UTF8"})
	public Object getAllAccounts(Integer page, Integer typeId) {
		AccountDto accountDto = null;
		if (typeId == -1) {
			accountDto = accountService.getAllAccounts(page);
		}
		return accountDto.getData();
	}
}
