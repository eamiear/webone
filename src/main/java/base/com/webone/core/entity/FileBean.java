package com.webone.core.entity;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件Bean
 * 用于接收文件
 * @author skz
 * @date 2015年10月19日
 * @time 下午10:01:32
 */
public class FileBean {

	private File file;				//文件
	private List<File> files;		//文件集合
		
	private MultipartFile multipartFile;	//multipartFile
	private List<MultipartFile> multipartFiles;	//multipartFile 集合
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}
	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}
}
