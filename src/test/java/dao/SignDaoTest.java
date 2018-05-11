package dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.SingleByteCharsetConverter;

import entity.Sign;
import entity.SignData;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SignDaoTest {
	@Autowired
	public SignDao signDao;
	
	@Test
	public void testInsertIntoSign() {
		Sign sign = new Sign();
		sign.setClassId(2);
		sign.setCourseId(1);
		sign.setDate(new Date());
		sign.setStatus(1);
		sign.setReason("不想来");
		sign.setUserId(1);
		int num = signDao.insertIntoSign(sign);
	}

	@Test
	public void testGetSignsByUserId() {
		List<Sign> list = signDao.getSignsByUserId(1);
		System.out.println(list);
	}

	@Test
	public void testGetSignsByClassIdAndRoleId() {
		List<Sign> list = signDao.getSignsByClassIdAndRoleId(1, 1);
		System.out.println(list);
	}

	@Test
	public void testGetAllSigns() {
		List<Sign> list = signDao.getAllSigns();
		System.out.println(list);
	}
	
	@Test
	public void testGetSignsByClassId() {
		List<SignData> list = signDao.getSignDatasByCourseIdAndHisClassId(1, 2);
		System.out.println(list);
	
	}
	
	
}
