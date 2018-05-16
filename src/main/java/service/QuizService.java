package service;

import dto.AccountDto;
import entity.Quiz;
import entity.QuizDetail;
import org.springframework.stereotype.Service;

/**
 * 问卷业务类
 *
 * @author Fish
 * created by 2018-05-15 15:10
 */
@Service
public interface QuizService
{
    /**
     * 主要是学生查看他填写的问卷，通过 userId 获得
     *
     * @param pageNumber 页数
     * @param userId     用户 id
     * @return 返回这个用户填写过的问卷
     */
    public AccountDto getQuizsByUser(Integer pageNumber, Integer userId, Integer courseId);

    /**
     * 通过 classId 来获取班级问卷
     * 传入 classId == null 就获取所有班级的，也就是管理员的权限
     * 传入 courseId == null 就获取所有课程的
     *
     * @param pageNumber 页数
     * @param classId    班级 id
     * @param courseId   课程 id
     * @return 返回这个班级的所有问卷
     */
    public AccountDto getQuizsByClassAndCourse(Integer pageNumber, Integer classId, Integer courseId);

    /**
     * 学生得到问卷问题
     *
     * @return 返回问卷问题
     */
    public AccountDto getQuiz();

    /**
     * 管理员获得所有问卷
     *
     * @param pageNumber 页数
     * @return 返回所有问卷, QuizDetail
     */
    public AccountDto getQuizes(Integer pageNumber);

    /**
     * 老师通过 userId 可以查到他的班级的学生的问卷
     * userId 必须传
     * 如果 courseId == null，就是课程
     *
     * @param pageNumber 页数
     * @param userId     用户 id
     * @param courseId   课程 id
     * @return 返回问卷信息
     */
    public AccountDto getQuizByClassIdAndCourseId(Integer pageNumber, Integer userId, Integer courseId);

    /**
     * 填写问卷
     * 需要给 quiz 传入 userId, classId, courseId 和 answers
     *
     * @param quiz 填写的问卷
     * @return 返回是否填写成功
     */
    public AccountDto writeQuiz(Quiz quiz) throws Exception;

    /**
     * 发布问卷调查
     * 必须传入 questions
     *
     * @param quizDetail 要发布的问卷
     * @return 返回是否发布成功
     */
    public AccountDto publishQuiz(QuizDetail quizDetail);
}
