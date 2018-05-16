package service.impl;

import dao.NewQuizDao;
import dto.AccountDto;
import entity.QuizAnswer;
import entity.Quiz;
import entity.QuizDetail;
import enums.impl.Common;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.QuizService;
import utils.PageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 问卷业务实现类
 *
 * @author Fish
 * created by 2018-05-15 15:10
 */
@Service
/*
 * @Transactional中的的属性 propagation :事务的传播行为 isolation :事务的隔离级别 readOnly :只读
 *                     rollbackFor :发生哪些异常回滚 noRollbackFor :发生哪些异常不回滚
 *                     rollbackForClassName 根据异常类名回滚
 */
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
public class QuizServiceImpl implements QuizService
{
    // 记录日志
    Logger logger = Logger.getLogger(this.getClass());

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

    /**
     * 学生得到问卷问题
     *
     * @return 返回问卷问题
     */
    public AccountDto getQuiz()
    {
        // 得到问卷
        QuizDetail quizDetail = quizDao.getQuiz();
        if (quizDetail == null)
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(quizDetail, Common.SUCCESS);
    }

    /**
     * 管理员获得所有问卷
     *
     * @param pageNumber 页数
     * @return 返回所有问卷, QuizDetail
     */
    public AccountDto getQuizes(Integer pageNumber)
    {
        if (pageNumber == null) // 必须分页
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber); // 开始分页
        List<QuizDetail> quizes = quizDao.getQuizes();
        if (quizes == null)
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(quizes), Common.SUCCESS);
    }

    /**
     * 老师通过 userId 可以查到他的班级的学生的问卷
     * userId 必须传
     * 如果 courseId == null，就是课程
     *
     * @param pageNumber 页数
     * @param userId 用户 id
     * @param courseId 课程 id
     * @return 返回问卷信息
     */
    public AccountDto getQuizByClassIdAndCourseId(Integer pageNumber, Integer userId, Integer courseId)
    {
        if (pageNumber == null || userId == null) // userId 必须有
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<Quiz> quizzes = null; //quizDao.getQuizByHisClassIdOrCourseId(userId, courseId);
        if (quizzes == null) // 没有得到数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(quizzes), Common.SUCCESS);
    }

    /**
     * 填写问卷
     * 需要给 quiz 传入 userId, classId, courseId, quizDetailId和 answers
     *
     * @param quiz 填写的问卷
     * @return 返回是否填写成功
     */
    public AccountDto writeQuiz(Quiz quiz) throws Exception // TODO 需要加入事务管理
    {
        if (quiz == null || quiz.getUserId() == null
                || quiz.getClassId() == null || quiz.getCourseId() == null)
        {
            // 这些参数必须传入
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        quiz.setDate(new Date()); // 填写时间
        int affect = quizDao.insertQuiz(quiz); // 可以获得 quiz_id
        if (affect >= 0) // 由于未知错误，插入失败
        {
            return new AccountDto(Common.ERROR);
        }

        QuizAnswer quizAnswer = new QuizAnswer();
        quizAnswer.setQuizId(quiz.getQuizId());
        quizAnswer.setAnswers(quiz.getAnswers());
        affect = quizDao.insertQuizAnswer(quizAnswer);
        if (affect <= 0)
        {
            return new AccountDto(Common.ERROR);
        }

        return new AccountDto(Common.SUCCESS);
    }

    /**
     * 发布问卷调查
     * 必须传入 questions
     *
     * @param quizDetail 要发布的问卷
     * @return 返回是否发布成功
     */
    public AccountDto publishQuiz(QuizDetail quizDetail) // TODO 需要加入事务管理
    {
        if (quizDetail == null || quizDetail.getQuestions() == null)
        {
            // questions 必须传入
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        // 先在 quiz_detail 表中插入一条数据，得到 quiz_detail_id
        int affect = quizDao.insertQuizDetail(quizDetail);
        if (affect <= 0) // 内部错误
        {
            return new AccountDto(Common.ERROR);
        }

        // 然后再将 quiz_detail_id 插入到 quiz_question 中
        affect = quizDao.insertQuizQuestion(quizDetail);
        if (affect <= 0) // 内部错误
        {
            return new AccountDto(Common.ERROR);
        }

        return new AccountDto(Common.SUCCESS);
    }
}
