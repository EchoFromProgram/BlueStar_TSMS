package service;

import dao.NoticeDao;
import dto.AccountDto;
import entity.Notice;
import entity.NoticeDetail;
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

    /**
     * 学生通过 classId 获取属于他的通知
     *
     * @param pageNumber 页数
     * @param classId    班级 id
     * @return 返回通知信息
     */
    public AccountDto getNotices(Integer pageNumber, Integer classId);

    /**
     * 管理员得到所有通知
     *
     * @param pageNumber 页数
     * @return 返回通知信息
     */
    public AccountDto getNotices(Integer pageNumber);
    
    /**
     * 新增一条通知和具体通知信息
     * @param notice 需要先插入一条具体通知，然后再插入通知类
     * @return 处理结果
     */
    public AccountDto insertNoticeAndNoticeDetail(NoticeDetail noticeDetail, Notice notice);
    
    /**
     * 更新具体通知
     * @param noticeDetail 具体通知对象
     * @return 处理结果
     */
    public AccountDto updateNoticeDetailByNoticeDetailId(NoticeDetail noticeDetail);
    
    /**
     * 根据具体通知id联表删除通知
     * @param noticeDetailId 具体通知id
     * @return 处理结果
     */
    public AccountDto deleteNoticeDetailAndNotice(Integer noticeDetailId);
    
}
