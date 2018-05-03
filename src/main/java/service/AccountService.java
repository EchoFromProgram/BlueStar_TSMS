package service;

import entity.Clazz;
import org.springframework.stereotype.Service;

import dto.AccountDto;
import entity.User;

import java.util.List;

/**
 * 账号相关业务类
 *
 * @author Fish
 */
@Service
public interface AccountService
{
    /**
     * 登陆业务，传入一个用户对象进行登陆
     *
     * @param user 要登陆的用户
     * @return 返回传输包装类，包含所有需要的信息
     */
    public AccountDto login(User user);

    /**
     * 验证用户名是否可用
     * 这里的可用有两种含义：
     * 1）登陆的时候：这个方法用于检测账号是否正确，在数据库中是否存在
     * 2）创建用户的时候：这个方法用于检测账号是否已经存在，存在时不允许创建这个账号
     *
     * @param username 用于验证的账号
     * @return true 账号存在，false 账号不存在
     */
    public boolean userNameExisted(String username);

    /**
     * 创建一个用户，由前台传过来一个新用户
     *
     * @param user 前台传过来的用户
     * @return 返回创建的信息状态
     * */
    public AccountDto createAccount(User user);

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
     * */
    public List<Clazz> getClasses(User user);

    /**
     * 签到方法，传入一个对象和验证码
     *
     * @param user 要签到的用户
     * @param clazz 用户所属班级
     *              如果用户没有班级，那就传入一个 null 即可
     * @param reason * 签到原因，（选填）
     *               如果是迟到或者旷课就必须填写
     * @return 返回签到情况
     * */
    public AccountDto sign(User user, Clazz clazz, String reason);
}
