package com.ray.exam.question.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ray.base.util.GridDataModel;
import com.ray.base.util.ModelAndViewUtil;
import com.ray.base.util.SessionUtil;
import com.ray.exam.question.form.QuestionForm;
import com.ray.exam.question.model.QuestionDO;
import com.ray.exam.question.service.QuestionService;
import com.ray.power.login.model.UserSession;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/question")
public class QuestionController {

	private final String tabCode = "question";// 表名标示
	private final String jspPath = "question";// JSP 包名称

	private static Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@Resource(name = "questionService")
	private QuestionService questionService;

	@RequestMapping("index")
	public ModelAndView index(
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session,
		Model model
	) {
		model.addAttribute("tabCode", tabCode);
		return ModelAndViewUtil.Jsp(jspPath + "/index");
	}
	
	@ApiOperation(value = "题库查询分页", httpMethod = "POST", response = String.class, notes = "")
	@RequestMapping(value="queryforlist", method = RequestMethod.POST)
	public ModelAndView queryforlist(
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session,
		QuestionForm form
	) {
		GridDataModel<QuestionDO> gdm = questionService.query(form);
		return ModelAndViewUtil.Json_ok(gdm);
	}

	/**
	 * 跳转到添加或编辑页面
	 */
	@RequestMapping("toSaveOrEdit/{new_or_edit}")
	public ModelAndView toSaveOrEdit (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@PathVariable("new_or_edit") String new_or_edit ,
			@RequestParam(value="qid", required=false)  Integer qid,
			@RequestParam(value="type", required=false)  Integer type){
		model.addAttribute("tabCode", tabCode);
		QuestionDO question = null ;
		if("create".equalsIgnoreCase(new_or_edit)){
			model.addAttribute("new_or_edit", "create");
			model.addAttribute("action","question/save");
			model.addAttribute("question", question);
		} else {
			question = questionService.findQuestionById( qid );
			type = question.getType();
			model.addAttribute("new_or_edit", "edit");
			model.addAttribute("question", question);
			model.addAttribute("action","question/edit/"+question.getQid());
		}
		return ModelAndViewUtil.Jsp( "question/saveOrEdit"+type );
	}
	
	@RequestMapping("save")
	public ModelAndView save (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			QuestionDO question
	){
		UserSession su = SessionUtil.getUserSession(session);
		question.setCid( su.getUserid() );
		question.setUid( su.getUserid() );
		questionService.saveQuestion(question) ;
		return ModelAndViewUtil.Json_ok();
	}
	
	@RequestMapping("edit/{qid}")
	public ModelAndView edit (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@PathVariable("qid") Integer qid ,
			QuestionDO question
	){
		UserSession su = SessionUtil.getUserSession(session);
		question.setQid( qid );
		question.setUid( su.getUserid() );
		questionService.updateQuestion( question ) ;
		return ModelAndViewUtil.Json_ok();
	}
	
	@RequestMapping("delete/{qid}")
	public ModelAndView delete (
			HttpSession session,
			HttpServletRequest request, 
			HttpServletResponse response, Model model,
			@PathVariable("qid") Integer qid
	){
		UserSession su = SessionUtil.getUserSession(session);
		int count = questionService.findPaperByQuestionId(qid);
		//存在级联，不能删除
		if( count >0 ){
			return ModelAndViewUtil.Json_error2();
		}
		questionService.deleteQuestion(su.getUserid() , qid );
		logger.info( su.getUserid() +" delete question " + qid );
		return ModelAndViewUtil.Json_ok();
	}
}