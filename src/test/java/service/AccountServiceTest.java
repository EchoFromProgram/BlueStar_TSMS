package service;

import dto.AccountDto;
import entity.User;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

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
        user.setUserName("macky");
        user.setPassword("123");

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
    public void testPageInfo() {
    	AccountDto<PageInfo<User>> accountDto = accountService.getAllAccounts(88);
    	System.out.println((accountDto.getData()));
    }
}
