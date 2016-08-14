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
import com.mb.service.BlogService;
import com.mb.util.QueryHelper;


@Controller
@RequestMapping("/blog")
public class BlogController  {
	
	@Resource
	private BlogService blogService;
	// =========分页用的参数=============
	protected int currentPage = 1;
	protected int pageSize = 20;
	
	
	/** 列表 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
	public String list(Model model,HttpServletRequest httpRequest) throws Exception {
		QueryHelper qh =  new QueryHelper(Blog.class, "l")
				.addOrderProperty("l.createdTime", false);
		String currentPage = httpRequest.getParameter("currentPage");
		PageBean pageBean = null;
		if(currentPage==null){
			pageBean = blogService.getPageBean(1, pageSize, qh);
		}else{
			int page = Integer.parseInt(currentPage);
			pageBean = blogService.getPageBean(page, pageSize, qh);
		}
		model.addAttribute("pageBean", pageBean);
		return "/blog/list";
	}
}
