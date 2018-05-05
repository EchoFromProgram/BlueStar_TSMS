package service.impl;

import constant.Role;
import dao.AccountDao;
import dao.SignDao;
import dto.AccountDto;
import entity.Clazz;
import entity.Sign;
import entity.User;
import enums.impl.Common;
import enums.impl.CreateAccountStatus;
import enums.impl.SignStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.SignService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 签到业务实现类
 *
 * @author Fish
 * */
@Service
public class SignServiceImpl implements SignService
{
    // 签到持久层对象
    private SignDao signDao = null;

    @Autowired
    public void setSignDao(SignDao signDao)
    {
        this.signDao = signDao;
    }

    // 账号持久层对象
    private AccountDao accountDao = null;

    @Autowired
    public void setAccountDao(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    /**
     * 获取到一个随机的签到码
     * 范围是 1000 ~ 9999
     *
     * @return 返回生成的签到码
     */
    @Override
    public int getSignCode()
    {
        return 1000 + (int) (9000 * Math.random());
    }

    /**
     * 获取用户所属班级，签到时需要老师指定现在在哪个班级上课
     *
     * @param user 指定的用户
     * @return 返回这个用户所属的班级
     */
    @Override
    public AccountDto getClasses(User user)
    {
        // 避免空指针
        if (user == null || user.getUserId() == null)
        {
            return new AccountDto(Common.ERROR);
        }

        List<Integer> classIds = accountDao.getClassIdsByUserId(user.getUserId());
        if (classIds == null || classIds.size() == 0)
        {
            // 这个用户没有所属班级
            return new AccountDto(Common.ERROR);
        }

        // 遍历去获取所有班级信息
        List<Clazz> clazzes = new ArrayList<>(classIds.size());
        for (Integer classId : classIds)
        {
            clazzes.add(accountDao.getClassByClassId(classId));
        }

        return new AccountDto<List<Clazz>>(clazzes, Common.SUCCESS);
    }

    /**
     * 签到方法，传入一个对象和验证码
     *
     * @param user   要签到的用户
     * @param clazz  用户所属班级
     *               如果用户没有班级，那就传入一个 null 即可
     * @param reason * 签到原因，（选填）
     *               如果是迟到或者旷课就必须填写
     * @return 返回签到情况
     */
    @Override
    public AccountDto sign(User user, Clazz clazz, String reason)
    {
        // 必须存在这个用户才能进行签到
        if (user == null || user.getUserId() == null)
        {
            return new AccountDto(SignStatus.ILLEGAL_SIGN);
        }

        // 判断这个班级是否属于这个用户
        List<Clazz> classes = (List<Clazz>) this.getClasses(user).getData();
        if (!classes.contains(clazz))
        {
            return new AccountDto(SignStatus.WRONG_CLASS);
        }

        Sign sign = new Sign();
        sign.setUserId(user.getUserId());
        sign.setClassId(clazz.getClassId());
        sign.setCourseId(clazz.getCourseId());
        sign.setDate(new Date());
        sign.setReason(reason);
        /* TODO 课程时间未定 */

        signDao.insertIntoSign(sign);

        return null;
    }

    /**
     * 根据班级信息获取签到信息
     *
     * @param clazz 指定的班级
     * @return 返回指定的签到信息
     */
    @Override
    public AccountDto getClassSigns(Clazz clazz)
    {
        if (clazz == null || clazz.getClassId() == null)
        {
            return new AccountDto(SignStatus.WRONG_CLASS);
        }

        return new AccountDto<List<Sign>>(signDao.getSignsByClassId(clazz.getClassId()),
                SignStatus.SUCCESS);
    }

    /**
     * 获取指定班级的老师的签到信息
     *
     * @param clazz 指定班级
     * @return 返回指定班级的老师签到信息
     */
    @Override
    public AccountDto getTeacherSignsByClass(Clazz clazz)
    {
        if (clazz == null || clazz.getClassId() == null)
        {
            return new AccountDto(SignStatus.WRONG_CLASS);
        }

        return new AccountDto<List<Sign>>(signDao.getSignsByClassIdAndRoleId(clazz.getClassId(), Role.TEACHER),
                SignStatus.SUCCESS);
    }

    /**
     * 获取指定班级的学生的签到信息
     *
     * @param clazz 指定班级
     * @return 返回指定班级的学生的签到信息
     */
    @Override
    public AccountDto getStudentSignsByClass(Clazz clazz)
    {
        if (clazz == null || clazz.getClassId() == null)
        {
            return new AccountDto(SignStatus.WRONG_CLASS);
        }

        return new AccountDto<List<Sign>>(signDao.getSignsByClassIdAndRoleId(clazz.getClassId(), Role.STUDENT),
                SignStatus.SUCCESS);
    }

    /**
     * 获取整个签到表的信息
     *
     * @return 返回整个签到表
     */
    @Override
    public AccountDto getSigns()
    {
        return new AccountDto<List<Sign>>(signDao.getAllSigns(), SignStatus.SUCCESS);
    }

    /**
     * 获取这个用户的所有签到信息
     *
     * @param user 要被查询的用户
     * @return 返回这个用户的签到信息
     */
    @Override
    public AccountDto getSignsByUser(User user)
    {
        // 先判断用户是否可用
        if (user == null || user.getUserId() == null)
        {
            return new AccountDto(CreateAccountStatus.USER_IS_NULL);
        }

        return new AccountDto<List<Sign>>(signDao.getSignsByUserId(user.getUserId()),
                SignStatus.SUCCESS);
    }
}
