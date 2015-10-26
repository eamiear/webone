package com.webone.core.utils.string;

public class StringUtils {

	
	/**
	 * 获取字符长度，一个汉字作为1个字符，一个英文子母作为0.5个字符
	 * @param text
	 * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4
	 */
	public static int getStringLength(String text){
		int textLength = text.length();
		int length = textLength;
		
		for (int i = 0; i < textLength; i++) {
			if(String.valueOf(text.charAt(i)).getBytes().length > 1){
				length ++;
			}
		}
		
		return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	}
}
