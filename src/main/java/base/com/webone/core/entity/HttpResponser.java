package com.webone.core.entity;

import java.util.Vector;

/**
 * Http请求响应体(信息)
 * @author skz
 * @date 2015年10月25日
 * @time 下午3:14:06
 */
public class HttpResponser {
	private Vector<String> responseContent;	//响应返回的内容
	private String urlString ;				//请求URL
	private int defaultPort ;				//默认端口
	private String file;					//请求URL的文件名
	private String host;					//主机
	private String path;					//URL的路径部分
	private int port;				//URL的端口数字
	private String protocol;		//协议
	private String query;			//URL的查询部分
	private String ref;				//
	private String userInfo;		//
	private String content;			//远程主机返回的数据内容
	private String contentEncoded;	//内容编码
	private int responseCode;		//相应状态码
	private String message;			//响应信息
	private String contentType;		//内容类型
	private String method;			//请求方式
	private int connectTimeout;		//请求超时时间
	private int readTimeout;		//读取超时时间
	
	
	public String getUrlString() {
		return urlString;
	}
	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}
	public int getDefaultPort() {
		return defaultPort;
	}
	public void setDefaultPort(int defaultPort) {
		this.defaultPort = defaultPort;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentEncoded() {
		return contentEncoded;
	}
	public void setContentEncoded(String contentEncoded) {
		this.contentEncoded = contentEncoded;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public int getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	public Vector<String> getResponseContent() {
		return responseContent;
	}
	public void setResponseContent(Vector<String> responseContent) {
		this.responseContent = responseContent;
	}
}
