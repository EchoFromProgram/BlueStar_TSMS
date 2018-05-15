package dao;

import entity.Quiz;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问卷 dao 方法
 *
 * @author Fish
 * created by 2018-05-15 15:05
 */
public interface NewQuizDao
{
    public List<Quiz> getQuizByUserIdOrCourseId(@Param("userId") Integer userId, @Param("courseId") Integer courseId);
}
