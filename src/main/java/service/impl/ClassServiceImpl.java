package service.impl;

import dao.AccountDao;
import dto.AccountDto;
import entity.Clazz;
import entity.Course;
import enums.impl.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ClassService;

/**
 * 班级相关业务实现类
 *
 * @author Fish
 * created by 2018-05-09
 */
@Service
public class ClassServiceImpl implements ClassService
{
    private AccountDao accountDao = null;

    @Autowired
    public void setAccountDao(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    /**
     * 通过班级 id 获取班级上到哪一节课
     *
     * @param classId 班级 id
     * @return 返回课程
     */
    @Override
    public AccountDto getCourseByClassId(Integer classId)
    {
        // 检测参数有没有问题
        if (classId == null)
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        Clazz clazz = accountDao.getClassByClassId(classId);
        if (clazz == null) // 没有查到数据，提示错误
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        // 通过班级查课程
        Course course = accountDao.getCoursesByCourseId(clazz.getCourseId());
        if (course == null) // 没有查到数据，报错
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(course, Common.SUCCESS);
    }
}
