package com.webone.core.utils.file;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.webone.core.utils.SystemProp;
import com.webone.core.utils.SystemProp2;

/**
 * 文件工具
 * @author skz
 * @date 2015年10月22日
 * @time 上午9:52:41
 */
@Component
public class FileTools {
	
	private static final Logger logger = Logger.getLogger(FileTools.class);
	
	private static String UPLOADPATH = SystemProp2.getProperty("uplpoad.path");
	
	/**
	 * 保存文件到本地
	 * @param file
	 * @return
	 */
	public static String saveFile2Local(File file){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = UPLOADPATH
				+ File.separator + "imgfolder" + File.separator
				+ sdf.format(new Date()) + File.separator;
		
		String fileName = UUID.randomUUID().toString() + "."
				+ FileTools.getFileSuffix(file.getName());
		
		String path = saveFileByUploadFile(file, filePath, fileName);
		return path;
	}
	
	/**
	 * 保存文件到服务器
	 * 保存到服务器路径
	 * @param file
	 * @return
	 */
	public static String saveFile2Server(File file){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		// System.getProperty("user.dir")
		logger.info("filePath------>"+System.getProperty("catalina.home"));
		String filePath = System.getProperty("catalina.home")
				+ File.separator + "imgfolder" + File.separator
				+ sdf.format(new Date()) + File.separator;
		
		String fileName = UUID.randomUUID().toString() + "."
				+ FileTools.getFileSuffix(file.getName());
		String path = null;
		try {
			path = FileTools.saveFileByInputStream(new FileInputStream(file), filePath, fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return path;
	}
	
	/**
	 * 保存文件
	 * @param file
	 * @return
	 */
	public static String saveFile(File file){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = UPLOADPATH
				+ File.separator + "imgfolder" + File.separator
				+ sdf.format(new Date()) + File.separator;
		
		String fileName = UUID.randomUUID().toString() + "."
				+ FileTools.getFileSuffix(file.getName());
		
		String path = saveFileByUploadFile(file, filePath, fileName);
		return path;
	}
	
	/**
	 * 保存多文件
	 * @param files
	 * @return
	 */
	public static List<String> saveFiles(List<File> files){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = UPLOADPATH
				+ File.separator + "upload" + File.separator
				+ sdf.format(new Date()) + File.separator;
		List<String> filePaths = new ArrayList<String>();
		String fileName = null;
		String fPath = null;
		for (File file : files) {
			fileName = UUID.randomUUID().toString() + "."
					+ FileTools.getFileSuffix(file.getName());
			fPath = FileTools.saveFileByUploadFile(file, filePath, fileName);
			filePaths.add(fPath);
		}
		return filePaths;
	}
	
	/**
	 * 保存多文件
	 * @param files
	 * @return
	 */
	public static List<String> saveFiles(File[] files){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = UPLOADPATH
				+ File.separator + "upload" + File.separator
				+ sdf.format(new Date()) + File.separator;
		List<String> filePaths = new ArrayList<String>();
		String fileName = null;
		String fPath = null;
		for (File file : files) {
			fileName = UUID.randomUUID().toString() + "."
					+ FileTools.getFileSuffix(file.getName());
			fPath = FileTools.saveFileByUploadFile(file, filePath, fileName);
			filePaths.add(fPath);
		}
		return filePaths;
	}
	
	
	/**
	 * 保存MultipartFile文件
	 * @param multipartFile
	 * @return
	 */
	public static String saveMultipartFile(MultipartFile multipartFile){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = SystemProp.getProperty("upload.path")
				+ File.separator + "Files" + File.separator
				+ sdf.format(new Date()) + File.separator;
		
		String fileName = UUID.randomUUID().toString() + "."
				+ FileTools.getFileSuffix(multipartFile.getOriginalFilename());
		
		String path = FileTools.saveMultipartFileByUploadFile(multipartFile, filePath, fileName);
		
		return path;
	}
	
	/**
	 * 保存多个MultipartFile文件
	 * @param multipartFiles
	 * @return
	 */
	public static List<String> saveMultipartFiles(List<MultipartFile> multipartFiles){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = SystemProp.getProperty("uplpad.path")
				+ File.separator + "imgfolder" + File.separator
				+ sdf.format(new Date()) + File.separator;
		List<String> filePaths = new ArrayList<String>();
		String fileName = null;
		String fPath = null;
		
		for (MultipartFile multipartFile : multipartFiles) {
			fileName = UUID.randomUUID().toString() + "."
					+ FileTools.getFileSuffix(multipartFile.getOriginalFilename());
			fPath = FileTools.saveMultipartFileWithBufferStream(multipartFile, filePath, fileName);
			filePaths.add(fPath);
		}
		return filePaths;
	}
	
	/**
	 * 保存多个MultipartFile文件
	 * @param multipartFiles
	 * @return
	 */
	public static List<String> saveMultipartFiles(MultipartFile[] multipartFiles){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filePath = UPLOADPATH
				+ File.separator + "imgfolder" + File.separator
				+ sdf.format(new Date()) + File.separator;
		List<String> filePaths = new ArrayList<String>();
		String fileName = null;
		String fPath = null;
		
		for (MultipartFile multipartFile : multipartFiles) {
			fileName = UUID.randomUUID().toString() + "."
					+ FileTools.getFileSuffix(multipartFile.getOriginalFilename());
			fPath = FileTools.saveMultipartFileWithBufferStream(multipartFile, filePath, fileName);
			filePaths.add(fPath);
			logger.info("file Path ----->" + fPath);
		}
		return filePaths;
	}
	
	/**
	 * 保存上传文件
	 * @param file  	上传文件
	 * @param fileDirs  文件保存目录
	 * @param fileName  文件名
	 * @return
	 */
	private static String saveFileByUploadFile(File file,String fileDirs,String fileName) {
		FileInputStream in   = null;
		FileOutputStream out = null;
		
		//创建目录
		File fPath = new File(fileDirs);
		if(!fPath.exists()){
			fPath.mkdirs();
		}
		
		//创建文件
		File files = new File(fileDirs + fileName);
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
			int read = 0;
			
			while((read = in.read(buffer)) != -1){
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
		
		return (fileDirs + fileName);
	}
	
	
	
	/**
	 * 保存上传文件（以springmvc MultipartFile）
	 * @param multipartFile		MultipartFile
	 * @param fileDirs			文件保存目录
	 * @param fileName			文件名
	 * @return
	 */
	private static String saveMultipartFileByUploadFile(MultipartFile multipartFile,String fileDirs,String fileName){
		// 创建目录
		File fPath = new File(fileDirs);
		if (!fPath.exists()) {
			fPath.mkdirs();
		}
		// 创建文件
		File file = new File(fileDirs + fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// converting MultipartFile to File
		try {
			multipartFile.transferTo(file);

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileDirs + fileName;
	}
	
	/**
	 * 通过BufferOutputStream保存MultipartFile文件
	 * 注:以字节的方式保存
	 * @param multipartFile		MultipartFile
	 * @param fileDirs			文件保存目录
	 * @param fileName			文件名
	 * @return
	 */
	private static String saveMultipartFileWithBufferStream(MultipartFile multipartFile,String fileDirs,String fileName){
		// 创建目录
		File fPath = new File(fileDirs);
		if (!fPath.exists()) {
			fPath.mkdirs();
		}
		// 创建文件
		File file = new File(fileDirs + fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try{
			 byte[] bytes = multipartFile.getBytes();
			 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
			 stream.write(bytes);
             stream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return fileDirs + fileName;
	}
	
	/**
	 * 根据文件输入流保存文件
	 * @param fileStream	文件流
	 * @param fileDirs		文件保存目录
	 * @param fileName		文件名
	 * @return
	 */
	private static String saveFileByInputStream(InputStream fileStream,String fileDirs,String fileName){
		FileOutputStream out = null;
		// 创建目录
		File fPath = new File(fileDirs);
		if (!fPath.exists()) {
			fPath.mkdirs();
		}
		// 创建文件
		File file = new File(fileDirs + fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//写入文件
		try {
			out = new FileOutputStream(file);
			byte[] buffer = new byte[1024 * 1024];
			int read = 0;
			while ((read = fileStream.read(buffer)) != -1) {
				out.write(buffer, 0, read);
				out.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(fileStream != null){
				try {
					fileStream.close();
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
		return fileDirs + fileName;
	}
	
	/**
	 * 根据二进制字节流保存文件
	 * @param fileBinary	字节文件
	 * @param fileDirs		文件保存目录
	 * @param fileName		文件名
	 * @return
	 */
	private static String saveFileByBinaryByteStream(byte[] fileBinary,String fileDirs,String fileName){
		//创建目录
		File fpath = new File(fileDirs);
		if(!fpath.exists()){
			fpath.mkdirs();
		}
		
		//创建文件
		File file = new File(fileDirs + fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//写入文件
		try {
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			out.write(fileBinary);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileDirs + fileName;
	}
	
	
	/**
	 * 获取文件后缀
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	/***********************************
	 * 关于文件的操作，org.apache.commons提供了很多方法
	 * 
	 * 查看源代码
	 * @URL http://www.boyunjian.com/do/jarse/s.do?keyword=org.apache.commons
	 * 
	 * TODO
	 * 获取文件的大小、文件的可读性大小值，复制文件，删除文件，将文件转为字节数组或将字节数组转为文件等等
	 * 都可以通过 org.apache.commons.io.FileUtils 提供的方法来实现
	 * @URL http://commons.apache.org/proper/commons-io/apidocs/index.html?org/apache/commons/io/FileUtils.html
	 * 
	 * TODO
	 * FilenameUtils 提供了关于文件名的操作方法，如获取文件后缀等。
	 * @URL http://commons.apache.org/proper/commons-io/apidocs/org/apache/commons/io/FilenameUtils.html
	 * 
	 */
	
	
	/**
	 * 获取文件大小
	 * @param file
	 * @return
	 */
	public static long getFileSize(File file){
		if(file.exists() && file.isFile()){
			return file.length();
		}else{
			logger.info("传入的参数不是文件!");
			return 0;
		}
	}
	
	/**
	 * 根据文件流通过FileChannel获取文件大小
	 * 不能用FileInputStream，该方式获取的大小为int
	 * 可能承受的最大值可能小于文件的大小
	 * @url http://docs.oracle.com/javase/7/docs/api/java/nio/channels/FileChannel.html
	 * @param stream
	 * @return
	 */
	public static long getInputStreamFileSize(FileInputStream stream){
		FileChannel fc = null;
		long size = 0;
		try {
			if(stream != null){
				fc= stream.getChannel(); 
				size = fc.size();
			}else{
				logger.info("文件流不存在");
				size = 0;
			}
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} finally{
			if(fc != null){
				try {
					fc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return size;
	}
	
	/**
	 * 格式化文件大小(单位)
	 * @url http://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
	 * @param size   文件大小
	 * @return
	 */
	public static String formatFileSize(long size){
		DecimalFormat formatter = new DecimalFormat("####.00");
		if (size < 1024) {// smaller than KB
			return size + "B";
		} else if (size < 1024 * 1024) {// smaller than MB
			return formatter.format(size / 1024f) + "KB";
		} else if (size < 1024 * 1024 * 1024) {// smaller than GB
			float mbSize = size / 1024f / 1024f;
			return formatter.format(mbSize) + "MB";
		} else if (size < 1024 * 1024 * 1024 * 1024) {// smaller than TB
			float gbSize = size / 1024f / 1024f / 1024f;
			return formatter.format(gbSize) + "GB";
		} else if (size < 1024 * 1024 * 1024 * 1024 * 1024) {// smaller than PB
			float tbSize = size / 1024f / 1024f / 1024f / 1024f;
			return formatter.format(tbSize) + "TB";
		} else if (size < 1024 * 1024 * 1024 * 1024 * 1024 * 1024) {// smaller than EB
			float ebSize = size / 1024f / 1024f / 1024f / 1024f / 1024f;
			return formatter.format(ebSize) + "PB";
		} else {
			return "too large !";
		}
	}
	
	/**
	 * 把文件写到字节数组
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static byte[] readFileToByteArray(File file) throws FileNotFoundException,IOException{
		ByteArrayOutputStream baos = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024*1024];
			int len = 0;
			while((len = inputStream.read(buffer)) != -1){
				baos.write(buffer,0,len);
			}
		} finally{
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(baos != null){
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos.toByteArray();
	}
}
