package com.webone.core.utils.hexbinary;

public class HexUtils {

	private static final char[] CHARS = "0123456789ABCDEF".toCharArray();
	private static final String CHARS_SERIAL = "0123456789ABCDEF";
	
	/**
	 * 字符串转成十六进制字符串
	 * @param str	 待转换的ASCII字符串
	 * @return		每个byte之间空格分隔
	 */
	public static String str2HexString(String str){
		StringBuilder sb = new StringBuilder("");
		
		byte[] b = str.getBytes();
		int bit;
		
		for (int i = 0; i < b.length; i++) {
			//按位与,二进制位比较，1&1-->1,1&0  0&0--->0
			bit = (b[i] & 0x0f0)  >> 4;
			sb.append(CHARS[bit]);
			bit = b[i] & 0x0f;
			sb.append(CHARS[bit]);
			sb.append(' ');
		}
		return sb.toString().trim();
	}
	
	/**
	 * 十六进制转为字符串
	 * @param hexString  
	 * @return
	 */
	public static String hexString2Str(String hexString){
		char[] hexs = hexString.toCharArray();
		byte[] bytes = new byte[hexString.length()/2];
		int n;
		
		for (int i = 0; i < bytes.length; i++) {
			n = CHARS_SERIAL.indexOf(hexs[2 * i]) * 16;
			n += CHARS_SERIAL.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte)(n & 0xff);
		}
		
		return new String(bytes);
	}
	
	/**
	 * bytes转为十六进制字符串
	 * @param b
	 * @return
	 */
	public static String byte2HexString(byte[] b){
		String stmp = "";
		StringBuilder sb = new StringBuilder("");
		
		for (int i = 0; i < b.length; i++) {
			stmp = Integer.toHexString(b[i] & 0xFF);
			sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
		}
		return sb.toString().toUpperCase().trim();
	}
	
	/**bytes字符串转为Byte值
	 * @param hexStr
	 * @return
	 */
	public static byte[] hexString2Bytes(String hexStr){
		int m = 0, n = 0;
		byte[] b = new byte[hexStr.length() / 2];
		for (int i = 0; i < b.length; i++) {
			m = i * 2 + 1;
			n = m + 1;
			
			b[i] = Byte.decode("0x" + hexStr.substring(i * 2, m) + hexStr.substring(m,n));
		}
		
		return b;
	}
	
	/**
	 * String字符串转为Unicode字符串
	 * @param str 全角字符串
	 * @return
	 */
	public static String str2Unicode(String str){
		char c;
		int intAsc;
		String strHex;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			intAsc = (int)c;
			strHex = Integer.toHexString(intAsc);
			if( intAsc > 128){
				sb.append("\\u" + strHex);
			}else{
				//地位在前面补00
				sb.append("\\u00" + strHex);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * unicode字符串转为String字符串
	 * @param unicodeStr	16进制字符串(一个unicode为2byte)
	 * @return	全角字符串
	 */
	public static String unicode2Str(String hexStr){
		int t = hexStr.length() / 6;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = hexStr.substring(i * 6,(i + 1) * 6);
			//高位补上00再转
			String s1 = s.substring(2,4) + "00";
			//地位直接转
			String s2 = s.substring(4);
			//将十六进制的string转为int
			int n = Integer.valueOf(s1,16) + Integer.valueOf(s2,16);
			//将int转为字符
			char[] chars = Character.toChars(n);
			sb.append(new String(chars));
		}
		
		return sb.toString();
	}
}
