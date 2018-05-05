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
	private  Integer quizDetailId;
	
	// 填写内容1
	private String fill1;
	
	// 填写内容2
	private String fill2;
	
	//问卷内容
	private QuizDetail quizDetail;

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

	public Integer getQuizDetailId() {
		return quizDetailId;
	}

	public void setQuizDetailId(Integer quizDetailId) {
		this.quizDetailId = quizDetailId;
	}

	public String getFill1() {
		return fill1;
	}

	public void setFill1(String fill1) {
		this.fill1 = fill1;
	}

	public String getFill2() {
		return fill2;
	}

	public void setFill2(String fill2) {
		this.fill2 = fill2;
	}

	public QuizDetail getQuizDetail() {
		return quizDetail;
	}

	public void setQuizDetail(QuizDetail quizDetail) {
		this.quizDetail = quizDetail;
	}

	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", classId=" + classId + ", userId=" + userId + ", courseId=" + courseId
				+ ", date=" + date + ", quizDetailId=" + quizDetailId + ", fill1=" + fill1 + ", fill2=" + fill2
				+ ", quizDetail=" + quizDetail + "]";
	}

	

	
	
}
