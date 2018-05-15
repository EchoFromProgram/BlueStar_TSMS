package service.impl;

import dao.NewQuizDao;
import dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.QuizService;

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
     * @return 返回这个用户填写过的问卷
     */
    public AccountDto getQuizsByUser(Integer pageNumber, Integer userId)
    {
        return null;
    }
}
