package dao;

import java.util.List;
import java.util.Map;

import javax.jws.soap.SOAPBinding.Use;

import entity.City;
import entity.Clazz;
import entity.Course;
import entity.Customer;
import entity.Power;
import entity.Province;
import entity.School;
import entity.SignData;
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
    public Map<String, Object> getUserByUserName(String username);

    /**
     * 创建一个新用户
     * @param user 传入一个用户类，用来创建一个新用户
     * @return 返回影响的行数，如果是1则创建成功
     */
    public Integer insertIntoUser(User user);
    
    /**
     * 向客户信息表插入一条客户信息
     * @param customer 客户
     * @return 返回影响的行数
     */
    public Integer inserIntoCustomer(Customer customer);
    
    /**
     * 向员工信息表插入一条员工信息
     * @param staff 员工
     * @return 返回影响的行数
     */
    public Integer insertIntoStaff(Staff staff);
   /**
    * 设置员工信息
    * @param staff 传入一个员工类，用来设置员工信息
    */
    public Integer settingStaffInfo(Staff staff);

    /**
     * 设置客户信息
     * @param customer 传入一个客户类，用来设置客户信息
     */
    public Integer settingCustomerInfo(Customer customer);
    
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
     * 通过用户id得到班级id
     * @param staffId 员工id，即信息id
     * @return 班级id集合
     */
    public List<Integer> getClassIdsByUserId(Integer userId);
    
    
    /**
     * 通过班级id得到对应的班级
     * @param classId 班级id
     * @return 班级对象
     */
    public Clazz getClassByClassId(Integer classId);
    
    /**
     * 通过课程id得到对应的课程
     * @param courseId 课程id
     * @return 课程集合
     */
    public Course getCoursesByCourseId(Integer courseId);
    
    /**
     * 得到所有用户
     * @return 用户集合
     */
    public List<User> getAllUsers();
    
    /**
     * 通过用户类型获得用户
     * @param typeId 用户类型id
     * @return 用户集合
     */
    public List<Map<String, Object>> getUsersByTypeId(Integer typeId);
    
    /**
     * 得到所有的省份
     * @return 省份集合
     */
    public List<Province> getProvinces();
    
    /**
     * 根据省份id得到对应的城市
     * @param provinceId 省份id
     * @return 城市集合
     */
    public List<City> getCitysByProvinceId(Integer provinceId);
    
    /**
     * 根据城市id得到对应的学校
     * @param cityId
     * @return 学校集合
     */
    public List<School> getSchoolsByCityId(Integer cityId);
    
    
    /**
     * 得到整张课程表
     * @return 课表集合
     */
    public List<Course> getAllcourses();
    
    /**
     * 根据学生的userId得到他老师的userId
     * @param userId 学生的userId
     * @return 
     */
    public int getHisTeacherUserId(Integer userId);
  
}
