package com.webone.core.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.webone.core.entity.PageBean;

/**
 * 该分页控制器仅针对通过js启动的BootstrapTable
 * @author skz
 * @date 2015年9月8日
 * @time 下午2:27:23
 */
public class PageController {
	/**
	 * spring中提供的分页对象
	 * @document http://docs.spring.io/spring-data/data-commons/docs/current/api/org/springframework/data/domain/PageRequest.html
	 */
	private PageRequest page;
	
	/**
	 * 创建分页
	 * 返回分页对象
	 * @param pageBean
	 * @return PageRequest
	 */
	public PageRequest getPage(PageBean pageBean) {
		int limit    = pageBean.getLimit();
		int offset   = pageBean.getOffset();
		String order = pageBean.getOrder();
		String sort  = pageBean.getSort();
		
		if(sort != null){
			page = new PageRequest(offset / limit , limit , new Sort("asc".equals(order.trim()) ? Direction.ASC : Direction.DESC , sort));
		}else{
			page = new PageRequest(offset / limit , limit);
		}
		
		return page;
	}
	
	/**
	 * 将返回的分页数据格式化成JSON格式的字符串
	 * @param pageList	//返回的分页数据对象
	 * @return String
	 * 
	 * @point  Page Interface Page<T>
	 * @document  http://docs.spring.io/spring-data/data-commons/docs/current/api/org/springframework/data/domain/Page.html
	 * @Note A page is a sublist of a list of objects : 一个分页对象是一个对象集合的子集
	 * 
	 * @desc   PageImpl继承Page，并扩展了方法---->Page对象实例可以调用PageImpl中拓展的方法
	 * @doc	http://docs.spring.io/spring-data/data-commons/docs/current/api/org/springframework/data/domain/PageImpl.html
	 */
	@SuppressWarnings("rawtypes")
	public String pageListToJson(Page pageList) {
		JSONObject jsonObj = new JSONObject();
		JSONArray resultJsonArray   = JSONArray.fromObject(pageList.getContent());
		
		/**
		 * total,rows返回给BootstrapTable
		 * total: 当前分页数据总数
		 * rows: 分页数据
		 */
		jsonObj.put("total", pageList.getTotalElements());
		jsonObj.put("rows", resultJsonArray);
		
		return jsonObj.toString();
	}
}
