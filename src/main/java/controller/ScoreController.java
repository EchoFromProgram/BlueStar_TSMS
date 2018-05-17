package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AccountDto;
import entity.Clazz;
import service.ScoreService;

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
	public Object studentGetScore(Integer page, Integer userId) {
		AccountDto accountDto = scoreService.getScoresByUserId(page, userId);
		return accountDto.getData();
	}
	
	@ResponseBody
	@RequestMapping(path = "teacher_get_score.do", produces = {"application/json;charset=UTF8"})
	public Object teacherGetScore(Integer page, Integer userId, Integer classId, Integer stage) {
		AccountDto accountDto = null;

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
}
