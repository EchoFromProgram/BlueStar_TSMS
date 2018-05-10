package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Score;

public interface ScoreService {
	
	/**
	 * 通过用户id
	 * @param userId
	 * @param courseId
	 * @return
	 */
	public List<Score> getScoresByUserIdAndCourseId(@Param("userId")Integer userId,
													@Param("courseId")Integer courseId);
	
	/**
	 * 得到所有成绩
	 * @return 成绩集合
	 */
	public List<Score> getAllScores();
	
	/**
	 * 新增一条成绩
	 * @param score
	 * @return
	 */
	public Integer insertScore(Score score);
}
