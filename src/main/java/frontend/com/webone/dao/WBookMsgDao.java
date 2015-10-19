package com.webone.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webone.entity.WBookMsg;


/**
 * 分页DAO
 * @author skz
 * @date 2015年9月8日
 * @time 下午3:00:46
 * 
 * @NOTE JpaRepository 继承了 CrudRepository<T,ID>, PagingAndSortingRepository<T,ID>
 */
@Repository
public interface WBookMsgDao extends JpaRepository<WBookMsg, Long> {
	
	/* 
	 * 按分页查询所有书籍信息
	 * @param pageable
	 * @return Page
	 * 
	 * @description Pageable 是 PageRequest 的父接口
	 * 				Page<T>  是对象集合的子集
	 */
	public Page<WBookMsg> findAll(Pageable pageable);
}
