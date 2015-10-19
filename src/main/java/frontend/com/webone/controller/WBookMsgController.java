package com.webone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webone.core.controller.PageController;
import com.webone.core.entity.PageBean;
import com.webone.entity.WBookMsg;
import com.webone.service.WBookMsgService;


/**
 * 分页控制器
 * @author skz
 * @date 2015年9月8日
 * @time 下午2:55:46
 */
@RestController
@RequestMapping("/wbookmsg")
public class WBookMsgController extends PageController {
	
	@Autowired private WBookMsgService bookMsgService;

	
	/**
	 * 获取图书分页信息
	 * @param page
	 * @return
	 */
	@RequestMapping("/get_book_list")
	public String getBookListInPage(PageBean page){
		PageRequest pageRequest = this.getPage(page);
		Page<WBookMsg> bookList = bookMsgService.getBookListInPage(pageRequest);
		return this.pageListToJson(bookList);
	}
	
}
