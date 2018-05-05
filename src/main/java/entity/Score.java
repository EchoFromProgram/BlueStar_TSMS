package entity;
/**
 * 成绩类，对应score表
 * @author happyChicken
 *
 */

import java.util.Date;

public class Score {
	
	//成绩id
	private Integer scoreId;
	
	//班级id
	private Integer classId;
	
	//用户id
	private Integer userId;
	
	//课程id
	private Integer courseId;
	
	//日期
	private Date date;
	
	//分数
	private Integer score;

	

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
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

	public int getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getScoreId() {
		return scoreId;
	}

	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "Score [scoreId=" + scoreId + ", classId=" + classId + ", userId=" + userId + ", courseId=" + courseId
				+ ", date=" + date + ", score=" + score + "]";
	}

	
	
}
