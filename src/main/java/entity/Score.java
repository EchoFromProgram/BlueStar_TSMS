package entity;
/**
 * 成绩类，对应score表
 * @author happyChicken
 *
 */

import java.util.Date;

public class Score {
	
	//成绩id
	private int quizId;
	
	//班级id
	private int classId;
	
	//用户id
	private int userId;
	
	//课程id
	private int courseId;
	
	//日期
	private Date date;
	
	//分数
	private int score;

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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Score [quizId=" + quizId + ", classId=" + classId + ", userId=" + userId + ", courseId=" + courseId
				+ ", date=" + date + ", score=" + score + "]";
	}
	
}
