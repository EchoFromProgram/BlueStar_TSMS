package entity;

import java.util.Date;

/**
 * 问卷类，对应quiz表
 * @author happyChicken
 *
 */
public class Quiz {
	
	// 问卷id
	private int quizId;
	
	// 班级id
	private int classId;
	
	// 用户id
	private int userId;
	
	// 课程id
	private int courseId;
	
	// 日期
	private Date date;
	
	// 问卷内容 
	private  int quizDetail;
	
	// 填写内容
	private String fill;

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

	public int getQuizDetail() {
		return quizDetail;
	}

	public void setQuizDetail(int quizDetail) {
		this.quizDetail = quizDetail;
	}

	public String getFill() {
		return fill;
	}

	public void setFill(String fill) {
		this.fill = fill;
	}

	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", classId=" + classId + ", userId=" + userId + ", courseId=" + courseId
				+ ", date=" + date + ", quizDetail=" + quizDetail + ", fill=" + fill + "]";
	}

	
	
}
