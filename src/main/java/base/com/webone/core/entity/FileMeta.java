package com.webone.core.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 文件信息Bean
 * @author skz
 * @date 2015年10月21日
 * @time 下午2:42:31
 */
//ignore "bytes" when return json format
@JsonIgnoreProperties({"bytes"}) 
public class FileMeta {

	private String fileName;	//文件名
	private String fileType;	//文件类型
	private String fileSize;	//文件大小
	//private long fileSize;
	
	private byte[] bytes;		//文件字节

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
