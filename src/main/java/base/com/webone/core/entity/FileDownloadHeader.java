package com.webone.core.entity;

import java.io.UnsupportedEncodingException;

public class FileDownloadHeader {

	private String agent;
	private String fileName;
	private String encodedFileName;
	private String contentType;
	private String header ;
	
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getEncodedFileName() {
		return encodedFileName;
	}
	public void setEncodedFileName(String encodedFileName) throws UnsupportedEncodingException {
		if(null != getAgent() && -1 != getAgent().indexOf("Mozilla")){//IE
			this.encodedFileName = new String(encodedFileName.getBytes("UTF-8"),"iso-8859-1");
		}else{
			this.encodedFileName = java.net.URLEncoder.encode(encodedFileName,"UTF-8");
		}
		
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		if(header == null && header == ""){
			this.header = new String("\"Content-Disposition\",\"attachment; filename=\"\"" + getEncodedFileName() + "\"");
		}else{
			this.header = header;
		}
		
	}
}
