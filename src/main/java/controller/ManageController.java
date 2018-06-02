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
import com.mysql.fabric.xmlrpc.base.Array;

import dto.AccountDto;
import entity.Clazz;
import entity.Role;
import entity.RolePower;
import entity.User;
import entity.UserClass;
import enums.impl.CreateAccountStatus;
import service.AccountService;
import service.RoleService;

@Controller
public class ManageController {

	@Resource
	private AccountService accountService;
	
	@Resource
	private RoleService roleService;
	
	//页面转跳用户管理
	@RequestMapping(path = "user_manage.do", produces = {"application/json;charset=UTF8"})
	public String userManage() {
		return "user_manage";
	}
	
	//页面转跳角色管理
	@RequestMapping(path = "role_manage.do", produces = {"application/json;charset=UTF8"})
	public String roleManage() {
		return "role_manage";
	}
	
	/***
	 * 新建用户，传入信息和班级数组
	 * 
	 * @param user
	 * @param classArr
	 * @return 操作提示
	 */
	@ResponseBody
	@RequestMapping(path = "insert_user.do", produces = {"application/json;charset=UTF8"})
	public Object insertUser(User user, String[] classArr) {
		UserClass userClass = new UserClass();
		if (classArr != null && classArr.length != 0) {
			List<Integer> classList = new ArrayList<>();
			for (String string : classArr) {
				classList.add(Integer.valueOf(string.trim()));
			}
			userClass.setClassIds(classList);
			AccountDto accountDto = accountService.createAccount(user, userClass);
			return accountDto;
		}
		
		return new AccountDto(CreateAccountStatus.CLASS_IS_NULL);
	}
	
	/***
	 * 更新用户，传入信息和班级数组
	 * 
	 * @param user
	 * @param classArr
	 * @return 操作提示
	 */
	@ResponseBody
	@RequestMapping(path = "update_user.do", produces = {"application/json;charset=UTF8"})
	public Object updateUser(User user, String[] classArr) {
		UserClass userClass = new UserClass();
		if (classArr != null && classArr.length != 0) {
			List<Integer> classList = new ArrayList<>();
			for (String string : classArr) {
				classList.add(Integer.valueOf(string.trim()));
			}
			userClass.setClassIds(classList);
			AccountDto accountDto = accountService.updateUser(user, userClass);
			return accountDto;
		}
		
		return new AccountDto(CreateAccountStatus.CLASS_IS_NULL);
	}
	
	/***
	 * 辅助函数，更新用户前查找是是不是存在这个账号
	 * 
	 * @param userName
	 * @return 存在则返回对应的用户信息
	 */
	@ResponseBody
	@RequestMapping(path = "find_user.do", produces = {"application/json;charset=UTF8"})
	public Object findUser(String userName) {
		System.out.println(userName);
		AccountDto accountDto = accountService.getUserByUserNameForUpdate(userName);
		return accountDto;
	}
	
	/***
	 * 根据用户类型查询所有用户信息，分页处理
	 * 
	 * @param page
	 * @param typeId
	 * @return 用户列表
	 */
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
	
	/***
	 * 创建角色，关联相关的权限
	 * 
	 * @param roleName
	 * @param roleIds
	 * @return 操作提示
	 */
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
	
	/***
	 * 获取所有角色，这里不做分页处理
	 * 
	 * @return 角色以及它的权限的列表
	 */
	@ResponseBody
	@RequestMapping(path = "get_all_role.do", produces = {"application/json;charset=UTF8"})
	public Object getAllRole() {
		AccountDto accountDto = roleService.getRoles();
		return accountDto;
	}
	
	/***
	 * 根据roleId删除角色
	 * 
	 * @param roleId
	 * @return 操作提示
	 */
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
