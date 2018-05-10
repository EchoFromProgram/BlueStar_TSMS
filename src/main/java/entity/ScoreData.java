package entity;
/**
 * 封装成绩数据
 * @author 王鸿175
 *
 */

import java.sql.Date;

public class ScoreData {
	//成绩编号
	public Integer scoreId;
	
	//班级名
	private String className;
	
	//姓名
	private String name;
	
	//阶段
	private Integer status;
	
	//日期
	private Date date;
	
	//分数
	private Integer score;

	public Integer getScoreId() {
		return scoreId;
	}

	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ScoreData [scoreId=" + scoreId + ", className=" + className + ", name=" + name + ", status=" + status
				+ ", date=" + date + ", score=" + score + "]";
	}

	


	
}
