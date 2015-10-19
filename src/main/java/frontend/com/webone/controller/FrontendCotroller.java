package com.webone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 前端控制器
 * @description 页面显示
 * @author skz
 * @date 2015年9月14日
 * @time 下午4:14:41
 */
@RestController
@RequestMapping("frontend")
public class FrontendCotroller {

	/**
	 * 前端首页
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView backendIndex(){
		return new ModelAndView("frontend/index");
	}
	
	/**
	 * 插件列表
	 * @return
	 */
	@RequestMapping("/plugin.html")
	public ModelAndView plugin(){
		return new ModelAndView("frontend/plugins/plugin");
	}
	
	/**
	 * 插件列表
	 * @return
	 */
	@RequestMapping("/dataTable.html")
	public ModelAndView dataTable(){
		return new ModelAndView("frontend/table/dataTable");
	}
	
	/*****
	 * 
	 * 表格
	 */
	
	/***** BootstarpTable ****/
	/**
	 * Js驱动bsTable
	 * @return
	 */
	@RequestMapping("/bsTable-by-js")
	public ModelAndView bsTableJs(){
		return new ModelAndView("frontend/table/bstable/bsTable-by-js");
	}
	
	/**
	 * tag驱动bsTable
	 * @return
	 */
	@RequestMapping("/bsTable-by-tag")
	public ModelAndView bsTableTag(){
		return new ModelAndView("frontend/table/bstable/bsTable-by-tag");
	}
	
	/**
	 * Js、tag驱动bsTable
	 * @return
	 */
	@RequestMapping("/bsTable-by-js-tag")
	public ModelAndView bsTableJsTag(){
		return new ModelAndView("frontend/table/bstable/bsTable-by-js-tag");
	}
	
	/**
	 * bsTable常用功能交互
	 * @return
	 */
	@RequestMapping("/bsTable-common-usage")
	public ModelAndView bsTableUsage(){
		return new ModelAndView("frontend/table/bstable/bsTable-usage");
	}
	
	
	/*****
	 * 
	 * 编辑器
	 */
	@RequestMapping("/simditor")
	public ModelAndView simditor(){
		return new ModelAndView("frontend/form/editor/simditor");
	}
}
