package service.impl;

import dao.ScoreDao;
import dto.AccountDto;
import entity.Score;
import entity.ScoreData;
import entity.User;
import enums.impl.Common;
import enums.impl.ScoreStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ScoreService;
import utils.PageUtil;

import java.util.List;

import javax.print.attribute.standard.RequestingUserName;

/**
 * 成绩业务实现类
 *
 * @author Fish
 * created by 2018-05-10
 */
@Service
public class ScoreServiceImpl implements ScoreService
{
    private ScoreDao scoreDao = null;

    @Autowired
    public void setScoreDao(ScoreDao scoreDao)
    {
        this.scoreDao = scoreDao;
    }

    /**
     * 得到所有成绩
     *
     * @param pageNumber 页数
     * @return 成绩集合
     */
    @Override
    public AccountDto getAllScores(Integer pageNumber)
    {
        if (pageNumber == null) // 页数必须传
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<ScoreData> scores = scoreDao.getAllScoreDatas();
        if (scores == null) // 数据为空
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(scores), Common.SUCCESS);
    }

    /**
     * 通过用户查询他的成绩
     *
     * @param pageNumber 页数
     * @param userId 用户 id
     * @return 返回成绩信息
     */
    public AccountDto getScoresByUserId(Integer pageNumber, Integer userId)
    {
        if (pageNumber == null || userId == null) // 参数错误
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<ScoreData> scores = scoreDao.getScoreDatasByUserId(userId);
        if (scores == null) // 未知错误
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(scores), Common.SUCCESS);
    }

    /**
     * 通过班级 id 查询班级学生成绩
     *
     * @param pageNumber 页数
     * @param classId 班级 id
     * @return 返回班级成绩信息
     */
    public AccountDto getScoresByClassId(Integer pageNumber, Integer classId)
    {
        if (pageNumber == null || classId == null) // 参数不能为空
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<ScoreData> scores = scoreDao.getScoresByClassId(classId);
        if (scores == null) // 未知错误
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(scores), Common.SUCCESS);
    }

    /**
     * 通过阶段查成绩
     *
     * @param pageNumber 页数
     * @param status 阶段
     * @return 返回成绩信息
     */
    public AccountDto getScoreByStatus(Integer pageNumber, Integer status)
    {
        if (pageNumber == null || status == null)
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<ScoreData> scores = scoreDao.getScoreDatasByStatus(status);
        if (scores == null)
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(scores), Common.SUCCESS);
    }

    /**
     * 通过班级和阶段查询成绩
     * 这里的阶段要引用 Level 里面的常量
     *
     * @param pageNumber 页数
     * @param status 阶段，目前只有三个
     * @return 返回成绩信息
     */
    public AccountDto getScoresByClassIdAndStatus(Integer pageNumber, Integer status, Integer classId)
    {
        // 参数不能为空
        if (pageNumber == null || status == null || classId == null)
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber);
        List<ScoreData> scores = scoreDao.getScoreDatasByClassIdAndStatus(status, classId);
        if (scores == null) // 数据没有获取到
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(scores), Common.SUCCESS);
    }

    /**
     * 通过 userId 获取成绩
     *
     * @param pageNumber 页数
     * @param userId 用户 id
     * @return 返回成绩信息
     */
    public AccountDto getScoresByHisClassId(Integer pageNumber, Integer userId)
    {
        // 参数错误
        if (pageNumber == null || userId == null)
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber); // 开始分页
        List<ScoreData> scores = scoreDao.getScoreDatasByHisClassId(userId);
        if (scores == null) // 没能得到数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(scores), Common.SUCCESS);
    }

    /**
     * 通过 userId 和阶段获取成绩
     *
     * @param pageNumber 页数
     * @param userId 用户 id
     * @param status 阶段
     * @return 返回成绩信息
     */
    public AccountDto getScoresByStatusAndHisClassId(Integer pageNumber, Integer userId, Integer status)
    {
        // 参数错误
        if (pageNumber == null || userId == null || status == null)
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }

        PageUtil.toPage(pageNumber); // 开始分页
        List<ScoreData> scores = scoreDao.getScoreDatasByStatusAndHisClassId(userId, status);
        if (scores == null) // 没能得到数据
        {
            return new AccountDto(Common.GET_IS_NULL);
        }

        return new AccountDto<>(PageUtil.pageInfo(scores), Common.SUCCESS);
    }

  
    /**
     * 通过 scoreId 更新数据
     *
     * @param score 要被更新的数据
     * @return 返回更新数据
     */
    public AccountDto updateScore(Score score)
    {
        if (score == null) // 参数错误
        {
            return new AccountDto(Common.WRONG_ARGEMENT);
        }
        int num = scoreDao.updateScoreByScoreId(score);
        if(num <= 0 ) 
        {
        	return new AccountDto(ScoreStatus.UPDATE_ERROR);
        }
        return new AccountDto(Common.SUCCESS);
    }
    
    /**
     * 批量插入成绩
     * @param scores 成绩对象集合
     * @return 插入结果
     */
	@Override
	public AccountDto insertScores(List<Score> scores) {
		if(scores == null || scores.size() == 0) {
			return new AccountDto(Common.WRONG_ARGEMENT);
		}
		int num = scoreDao.insertScores(scores);
		if(num <= 0) {
			return new AccountDto(ScoreStatus.INSERT_ERROR);
		}
		return new AccountDto(Common.SUCCESS);
	}
	
	 /**
     * 删除成绩
     * @param scoreId 成绩id
     * @return 删除结果
     */
	@Override
	public AccountDto deleteScore(Integer scoreId) {
		if(scoreId == null) {
			return new AccountDto(Common.WRONG_ARGEMENT);
		}
		int num = scoreDao.deleteScoreByScoreId(scoreId);
		if(num <= 0) {
			return new AccountDto(ScoreStatus.DELTE_ERROR);
		}
		return new AccountDto(Common.SUCCESS);
	}
	
	/**
	 * 根据班级id得到该班级的用户
	 * @param classId 班级id
	 * @return 用户集合
	 */
	@Override
	public AccountDto getUsersByClassId(Integer classId) {
		if(classId == null) {
			return new AccountDto(Common.WRONG_ARGEMENT);
		}
		List<User> users = scoreDao.getUsersByClassId(classId);
		if(users == null || users.size() == 0) {
			return new AccountDto(Common.GET_IS_NULL);
		}
		return new AccountDto<List<User>>(users,Common.SUCCESS);
		
	}
}
