package service;

import dto.AccountDto;
import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试账号业务方法
 *
 * @author Fish
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class AccountServiceTest
{
    @Autowired
    private AccountService accountService = null;

    @Test
    public void testLogin()
    {
        User user = new User();
        user.setUserName("1");
        user.setPassword("23456");

        AccountDto accountDto = accountService.login(user);

        System.out.println(accountDto);
    }

    @Test
    public void testcreateAccount()
    {
        User user = new User();
        user.setUserName("Fish");
        user.setPassword("666");
        user.setName("叶子");
        user.setRoleId(1);
        user.setTypeId(1);

        AccountDto accountDto = accountService.createAccount(user);

        System.out.println(accountDto);
    }

    @Test
    public void testGetSignCode()
    {
        for (int i = 0; i < 20; i++)
        {
            System.out.println(accountService.getSignCode());
        }
    }
}
