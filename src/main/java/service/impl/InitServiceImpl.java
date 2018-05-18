package service.impl;

import dao.AccountDao;
import dao.NewQuizDao;
import dto.AccountDto;
import entity.Course;
import entity.Power;
import enums.impl.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.InitService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提供服务器初始化的业务实现类
 *
 * @author Fish
 */
@Service
public class InitServiceImpl implements InitService
{
    private AccountDao accountDao = null;

    @Autowired
    public void setAccountDao(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    private NewQuizDao quizDao = null;

    @Autowired
    public void setQuizDao(NewQuizDao quizDao)
    {
        this.quizDao = quizDao;
    }

    /**
     * 得到整张权限表
     *
     * @return 整张权限表，以 Map 封装，key 值为权限 id，value 值为权限（这里主要是 URL 和功能名称）
     */
    public AccountDto getAllPowers()
    {
        Map<Integer, Power> powersMap = new HashMap<>();
        List<Power> powers = accountDao.getPowers();

        if (powers != null)
        {
            // 如果成功得到权限表，就将它转成 Map
            for (Power power : powers)
            {
                powersMap.put(power.getPowerId(), power);
            }
        }

        return new AccountDto<Map<Integer, Power>>(powersMap, Common.SUCCESS);
    }

    /**
     * 得到整张课程表
     *
     * @return 整张课程表
     */
    public AccountDto getAllCourses()
    {
        List<Course> courses = accountDao.getAllcourses();
        if (courses == null) // 没有获取数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        // 课程排序器
        courses.sort((o1, o2) ->
        {
            if (o1.getCourseId() > o2.getCourseId())
            {
                return 1;
            }
            else if (o1.getCourseId() < o2.getCourseId())
            {
                return -1;
            }

            return 0;
        });

        return new AccountDto<List<Course>>(courses, Common.SUCCESS);
    }

    /**
     * 得到现在活跃的问卷
     *
     * @return 返回问卷
     */
    public AccountDto getQuiz()
    {
        return new AccountDto<>(quizDao.getQuiz(), Common.SUCCESS);
    }
}
