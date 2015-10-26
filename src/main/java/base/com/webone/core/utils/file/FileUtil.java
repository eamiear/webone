package com.webone.core.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.webone.core.utils.Constants;


/**
 * 文件操作工具类
 *
 */
public class FileUtil {

	/**
	 * 图片类型
	 */
	public final static String[] IMG_FILE_TYPE = {"jpg", "bmp", "png", "gif"};
	
	/**
	 * 视频类型
	 */
	public final static String[] VIDEO_FILE_TYPE = {"mp4", "gmf", "wmv" , "avi"};
	
	
	/**
	 * 保存文件
	 * @return String
	 */
	public static String saveFileToLocal(File file){
		FileInputStream in   = null;
		FileOutputStream out = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath      = System.getProperty("user.dir") + File.separator + "imgfolder" + File.separator + sdf.format(new Date()) + File.separator;
		String fileName      = UUID.randomUUID().toString() + "." + FileUtil.getFileSuffix(file.getName());
		
		//创建目录
		File fPath = new File(filePath);
		if(!fPath.exists()){
			fPath.mkdirs();
		}
		
		//创建文件
		File files = new File(filePath + fileName);
		if(!files.exists()){
			try {
				files.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			//把流写入文件中
			out = new FileOutputStream(files);
			in  = new FileInputStream(file);
			
			byte[] buffer = new byte[1024 * 1024];
			//int sum = 0;
			int read = 0;
			
			while((read = in.read(buffer)) != -1){
				//sum += read;
				out.write(buffer, 0, read);
				out.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return (filePath + fileName);
	}
	
	
	/**
	 * 保存文件到服务器
	 * 运用spring mvc的上传操作
	 * @param profile
	 * @return String
	 */
	public static String saveInputfileToLocal(MultipartFile profile) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		if (profile == null || profile.isEmpty()) {
			return "";
		}

		String fileName = UUID.randomUUID().toString() + "."
				+ FileUtil.getFileSuffix(profile.getOriginalFilename());
		String filePath = System.getProperty("user.dir") + File.separator
				+ "imgfolder" + File.separator + sdf.format(new Date())
				+ File.separator;

		// 创建目录
		File fPath = new File(filePath);
		if (!fPath.exists()) {
			fPath.mkdirs();
		}
		// 创建文件
		File file = new File(filePath + fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// converting MultipartFile to File
		try {
			profile.transferTo(file);

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return filePath + fileName;
	}
	
	
	/**
	 * 根据磁盘路径获取http图片路径
	 * @param diskPath
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	public static void showHttpPicture(HttpServletRequest request , HttpServletResponse response) throws UnsupportedEncodingException{
		String filepath = URLDecoder.decode(request.getParameter("filepath"), "UTF-8");//文件路径
		File file       = new File(filepath);
		
		if(!file.exists()){
			return ;
		}
		
		String fileName  = file.getName();
		InputStream in   = null;
		OutputStream out = null;
		
		try {
			out = response.getOutputStream();
			in  = new FileInputStream(file);
			
			//设置文件头信息
			response.setContentType(new MimetypesFileTypeMap().getContentType(file) + ";charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1" ));
			response.addHeader("Content-Length", "" + file.length());
			
			//写文件流
			int len       = 0;
			byte[] buffer = new byte[1024 * 1024];
			while((len = in.read(buffer, 0, buffer.length)) != -1){
				out.write(buffer , 0 , len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 根据磁盘路径获取http路径
	 * @param personUrl
	 * @return String
	 */
	public static String getHttpFilePath(String path) {
		if(path == null) return null;
		
		StringBuffer url = new StringBuffer();
		
		try {
			url.append(Constants.LOCAL_HTTP_PIC_URL)
			   .append("?filepath=").append(URLEncoder.encode(path, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return url.toString();
	}
	
	/**
	 * 判断文件名是否为图片类型
	 * @param fileName
	 * @return
	 */
	public static boolean bImgFileType(String fileName) {
		String fileSuffix = getFileSuffix(fileName);
		for (int i = 0; i < IMG_FILE_TYPE.length; i++) {
			if (IMG_FILE_TYPE[i].equalsIgnoreCase(fileSuffix)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 视频格式
	 * @param fileName
	 * @return
	 */
	public static boolean bVideoFileType(String fileName) {
		String fileSuffix = getFileSuffix(fileName);
		for (int i = 0; i < VIDEO_FILE_TYPE.length; i++) {
			if (VIDEO_FILE_TYPE[i].equalsIgnoreCase(fileSuffix)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 获取文件后缀
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
}
