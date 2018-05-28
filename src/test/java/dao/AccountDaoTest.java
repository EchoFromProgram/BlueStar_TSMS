package dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.aspectj.bridge.MessageWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;

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
import entity.UserClass;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class AccountDaoTest {

	@Autowired
	public AccountDao accountDao;
	
	@Test
	public void testUserNameIsExit() {
		int num = accountDao.userNameIsExit("2222");
		System.out.println(num);
	}

	@Test
	public void testGetUserByUserName() {
		Map<String, Object> user = accountDao.getUserByUserName("Fish");
		System.out.println(user);
	}

	@Test
	public void testCreateAccount() {
		
		Customer customer = new Customer();
		accountDao.inserIntoCustomer(customer);
		System.out.println(customer.getInfoId());
	}

	@Test
	public void testSettingStaffInfo() {
		Staff staff = new Staff();
		staff.setQq("1112");
		staff.settId(1);
		staff.setEmail("131");
		accountDao.settingStaffInfo(staff);
	}

	@Test
	public void testSettingCustomerInfo() {
		Customer customer = new Customer();
		customer.setEmail("1133");
		customer.setInfoId(1);
		customer.setCity("福州");
		accountDao.settingCustomerInfo(customer);
	}
	
	@Test
	public void testGetPowerIdByRoleId() {
		int roleId = 2;
		List<Integer> list = accountDao.getPowerIdByRoleId(roleId);
		System.out.println(list);
	}
	
	@Test
	public void testGetPowers() {
		List<Power> list = accountDao.getPowers();
		System.out.println(list);
	}
	
	@Test
	public void testGetStaffDetailByTid() {
		Staff staff = accountDao.getStaffDetailByTid(1);
		System.out.println(staff);
	}
	
	@Test
	public void testGetCustomerDetailByInfoId() {
		Customer customer = accountDao.getCustomerDetailByInfoId(1);
		System.out.println(customer);
	}
	

	
	@Test
	public void testGetCoursesByCourseId() {
		Course course = accountDao.getCoursesByCourseId(2);
		System.out.println(course);
	}
	
	@Test 
	public void testGetClassesIdsByClassId() {
		Clazz class1 = accountDao.getClassByClassId(2);
		System.out.println(class1);
		Clazz class2 = accountDao.getClassByClassId(2);
		
	}
	
	@Test
	public void testGetClassIdsByStaffId() {
		List<Integer> list = accountDao.getClassIdsByUserId(1);
		System.out.println(list);
	}
	
	@Test
	public void testGetAllUsers() {
		List<User> list = accountDao.getAllUsers();
		System.out.println(list);
	}
	
	@Test
	public void testPro() {
		List<School> list = accountDao.getSchoolsByCityId(1);
		System.out.println(list);
	}
	
	@Test
	public void test1() {
		User user = new User();
		user.setUserId(16);
		user.setUserName("xxx");
		user.setPassword("15141");
		user.setName("你好");
		accountDao.updateUser(user);
	}
	
	

}
