package entity;
/**
 * 详细问卷内容，对应quiz_detail表
 * @author happyChicken
 *
 */
public class QuizDetail {
	
	// 问卷id
	private int quizDetail;
	
	// 问题
	private String question;

	public int getQuizDetail() {
		return quizDetail;
	}

	public void setQuizDetail(int quizDetail) {
		this.quizDetail = quizDetail;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "QuizDetail [quizDetail=" + quizDetail + ", question=" + question + "]";
	}
}
