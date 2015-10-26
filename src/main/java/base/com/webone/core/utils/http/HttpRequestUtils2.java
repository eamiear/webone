package com.webone.core.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * HTTP请求工具类
 * @author skz
 * @date 2015年10月25日
 * @time 下午5:24:39
 */
public class HttpRequestUtils2 {

	
	/**
	 * 发送GET请求
	 * @param urlString
	 * @return
	 */
	public static String get(String urlString){
		return _send(urlString, "GET", "", null);
	}
	
	/**
	 * 发送GET请求
	 * @param urlString
	 * @param params  请求参数集合
	 * @return
	 */
	public static String get(String urlString,Map<String,String> params){
		return _send(urlString, "GET", params, null);
	}
	
	/**
	 * 发送GET请求
	 * @param urlString
	 * @param params  请求参数字符串
	 * @return
	 */
	public static String get(String urlString,String params){
		return _send(urlString, "GET", params, null);
	}
	
	/**
	 * 发送GET请求
	 * @param urlString
	 * @param params  请求参数集合
	 * @param propertys 请求属性
	 * @return
	 */
	public static String get(String urlString,Map<String,String> params ,Map<String,String> propertys){
		return _send(urlString, "GET", params, propertys);
	}
	
	/**
	 * 发送GET请求
	 * @param urlString
	 * @param params  请求参数字符串
	 * @param propertys 请求属性
	 * @return
	 */
	public static String get(String urlString,String params ,Map<String,String> propertys){
		return _send(urlString, "GET", params, propertys);
	}
	
	/**
	 * 发送POST请求
	 * @param urlString
	 * @return
	 */
	public static String post(String urlString){
		return _send(urlString, "POST", "", null);
	}
	
	/**
	 * 发送POST请求
	 * @param urlString
	 * @param params  请求参数集合
	 * @return
	 */
	public static String post(String urlString,Map<String,String> params){
		return _send(urlString, "POST", params, null);
	}
	
	/**
	 * 发送POST请求
	 * @param urlString
	 * @param params  请求参数字符串
	 * @return
	 */
	public static String post(String urlString,String params){
		return _send(urlString, "POST", params, null);
	}
	
	/**
	 * 发送POST请求
	 * @param urlString
	 * @param params  请求参数字符串
	 * @param propertys 请求属性
	 * @return
	 */
	public static String post(String urlString,String params ,Map<String,String> propertys){
		return _send(urlString, "POST", params, propertys);
	}
	
