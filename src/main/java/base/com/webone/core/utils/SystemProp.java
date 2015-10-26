package com.webone.core.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;

/**
 * 获取系统属性
 * @author skz
 * @date 2015年10月22日
 * @time 下午2:45:55
 */
public final class SystemProp {
	private static Logger logger = Logger.getLogger(SystemProp.class);
	public static Properties SYSTEM_PROP;
	
	/**
	 * 根据key获取值
	 * @param key
	 * @return
	 */
	public static String getProperty(String key){
		SYSTEM_PROP = getSystemProp();
		logger.info("basePath ---->" + (String)SYSTEM_PROP.getProperty(key));
		return (String) SYSTEM_PROP.get(key);
	}
	
	/**
	 * 设置键值对
	 * @param key
	 * @param value
	 */
	public static void setProperty(Object key , Object value){
		getSystemProp().put(key, value);
	}
	
	/**
	 * 获取properties文件对象
	 * @return
	 */
	public static Properties getSystemProp(){
		if(SYSTEM_PROP == null){
			SYSTEM_PROP = new Properties();
			ResourceLoader resourceLoader = new DefaultResourceLoader();
			Resource resource = resourceLoader.getResource("application.properties");
			try {
				if(resource != null){
					DefaultPropertiesPersister propPersister = new DefaultPropertiesPersister();
					propPersister.load(SYSTEM_PROP, new InputStreamReader(resource.getInputStream()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SYSTEM_PROP;
	}
}
