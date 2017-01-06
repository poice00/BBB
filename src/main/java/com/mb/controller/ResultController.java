package com.mb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mb.domain.Blog;
import com.mb.domain.PageBean;
import com.mb.domain.ResultCenter;
import com.mb.domain.ResultKS;
import com.mb.domain.ResultMax;
import com.mb.domain.ResultMyPR;
import com.mb.domain.ResultPR;
import com.mb.domain.User;
import com.mb.service.BlogService;
import com.mb.service.ResultCenterService;
import com.mb.service.ResultKSService;
import com.mb.service.ResultMaxService;
import com.mb.service.ResultMyPRService;
import com.mb.service.ResultPRService;
import com.mb.service.UserService;
import com.mb.util.QueryHelper;


@Controller
@RequestMapping("/result")
@SuppressWarnings("unchecked")
public class ResultController  {
	
	@Resource
	private ResultMyPRService resultMyPRService;
	@Resource
	private ResultPRService resultPRService;
	@Resource
	private ResultCenterService resultCenterService;
	@Resource
	private ResultMaxService resultMaxService;
	@Resource
	private ResultKSService resultKSService;
		
	/** 列表 */
	@RequestMapping("/list/ks")
	public String ks(Model model,HttpServletRequest httpRequest) throws Exception {
		List<ResultKS> resultList = resultKSService.getByValue();
		model.addAttribute("resultList", resultList);
		model.addAttribute("aname", "KS");
		return "/result/list";
	}
	/** 列表 */
	@RequestMapping("/list/pr")
	public String pr(Model model,HttpServletRequest httpRequest) throws Exception {
		List<ResultPR> resultList = resultPRService.getByValue();
		model.addAttribute("resultList", resultList);
		model.addAttribute("aname", "MDD");
		return "/result/list";
	}
	/** 列表 */
	@RequestMapping("/list/rc")
	public String rc(Model model,HttpServletRequest httpRequest) throws Exception {
		List<ResultCenter> resultList = resultCenterService.getByValue();
		model.addAttribute("resultList", resultList);
		model.addAttribute("aname", "CenterDistance");
		return "/result/list";
	}
	/** 列表 */
	@RequestMapping("/list/max")
	public String list(Model model,HttpServletRequest httpRequest) throws Exception {
		List<ResultMax> resultList = resultMaxService.getByValue();
		model.addAttribute("resultList", resultList);
		model.addAttribute("aname", "Degree");
		return "/result/list";
	}
	/** 列表 */
	@RequestMapping("/list/mypr")
	public String mypr(Model model,HttpServletRequest httpRequest) throws Exception {
		List<ResultMyPR> resultList = resultMyPRService.getByValue();
		model.addAttribute("resultList", resultList);
		model.addAttribute("aname", "MDD_IB");
		return "/result/list";
	}
}
