package dao;

import entity.Customer;
import entity.Staff;
import entity.User;

/**
 * 该方法用于管理用户
 *
 * @author happyChicken
 */
public interface AccountDao
{
	
	/**
	 * 根据记录数判断用户名是否存在
	 * @param username 传入一个用户名，用来判断用户名是否存在
	 * @return 返回记录数，1是用户名已存在，0是不存在
	 */
	public int userNameIsExit(String username);
	
    /**
     * 登录的方法，通过传入的用户名得到一个用户(user)对象
     * @param username 登录时传入的用户名
     * @return 返回一个用户（user）对象
     */
    public User getUserByUserName(String username);

    /**
     * 创建一个新用户
     * @param user 传入一个用户类，用来创建一个新用户
     * @return 返回影响的行数，如果是1则创建成功
     */
    public int createAccount(User user);

   /**
    * 设置员工信息
    * @param staff 传入一个员工类，用来设置员工信息
    */
    public void settingStaffInfo(Staff staff);

    /**
     * 设置客户信息
     * @param customer 传入一个客户类，用来设置客户信息
     */
    public void settingCustomerInfo(Customer customer);
    
    /**
     * 根据信息id查询员工表中某个员工的信息
     * @param infoId 信息id，根据该id查询具体信息
     */
    public void getStaffDetailByInfoId(int infoId);
    
}
