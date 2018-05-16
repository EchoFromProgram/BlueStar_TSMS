package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Role;
import entity.RolePowerName;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RoleDaoTest {
	@Autowired
	private RoleDao roleDao;
	@Test
	public void test() {
		List<Role> list = roleDao.getRolesPowerName();
		System.out.println(list);
	}

}
