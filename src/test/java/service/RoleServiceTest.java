package service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import dao.RoleDao;
import dto.AccountDto;
import entity.PowerId;
import entity.Role;
import entity.RolePower;
import utils.ListUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class RoleServiceTest {
	@Autowired
	private RoleService roleService;
	
	@Test
	public void test() {
////		Role role = new Role();
////		role.setRole("测试");
//		List<Integer> list = new ArrayList<>();
//		list.add(5);
//		list.add(6);
//		List<PowerId> powerIds = ListUtil.integers2Powers(list);
//		RolePower rolePower = new RolePower();
//		rolePower.setPowerIds(powerIds);
////		roleService.insertRole(role, rolePower);
//		rolePower.setRoleId(6);
//		roleService.updateRole(rolePower);
		
		roleService.deleteRole(6);
		
	}

}
