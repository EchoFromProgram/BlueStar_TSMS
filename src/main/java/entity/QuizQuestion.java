package entity;

/**
 * 问题类，就一个 String
 *
 * @author Fish
 * created by 2018-05-16 19:05
 */
public class QuizQuestion
{
    private String question = null;

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    @Override
    public String toString()
    {
        return "QuizQuestion{" +
                "question='" + question + '\'' +
                '}';
    }
}
