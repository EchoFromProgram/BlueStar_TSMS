package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Clazz;
import entity.Course;
import entity.Customer;
import entity.Power;
import entity.Staff;
import entity.User;
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
		User user = accountDao.getUserByUserName("h123");
		System.out.println(user);
	}

	@Test
	public void testCreateAccount() {
		fail("Not yet implemented");
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
		customer.setClassId(1);
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
	}
	
	@Test
	public void testGetClassIdsByStaffId() {
		List<Integer> list = accountDao.getClassIdsByUserId(1);
		System.out.println(list);
	}

}
