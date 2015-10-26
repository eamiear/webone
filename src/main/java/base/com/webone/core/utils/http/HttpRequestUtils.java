package com.webone.core.utils.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Vector;

import com.webone.core.entity.HttpResponser;

public class HttpRequestUtils {

	/**
	 * 发送GET请求
	 * @param urlString
	 * @return
	 */
	public static HttpResponser get(String urlString){
		return _send(urlString, "GET", null, null);
	}
	
	/**
	 * 发送GET请求
	 * @param urlString
	 * @param params  请求参数集合
	 * @return
	 */
	public static HttpResponser get(String urlString,Map<String,String> params){
		return _send(urlString, "GET", params, null);
	}
	
	/**
	 * 发送GET请求
	 * @param urlString
	 * @param params  请求参数集合
	 * @param propertys 请求属性
	 * @return
	 */
	public static HttpResponser get(String urlString,Map<String,String> params ,Map<String,String> propertys){
		return _send(urlString, "GET", params, propertys);
	}
	
	/**
	 * 发送POST请求
	 * @param urlString
	 * @return
	 */
	public static HttpResponser post(String urlString){
		return _send(urlString, "POST", null, null);
	}
	
	/**
	 * 发送POST请求
	 * @param urlString
	 * @param params  请求参数集合
	 * @return
	 */
	public static HttpResponser post(String urlString,Map<String,String> params){
		return _send(urlString, "POST", params, null);
	}
	
	/**
	 * 发送POST请求
	 * @param urlString
	 * @param params  请求参数集合
	 * @param propertys 请求属性
	 * @return
	 */
	public static HttpResponser post(String urlString,Map<String,String> params ,Map<String,String> propertys){
		return _send(urlString, "POST", params, propertys);
	}
	
	/**
	 * 放松HTTP请求
	 * @param urlString	请求地址
	 * @param method	请求方式get/post
	 * @param params	请求参数(键值对)
	 * @param propertys 请求属性(键值对)
	 * @return 响应对象
	 */
	public static HttpResponser _send(String urlString,String method,Map<String, String> params,Map<String, String> propertys){
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
		
		if(method.equalsIgnoreCase("POST") && params != null){
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
		return _getResponses(urlString,urlConnection);
	}
	
	/**
	 * 获取响应体
	 * @param urlString		请求URL路径
	 * @param urlConnection	连接对象
	 * @return
	 */
	private static HttpResponser _getResponses(String urlString,HttpURLConnection urlConnection){
		HttpResponser httpResponser = new HttpResponser();
		
		try {
			//获取远程主机响应的输入流,以读入远程数据
			InputStream is = urlConnection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			//获取主机响应内容
			httpResponser.setResponseContent(new Vector<String>());
			//
			StringBuffer sb = new StringBuffer();
			String line = br.readLine();
			while (line != null) {
				httpResponser.getResponseContent().add(line);
				sb.append(line).append("\r\n");
				line = br.readLine();
			}
			br.close();
			
			//内容编码
			String encode = urlConnection.getContentEncoding();
			if (encode == null){
				encode = Charset.defaultCharset().name();
			}
			//请求连接
			httpResponser.setUrlString(urlString);
			//获取请求URL的默认端口
			httpResponser.setDefaultPort(urlConnection.getURL().getDefaultPort());
			//获取请求URL的文件名
			httpResponser.setFile(urlConnection.getURL().getFile());
			//host name of this URL
			httpResponser.setHost(urlConnection.getURL().getHost());
			//获取URL的路径部分
			httpResponser.setPath(urlConnection.getURL().getPath());
			//获取URL的端口数字
			httpResponser.setPort(urlConnection.getURL().getPort());
			//获取URL的协议名称
			httpResponser.setProtocol(urlConnection.getURL().getProtocol());
			//获取URL的查询部分
			httpResponser.setQuery(urlConnection.getURL().getQuery());
			//
			httpResponser.setRef(urlConnection.getURL().getRef());
			//
			httpResponser.setUserInfo(urlConnection.getURL().getUserInfo());
			//远程主机返回的数据内容
			httpResponser.setContent(new String(sb.toString().getBytes(),encode));
			//内容的编码
			httpResponser.setContentEncoded(encode);
			//获取远程主机响应状态码 200、
			httpResponser.setResponseCode(urlConnection.getResponseCode());
			//响应信息
			httpResponser.setMessage(urlConnection.getResponseMessage());
			//
			httpResponser.setContentType(urlConnection.getContentType());
			//
			httpResponser.setMethod(urlConnection.getRequestMethod());
			httpResponser.setConnectTimeout(urlConnection.getConnectTimeout());
			httpResponser.setReadTimeout(urlConnection.getReadTimeout());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(urlConnection != null){
				urlConnection.disconnect();
			}
		}
		return httpResponser;
	}
}
