package utils;

import entity.PowerId;
import entity.QuizQuestion;
import entity.Score;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

/**
 * 列表工具类
 *
 * @author Fish
 * created by 2018-05-16 20:15
 */
public final class ListUtil
{
    /**
     * 将一堆的 String 转成一堆的 QuizQuestion
     *
     * @param list 一堆的 String
     * @return 返回一堆的 QuizQuestion
     */
    public static List<QuizQuestion> strings2QuizQuestions(List<String> list)
    {
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        for (String s : list)
        {
            quizQuestions.add(new QuizQuestion(s));
        }

        return quizQuestions;
    }
    
    /**
     * 将前台的数据封装成成绩对象集合
     * @param classId 班级id
     * @param status 阶段
     * @param date 日期
     * @param map 用户id:成绩
     * @return 成绩对象集合
     */
    public static List<Score> toScores(Integer classId, Integer status, Date date,Map<Integer, Integer> map){
    	List<Score> scores = new ArrayList<>();
    	for(Integer u: map.keySet()) {
    		scores.add(new Score(classId, u, status, date, map.get(u)));
    	}
    	
    	return scores;
    }
}
