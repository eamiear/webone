package com.webone.controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webone.core.entity.FileMeta;
import com.webone.core.utils.file.FileDownloadUtil;
import com.webone.core.utils.file.FileTools;

@RestController
@RequestMapping("backhandler")
public class BackendHandlerController {

	private static final Logger logger = Logger.getLogger(BackendHandlerController.class);
	//用于测试download
	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	
	/**
	 * 上传多文件
	 * jQuery upload
	 * @param multpartFiles
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/upload")
	public String upload(@RequestParam(value = "files[]") MultipartFile[] multpartFiles) throws IOException {
		//List<String> filePaths = FileTools.saveMultipartFiles(icoUrls);
		//LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		if(multpartFiles != null && multpartFiles.length > 0){
			for (MultipartFile multipartFile : multpartFiles) {
				FileMeta fileMeta = new FileMeta();
				fileMeta.setFileName(multipartFile.getOriginalFilename());
				fileMeta.setFileType(multipartFile.getContentType());
				fileMeta.setFileSize(FileUtils.byteCountToDisplaySize(multipartFile.getSize()));
				fileMeta.setBytes(multipartFile.getBytes());
				files.add(fileMeta);
				String path = FileTools.saveMultipartFile(multipartFile);
				logger.info("path ---->" + path);
			}
			logger.info(JSONArray.fromObject(files).toString());
		}
		//System.out.println("FIles --->"+ JSONArray.fromObject(files).toString());
		return JSONArray.fromObject(files).toString();
	}
	
	/**
	 * 上传多文件
	 * 
	 * @param multpartFiles
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/upload.html")
	public String upload1(@RequestParam(value = "file") MultipartFile[] multpartFiles) throws IOException {
		//List<String> filePaths = FileTools.saveMultipartFiles(icoUrls);
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		if(multpartFiles != null && multpartFiles.length > 0){
			for (MultipartFile multipartFile : multpartFiles) {
				FileMeta fileMeta = new FileMeta();
				fileMeta.setFileName(multipartFile.getOriginalFilename());
				fileMeta.setFileType(multipartFile.getContentType());
				fileMeta.setFileSize(FileUtils.byteCountToDisplaySize(multipartFile.getSize()));
				files.add(fileMeta);
				String path = FileTools.saveMultipartFile(multipartFile);
				logger.info("path ---->" + path);
			}
			logger.info(JSONArray.fromObject(files).toString());
		}
		//System.out.println("FIles --->"+ JSONArray.fromObject(files).toString());
		return JSONArray.fromObject(files).toString();
	}
	
	/**
	 * 下载
	 * @param request
	 * @param response
	 * @param value
	 */
	@RequestMapping(value = "/get/{value}")
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String value) {
		FileMeta file = files.get(Integer.parseInt(value));
		try {
			FileDownloadUtil.downloadFile(request, response, file.getBytes(),
					file.getFileName(), file.getFileType(), 63950930l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
