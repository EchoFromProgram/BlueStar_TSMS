package service;

import dto.AccountDto;
import entity.Clazz;
import entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 签到相关业务
 *
 * @author Fish
 * */
@Service
public interface SignService
{
    /**
     * 获取到一个随机的签到码
     *
     * @return 返回生成的签到码
     */
    public int getSignCode();

    /**
     * 获取用户所属班级，签到时需要老师指定现在在哪个班级上课
     *
     * @param user 指定的用户
     * @return 返回这个用户所属的班级
     */
    public List<Clazz> getClasses(User user);

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
    public AccountDto sign(User user, Clazz clazz, String reason);

    /**
     * 根据班级信息获取签到信息
     *
     * @param clazz 指定的班级
     * @return 返回指定的签到信息
     * */
    public AccountDto getClassSigns(Clazz clazz);

    /**
     * 获取指定班级的老师的签到信息
     *
     * @param clazz 指定班级
     * @return 返回指定班级的老师签到信息
     * */
    public AccountDto getTeacherSignsByClass(Clazz clazz);

    /**
     * 获取指定班级的学生的签到信息
     *
     * @param clazz 指定班级
     * @return 返回指定班级的学生的签到信息
     * */
    public AccountDto getStudentSignsByClass(Clazz clazz);

    /**
     * 获取整个签到表的信息
     *
     * @return 返回整个签到表
     * */
    public AccountDto getSigns();
}
