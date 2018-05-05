package dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Notice;
import entity.NoticeDetail;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class NoticeDaoTest {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Test
	public void testInsertNoticeDetail() {
		NoticeDetail noticeDetail = new NoticeDetail();
		noticeDetail.setContent("放假了");
		int num = noticeDao.insertNoticeDetail(noticeDetail);
		System.out.println(noticeDetail.getNoticeDetailId());
	}

	@Test
	public void testInsertNotice() {
		Notice notice = new Notice();
		notice.setCourseId(1);
		notice.setDate(new Date());
		notice.setNoticeDetailId(1);
		notice.setUserId(1);
		notice.setClassId(1);
		noticeDao.insertNotice(notice);
		System.out.println(notice.getNoticeId());
	}

	@Test
	public void testUpdateNoticeByClassIdAndNoticeDetailId() {
		//问题，userId对应多条记录
		Notice notice = new Notice();
		notice.setCourseId(3);
		notice.setDate(new Date());
		notice.setNoticeDetailId(1);
		notice.setUserId(1);
		notice.setClassId(2);
		noticeDao.updateNoticeByClassIdAndNoticeDetailId(notice);
	}

	@Test
	public void testUpdateNoticeDetailByNoticeDetailId() {
		NoticeDetail noticeDetail = new NoticeDetail();
		noticeDetail.setContent("后天上课");
		noticeDetail.setNoticeDetailId(1);
		noticeDao.updateNoticeDetailByNoticeDetailId(noticeDetail);
	}

	@Test
	public void testGetNoticesByClassId() {
		List<Notice> list = noticeDao.getNoticesByClassId(2);
		System.out.println(list);
	}

	@Test
	public void testGetNoticesByUserId() {
		List<Notice> list = noticeDao.getNoticesByUserId(1);
		System.out.println(list);
	}

}