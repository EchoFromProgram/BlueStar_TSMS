package service.impl;

import dao.NewQuizDao;
import dto.AccountDto;
import entity.Quiz;
import enums.impl.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.QuizService;
import utils.PageUtil;

import java.util.List;

/**
 * 问卷业务实现类
 *
 * @author Fish
 * created by 2018-05-15 15:10
 */
@Service
public class QuizServiceImpl implements QuizService
{
    private static NewQuizDao quizDao = null;

    @Autowired
    public void setQuizDao(NewQuizDao quizDao)
    {
        this.quizDao = quizDao;
    }

    /**
     * 主要是学生查看他填写的问卷，通过 userId 获得
     *
     * @param pageNumber 页数
     * @param userId 用户 id
     * @param courseId 课程 id，如果不用可以传入 null
     * @return 返回这个用户填写过的问卷
     */
    public AccountDto getQuizsByUser(Integer pageNumber, Integer userId, Integer courseId)
    {
        if (pageNumber == null || userId == null) // 参数为空错误
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<Quiz> quizzes = quizDao.getQuizByUserIdOrCourseId(1, courseId);
        if (quizzes == null) // 没有得到数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        for (Quiz quiz : quizzes) // 将问题和答案都找出来
        {
            quiz.setQuestions(quizDao.getQuestionsByQuizDetailId(quiz.getQuizDetailId()));
            quiz.setAnswers(quizDao.getAnswersByQuizId(quiz.getQuizId()));
        }

        return new AccountDto<>(PageUtil.pageInfo(quizzes), Common.SUCCESS);
    }

    /**
     * 通过 classId 来获取班级问卷
     * 传入 classId == null 就获取所有班级的，也就是管理员的权限
     * 传入 courseId == null 就获取所有课程的
     *
     * @param pageNumber 页数
     * @param classId 班级 id
     * @param courseId 课程 id
     * @return 返回这个班级的所有问卷
     */
    public AccountDto getQuizsByClassAndCourse(Integer pageNumber, Integer classId, Integer courseId)
    {
        if (pageNumber == null) // 参数错误
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<Quiz> quizzes = quizDao.getQuizByClassIdOrCourseId(classId, courseId);
        if (quizzes == null) // 没有得到数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        for (Quiz quiz : quizzes) // 将问题和答案都找出来
        {
            quiz.setQuestions(quizDao.getQuestionsByQuizDetailId(quiz.getQuizDetailId()));
            quiz.setAnswers(quizDao.getAnswersByQuizId(quiz.getQuizId()));

            System.out.println(quiz);
        }
        return new AccountDto<>(PageUtil.pageInfo(quizzes), Common.SUCCESS);
    }
}
