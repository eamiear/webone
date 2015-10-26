package com.webone.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author skz
 * @date 2015年10月25日
 * @time 下午8:21:05
 */
public class CommonUtils {

	
	/**
	 * 将汉字转为拼音
	 * @param str
	 * @return
	 */
	public static String hanYu2PinYin(String str){
		String py = "";
		String[] hz2py = new String[str.length()];
		char[] hanzi = new char[str.length()];
		
		for (int i = 0; i < str.length(); i++) {
			hanzi[i] = str.charAt(i);
		}
		
		//设置拼音输出格式
		HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
		//设置拼音大小写
		pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		//设置声调格式(无声调)
		pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		//字符类型
		pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		
		try {
			for (int i = 0; i < str.length(); i++) {
				//如果字符串中包含字符'a~z'或'A~Z','0~9'，不做处理
				/*if ((str.charAt(i) >= 'a' && str.charAt(i) < 'z')
						|| (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
						|| (str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
					py += str.charAt(i);
				} else {//汉字字符转拼音
					hz2py = PinyinHelper.toHanyuPinyinStringArray(hanzi[i], pyFormat);
					py += hz2py[0];
				}*/
				
				if(isHanZi(Character.toString(str.charAt(i)))){
					hz2py = PinyinHelper.toHanyuPinyinStringArray(hanzi[i], pyFormat);
					py += hz2py[0]+" ";//拼音间加个空格
				}else{
					py += str.charAt(i);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		
		return py.trim().toString();
	}
	
	/**
	 * 判断字符串包含汉字
	 * @param str
	 * @return
	 */
	public static boolean isHanZi(String str){
		String hanziPattern = "[\u4E00-\uFA29]|[\uE7C7-\uE7F3]";
		Pattern pattern = Pattern.compile(hanziPattern);
		Matcher matcher = pattern.matcher(str);
		
		if(matcher.find()){
			return true;
		}else{
			return false;
		}
	}
}
