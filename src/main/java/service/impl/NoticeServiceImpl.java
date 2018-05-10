package service.impl;

import com.github.pagehelper.PageInfo;
import dao.NoticeDao;
import dto.AccountDto;
import entity.Notice;
import entity.User;
import enums.impl.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.NoticeService;
import utils.PageUtil;

import java.util.List;

/**
 * 通知业务实现类
 *
 * @author Fish
 * created by 2018-05-07
 */
@Service
public class NoticeServiceImpl implements NoticeService
{
    private NoticeDao noticeDao = null;

    @Autowired
    public void setNoticeDao(NoticeDao noticeDao)
    {
        this.noticeDao = noticeDao;
    }

    /**
     * 老师获取通知，这里使用分页，一次显示 Page.ONE_PAGE_NUMBER 页数
     *
     * @param pageNumber 显示第几页
     * @return 返回分页之后的通知信息
     */
    @Override
    public AccountDto getNotices(Integer pageNumber, User user)
    {
        // 如果参数为空，则返回参数错误
        if (pageNumber == null || user == null || user.getUserId() == null)
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<Notice> notices = noticeDao.getNoticesByUserId(user.getUserId());
        if (notices == null) // 得到的是空数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(notices), Common.SUCCESS);
    }

    /**
     * 学生通过 classId 获取属于他的通知
     *
     * @param pageNumber 页数
     * @param classId 班级 id
     * @return 返回通知信息
     */
    public AccountDto getNotices(Integer pageNumber, Integer classId)
    {
        // 如果参数为空，则返回参数错误
        if (pageNumber == null || classId == null)
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<Notice> notices = noticeDao.getNoticesByClassId(classId);
        if (notices == null) // 没有得到数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(notices), Common.SUCCESS);
    }

    /**
     * 管理员得到所有通知
     *
     * @param pageNumber 页数
     * @return 返回通知信息
     */
    public AccountDto getNotices(Integer pageNumber)
    {
        // 如果参数为空，则返回参数错误
        if (pageNumber == null)
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        // TODO 管理员获得所有通知
        return null;
    }
}
