package service;

import dao.NoticeDao;
import dto.AccountDto;
import entity.User;

/**
 * 问卷业务类
 *
 * @author Fish
 * */
public interface NoticeService
{
    /**
     * 老师获取通知，这里使用分页，一次显示 Page.ONE_PAGE_NUMBER 页数
     *
     * @param pageNumber 显示第几页
     * @return 返回分页之后的通知信息
     * */
    public AccountDto getNotices(Integer pageNumber, User user);
}
