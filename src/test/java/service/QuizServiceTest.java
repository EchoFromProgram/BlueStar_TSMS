package service;

import com.github.pagehelper.PageInfo;
import dto.AccountDto;
import entity.Quiz;
import entity.QuizDetail;
import entity.QuizQuestion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fish
 * created by 2018-05-15 20:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class QuizServiceTest
{
    @Autowired
    private QuizService quizService = null;

    @Test
    public void testGetQuizsByUser()
    {
        System.out.println(quizService.getQuizsByUser(1, 1, null));
    }

    @Test
    public void testGetQuizsByClassAndCourse()
    {
        Integer classId = null;
        Integer courseId = null;
        System.out.println(quizService.getQuizsByClassAndCourse(1, classId, courseId));
    }

    @Test
    public void testGetQuiz()
    {
        System.out.println(quizService.getQuiz());
    }

    @Test
    public void testGetQuizes()
    {
        System.out.println(quizService.getQuizes(1));
    }

    @Test
    public void testGetQuizByClassIdAndCourseId()
    {
        // TODO 待测试
        Integer userId = 1;
        Integer courseId = 1;
        System.out.println(quizService.getQuizByClassIdAndCourseId(1, userId, courseId));
    }

    @Test
    public void testWriteQuiz()
    {
        Quiz quiz = new Quiz();
        quiz.setClassId(2);
        quiz.setUserId(4);
        quiz.setCourseId(2);
        quiz.setQuizDetailId(2);

        List<String> answers = new ArrayList<>();
        answers.add("我还想听你说那三个字！");
        answers.add("老师很生猛，真的~~");
        quiz.setAnswers(answers);

        System.out.println(quizService.writeQuiz(quiz));
    }

    @Test
    public void testPublishQuiz()
    {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("您觉得还有什么上升空间？"));
        questions.add(new QuizQuestion("中国的国土面积多大？"));
        questions.add(new QuizQuestion("下课之后想干嘛？"));

        QuizDetail quizDetail = new QuizDetail();
        quizDetail.setQuestions(questions);

        System.out.println(quizService.publishQuiz(quizDetail));
    }
}
