package service.impl;

import dao.AccountDao;
import dto.AccountDto;
import entity.User;
import enums.impl.CreateAccountStatus;
import enums.impl.LoginStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AccountService;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    public void setAccountDao(AccountDao accountDao)
    {
        this.accountDao = accountDao;
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

    @Override
    public void sign()
    {
        // TODO 未完成
    }
}
