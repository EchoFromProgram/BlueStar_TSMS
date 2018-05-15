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

    // 问题1
    private List<String> questions = null;

    public List<String> getQuestions()
    {
        return questions;
    }

    public void setQuestions(List<String> questions)
    {
        this.questions = questions;
    }

    public Integer getQuizDetailId()
    {
        return quizDetailId;
    }

    public void setQuizDetailId(Integer quizDetailId)
    {
        this.quizDetailId = quizDetailId;
    }

    @Override
    public String toString()
    {
        return "QuizDetail{" +
                "quizDetailId=" + quizDetailId +
                ", questions=" + questions +
                '}';
    }
}
