package dao;

import java.util.List;

import entity.Sign;

/**
 * 签到方法
 * @author happyChicken
 *
 */
public interface SignDao {
	
	/**
	 * 插入一个签到->签到表
	 * @param sign 签到状态
	 * @return	影响的行数，如果是1则签到成功
	 */
	public int insertIntoSign(Sign sign);
	
	/**
	 * 根据用户id查询签到情况
	 * @param userId 用户id
	 * @return 签到情况集合
	 */
	public List<Sign> getSignsByUserId(int userId);
	
	/**
	 * 根据班级id查询签到情况
	 * @param classId 班级id
	 * @return 班级签到情况集合
	 */
	public List<Sign> getSignsByClassId(int classId);
	
	/**
	 * 得到所有的签到情况
	 * @return 所有签到情况集合
	 */
	public List<Sign> getAllSigns();
}
