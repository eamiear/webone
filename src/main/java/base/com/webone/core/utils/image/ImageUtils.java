package com.webone.core.utils.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


/**
 * 图片工具类
 * @author skz
 * @date 2015年10月24日
 * @time 下午9:40:10
 */
public class ImageUtils {

	/**
	 * 图片缩放
	 * @param originPath	原图路径
	 * @param scalePath		缩放图片路径(保存路径)
	 * @param height		缩放高度
	 * @param width			缩放宽度
	 * @param padding		比例不对是否自动补白
	 * @return
	 */
	public static boolean scalingImage(String originPath, String scalePath,
			int height, int width, boolean padding) {
		boolean scaled = false;//是否已压缩
		String imageType = "";	//图片类型
		double ratio = 0;	//缩放比例
		BufferedImage bi = null;
		
		if(!"".equals(originPath) && originPath != null){
			imageType = originPath.substring(originPath.lastIndexOf(".")+1,originPath.length());
		}
		
		File orginalImage = new File(originPath);
		File scalingImage = new File(scalePath);
		
		try {
			//读取原图片
			bi = ImageIO.read(orginalImage);
			Image image = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
			int imageWidth = bi.getWidth();
			int imageHeight = bi.getHeight();
			
			//计算比例
			if((imageWidth > width) || (imageHeight > height)){
				/*if(imageHeight > imageWidth){
					ratio = new Integer(height).doubleValue() / imageHeight;
				} else{
					ratio = new Integer(width).doubleValue() / imageWidth;
				}*/
				ratio = Math.min(new Integer(height).doubleValue()
						/ imageHeight, new Integer(width).doubleValue()
						/ imageWidth);
				AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
				image = op.filter(bi, null);
				if(padding){//自动补白
					BufferedImage pd = new BufferedImage(width, imageHeight, BufferedImage.TYPE_INT_RGB);
					Graphics2D g = pd.createGraphics();
					g.setColor(Color.white);
					g.fillRect(0, 0, width, height);
					if(width == image.getWidth(null)){
						g.drawImage(image, 0,
								(height - image.getHeight(null)) / 2,
								image.getWidth(null), image.getHeight(null),
								Color.white, null);
					}else{
						g.drawImage(image, (width - image.getWidth(null)) / 2, 0,
								image.getWidth(null), image.getHeight(null),
								Color.white, null);
					}
				}
				//将压缩的图片数据写到保存文件
				ImageIO.write((BufferedImage) image, imageType, scalingImage);
				scaled = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return scaled;
	}
	
	/**
	 * 等比压缩图片
	 * @param oldImage		待压缩的文件
	 * @param newImage		压缩后的文件
	 * @param width			宽度(设置宽度，高度为0时，等比缩放)
	 * @param height		高度(设置高度，宽度为0时，等比缩放)
	 * @param quality		图片压缩质量
	 * @return				压缩文件的保存路径
	 */
	public static String compressedImage(File oldImage, File newImage,
			int width, int height, float quality) {
		if(oldImage == null){
			return null;
		}
		try {
			Image originImage = ImageIO.read(oldImage);
			int imageWidth = originImage.getWidth(null);
			int imageHeight = originImage.getHeight(null);
			double ratio = 0;
			
			if(width > 0){
				ratio = width / (double)imageWidth;
				height = (int) (imageHeight * ratio);
			}else if(height > 0){
				ratio = height / (double)imageHeight;
				width = (int)(imageHeight * ratio);
			}
			
			//设置宽高
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			bi.getGraphics().drawImage(originImage, 0, 0, width, height, null);
			
			//压缩后存放位置
			FileOutputStream fos = new FileOutputStream(newImage);
			
			JPEGImageEncoder imageEncoder = JPEGCodec.createJPEGEncoder(fos);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(bi);
			//压缩质量
			jep.setQuality(quality, true);
			imageEncoder.encode(bi, jep);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return newImage.getAbsolutePath();
	}
	
	/**
	 * 给图片添加水印图片
	 * @param targetImage		 需要添加水印的图片，图片路径
	 * @param waterMarkedImage	水印图片的路径
	 * @param x					水印图片距离目标图片左侧的偏移量（x<0,在正中间）
	 * @param y					水印图片距离目标图片上侧偏移量（y<0,在正中间）
	 * @param alpha				透明度（0~1，0为透明，1为不透明）
	 * 生成的水印图片覆盖原来的图片
	 */
	public static void waterMarkingImage(String targetImage,
			String waterMarkedImage, int x, int y, float alpha) {
		try {
			File tImage = new File(targetImage);
			Image image = ImageIO.read(tImage);
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			
			//创建画布容器
			BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象  
			Graphics2D g = bi.createGraphics();
			//在容器中添加目标图片
			g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
			
			//对线段的锯齿状边缘处理 
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);;
			
			//读取要添加的水印文件
			Image waterImage = ImageIO.read(new File(waterMarkedImage));
			int waterImageWidth = waterImage.getWidth(null);
			int waterImageHeight = waterImage.getHeight(null);
			
			//设置透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
			
			//设置偏移量
			int widthGap = imageWidth - waterImageWidth;
			int heightGap = imageHeight - waterImageHeight;
			
			if(x < 0){
				x = widthGap / 2;
			}else if(x < widthGap){
				x = widthGap;
			}
			if(y < 0 ){
				y = heightGap / 2;
			}else if( y > heightGap){
				y = heightGap;
			}
			
			//在容器中添加水印文件
			g.drawImage(waterImage, x, y, waterImageWidth, waterImageHeight, null);
			g.dispose();
			
			//将水印数据写到目标文件中
			ImageIO.write(bi, FilenameUtils.getExtension(targetImage), tImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 给图片添加水印图片
	 * 生成的水印图片会覆盖原来的图片
	 * @param targetImage		 需要添加水印的图片，图片路径
	 * @param waterMarkedImage	水印图片的路径
	 * @param x					水印图片距离目标图片左侧的偏移量（x<0,在正中间）
	 * @param y					水印图片距离目标图片上侧偏移量（y<0,在正中间）
	 * @param alpha				透明度（0~1，0为透明，1为不透明）
	 * @param degree			水印图片旋转角度
	 * 
	 */
	public static void waterMarkingImage(String targetImage,
			String waterMarkedImage, int x, int y, float alpha, Integer degree) {
		try {
			File tImage = new File(targetImage);
			Image image = ImageIO.read(tImage);
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			
			//创建画布容器
			BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象  
			Graphics2D g = bi.createGraphics();
			//对线段的锯齿状边缘处理 
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//在容器中添加目标图片
			g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
			
			//设置水印旋转
			if(null != degree){
				g.rotate(Math.toRadians(degree), (double) bi.getWidth() / 2,
						(double) bi.getHeight() / 2);
			}
			
			//读取要添加的水印文件
			Image waterImage = ImageIO.read(new File(waterMarkedImage));
			int waterImageWidth = waterImage.getWidth(null);
			int waterImageHeight = waterImage.getHeight(null);
			
			//设置透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
			
			//设置偏移量
			int widthGap = imageWidth - waterImageWidth;
			int heightGap = imageHeight - waterImageHeight;
			
			if(x < 0){
				x = widthGap / 2;
			}else if(x < widthGap){
				x = widthGap;
			}
			if(y < 0 ){
				y = heightGap / 2;
			}else if( y > heightGap){
				y = heightGap;
			}
			
			//在容器中添加水印文件
			g.drawImage(waterImage, x, y, waterImageWidth, waterImageHeight, null);
			//释放资源
			g.dispose();
			
			//将水印数据写到目标文件中
			ImageIO.write(bi, FilenameUtils.getExtension(targetImage), tImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 给图片添加水印图片
	 * 生成的水印图片保存到指定的路径下
	 * @param targetImage		 需要添加水印的图片，图片路径
	 * @param waterMarkedImage	水印图片的路径
	 * @param waterImageSavePath生成的水印图片的保存路径
	 * @param x					水印图片距离目标图片左侧的偏移量（x<0,在正中间）
	 * @param y					水印图片距离目标图片上侧偏移量（y<0,在正中间）
	 * @param alpha				透明度（0~1，0为透明，1为不透明）
	 */
	public static void waterMarkingImage(String targetImage,
			String waterMarkedImage, String waterImageSavePath, int x, int y,
			float alpha) {
		try {
			File tImage = new File(targetImage);
			Image image = ImageIO.read(tImage);
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			
			//创建画布容器
			BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象  
			Graphics2D g = bi.createGraphics();
			//在容器中添加目标图片
			g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
			
			//对线段的锯齿状边缘处理 
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);;
			
			//读取要添加的水印文件
			Image waterImage = ImageIO.read(new File(waterMarkedImage));
			int waterImageWidth = waterImage.getWidth(null);
			int waterImageHeight = waterImage.getHeight(null);
			
			//设置透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
			
			//设置偏移量
			int widthGap = imageWidth - waterImageWidth;
			int heightGap = imageHeight - waterImageHeight;
			
			if(x < 0){
				x = widthGap / 2;
			}else if(x < widthGap){
				x = widthGap;
			}
			if(y < 0 ){
				y = heightGap / 2;
			}else if( y > heightGap){
				y = heightGap;
			}
			
			//在容器中添加水印文件
			g.drawImage(waterImage, x, y, waterImageWidth, waterImageHeight, null);
			g.dispose();
			
			//File savePath = new File(waterImageSavePath);
			FileOutputStream fos = new FileOutputStream(waterImageSavePath);
			
			//将水印数据写到目标文件中
			//ImageIO.write(bi, FilenameUtils.getExtension(targetImage), savePath);
			ImageIO.write(bi, FilenameUtils.getExtension(targetImage), fos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 给图片添加水印图片
	 * 生成的水印图片保存到指定的路径
	 * @param targetImage		 需要添加水印的图片，图片路径
	 * @param waterMarkedImage	水印图片的路径
	 * @param x					水印图片距离目标图片左侧的偏移量（x<0,在正中间）
	 * @param y					水印图片距离目标图片上侧偏移量（y<0,在正中间）
	 * @param alpha				透明度（0~1，0为透明，1为不透明）
	 * @param degree			水印图片旋转角度
	 * 
	 */
	public static void waterMarkingImage(String targetImage,
			String waterMarkedImage, String waterImageSavePath, int x, int y,
			float alpha, Integer degree) {
		
		try {
			File tImage = new File(targetImage);
			Image image = ImageIO.read(tImage);
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			
			//创建画布容器
			BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象  
			Graphics2D g = bi.createGraphics();
			//对线段的锯齿状边缘处理 
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			//在容器中添加目标图片
			g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
			
			//设置水印旋转
			if(null != degree){
				g.rotate(Math.toRadians(degree), (double) bi.getWidth() / 2,
						(double) bi.getHeight() / 2);
			}
			
			//读取要添加的水印文件
			Image waterImage = ImageIO.read(new File(waterMarkedImage));
			int waterImageWidth = waterImage.getWidth(null);
			int waterImageHeight = waterImage.getHeight(null);
			
			//设置透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
			
			//设置偏移量
			int widthGap = imageWidth - waterImageWidth;
			int heightGap = imageHeight - waterImageHeight;
			
			if(x < 0){
				x = widthGap / 2;
			}else if(x < widthGap){
				x = widthGap;
			}
			if(y < 0 ){
				y = heightGap / 2;
			}else if( y > heightGap){
				y = heightGap;
			}
			
			//在容器中添加水印文件
			g.drawImage(waterImage, x, y, waterImageWidth, waterImageHeight, null);
			//释放资源
			g.dispose();
			
			FileOutputStream fos = new FileOutputStream(waterImageSavePath);
			
			//将水印数据写到目标文件中
			ImageIO.write(bi, FilenameUtils.getExtension(targetImage), fos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 添加文字水印
	 * 生成的图片会覆盖原来的图片
	 * @param targetImage    需要添加水印的图片，图片路径
	 * @param waterMarkText	  水印文字
	 * @param fontName		 字体名称
	 * @param fontStyle		字体样式（Font.BOLD/Font.ITALIC）
	 * @param fontSize		字体大小，px
	 * @param color			字体颜色(Color.red)
	 * @param x				水印图片距离目标图片左侧的偏移量（x<0,在正中间）
	 * @param y				水印图片距离目标图片上侧偏移量（y<0,在正中间）
	 * @param alpha			透明度（0~1，0为透明，1为不透明）
	 */
	public static void waterMarkingText(String targetImage,
			String waterMarkText, String fontName, int fontStyle, int fontSize,
			Color color, int x, int y, float alpha) {
		try {
			File tImage = new File(targetImage);
			Image image = ImageIO.read(tImage);
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			
			BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
			
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setColor(color);
			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
			
			//设置文字水印的宽高
			int waterTextWidth = fontSize * StringUtils.length(waterMarkText); 
			int waterTextHeight = fontSize;
			
			int widthGap = imageWidth - waterTextWidth;
			int heightGap = imageHeight - waterTextHeight;
			
			if(x < 0){
				x = widthGap / 2;
			}else if(x > widthGap){
				x = widthGap;
			}
			if(y < 0){
				y = heightGap / 2;
			}else if(y > heightGap){
				y = heightGap;
			}
			
			//在容器中填充文字水印
			g.drawString(waterMarkText, x, y + waterTextHeight);
			g.dispose();
			ImageIO.write(bi, FilenameUtils.getExtension(targetImage), tImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加文字水印
	 * 生成的图片会覆盖原来的图片
	 * @param targetImage    需要添加水印的图片，图片路径
	 * @param waterMarkText	  水印文字
	 * @param fontName		 字体名称
	 * @param fontStyle		字体样式（Font.BOLD/Font.ITALIC）
	 * @param fontSize		字体大小，px
	 * @param color			字体颜色(Color.red)
	 * @param x				水印图片距离目标图片左侧的偏移量（x<0,在正中间）
	 * @param y				水印图片距离目标图片上侧偏移量（y<0,在正中间）
	 * @param alpha			透明度（0~1，0为透明，1为不透明）
	 */
	public static void waterMarkingText(String targetImage,
			String waterImageSavePath, String waterMarkText, String fontName,
			int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
		try {
			File tImage = new File(targetImage);
			Image image = ImageIO.read(tImage);
			int imageWidth = image.getWidth(null);
			int imageHeight = image.getHeight(null);
			
			BufferedImage bi = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			g.drawImage(image, 0, 0, imageWidth, imageHeight, null);
			
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setColor(color);
			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
			
			//设置文字水印的宽高
			int waterTextWidth = fontSize * StringUtils.length(waterMarkText); 
			int waterTextHeight = fontSize;
			
			int widthGap = imageWidth - waterTextWidth;
			int heightGap = imageHeight - waterTextHeight;
			
			if(x < 0){
				x = widthGap / 2;
			}else if(x > widthGap){
				x = widthGap;
			}
			if(y < 0){
				y = heightGap / 2;
			}else if(y > heightGap){
				y = heightGap;
			}
			
			//在容器中填充文字水印
			g.drawString(waterMarkText, x, y + waterTextHeight);
			
			g.dispose();
			
			FileOutputStream fos = new FileOutputStream(waterImageSavePath);
			ImageIO.write(bi, FilenameUtils.getExtension(targetImage), fos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对图片进行剪切
	 * @param targetImagePath	目标图片路径
	 * @param imageSavePath		剪切图片的保存路径
	 * @param imageType			图片类型
	 * @param width				目标图片宽度
	 * @param height			目标图片高度
	 * @param x					剪切的x轴偏移量
	 * @param y					剪切的y轴偏移量
	 * @return
	 */
	public static boolean cutImage(String targetImagePath,
			String imageSavePath, String imageType, int width,
			int height, int x, int y) {
		
		FileInputStream fis = null;
		ImageInputStream iis = null;
		boolean success = false;
		
		try {
			File srcImage = new File(targetImagePath);
			fis = new FileInputStream(srcImage);
			//根据图片类型获取图片读取器
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(imageType);
			ImageReader reader = it.next();
			//获取图片数据输入类
			iis = ImageIO.createImageInputStream(fis);
			
			reader.setInput(iis,true);
			ImageReadParam param = reader.getDefaultReadParam();
			//设置矩形区域(剪切图片区域)
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);
			//存取剪切图片
			BufferedImage bi = reader.read(0, param);
			
			//获取原图片的信息，实际宽/高大于目标宽/高时，才能进行剪切
			BufferedImage bimg = ImageIO.read(srcImage);
			int imageWidth = bimg.getWidth();
			int imageHeight = bimg.getHeight();
			if((imageWidth > width) || (imageHeight > height)){//实际宽高大于目标宽高
				ImageIO.write(bi, imageType, new File(imageSavePath));
				success = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return success;
	}
	
}
