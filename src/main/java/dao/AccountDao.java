package dao;

import java.util.List;

import entity.Clazz;
import entity.Course;
import entity.Customer;
import entity.Power;
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
	public Integer userNameIsExit(String username);
	
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
    public Integer createAccount(User user);

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
     * 查询一个员工的具体信息
     * @param tId 对应的信息id
     * @return 返回一个查询到的员工信息
     */
    public Staff getStaffDetailByTid(Integer tId);
    
    /**
     * 查询一个客户的具体信息
     * @param infoId 对应的信息id
     * @return 返回一个查询到的客户信息
     */
    public Customer getCustomerDetailByInfoId(Integer infoId);
    
    /**
     * 根据角色id查询对应的权限集合
     * @param roleId
     * @return 返回一个权限集合
     */
    public List<Integer> getPowerIdByRoleId(Integer roleId);
    
    /**
     * 得到整张权限表
     * @return 包含多个权限对象的集合
     */
    public List<Power> getPowers();
    
    /**
     * 通过客户的信息id得到对应的班级
     * @param infoId 信息id
     * @return 返回客户所属班级
     */
    public Integer getClassIdByInfoId(Integer infoId);
    
    /**
     * 通过员工id的信息id对应的班级
     * @param staffId 员工id，即信息id
     * @return 
     */
    public List<Integer> getClassIdsByStaffId(Integer staffId);
    
    
    /**
     * 通过班级id得到对应的班
     * @param classId 班级id
     * @return 课程id集合
     */
    public Clazz getClassByClassId(Integer classId);
    
    /**
     * 通过课程id得到对应的课程
     * @param courseId 课程id
     * @return 课程集合
     */
    public Course getCoursesByCourseId(Integer courseId);
    
    
    
}
