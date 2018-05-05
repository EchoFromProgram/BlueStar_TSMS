package dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.javassist.scopedpool.ScopedClassPoolRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Score;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ScoreDaoTest {
	
	@Autowired
	private ScoreDao scoreDao;
	
	@Test
	public void testInsertScore() {
		Score score = new Score();
		score.setClassId(1);
		score.setCourseId(2);
		score.setDate(new Date());
		score.setScore(50);
		score.setUserId(1);
		int num = scoreDao.insertScore(score);
		System.out.println(num);
	}

	@Test
	public void testGetScoresByClassId() {
		List<Score> list = scoreDao.getScoresByClassId(1);
		System.out.println(list);
	}

	@Test
	public void testGetScoreByUserId() {
		List<Score> list  = scoreDao.getScoreByUserId(1);
		System.out.println(list);
	}

	@Test
	public void testGetAllScores() {
		List<Score> list = scoreDao.getAllScores();
		System.out.println(list);
	}
	
	@Test
	public void testUpDateScoreByUserIdAndCourseId() {
		Score score = new Score();
		score.setCourseId(1);
		score.setUserId(1);
		score.setScore(22);
		int num = scoreDao.updateScoreByUserIdAndCourseId(score);
		System.out.println(num);
		System.out.println(score.getScoreId());
	}
	
}
