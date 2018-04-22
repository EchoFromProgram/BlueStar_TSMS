package dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
		fail("Not yet implemented");
	}

	@Test
	public void testCreateAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testSettingStaffInfo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSettingCustomerInfo() {
		fail("Not yet implemented");
	}

}
