package service;

import org.springframework.stereotype.Service;

import dto.AccountDto;
import entity.User;

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
    public boolean checkUserNameIfValidated(String username);
}
