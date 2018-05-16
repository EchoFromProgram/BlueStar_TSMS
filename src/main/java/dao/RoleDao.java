package dao;

import java.util.List;
import java.util.Map;

import entity.Role;
import entity.RolePowerName;

/**
 * 角色管理方法
 * 
 * @author 王鸿175
 *
 */
public interface RoleDao {
	
	/**
	 * 得到角色名已经所拥有的权限名
	 * @return
	 */
	public List<Role> getRolesPowerName();
}
