package dao;

import entity.Quiz;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 问卷 dao 方法
 *
 * @author Fish
 * created by 2018-05-15 15:05
 */
public interface NewQuizDao
{
    public List<Quiz> getQuizByUserIdOrCourseId(@Param("userId") Integer userId, @Param("courseId") Integer courseId);

    public List<String> getQuestionsByQuizDetailId(@Param("quizDetailId") Integer quizDetailId);

    public List<String> getAnswersByQuizId(@Param("quizId") Integer quizId);

    public List<Quiz> getQuizByClassIdOrCourseId(@Param("classId") Integer classId, @Param("courseId") Integer courseId);
}
