package service.impl;

import dao.AccountDao;
import dto.AccountDto;
import entity.User;
import enums.LoginStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AccountService;

import java.rmi.server.ObjID;
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
            return new AccountDto<String, LoginStatus>(LoginStatus.WRONG_USERNAME.getInfo(), LoginStatus.WRONG_USERNAME);
        }

        // 从数据库中查出这个账号的密码
        User u = accountDao.getUserByUserName(user.getUserName());

        // 用户名不存在！
        if (u == null)
        {
            return new AccountDto<String, LoginStatus>("用户名不存在！", LoginStatus.WRONG_USERNAME);
        }

        // 判断前台登陆用户输入的密码和后台数据的密码是否一致
        if (!u.getPassword().equals(user.getPassword()))
        {
            return new AccountDto<String, LoginStatus>(LoginStatus.WRONG_PASSWORD.getInfo(), LoginStatus.WRONG_PASSWORD);
        }

        // 登陆成功，将要携带的信息带给前台
        Map<String, Object> infos = new HashMap<String,Object>();
        infos.put("user", u);
        infos.put("HisPowers", accountDao.getPowerIdByRoleId(u.getRoleId()));

        return new AccountDto<Map, LoginStatus>(infos, LoginStatus.SUCCESS);
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
    public boolean checkUserNameIfValidated(String username)
    {
        return accountDao.userNameIsExit(username) > 0;
    }
}
