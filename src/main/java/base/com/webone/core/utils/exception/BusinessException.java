package com.webone.core.utils.exception;

/**
 * 自定义事务异常
 * @author skz
 * @date 2015年10月21日
 * @time 下午9:52:00
 */
@SuppressWarnings("serial")
public class BusinessException extends Exception{
	
	private String message = null;
	
	public BusinessException(){
		super();
	}
	
	public BusinessException(String message){
		super(message);
		this.message = message;
	}
	
	public BusinessException(Throwable cause){
		super(cause);
	}
	
	public BusinessException(String message,Throwable throwable){
		super(message, throwable);
	}
	
	public String toString(){
		return message;
	}
	
	public String getMessage(){
		return message;
	}
}
