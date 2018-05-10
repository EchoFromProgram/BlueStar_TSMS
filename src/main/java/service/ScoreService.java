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
	
}
