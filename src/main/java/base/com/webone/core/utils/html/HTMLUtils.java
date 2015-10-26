package com.webone.core.utils.html;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * HTML工具类
 * @author skz
 * @date 2015年10月25日
 * @time 下午7:43:43
 */
public class HTMLUtils {

	/**
	 * 过滤html标签
	 * @param html
	 * @return
	 */
	public static String escapeHtmlTag(String html){
		String scriptRegExp = "<script[^>*?>[\\s\\S]*?<\\/script>";//script正则表达式
		String styleRegExp = "<style[^>*?>[\\s\\S]*?<\\/style>";//style正则表达式
		String htmlRegExp = "<[^>]+>";//html标签
		
		//过滤script标签
		Pattern scriptPattern = Pattern.compile(scriptRegExp,Pattern.CASE_INSENSITIVE);
		Matcher scriptMatcher = scriptPattern.matcher(html);
		html = scriptMatcher.replaceAll("");
		
		//过滤style标签
		Pattern stylePattern = Pattern.compile(styleRegExp,Pattern.CASE_INSENSITIVE);
		Matcher styleMatcher = stylePattern.matcher(html);
		html = styleMatcher.replaceAll("");
		
		//过滤html标签
		Pattern htmlPattern = Pattern.compile(htmlRegExp,Pattern.CASE_INSENSITIVE);
		Matcher htmlMatcher = htmlPattern.matcher(html);
		html = htmlMatcher.replaceAll("");
	
		return html.trim();
	}
}
