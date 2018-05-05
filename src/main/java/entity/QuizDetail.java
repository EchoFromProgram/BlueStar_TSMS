package entity;
/**
 * 详细问卷内容，对应quiz_detail表
 * @author happyChicken
 *
 */
public class QuizDetail {
	
	// 问卷id
	private Integer quizDetailId;
	
	// 问题1
	private String question1;
	
	private String question2;

	
	
	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}


	public Integer getQuizDetailId() {
		return quizDetailId;
	}

	public void setQuizDetailId(Integer quizDetailId) {
		this.quizDetailId = quizDetailId;
	}

	@Override
	public String toString() {
		return "QuizDetail [quizDetailId=" + quizDetailId + ", question1=" + question1 + ", question2=" + question2
				+ "]";
	}

	
}
