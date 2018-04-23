package entity;

import java.util.Date;

/**
 * 签到类，对应sign表
 * @author happyChicken
 *
 */
public class Sign {

	//签到id
	private int signId;
		
	//班级id
	private int classId;
		
	//用户id
	private int userId;
		
	//课程id
	private int courseId;
		
	//日期
	private Date date;
	
	//签到状态
	private int status;
	
	//迟到原因
	private String reason;

	public int getSignId() {
		return signId;
	}

	public void setSignId(int signId) {
		this.signId = signId;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Sign [signId=" + signId + ", classId=" + classId + ", userId=" + userId + ", courseId=" + courseId
				+ ", date=" + date + ", status=" + status + ", reason=" + reason + "]";
	}

	
}
