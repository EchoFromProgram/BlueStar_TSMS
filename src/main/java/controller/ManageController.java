package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.Page;

import dto.AccountDto;
import entity.Clazz;
import entity.Role;
import entity.RolePower;
import entity.User;
import entity.UserClass;
import service.AccountService;
import service.RoleService;

@Controller
public class ManageController {

	@Resource
	private AccountService accountService;
	
	@Resource
	private RoleService roleService;
	
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
	public Object insertUser(User user, Integer[] classArr) {
		UserClass userClass = new UserClass();
		List<Integer> classList = Arrays.asList(classArr);
		userClass.setClassIds(classList);
		AccountDto accountDto = accountService.createAccount(user, userClass);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "get_all_accounts.do", produces = {"application/json;charset=UTF8"})
	public Object getAllAccounts(Integer page, Integer typeId) {
		AccountDto accountDto = null;
		System.out.println(typeId);
		if (typeId == -1) {
			accountDto = accountService.getAllAccounts(page);
		}
		else {
			accountDto = accountService.getAccounts(page, typeId);
		}
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "create_role.do", produces = {"application/json;charset=UTF8"})
	public Object createRole(String roleName, Integer[] roleIds) {
		System.out.println(roleName);
		System.out.println(roleIds);
		Role role = new Role();
		RolePower rolePower = new RolePower();
		role.setRole(roleName);
		List<Integer> roleArr = Arrays.asList(roleIds);
		rolePower.setPowerIds(roleArr);
		AccountDto accountDto = roleService.insertRole(role, rolePower);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "get_all_role.do", produces = {"application/json;charset=UTF8"})
	public Object getAllRole() {
		AccountDto accountDto = roleService.getRoles();
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "delete_role.do", produces = {"application/json;charset=UTF8"})
	public Object deleteRole(Integer roleId) {
		AccountDto accountDto = roleService.deleteRole(roleId);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "update_role.do", produces = {"application/json;charset=UTF8"})
	public Object updateRole(Integer roleId, Integer[] powerIds) {
		RolePower rolePower = new RolePower();
		List<Integer> powerIdArray = Arrays.asList(powerIds);
		rolePower.setRoleId(roleId);
		rolePower.setPowerIds(powerIdArray);
		AccountDto accountDto = roleService.updateRole(rolePower);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "add_class.do", produces = {"application/json;charset=UTF8"})
	public Object addClass(String className) {
		Clazz clazz = new Clazz();
		clazz.setClassName(className);
		AccountDto accountDto = accountService.saveClass(clazz);
		return accountDto;
	}
}
