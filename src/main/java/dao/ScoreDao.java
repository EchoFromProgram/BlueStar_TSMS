package dao;
/**
 * 成绩方法
 * @author happyChicken
 *
 */

import java.util.List;

import entity.Score;

public interface ScoreDao {
	
	/**
	 * 插入一条成绩
	 * @param socre 成绩类
	 * @return 影响的行数，如果是1则插入成功 
	 */ 
	public Integer insertScore(Score socre); 
	
	/**
	 * 根据班级id得到该班级的成绩
	 * @return 成绩集合
	 */
	public List<Score> getScoresByClassId();
	
	/**
	 * 根据userId得到一个学生的成绩 
	 * @return 一个学生的成绩    
	 */
	public Score getScoreByUserId();  
	
	/**
	 * 得到所有成绩，用于管理员查看所有学员成绩
	 * @return
	 */
	public List<Score> getAllScores(); 
	
}