	/**
	 * 发送POST请求
	 * @param urlString
	 * @param params  请求参数集合
	 * @param propertys 请求属性
	 * @return
	 */
	public static String post(String urlString,Map<String,String> params ,Map<String,String> propertys){
		return _send(urlString, "POST", params, propertys);
	}
	
	
	/**
	 * 发送HTTP请求
	 * @param urlString	请求地址
	 * @param method	请求方式get/post
	 * @param params	请求参数(键值对)
	 * @param propertys 请求属性(键值对)
	 * @return 响应对象
	 */
	public static String _send(String urlString,String method,Map<String, String> params,Map<String, String> propertys){
		HttpURLConnection urlConnection = null;
		OutputStream os = null;//写入流(写数据到发送流给远程主机)
		
		//get请求，构建URL
		if(method.equalsIgnoreCase("GET") && params != null){
			StringBuffer param = new StringBuffer();
			int i = 0;
			//构建请求参数
			for (String key : params.keySet()) {
				if(i == 0){
					param.append("?");
				}else{
					param.append("&");
				}
				param.append(key).append("=").append(params.get(key));
				i++;
			}
			urlString += param;
		}
		
		
		try {
			URL url = new URL(urlString);
			urlConnection = (HttpURLConnection)url.openConnection();
			//设置请求方式
			urlConnection.setRequestMethod(method);
			//设置使用URL连接进行输入/输出,其值默认为false(用于post)
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			//
			urlConnection.setUseCaches(false);
			//30秒连接超时/读取超时
			urlConnection.setConnectTimeout(30000);
			urlConnection.setReadTimeout(30000);
			
			//添加请求属性
			if(propertys != null){
				for (String key : propertys.keySet()) {
					urlConnection.addRequestProperty(key, propertys.get(key));
				}
			}
			// 建立实际的连接
			// urlConnection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(method.equalsIgnoreCase("POST") && params != null && !params.equals("")){
			StringBuffer param = new StringBuffer();
			for (String key : params.keySet()) {//key=vlue&key=value&key=value
				param.append("&");
				param.append(key).append("=").append(params.get(key));
			}
			/*for (String key : params.keySet()) {
				param.append(key).append("=").append(params.get(key)).append("&");
			}
			if(param.length() > 0) param.setLength(param.length() - 1);//删掉最后一个&  */			
			try {
				os = urlConnection.getOutputStream();
				//将请求参数写到发送流
				os.write(param.toString().getBytes());
				//清空缓冲区,发送数据
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return _getResponses(urlConnection);
	}
	
	/**
	 * 发送HTTP请求
	 * @param urlString	请求地址
	 * @param method	请求方式get/post
	 * @param params	请求参数(键值对)字符串[?](key=value[&]*)
	 * @param propertys 请求属性(键值对)
	 * @return 响应对象
	 */
	public static String _send(String urlString,String method,String params,Map<String, String> propertys){
		HttpURLConnection urlConnection = null;
		OutputStream os = null;//写入流(写数据到发送流给远程主机)
		
		//get请求，构建URL
		if(method.equalsIgnoreCase("GET") && params != null && !params.equals("")){
			StringBuffer param = new StringBuffer();
			//构建请求参数 ?key=value&key=value
			if(params.indexOf("?") == -1){
				param.append("?").append(params);
			}else{
				param.append(params);
			}
			urlString += param;
		}
		
		
		try {
			URL url = new URL(urlString);
			urlConnection = (HttpURLConnection)url.openConnection();
			//设置请求方式
			urlConnection.setRequestMethod(method);
			//设置使用URL连接进行输入/输出,其值默认为false(用于post)
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			//
			urlConnection.setUseCaches(false);
			//30秒连接超时/读取超时
			urlConnection.setConnectTimeout(30000);
			urlConnection.setReadTimeout(30000);
			
			//添加请求属性
			if(propertys != null){
				for (String key : propertys.keySet()) {
					urlConnection.addRequestProperty(key, propertys.get(key));
				}
			}
			// 建立实际的连接
			// urlConnection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(method.equalsIgnoreCase("POST") && params != null && !params.equals("")){
			StringBuffer param = new StringBuffer();
			if(params.indexOf("?") != -1){//key=vlue&key=value&key=value
				params.replace("?", "");
			}
		
			try {
				os = urlConnection.getOutputStream();
				//将请求参数写到发送流
				os.write(param.toString().getBytes());
				//清空缓冲区,发送数据
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return _getResponses(urlConnection);
	}
	
	/**
	 * TODO
	 * GET
	 * @param urlString
	 * @param params
	 * @param propertys
	 * @return
	 */
	private static String _get(String urlString,String params,Map<String, String> propertys){
		HttpURLConnection urlConnection = null;
		
		//get请求，构建URL
		if(params != null && !"".equals(params)){
			StringBuffer param = new StringBuffer();
			//构建请求参数 ?key=value&key=value
			if(params.indexOf("?") == -1){
				param.append("?").append(params);
			}else{
				param.append(params);
			}
			urlString += param;
		}
		
		try {
			URL url = new URL(urlString);
			urlConnection = (HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");
			// 设置通用的请求属性
			//urlConnection.setRequestProperty("accept", "*/*");
			//urlConnection.setRequestProperty("connection", "Keep-Alive");
			//urlConnection.setRequestProperty("user-agent",
		   //       "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			//设置请求xml格式内容
			//urlConnection.setRequestProperty( " Pragma: " ,  " no-cache " ); 
			//urlConnection.setRequestProperty( " Cache-Control " ,  " no-cache " ); 
			//urlConnection.setRequestProperty( " Content-Type " ,  " text/xml " );
			//
			urlConnection.setUseCaches(false);
			//30秒连接超时/读取超时
			urlConnection.setConnectTimeout(30000);
			urlConnection.setReadTimeout(30000);
			
			//添加请求属性
			if(propertys != null){
				for (String key : propertys.keySet()) {
					urlConnection.addRequestProperty(key, propertys.get(key));
				}
			}
			// 建立实际的连接
			urlConnection.connect();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return _getResponses(urlConnection);
	}
	
	/**
	 * TODO
	 * POST
	 * @param urlString
	 * @param params
	 * @param propertys
	 * @return
	 */
	private static String _post(String urlString,String params,Map<String, String> propertys){
		HttpURLConnection urlConnection = null;
		OutputStream os = null;//写入流(写数据到发送流给远程主机)
		
		try {
			URL url = new URL(urlString);
			urlConnection = (HttpURLConnection)url.openConnection();
			//设置请求方式
			urlConnection.setRequestMethod("POST");
			//设置使用URL连接进行输入/输出,其值默认为false(用于post)
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			//
			urlConnection.setUseCaches(false);
			//30秒连接超时/读取超时
			urlConnection.setConnectTimeout(30000);
			urlConnection.setReadTimeout(30000);
			
			//添加请求属性
			if(propertys != null){
				for (String key : propertys.keySet()) {
					urlConnection.addRequestProperty(key, propertys.get(key));
				}
			}
			//key=vlue&key=value&key=value
			if(params != null && !"".equals(params)){
				if(params.indexOf("?") != -1){
					params.replace("?", "");
				}
				os = urlConnection.getOutputStream();
				//将请求参数写到发送流
				os.write(params.toString().getBytes());
				//清空缓冲区,发送数据
				os.flush();
				os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return _getResponses(urlConnection);
	}
	
	/**
	 * 获取响应内容
	 * @param urlConnection
	 * @return
	 */
	public static String _getResponses(HttpURLConnection urlConnection){
		StringBuffer sb = null;
		try {
			//获取远程主机响应的输入流,以读入远程数据
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			sb = new StringBuffer();
			String line = br.readLine();
			while (line != null) {
				//sb.append(line).append("\r\n");
				sb.append(line);
				line = br.readLine();
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(urlConnection != null){
				urlConnection.disconnect();
			}
		}
		
		return sb.toString();
	}
	
	//test
	public static void main(String[] args) throws UnsupportedEncodingException {
	  Map<String, String> params = new HashMap<String, String>();
	  params.put("id", "1");
	  String url = "http://192.168.33.18/webone/app/index.html";
	  System.out.println(post(url, params));
	}
}
