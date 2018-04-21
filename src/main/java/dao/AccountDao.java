package dao;

import entity.User;

/**
 * 该方法用于管理用户
 * @author happyChicken
 */
public interface AccountDao {
	
	/**
	 * 登录的方法，通过传入的用户名得到一个用户(user)对象
	 * @param username 登录时传入的用户名
	 * @return 返回一个用户（user）对象
	 */
	public User getUserByUserName(String username);
	
	/**
	 * 创建一个新账户，赋予新账户相应的角色
	 * @param role 传入的角色id,根据该id赋予相应的角色
	 */
	public void createAccount(User user);
	
	/**
	 * 设置员工（Staff）的信息
	 * @param identityNum 身份证
	 * @param resume 履历
	 * @param qq QQ号
	 * @param telephone 电话号码
	 * @param email 邮箱
	 */
	public void settingStaffInfo(String identityNum, String resume, String qq, 
								 String telephone, String email);
	
	/**
	 * 设置客户（customer）的信息
	 * @param identityNum 身份证
	 * @param school 学校
	 * @param gradeMajor 专业班级	
	 * @param telephone 电话
	 * @param email 邮箱
	 * @param qq QQ号
	 * @param classId 班级id
	 */
	public void settingCustomerInfo(String identityNum, String school, String gradeMajor, 
			 						String telephone, String email, String qq, int classId);
}
