package com.webone.core.utils.file;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.web.multipart.MultipartFile;

/**
 * 获取文件Mime类型工具类
 * @author skz
 * @date 2015年10月24日
 * @time 下午9:40:34
 */
public class FileMimeTypeUtil {

	/**
	 * 通过java 7获取文件mime类型
	 * @param filePath 文件绝对路径
	 * @return
	 */
	public static String getMimeTypeByJ7(String filePath){
		String mimeType = null;
		//Path source = Paths.get(filePath);
		// mimeType = Files.probeContentType(source);
		
		return mimeType;
	}
	
	/**
	 * 获取文件mime类型
	 * @param file
	 * @return
	 */
	public static String getMimeType(File file){
		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(file);
		
		return mimeType;
	}
	
	/**
	 * 根据文件URL获取mimeType
	 * 响应慢
	 * @param fileUrl
	 * @return
	 * @throws IOException
	 * @throws MalformedURLException
	 */
	/*public static String getMimeType(String fileUrl) throws IOException,MalformedURLException{
		String mimeType = null;
		URLConnection urlConnect = null;
		
		URL url = new URL(fileUrl);
		urlConnect = url.openConnection();
		mimeType = urlConnect.getContentType();
		
		return mimeType;
	}*/
	
	/**
	 * 根据文件URL获取mimeType
	 * @param fileUrl
	 * @return
	 */
	public static String getMimeType(String fileUrl){
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String mimeType = fileNameMap.getContentTypeFor(fileUrl);
		
		return mimeType;
	}
	
	/**
	 * 获取MultipartFile文件mime类型
	 * @param multipartFile
	 * @return
	 */
	public static String getMultipartFileMimeType(MultipartFile multipartFile){
		return multipartFile.getContentType();
	}
	
	
	
	//test
	public static void main(String args[]) throws Exception {
	      System.out.println(FileMimeTypeUtil.getMimeType("file://c:/temp/test.TXT"));
	    }
}
