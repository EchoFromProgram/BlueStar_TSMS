package entity;
/**
 * 通知类，对应notice表
 * @author happyChicken
 *
 */

import java.util.Date;

public class Notice {
	
	// 角色id
	private int quizId;
	
	// 班级id
	private int classId;
	
	// 用户id
	private int userId;
	
	// 课程id
	private int courseId;
	
	// 日期
	private Date date;
	
	// 通知详情id
	private int noticeDetail;

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNoticeDetail() {
		return noticeDetail;
	}

	public void setNoticeDetail(int noticeDetail) {
		this.noticeDetail = noticeDetail;
	}

	@Override
	public String toString() {
		return "Notice [quizId=" + quizId + ", classId=" + classId + ", userId=" + userId + ", courseId=" + courseId
				+ ", date=" + date + ", noticeDetail=" + noticeDetail + "]";
	}
	
}
