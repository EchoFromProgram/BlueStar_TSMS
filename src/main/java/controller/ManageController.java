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
import utils.ListUtil;

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
		userClass.setClassIds(ListUtil.strings2integers(classArr));
		AccountDto accountDto = accountService.createAccount(user, userClass);
		return accountDto;
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
		userClass.setClassIds(ListUtil.strings2integers(classArr));
		AccountDto accountDto = accountService.updateUser(user, userClass);
		return accountDto;
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
		AccountDto accountDto = accountService.getUserByUserNameForUpdate(userName);
		return accountDto;
	}
	
	/***
	 * 管理员根据用户类型查询所有用户信息，分页处理
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
		return accountDto;
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
	public Object createRole(String roleName, String[] roleIds) {
		Role role = new Role();
		RolePower rolePower = new RolePower();
		role.setRole(roleName);
		rolePower.setPowerIds(ListUtil.strings2integers(roleIds));
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
	
	
	/***
	 * 更新角色权限
	 * 
	 * @param roleId
	 * @param powerIds
	 * @return 操作提示
	 */
	@ResponseBody
	@RequestMapping(path = "update_role.do", produces = {"application/json;charset=UTF8"})
	public Object updateRole(Integer roleId, String[] powerIds) {
		RolePower rolePower = new RolePower();
		rolePower.setRoleId(roleId);
		rolePower.setPowerIds(ListUtil.strings2integers(powerIds));
		AccountDto accountDto = roleService.updateRole(rolePower);
		return accountDto;
	}
	
	/***
	 * 添加班级，需要输入班级名称（班级名称唯一）
	 * 
	 * @param className
	 * @return 操作提示
	 */
	@ResponseBody
	@RequestMapping(path = "add_class.do", produces = {"application/json;charset=UTF8"})
	public Object addClass(String className) {
		Clazz clazz = new Clazz();
		clazz.setClassName(className);
		AccountDto accountDto = accountService.saveClass(clazz);
		return accountDto;
	}
}
