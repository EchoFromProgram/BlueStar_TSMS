package service;

import dto.AccountDto;
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
}
