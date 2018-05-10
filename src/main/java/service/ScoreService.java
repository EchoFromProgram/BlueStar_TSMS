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
     * 新增一条成绩
     *
     * @param score
     * @return
     */
    public AccountDto insertScore(Score score);
}
