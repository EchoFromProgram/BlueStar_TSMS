package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AccountDto;
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
}
