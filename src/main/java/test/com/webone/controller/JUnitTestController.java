package com.webone.controller;

import org.junit.Test;

import com.webone.core.utils.CommonUtils;
import com.webone.core.utils.encryt.EncryptUtil;
import com.webone.core.utils.hexbinary.HexUtils;

/**
 * JUnit测试控制器
 * @author skz
 * @date 2015年10月25日
 * @time 下午8:25:25
 */
public class JUnitTestController {

	/**
	 * 汉字转拼音
	 */
	@Test
	public void HanYu2PinYin(){
		System.out.println("我叫苏东坡!--->"+CommonUtils.hanYu2PinYin("我叫苏东坡! 我来自古代中国。"));
	}
	
	/**
	 * 是否汉字
	 */
	@Test
	public void isHanZi(){
		System.out.println("'我' 是否是汉字----->" + CommonUtils.isHanZi("我    3"));
	}
	
	/**
	 * 加密密码
	 */
	@Test
	public void DECEncryptPassword(){
		String password = "123456";
		String encrpt = EncryptUtil.DESEncryptPassword(password);
		String decrypt = EncryptUtil.DESDecrptPassword(encrpt);
		
		System.out.println(password + " 密码加密------>" + encrpt);
		System.out.println("密码解密------>" + decrypt);
	}
	
	@Test
	public void hexString2String(){
		 String hex = "ef2c71b29202f3e642f2abd8d518f367ec3fbf6a6a61beb678ae0c871ee368ac";
		 System.out.println(HexUtils.hexString2Str(hex));
	}
	
}
