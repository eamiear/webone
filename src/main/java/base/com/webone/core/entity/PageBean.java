package com.webone.core.entity;

import java.io.Serializable;

/**
 * 分页类
 * @description  此为针对BootstrapTable的一个分页类
 * bootstrapTable默认会传递sort、order、limit、offset参数
 * 这些参数的值，BootstrapTable中会自动赋值
 * @author skz
 * @date 2015年9月8日
 * @time 下午2:19:00
 */
@SuppressWarnings("serial")
public class PageBean implements Serializable{
	private String sort;  //排序字段
	private String order; //desc:降序排列    asc:升序排列
	private int limit;	 //每页显示记录数量 
	private int offset;  //已显示记录数量
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
}
