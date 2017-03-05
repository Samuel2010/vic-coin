package com.vc.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.tw.ei.baseclass.util.PropertiesHelper;

public class WebUtil {
	private static Logger logger = Logger.getLogger(WebUtil.class);

	private static PropertiesHelper propertiesHelper = null;

	public static String LOGIN_USER_SESSION = "WEIXIN_API_QY_USER";

	/**
	 * 获取当前应用路径
	 * 
	 * @return
	 */
	public static String getAppUrl() {
		String url = WebUtil.getSysConfigValue("app_path");
		if (!url.endsWith("/")) {
			url = url + "/";
		}

		return url;
	}

	/**
	 * 读sysConfig.xml中的内容
	 * 
	 * @param key
	 * @return
	 */
	public static String getSysConfigValue(String key) {
		return WebUtil.getSysConfigValue(key, false);
	}

	/**
	 * 读sysConfig.xml中的内容
	 * 
	 * @param key
	 * @param reload
	 * @return
	 */
	public static String getSysConfigValue(String key, boolean reload) {
		if (WebUtil.propertiesHelper == null || reload) {
			try {
				String filePath = SysConfig.class.getResource("").getPath();
				filePath = java.net.URLDecoder.decode(filePath, "utf-8");
				if (filePath != null && filePath.length() > 0) {
					if (filePath.indexOf("/com") > 0) {
						filePath = filePath.substring(0, filePath
								.indexOf("/com"));
					}
					if (!filePath.endsWith("/")) {
						filePath += "/";
					}
					filePath += "sys-config.xml";
					File file = new File(filePath);
					if (file.exists()) {
						InputStream is = new FileInputStream(file);

						Properties properties = new Properties();
						WebUtil.propertiesHelper = new PropertiesHelper(
								properties);
						WebUtil.propertiesHelper.loadFromXML(is);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String result = "";
		if (WebUtil.propertiesHelper.get(key) != null) {
			result = (String) WebUtil.propertiesHelper.get(key);
		}

		return result;
	}

	/**
	 * 正则查找，返回第一个结果集
	 * 
	 * @param regex
	 * @param findStr
	 * @param groupIndex
	 * @return
	 */
	public static String findMatcher(String regex, CharSequence findStr,
			int groupIndex) {
		String result = "";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(findStr);
		if (matcher.find()) {
			result = matcher.group(groupIndex);
		}
		if (result == null || result.trim().equals("")) {
			logger.info("没有找到regex在:\n" + findStr.toString() + "里面");
		}
		return result.trim();
	}

	/**
	 * 正则查找，返回所有匹配结果集
	 * 
	 * @param regex
	 * @param input
	 * @param groupIndex
	 * @return
	 */
	public static List<String> findAllMatch(String regex, CharSequence input,
			int groupIndex) {
		List<String> list = new ArrayList<String>();

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		int start = 0;
		while (matcher.find(start)) {
			start = matcher.end();

			String findStr = matcher.group(groupIndex);
			if (findStr != null && findStr.trim().length() > 0) {
				list.add(findStr.trim());
			}
		}

		return list;
	}

	/**
	 * 字符串去空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trimStr(String str) {
		if (str != null && str.trim().length() > 0) {
			str = str.trim();
		} else {
			str = "";
		}

		return str;
	}

	/**
	 * 从json字符串中获取值
	 * 
	 * @param flag
	 * @return
	 */
	public static String getRegexFromJson(String flag) {
		flag = trimStr(flag);
		return "\"*" + flag + "\"*\\s*:\\s*\"*([^,|\"|\\}]+)\"*";
	}

	/**
	 * 获取带字母的随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String getCharAndNumr(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}
		}

		return val;
	}

}
