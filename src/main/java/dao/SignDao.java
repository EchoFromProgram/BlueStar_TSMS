package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	public Integer insertIntoSign(Sign sign);
	
	/**
	 * 根据用户id查询签到情况
	 * @param userId 用户id
	 * @return 签到情况集合
	 */
	public List<Sign> getSignsByUserId(Integer userId);
	
	/**
	 * 根据班级id和角色id，查询某班级学生的签到情况
	 * @param classId 班级id
	 * @param roleId  角色id
	 * @return 班级学生签到集合
	 */
	public List<Sign> getSignsByClassIdAndRoleId(@Param("classId")Integer classId, 
												 @Param("roleId")Integer  roleId);
	
	/**
	 * 得到所有的签到情况
	 * @return 所有签到情况集合
	 */
	public List<Sign> getAllSigns();
	
	
}
