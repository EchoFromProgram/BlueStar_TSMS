package dao;
/**
 * 成绩方法
 * @author happyChicken
 *
 */

import java.util.List;

import javax.swing.ListModel;

import org.apache.ibatis.annotations.Param;

import entity.Score;
import entity.ScoreData;

public interface ScoreDao {
	
	/**
	 * 插入一条成绩        
	 * @param score 成绩类
	 * @return 影响的行数，如果是1则插入成功 
	 */ 
	public Integer insertScore(Score score);    
	
	/**
	 * 根据班级id得到该班级的成绩
	 * @return 成绩集合
	 */
	public List<ScoreData> getScoresByClassId(Integer classId);
	
	/**
	 * 根据userId得到一个学生的成绩 
	 * @return 一个学生的成绩    
	 */
	public List<Score> getScoreByUserId(Integer userId);  
	
	/**
	 * 得到所有成绩，用于管理员查看所有学员成绩
	 * @return 所有成绩的集合
	 */
	public List<Score> getAllScores(); 
	
	/**
	 * 更新成绩
	 * @param score 成绩对象
	 * @return 影响的行数，如果是1则更新成功
	 */
	public Integer updateScoreByUserIdAndClassIdAndStatus(Score score); 
	

	
	/**
	 * 根据用户id得到成绩数据
	 * @param userId
	 * @return 成绩数据集合
	 */
	public List<ScoreData> getScoreDatasByUserId(Integer userId);
	
	/**
	 * 根据班级和阶段得到成绩数据
	 * @param status
	 * @return 成绩数据集合
	 */
	public List<ScoreData> getScoreDatasByClassIdAndStatus(@Param("status")Integer status,
													@Param("classId")Integer classId);
	
	/**
	 * 得到所有的成绩数据
	 * @return 成绩数据集合
	 */
	public List<ScoreData> getAllScoreDatas();
	
	/**
	 * 根据阶段查询成绩
	 * @param status 阶段
	 * @return 成绩数据集合
	 */
	public List<ScoreData> getScoreDatasByStatus(Integer status);
	
	/**
	 * 根据老师所在班级查询班级的成绩
	 * @param useId 用户id  用户得到所属班级
	 * @return 成绩数据集合
	 */
	public List<ScoreData> getScoreDatasByHisClassId(Integer useId);
	
	/**
	 * 根据老师所在班级和课程id查询班级成绩
	 * @param userId 用户id
	 * @param status 阶段id
	 * @return 成绩数据集合
	 */
	public List<ScoreData> getScoreDatasByStatusAndHisClassId(@Param("userId")Integer userId,
														 	  @Param("status")Integer status);
	
	/**
	 * 根据用户id更新成绩
	 * @param userId 用户id
	 * @return
	 */
	public Integer updateScoreByUserId(Integer userId);
	
	/**
	 * 根据用户id删除成绩
	 * @param userId
	 * @return
	 */
	public Integer deleteScoreByUserId(Integer userId);
}
