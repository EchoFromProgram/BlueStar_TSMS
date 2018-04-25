package entity;

import java.util.Date;

/**
 * 问卷类，对应quiz表
 * @author happyChicken
 *
 */
public class Quiz {
	
	// 问卷id
	private Integer quizId;
	
	// 班级id
	private Integer classId;
	
	// 用户id
	private Integer userId;
	
	// 课程id
	private Integer courseId;
	
	// 日期
	private Date date;
	
	// 问卷内容 
	private  Integer quizDetail;
	
	// 填写内容
	private String fill;

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public int getCourseId() {
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

	public int getQuizDetail() {
		return quizDetail;
	}

	public void setQuizDetail(Integer quizDetail) {
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
