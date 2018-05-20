package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AccountDto;
import entity.Clazz;
import entity.Score;
import service.ScoreService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ScoreController {

	@Resource
	private ScoreService scoreService;
	
	@RequestMapping(path = "score_admin.do", produces = {"application/json;charset=UTF8"})
	public String scoreAdmin() {
		return "score_admin";
	}
	
	@RequestMapping(path = "score_student.do", produces = {"application/json;charset=UTF8"})
	public String scoreStudent() {
		return "score_student";
	}
	
	@RequestMapping(path = "score_teacher.do", produces = {"application/json;charset=UTF8"})
	public String scoreTeacher() {
		return "score_teacher";
	}
	
	/**
	 * 学生通过他的userId来查询他的成绩
	 * 
	 * @param page 分页号
	 * @param userId 学生的userId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "student_get_score.do", produces = {"application/json;charset=UTF8"})
	public Object studentGetScore(Integer page, HttpSession session) {
		AccountDto accountDto = scoreService.getScoresByUserId(page, (Integer)((Map)session.getAttribute("user")).get("user_id"));
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "teacher_get_score.do", produces = {"application/json;charset=UTF8"})
	public Object teacherGetScore(Integer page, Integer classId, Integer stage, HttpSession session) {
		AccountDto accountDto = null;
		Integer userId = (Integer)((Map)session.getAttribute("user")).get("user_id");
		if(classId == 0 && stage == 0) {
			accountDto = scoreService.getScoresByHisClassId(page, userId);
		}
		else if (classId != 0 && stage == 0) {
			accountDto = scoreService.getScoresByClassId(page, classId);
		}
		else if (classId == 0 && stage != 0) {
			accountDto = scoreService.getScoresByStatusAndHisClassId(page, userId, stage);
		}
		else if (classId != 0 && stage != 0) {
			accountDto = scoreService.getScoresByClassIdAndStatus(page, stage, classId);
		}
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "admin_get_score.do", produces = {"application/json;charset=UTF8"})
	public Object adminGetScore(Integer page, Integer classId, Integer stage) {
		AccountDto accountDto = null;

		if(classId == 0 && stage == 0) {
			accountDto = scoreService.getAllScores(page);
		}
		else if (classId != 0 && stage == 0) {
			accountDto = scoreService.getScoresByClassId(page, classId);
		}
		else if (classId == 0 && stage != 0) {
			accountDto = scoreService.getScoreByStatus(page, stage);
		}
		else if (classId != 0 && stage != 0) {
			accountDto = scoreService.getScoresByClassIdAndStatus(page, stage, classId);
		}
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "update_score.do", produces = {"application/json;charset=UTF8"})
	public Object updateScore(Integer scoreId, Integer socreNum) {
		Score score = new Score();
		score.setScore(socreNum);
		score.setScoreId(scoreId);
		AccountDto accountDto = scoreService.updateScore(score);
		return accountDto;
	}
	
	/***
	 * 根据分数id删除列表中的分数信息
	 * 
	 * @param scoreId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "delete_score.do", produces = {"application/json;charset=UTF8"})
	public Object deleteScore(Integer scoreId) {
		AccountDto accountDto = scoreService.deleteScore(scoreId);
		return accountDto;
	}
	
	/***
	 * 
	 * 
	 * @param userIds
	 * @param scoreArr
	 * @param classId
	 * @param stage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(path = "public_score.do", produces = {"application/json;charset=UTF8"})
	public Object publicScore(Integer[] userIds, Integer[] scoreArr, Integer classId, Integer stage) {
		List<Score> scores = new ArrayList<Score>();
		Date date = new Date();
		AccountDto accountDto = scoreService.insertScores(scores);
		return accountDto;
	}
	
//	@ResponseBody
//	@RequestMapping(path = "get_users.do", produces = {"application/json;charset=UTF8"})
//	public Object getUsersByClassId(Integer classId) {
//		List<Score> scores = new ArrayList<Score>();
//		Date date = new Date();
//		AccountDto accountDto
//		return accountDto;
//	}
}
