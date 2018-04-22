package dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Customer;
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

}
