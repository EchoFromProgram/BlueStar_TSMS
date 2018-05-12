package service;

import java.util.List;

import dto.AccountDto;
import org.apache.ibatis.annotations.Param;

import entity.Score;

/**
 * 成绩业务类
 *
 * @author imp，Fish
 */
public interface ScoreService
{
    /**
     * 得到所有成绩
     *
     * @param pageNumber 页数
     * @return 成绩集合
     */
    public AccountDto getAllScores(Integer pageNumber);

    /**
     * 通过用户查询他的成绩
     *
     * @param pageNumber 页数
     * @param userId     用户 id
     * @return 返回成绩信息
     */
    public AccountDto getScoresByUserId(Integer pageNumber, Integer userId);

    /**
     * 通过班级 id 查询班级学生成绩
     *
     * @param pageNumber 页数
     * @param classId    班级 id
     * @return 返回班级成绩信息
     */
    public AccountDto getScoresByClassId(Integer pageNumber, Integer classId);

    /**
     * 通过阶段查成绩
     *
     * @param pageNumber 页数
     * @param status     阶段
     * @return 返回成绩信息
     */
    public AccountDto getScoreByStatus(Integer pageNumber, Integer status);

    /**
     * 通过班级和阶段查询成绩
     * 这里的阶段要引用 Level 里面的常量
     *
     * @param pageNumber 页数
     * @param status     阶段，目前只有三个
     * @return 返回成绩信息
     */
    public AccountDto getScoresByClassIdAndStatus(Integer pageNumber, Integer status, Integer classId);

    /**
     * 通过 userId 获取成绩
     *
     * @param pageNumber 页数
     * @param userId     用户 id
     * @return 返回成绩信息
     */
    public AccountDto getScoresByHisClassId(Integer pageNumber, Integer userId);

    /**
     * 通过 userId 和阶段获取成绩
     *
     * @param pageNumber 页数
     * @param userId     用户 id
     * @param status     阶段
     * @return 返回成绩信息
     */
    public AccountDto getScoresByStatusAndHisClassId(Integer pageNumber, Integer userId, Integer status);

    /**
     * 新增一条成绩
     *
     * @param score
     * @return
     */
    public AccountDto insertScore(Score score);
}
