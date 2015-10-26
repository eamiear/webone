package com.webone.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取属性文件
 * @author skz
 * @date 2015年10月22日
 * @time 下午8:25:20
 */
public class SystemProp2 {

	public static Properties SYSTEM_PROP; 
	
	public SystemProp2(){
		
	}
	
	/**
	 * 根据key获取属性值
	 * @param key
	 * @return
	 */
	public static String getProperty(String key){
		return getSystemProp().getProperty(key);
	}
	
	/**
	 * 设置properties键值对
	 * @param key
	 * @param value
	 */
	public static void setProperty(Object key ,Object value){
		getSystemProp().put(key, value);
	}
	
	/**
	 * 获取properties对象
	 * @return
	 */
	public static Properties getSystemProp(){
		if(SYSTEM_PROP == null){
			setSystemProp();
		}
		return SYSTEM_PROP;
	}
	
	/**
	 * 设置properties对象
	 */
	public static void setSystemProp(){
		try {
			SYSTEM_PROP = new Properties();
			InputStream inputStream = SystemProp2.class.getClassLoader()
					.getResourceAsStream("application.properties");
			SYSTEM_PROP.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
