package com.webone.core.utils.hexbinary;

/**
 * 二进制工具类
 * @author skz
 * @date 2015年10月25日
 * @time 下午9:30:24
 */
public class BinaryUtil {

	
	/**
	 * TODO 不理解
	 * 将二进制转为字符串
	 * 16进制
	 * @param bytes
	 * @return
	 */
	public static String byte2hex(byte[] bytes){
		String hs = "";
		String stmp = "";
		
		for (int i = 0; i < bytes.length; i++) {
			stmp = Integer.toHexString(bytes[i] & 0XFF);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		
		return hs.toUpperCase();
	}
	
	/**
	 * TODO 不懂
	 * 字符串字节转为16进制字节
	 * @param b
	 * @return
	 */
	public static byte[] hex2byte(byte[] b){
		if(b.length%2 != 0) throw new IllegalArgumentException("长度不是偶数");
		
		byte[] bytes = new byte[b.length/2];
		
		for (int i = 0; i < b.length; i+=2) {
			String item = new String(b,i,2);
			bytes[i/2] = (byte)Integer.parseInt(item,16);
		}
		
		return bytes;
	}
}
