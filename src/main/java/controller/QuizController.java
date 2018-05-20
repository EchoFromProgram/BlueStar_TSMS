package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import dto.AccountDto;
import entity.Clazz;
import entity.Quiz;
import entity.QuizDetail;
import entity.User;
import service.QuizService;
import utils.ListUtil;

@Controller
public class QuizController {

	@Resource
	private QuizService quizService;
	
	@RequestMapping(path = "quiz_admin.do", produces = {"application/json;charset=UTF8"})
	public String quizAdmin() {
		return "quiz_admin";
	}
	
	@RequestMapping(path = "quiz_student.do", produces = {"application/json;charset=UTF8"})
	public String quizStudent() {
		return "quiz_student";
	}
	
	@RequestMapping(path = "quiz_teacher.do", produces = {"application/json;charset=UTF8"})
	public String quizTeacher() {
		return "quiz_teacher";
	}
	
	@ResponseBody
	@RequestMapping(path = "teacher_get_quiz.do", produces = {"application/json;charset=UTF8"})
	public Object teacherGetQuiz(Integer page, Integer classId, Integer courseId, HttpSession session) {
		AccountDto accountDto = null;
		Integer userId = (Integer)((Map)session.getAttribute("user")).get("user_id");
		if(classId == 0 && courseId == 0) {
			accountDto = quizService.getQuizByClassIdAndCourseId(page, userId, null);
		}else if(classId == 0 && courseId != 0) {
			accountDto = quizService.getQuizByClassIdAndCourseId(page, userId, courseId);
		}else if(classId != 0 && courseId == 0) {
			accountDto = quizService.getQuizsByClassAndCourse(page, classId, null);
		}else if(classId != 0 && courseId != 0) {
			accountDto = quizService.getQuizsByClassAndCourse(page, classId, courseId);
		}
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "admin_get_quiz.do", produces = {"application/json;charset=UTF8"})
	public Object adminGetQuiz(Integer page, Integer classId, Integer courseId) {
		AccountDto accountDto = null;
		if(classId == 0 && courseId == 0) {
			accountDto = quizService.getQuizsByClassAndCourse(page, null, null);
		}else if(classId == 0 && courseId != 0) {
			accountDto = quizService.getQuizsByClassAndCourse(page, null, courseId);
		}else if(classId != 0 && courseId == 0) {
			accountDto = quizService.getQuizsByClassAndCourse(page, classId, null);
		}else if(classId != 0 && courseId != 0) {
			accountDto = quizService.getQuizsByClassAndCourse(page, classId, courseId);
		}
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "get_quiz_by_id.do", produces = {"application/json;charset=UTF8"})
	public Object getQuizById(Integer quizId) {
		AccountDto accountDto = quizService.getQuizById(quizId);
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "student_get_quiz.do", produces = {"application/json;charset=UTF8"})
	public Object studentGetQuiz(Integer page, HttpSession session) {
		AccountDto accountDto = quizService.getQuizsByUser(page, (Integer)((Map)session.getAttribute("user")).get("user_id"), null);
		return accountDto;
	}
	
	@ResponseBody
	@RequestMapping(path = "get_quiz_question.do", produces = {"application/json;charset=UTF8"})
	public Object getQuizQuestion() {
		AccountDto accountDto = quizService.getQuiz();
		return accountDto.getData();
	}
	
	
	@ResponseBody
	@RequestMapping(path = "write_quiz.do", produces = {"application/json;charset=UTF8"})
	public Object writeQuiz(HttpSession session, String[] answers, Integer quizDetailId) {
		Quiz quiz = new Quiz();
		quiz.setUserId((Integer)((Map)session.getAttribute("user")).get("user_id"));
		quiz.setClassId(((List<Clazz>)session.getAttribute("hisClasses")).get(0).getClassId());
		quiz.setQuizDetailId(quizDetailId);
		List<String> answerList = Arrays.asList(answers);
		quiz.setAnswers(answerList);
		AccountDto accountDto = quizService.writeQuiz(quiz);
		return accountDto;
	}
	
	
	@ResponseBody
	@RequestMapping(path = "public_quiz.do", produces = {"application/json;charset=UTF8"})
	public Object publicQuiz(String[] questions) {
		List<String> questionList = Arrays.asList(questions);
		QuizDetail quizDetail = new QuizDetail();
		quizDetail.setQuestions(ListUtil.strings2QuizQuestions(questionList));
		AccountDto accountDto = quizService.publishQuiz(quizDetail);
		return accountDto;
	}
}
