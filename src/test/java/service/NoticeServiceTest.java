package service;

import com.github.pagehelper.PageInfo;
import dto.AccountDto;
import entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 通知业务测试类
 *
 * @author Fish
 * created by 2018-05-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"
})
public class NoticeServiceTest
{
    @Autowired
    NoticeService noticeService = null;

    @Test
    public void testGetNotices()
    {
        Integer pn = 1;
        User user = new User();
        user.setUserId(2);

        AccountDto accountDto = noticeService.getNotices(pn, user);
        System.out.println(accountDto.getData());

        accountDto = noticeService.getNotices(1, 2);
        System.out.println(accountDto.getData());
    }
}
