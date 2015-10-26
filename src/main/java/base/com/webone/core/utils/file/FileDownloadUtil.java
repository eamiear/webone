package com.webone.core.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;


/**
 * 文件下载工具类
 * @author skz
 * @date 2015年10月24日
 * @time 下午9:40:51
 */
public class FileDownloadUtil {

	private final static Logger logger = Logger.getLogger(FileDownloadUtil.class);
	
	/**
	 * 浏览器弹出下载对话框的Header中的文件名
	 * 根据不同的浏览器设置不同的编码，防止中文乱码
	 * @param request	HttpServletRequest
	 * @param fileName  下载后的文件名
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeDownloadFileName(HttpServletRequest request,String fileName){
		String encodedFileName = null;
		String agent = request.getHeader("USER-AGENT");
		try {
			if(null != agent && -1 != agent.indexOf("Mozilla")){
				encodedFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
			}else{
				encodedFileName = java.net.URLEncoder.encode(fileName,"UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return encodedFileName;
	}
	
	/**
	 * 设置文件下载Header头部
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param downloadFileName   下载文件名
	 * @param contentType		   下载文件类型
	 * @param fileLength		   下载文件大小
	 */
	public static void setDownloadFileHeader(HttpServletRequest request,
			HttpServletResponse response, String downloadFileName,
			String contentType, long fileLength) {
		contentType = contentType != null ? contentType : "application/octet-stream";
		downloadFileName = downloadFileName != null ? downloadFileName : UUID.randomUUID().toString() + FilenameUtils.getExtension(downloadFileName);
		if(fileLength <= 0){
			return ;
		}
		response.setContentType(contentType);
		response.setContentType(FileUtils.byteCountToDisplaySize(fileLength));
		response.setHeader("Content-Disposition", "attachment; filename=" + encodeDownloadFileName(request,downloadFileName)); 
	}
	
	/**
	 * 根据文件路径下载文件
	 * @param request
	 * @param response
	 * @param fileBasePath	文件基路经（服务器路经）
	 * @param filePath		文件路径
	 * @param downloadFileName 下载文件的名称
	 * @return
	 */
	public static boolean downLoadFile(HttpServletRequest request,
			HttpServletResponse response, String fileBasePath, String filePath,
			String downloadFileName) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		boolean flag = false;//文件下载是否成功
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			File downloadFile = new File(fileBasePath + filePath);
			//设置Header
			FileDownloadUtil.setDownloadFileHeader(request, response,
					downloadFileName,
					FileMimeTypeUtil.getMimeType(downloadFile),
					downloadFile.length());
			//获取文件流
			bis = new BufferedInputStream(new FileInputStream(downloadFile));
			//输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			
			byte[] buffer = new byte[2048];
			int read;
			while ((read = bis.read(buffer,0,buffer.length)) != -1) {
				bos.write(buffer, 0, read);
			}
			logger.info("downFile success!");
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flag;
	}
	
	/**
	 * 根据文件字节下载文件
	 * TODO 字节长度可能有问题
	 * @param request
	 * @param response
	 * @param fileBytes			文件字节
	 * @param downloadFileName	文件下载名
	 * @param contentType		文件类型
	 * @return
	 */
	public static boolean downloadFile(HttpServletRequest request,
			HttpServletResponse response, byte[] fileBytes,
			String downloadFileName, String contentType) {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		boolean flag = false;//文件下载是否成功
		BufferedOutputStream bos = null;
		
		try {
			//设置Header
			FileDownloadUtil.setDownloadFileHeader(request, response,
					downloadFileName, contentType, fileBytes.length);
			//输出流
			bos = new BufferedOutputStream(response.getOutputStream());
			bos.write(fileBytes, 0, fileBytes.length);
			
			logger.info("download success!");
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flag;
	}

	/**
	 * 下载文件
	 * 这里使用了springmvc提供的FileCopyUtils工具类
	 * @param request
	 * @param response
	 * @param fileBytes
	 * @param downloadFileName
	 * @param contentType
	 * @param fileLength
	 * @return
	 */
	public static boolean downloadFile(HttpServletRequest request,
			HttpServletResponse response, byte[] fileBytes,
			String downloadFileName, String contentType, long fileLength) {
		boolean flag = false;
		try {
			FileDownloadUtil.setDownloadFileHeader(request, response, downloadFileName, contentType, fileLength);
			FileCopyUtils.copy(fileBytes, response.getOutputStream());
			logger.info("donwload success!");
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
