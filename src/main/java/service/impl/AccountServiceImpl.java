package service.impl;

import dao.AccountDao;
import dao.SignDao;
import dto.AccountDto;
import entity.*;
import enums.TYPE;
import enums.impl.CreateAccountStatus;
import enums.impl.LoginStatus;
import enums.impl.SignStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AccountService;

import java.util.*;

/**
 * 账号业务实现类
 *
 * @author Fish
 * */
@Service
public class AccountServiceImpl implements AccountService
{
    // 账号持久层对象
    private AccountDao accountDao = null;

    // 签到持久层对象
    private SignDao signDao = null;

    @Autowired
    public void setAccountDao(AccountDao accountDao)
    {
        this.accountDao = accountDao;
    }

    @Autowired
    public void setSignDao(SignDao signDao)
    {
        this.signDao = signDao;
    }

    /**
     * 登陆业务，传入一个用户对象进行登陆
     *
     * @param user 前台传过来的要登陆的用户
     * @return true 登陆验证通过，false 用户名或密码不正确
     */
    public AccountDto login(User user)
    {
        // 如果前台给过来的用户名是空的，返回错误提示
        if (user == null || user.getUserName() == null || "".equals(user.getUserName()))
        {
            return new AccountDto<String>(LoginStatus.WRONG_USERNAME.getInfo(), LoginStatus.WRONG_USERNAME);
        }

        // 从数据库中查出这个账号的密码
        User u = accountDao.getUserByUserName(user.getUserName());

        // 用户名不存在！
        if (u == null)
        {
            return new AccountDto<String>("用户名不存在！", LoginStatus.WRONG_USERNAME);
        }

        // 判断前台登陆用户输入的密码和后台数据的密码是否一致
        if (!u.getPassword().equals(user.getPassword()))
        {
            return new AccountDto<String>(LoginStatus.WRONG_PASSWORD.getInfo(), LoginStatus.WRONG_PASSWORD);
        }

        // 登陆成功，将要携带的信息带给前台
        Map<String, Object> infos = new HashMap<String,Object>();
        infos.put("user", u);
        infos.put("hisPowers", accountDao.getPowerIdByRoleId(u.getRoleId()));

        return new AccountDto<Map>(infos, LoginStatus.SUCCESS);
    }

    /**
     * 验证用户名是否可用
     * 这里的可用有两种含义：
     * 1）登陆的时候：这个方法用于检测账号是否正确，在数据库中是否存在
     * 2）创建用户的时候：这个方法用于检测账号是否已经存在，存在时不允许创建这个账号
     *
     * @param username 用于验证的账号
     * @return true 账号存在，false 账号不存在
     */
    public boolean userNameExisted(String username)
    {
        return accountDao.userNameIsExit(username) > 0;
    }

    /**
     * 创建一个用户，由前台传过来一个新用户
     *
     * @param user 前台传过来的用户
     * @return 返回创建的信息状态
     */
    public AccountDto createAccount(User user)
    {
        // 如果前台传了一个空对象过来，创建失败
        if (user == null)
        {
            return new AccountDto<String>(CreateAccountStatus.USER_IS_NULL.getInfo(),
                    CreateAccountStatus.USER_IS_NULL);
        }

        // 如果这个用户的账号或密码为空，返回提示
        if (user.getUserName() == null || "".equals(user.getUserName())
                || user.getPassword() == null || "".equals(user.getPassword()))
        {
            return new AccountDto<String>(CreateAccountStatus.CORE_INFO_IS_NULL.getInfo(),
                    CreateAccountStatus.CORE_INFO_IS_NULL);
        }

        // 如果这个用户的其他信息为空，返回提示
        if (user.getName() == null || "".equals(user.getName())
                || user.getRoleId() == null || user.getTypeId() == null)
        {
            return new AccountDto<String>(CreateAccountStatus.INFO_IS_NOT_COMPLETED.getInfo(),
                    CreateAccountStatus.INFO_IS_NOT_COMPLETED);
        }

        // 当所有信息都填完整了，就进行数据库查询，看看这个用户是否存在
        if (userNameExisted(user.getUserName()))
        {
            return new AccountDto<String>(CreateAccountStatus.USERNAME_EXISTED.getInfo(),
                    CreateAccountStatus.USERNAME_EXISTED);
        }

        int affect = accountDao.createAccount(user);
        if (affect > 0) // 创建成功！
        {
            return new AccountDto<String>(CreateAccountStatus.SUCCESS.getInfo(),
                    CreateAccountStatus.SUCCESS);
        }

        // 没有新增成功！未知错误！
        return new AccountDto<String>(CreateAccountStatus.UNKNOWN_ERROR.getInfo(),
                CreateAccountStatus.UNKNOWN_ERROR);
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
        return 1000 + (int)(9000 * Math.random());
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
     * @param user 要签到的用户
     * @param clazz 用户所属班级
     *              如果用户没有班级，那就传入一个 null 即可
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
        /* TODO 班级号没加 */
        //sign.setClassId(clazz.get);
        sign.setCourseId(clazz.getCourseId());
        sign.setDate(new Date());
        sign.setReason(reason);
        /* TODO 课程时间未定 */

        return null;
    }
}
