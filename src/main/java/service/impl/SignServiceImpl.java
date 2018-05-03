package service.impl;

import dao.AccountDao;
import dao.SignDao;
import dto.AccountDto;
import entity.Clazz;
import entity.Sign;
import entity.User;
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
    public List<Clazz> getClasses(User user)
    {
        // 避免空指针
        if (user == null || user.getUserId() == null)
        {
            return null;
        }

        List<Integer> classIds = accountDao.getClassIdsByUserId(user.getUserId());
        if (classIds == null || classIds.size() == 0)
        {
            // 这个用户没有所属班级
            return null;
        }

        // 遍历去获取所有班级信息
        List<Clazz> clazzes = new ArrayList<>(classIds.size());
        for (Integer classId : classIds)
        {
            clazzes.add(accountDao.getClassByClassId(classId));
        }

        return clazzes;
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
            return new AccountDto<String>(SignStatus.ILLEGAL_SIGN.getInfo(),
                    SignStatus.ILLEGAL_SIGN);
        }

        // 判断这个班级是否属于这个用户
        List<Clazz> classes = this.getClasses(user);
        if (!classes.contains(clazz))
        {
            return new AccountDto<String>(SignStatus.WRONG_CLASS.getInfo(),
                    SignStatus.WRONG_CLASS);
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
}
