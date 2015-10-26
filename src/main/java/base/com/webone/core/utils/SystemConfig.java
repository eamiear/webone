package com.webone.core.utils;


import java.util.Properties;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * 全局系统配置类
 * @author yaoxiangping
 *
 */
public final class SystemConfig {
	/**项目名称**/
    public static String PROJECT_PACKAGE_NAME = "webone";
    public static Properties SYSTEM_CONFIG;
    
    /**
     * 根据key获取键值
     * @param key
     * @return String
     */
    public static String getProperty(String key){
    	return (String)SYSTEM_CONFIG.get(key);
    }
    
    /**
     * 通过key修改对应的键,如果key没有就新增对应的key
     * @param key
     * @param value
     */
    public static void setProperty(Object key, Object value){
    	SYSTEM_CONFIG.put(key, value);
    }
    
    /**
     * 获取系统配置属性键值对
     * @return Properties
     */
    public static Properties getSystemConfig(){
    	if(SYSTEM_CONFIG == null){
    		SYSTEM_CONFIG                                = new Properties();
    		PropertiesPersister propertiesPersister      = new DefaultPropertiesPersister();
    		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    		
    		try{
    	        if ((resolver.getResources("classpath*:application.properties") != null) && (resolver.getResources("classpath*:application.properties").length > 0))
    	          propertiesPersister.load(SYSTEM_CONFIG, resolver.getResources("classpath*:application.properties")[0].getInputStream());
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}
    		
    		return SYSTEM_CONFIG;
    	}else{
    		return SYSTEM_CONFIG;
    	}
    }
}
