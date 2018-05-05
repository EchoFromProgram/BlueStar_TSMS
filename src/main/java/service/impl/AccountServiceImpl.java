package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import constant.Page;
import dao.AccountDao;
import dto.AccountDto;
import entity.*;
import enums.impl.Common;
import enums.impl.CreateAccountStatus;
import enums.impl.LoginStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AccountService;
import utils.PageUtil;

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
            return new AccountDto(LoginStatus.WRONG_USERNAME);
        }

        // 从数据库中查出这个账号的密码
        User u = accountDao.getUserByUserName(user.getUserName());

        // 用户名不存在！
        if (u == null)
        {
            return new AccountDto(LoginStatus.WRONG_USERNAME);
        }

        // 判断前台登陆用户输入的密码和后台数据的密码是否一致
        if (!u.getPassword().equals(user.getPassword()))
        {
            return new AccountDto(LoginStatus.WRONG_PASSWORD);
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
            return new AccountDto(CreateAccountStatus.USER_IS_NULL);
        }

        // 如果这个用户的账号或密码为空，返回提示
        if (user.getUserName() == null || "".equals(user.getUserName())
                || user.getPassword() == null || "".equals(user.getPassword()))
        {
            return new AccountDto(CreateAccountStatus.CORE_INFO_IS_NULL);
        }

        // 如果这个用户的其他信息为空，返回提示
        if (user.getName() == null || "".equals(user.getName())
                || user.getRoleId() == null || user.getTypeId() == null)
        {
            return new AccountDto(CreateAccountStatus.INFO_IS_NOT_COMPLETED);
        }

        // 当所有信息都填完整了，就进行数据库查询，看看这个用户是否存在
        if (userNameExisted(user.getUserName()))
        {
            return new AccountDto(CreateAccountStatus.USERNAME_EXISTED);
        }

        int affect = accountDao.createAccount(user);
        if (affect > 0) // 创建成功！
        {
            return new AccountDto(CreateAccountStatus.SUCCESS);
        }

        // 没有新增成功！未知错误！
        return new AccountDto(CreateAccountStatus.UNKNOWN_ERROR);
    }

    /**
     * 得到所有的账户信息，主要供管理员使用
     *
     * @param pageNumber 获取第几页的数据
     * @return 返回所有账户的信息
     */
    @Override
    public AccountDto getAllAccounts(Integer pageNumber)
    {
        // pageHelper 中每进行一次分页就要执行一次这个方法
        PageUtil.toPage(pageNumber);

        List<User> users = accountDao.getAllUsers();
        if (users == null) // 如果为空，说明没有获取到数据，有可能是系统错误
        {
            return new AccountDto(Common.ERROR);
        }

        // 这里如果 users 的元素个数为 0 也算成功，只能说没有成员
        return new AccountDto<PageInfo<User>>(PageUtil.pageInfo(users), Common.SUCCESS);
    }
}
