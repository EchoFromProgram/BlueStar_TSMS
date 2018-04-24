package dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.validator.PublicClassValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Notice;
import entity.NoticeDetail;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class NoticeTest {
	@Autowired
	public NoticeDao noticeDao;
	
	@Test
	public void testInsertNotice() {
		Notice notice = new Notice();
		notice.setClassId(1);
		notice.setCourseId(1);
		notice.setDate(new Date());
		notice.setNoticeDetail(1);
		notice.setUserId(1);
		int num = noticeDao.insertNotice(notice);
	}
	
	@Test
	public void testInsertNoticeDetail() {
		NoticeDetail noticeDetail = new NoticeDetail();
		noticeDetail.setContent("明天不上课");
		noticeDetail.setNoticeDetailId(3);
		noticeDao.insertNoticeDetail(noticeDetail);
	}
	
	@Test
	public void testUpdateNoticeByQuizId() {
		Notice notice = new Notice();
		notice.setClassId(1);
		notice.setCourseId(1);
		notice.setDate(new Date());
		notice.setNoticeDetail(2);
		notice.setUserId(5);
		notice.setQuizId(2);
		int num = noticeDao.updateNoticeByQuizId(notice);
	}
	
	@Test
	public void testUpdateNoticeByClassId() {
		Notice notice = new Notice();
		notice.setClassId(1);
		notice.setCourseId(1);
		notice.setDate(new Date());
		notice.setNoticeDetail(2);
		notice.setUserId(6);
		int num = noticeDao.updateNoticeByClassId(notice);
		System.out.println(num);
	}
	
	@Test
	public void testUpdateContentByNoticeDetail() {
		NoticeDetail noticeDetail = new NoticeDetail();
		noticeDetail.setContent("五一放假三天");
		noticeDetail.setNoticeDetailId(3);
		noticeDao.updateContentByNoticeDetail(noticeDetail);
		
	}
	
	@Test
	public void testGetNoticeByQuizId() {
		Notice notice = noticeDao.getNoticeByQuizId(2);
		System.out.println(notice);
	}
	
	@Test
	public void testGetNoticeDetailByNoticeDetailId() {
		NoticeDetail noticeDetail = noticeDao.getNoticeDetailByNoticeDetailId(1);
		System.out.println(noticeDetail);
	}
	

}
