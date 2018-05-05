package dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.catalina.LifecycleListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Quiz;
import entity.QuizDetail;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class QuizDaoTest {
	
	@Autowired
	private QuizDao quizDao;
	@Test
	public void testInsertQuiz() {
		Quiz quiz = new Quiz();
		quiz.setCourseId(1);
		quiz.setClassId(1);
		quiz.setDate(new Date());
		quiz.setFill1("老师讲的很好");
		quiz.setFill2("还可以");
		quiz.setQuizDetailId(1);
		quiz.setUserId(1);
		quizDao.insertQuiz(quiz);
	}

	@Test
	public void testInsertQuizDetail() {
		QuizDetail quizDetail = new QuizDetail();
		quizDetail.setQuestion1("问题1");
		quizDetail.setQuestion2("问题2");
		quizDao.insertQuizDetail(quizDetail);
		
	}

	@Test
	public void testGetQuizsByClassIdAndCourseId() {
		List<Quiz> list = quizDao.getQuizsByClassIdAndCourseId(1, 1);
		System.out.println(list);
	}

	@Test
	public void testGetQuizsByUserId() {
		List<Quiz> list = quizDao.getQuizsByUserId(2);
		System.out.println(list);
	}

	@Test
	public void testGetQuizDetailByQuizDetailId() {
		QuizDetail quizDetail = quizDao.getQuizDetailByQuizDetailId(1);
		System.out.println(quizDetail);
	}

	@Test
	public void testGetAllQuizs() {
		List<Quiz> list = quizDao.getAllQuizs();
		System.out.println(list);
	}
	
	@Test
	public void testWithDetail() {
		List<Quiz> list = quizDao.getQuizsByClassIdAndCourseIdWithDetail(1, 1);
		System.out.println(list);
	}

}
