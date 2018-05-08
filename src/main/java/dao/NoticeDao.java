package dao;

import java.util.List;

import entity.Notice;
import entity.NoticeDetail;
import entity.User;
import net.sf.jsqlparser.statement.insert.Insert;

/**
 * 该方法用于通知
 * @author happyChicken
 *
 */
public interface NoticeDao {
	
	/**
	 * 插入具体的签到信息
	 * @param noticeDetail 具体签到类
	 * @return 影响的行数，如果是1则插入成功
	 */
	public Integer insertNoticeDetail(NoticeDetail noticeDetail);
	
	/**
	 * 插入签到信息
	 * @param notice 签到类
	 * @return 影响的行数，如果是1则插入成功
	 */
	public Integer insertNotice(Notice notice);
	
	/**
	 * 更新通知
	 * @param notice 通知对象
	 * @return 影响的行数，如果是1则插入成功
	 */
	public Integer updateNoticeByClassIdAndNoticeDetailId(Notice notice);
	
	/**
	 * 更新具体签到信息
	 * @param noticeDetail 具体通知id
	 * @return	影响的行数，如果是1则修改成功
	 */
	public Integer updateNoticeDetailByNoticeDetailId(NoticeDetail noticeDetail);
	
	/**
	 * 通过班级id查询通知信息(里面包含了具体通知信息)
	 * @param classId 班级id
	 * @return 通知信息集合
	 */
	public List<Notice> getNoticesByClassId(Integer classId);
	
	/**
	 * 通过用户id查询通知信息(里面包含了具体通知信息)
	 * @param userId 用户id
	 * @return 通知信息集合
	 */
	public List<Notice> getNoticesByUserId(Integer userId);
	

	
	/**
	 * 删除通知和通知具体信息
	 * @param noticeDetailId 通知id
	 * @return 影响的行数，如果是1则删除成功
	 */
	public Integer deleteNoticeByNoticeDetailId(Integer noticeDetailId);
	
	
}
