package service;

import com.github.pagehelper.PageInfo;
import dto.AccountDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
