package entity;

import java.util.List;

/**
 * 详细问卷内容，对应quiz_detail表
 *
 * @author happyChicken
 */
public class QuizDetail
{
    // 问卷id
    private Integer quizDetailId;

    // 是否被使用
    private boolean isUsed = false;

    // 问题
    private List<String> questions = null;

    public Integer getQuizDetailId()
    {
        return quizDetailId;
    }

    public void setQuizDetailId(Integer quizDetailId)
    {
        this.quizDetailId = quizDetailId;
    }

    public boolean isUsed()
    {
        return isUsed;
    }

    public void setUsed(boolean used)
    {
        isUsed = used;
    }

    public List<String> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<String> questions)
    {
        this.questions = questions;
    }

    @Override
    public String toString()
    {
        return "QuizDetail{" +
                "quizDetailId=" + quizDetailId +
                ", isUsed=" + isUsed +
                ", questions=" + questions +
                '}';
    }
}
