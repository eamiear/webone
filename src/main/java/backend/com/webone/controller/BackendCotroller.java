package com.webone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台控制器
 * @author skz
 * @date 2015年9月14日
 * @time 下午4:14:41
 */
@Controller
@RequestMapping("backend")
public class BackendCotroller {

	/**
	 * 后台功能首页
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView backendIndex(){
		return new ModelAndView("backend/index");
	}
	
	/**
	 * 基本上传
	 * @return
	 */
	@RequestMapping("/base-upload.html")
	public ModelAndView baseUpload(){
		return new ModelAndView("backend/form/upload/base-upload");
	}
	
	
	@RequestMapping("/upload.html")
	public ModelAndView upload(){
		return new ModelAndView("backend/backup/upload");
	}
	
	/**
	 * jQueryUpload上传
	 * @return
	 */
	@RequestMapping("/jquery-upload.html")
	public String jqueryUpload(){
		return "backend/form/upload/jquery-upload";
	}
	
}
