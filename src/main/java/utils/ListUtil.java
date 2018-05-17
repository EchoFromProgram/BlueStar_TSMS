package utils;

import entity.PowerId;
import entity.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

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
    
//    /**
//     * 讲int-> powerIds
//     * @param list int集合
//     * @return powerIds集合
//     */
//    public static List<PowerId> integers2Powers(List<Integer> list)
//    {
//        List<PowerId> powerIds = new ArrayList<>();
//        for (Integer i : list)
//        {
//            powerIds.add(new PowerId(i));
//        }
//
//        return powerIds;
//    }
}
