package service;

import dto.AccountDto;
import entity.SignData;
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

    @Autowired
    private SignService signService = null;

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
    public void testCreateAccount()
    {
        User user = new User();
        user.setUserName("Goddess");
        user.setPassword("696969");
        user.setName("啦啦啦，卖报的小画家");
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

    @Test
    public void testGetSignsByClassId()
    {
        AccountDto<PageInfo<SignData>> signs = signService.getSignsByUserId(1, 2);
        System.out.println(signs);
    }
}
