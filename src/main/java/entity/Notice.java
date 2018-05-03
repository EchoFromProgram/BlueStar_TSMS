package entity;
/**
 * 通知类，对应notice表
 * @author happyChicken
 *
 */

import java.util.Date;

public class Notice {
	
	// 角色id
	private Integer noticeId;
	
	// 班级id
	private Integer classId;
	
	// 用户id
	private Integer userId;
	
	// 课程id
	private Integer courseId;
	
	// 日期
	private Date date;
	
	// 通知详情id
	private Integer noticeDetailId;
	
	//具体通知消息
	private NoticeDetail noticeDetail;

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNoticeDetailId() {
		return noticeDetailId;
	}

	public void setNoticeDetailId(Integer noticeDetailId) {
		this.noticeDetailId = noticeDetailId;
	}

	public NoticeDetail getNoticeDetail() {
		return noticeDetail;
	}

	public void setNoticeDetail(NoticeDetail noticeDetail) {
		this.noticeDetail = noticeDetail;
	}

	@Override
	public String toString() {
		return "Notice [noticeId=" + noticeId + ", classId=" + classId + ", userId=" + userId + ", courseId=" + courseId
				+ ", date=" + date + ", noticeDetailId=" + noticeDetailId + ", noticeDetail=" + noticeDetail + "]";
	}

	

	
	
}
