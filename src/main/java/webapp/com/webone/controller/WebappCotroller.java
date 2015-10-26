package com.webone.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 手机页面控制器
 * @author skz
 * @date 2015年9月14日
 * @time 下午4:14:41
 */
@Controller
@RequestMapping("/webapp")
public class WebappCotroller {

	private static final Logger logger = Logger.getLogger(WebappCotroller.class);
	/**
	 * webapp首页
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView webappIndex(){
		return new ModelAndView("webapp/index");
	}
	
	/**
	 * easypie图表
	 * @return
	 */
	@RequestMapping("/easy-pie-chart.html")
	public String easyPieChart(){
		return "webapp/mobile/web/easy-pie";
	}
}
