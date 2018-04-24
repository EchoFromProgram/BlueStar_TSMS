package dao;

import entity.Notice;
import entity.NoticeDetail;

/**
 * 该方法用于通知
 * @author happyChicken
 *
 */
public interface NoticeDao {
	
	/**
	 * 增加通知对象
	 * @param notice 通知对象
	 * @return 影响的行数，如果是1则插入成功
	 */
	public Integer insertNotice(Notice notice);
	
	/**
	 * 增加具体通知内容
	 * @param noticeDetail 具体通知内容对象
	 * @return 影响的行数
	 */
	public Integer insertNoticeDetail(NoticeDetail noticeDetail);
	
	/**
	 * 根据通知id修改通知对象
	 * @param notice 通知对象
	 * @return 影响的行数，如果是1则修改成功
	 */
	public Integer updateNoticeByQuizId(Notice notice);
	
	/**
	 * 根据班级id修改通知对象
	 * @param notice 通知对象
	 * @return 影响的行数，如果是1则修改成功
	 */
	public Integer updateNoticeByClassId(Notice notice);
	
	
	/**
	 * 通过notice_detail（id）修改通知内容
	 * @param NoticeDetail
	 * @return
	 */
	public Integer updateContentByNoticeDetail(NoticeDetail noticeDetail);
	
	/**
	 * 通过通知id得到通知对象
	 * @param quizId
	 * @return
	 */
	public Notice getNoticeByQuizId(Integer quizId);
	
	/**
	 * 通过具体通知的id得到具体通知对象
	 * @param noticeDetail
	 * @return
	 */
	public NoticeDetail getNoticeDetailByNoticeDetailId(Integer noticeDetailId);
	
}
