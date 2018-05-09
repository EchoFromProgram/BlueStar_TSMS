package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Quiz;
import entity.QuizDetail;

/**
 * 问卷方法
 * @author 王鸿175
 *
 */
public interface QuizDao {
	
	/**
	 * 提交问卷
	 * @return 影响的行数，如果是1则提交成功
	 */
	public Integer insertQuiz(Quiz quiz);
	
	/**
	 * 新建问卷
	 * @param quizDetail 问卷问题类
	 * @return 影响的行数，如果是1则创建成功
	 */
	public Integer insertQuizDetail(QuizDetail quizDetail);
	
	/**
	 * 得到指定班级的问卷填写情况
	 * @param classId 班级id
	 * @return 问卷集合
	 */
	public List<Quiz> getQuizsByClassIdAndCourseId(@Param("classId")Integer classId,
												   @Param("courseId")Integer courseId);
	
	/**
	 * 得到指定用户的所有问卷填写情况
	 * @param userId 班级id
	 * @return 问卷集合
	 */
	public List<Quiz> getQuizsByUserId(Integer userId);
	
	/**
	 * 根据问卷内容id得到问卷内容
	 * @param quizDetailId 问卷内容id
	 * @return 一个问卷内容
	 */
	public QuizDetail getQuizDetailByQuizDetailId(Integer quizDetailId);
	
	/**
	 * 得到所有问卷填写情况
	 * @return 问卷填写情况集合
	 */
	public List<Quiz> getAllQuizs();
	
	/**
	 * 得到问卷填写情况和问卷内容
	 * @param classId 班级id
	 * @param courseId 课程id
	 * @return
	 */
	public List<Quiz> getQuizsByClassIdAndCourseIdWithDetail(@Param("classId")Integer classId,
			   												 @Param("courseId")Integer courseId);
	 
}
