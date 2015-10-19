package com.webone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webone.dao.WBookMsgDao;
import com.webone.entity.WBookMsg;

/**
 * 分页服务
 * @author skz
 * @date 2015年9月8日
 * @time 下午2:58:33
 */
@Service
@Transactional
public class WBookMsgService {

	@Autowired private WBookMsgDao bookMsgDao;
	
	/**
	 * 获取图书列表信息
	 * @param pageable
	 * @return Page<T>
	 */
	public Page<WBookMsg> getBookListInPage(Pageable pageable){
		return bookMsgDao.findAll(pageable);
	}
}
