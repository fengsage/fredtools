package com.fredzhu.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * 工具函数集合
 * @author guosong <guosong@mbaobao.com>
 */
public class StringUtil {
	
	protected static final Logger	log	= Logger.getLogger(StringUtil.class);
	
	public static int getPid() {
		RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
		String name = runtime.getName(); // format: "pid@hostname"   
		try {
			return Integer.parseInt(name.substring(0, name.indexOf('@')));
		} catch (Exception e) {
			return -1;
		}
	}
	
	//    public static void main(String[] args) {
	//        System.out.println(isMobileNO("13525468562"));
	//    }
	
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	* 生成随机密码
	* @param passLenth 生成的密码长度
	* @return 随机密码
	*/
	public static String getRandomPass() {
		//没有字母O的大小写
		StringBuffer buffer = new StringBuffer(
			"0123456789abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < 8; i++) {
			//生成指定范围类的随机数0—字符串长度(包括0、不包括字符串长度)
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}
	
	/**  
	     * 此方法描述的是：取指定长度字符串的值;  
	     * @param String str,int byteSize;    
	     * @return String;  
	     */
	public String getAllStringSize(String str, int byteSize) {
		int len = 0;
		char c;
		String words = "";
		
		if (str == null || "null".equals(str)) {
			return "";
		}
		
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				// 字母, 数字   
				len++;
			} else {
				if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]")) { // 中文   
					len += 2;
				} else { // 符号或控制字符   
					len++;
				}
			}
			words += String.valueOf(c);
			if (len >= byteSize) {//   
				words += "..";
				break;
			}
		}
		return words;
	}
	
	//该方法还处理不了中文符号！，。等
	public static boolean isOverLength(String str, int length) {
		int len = 0;
		char c;
		
		if (str == null || "null".equals(str)) {
			return true;
		}
		
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
				// 字母, 数字   
				len++;
			} else {
				if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]")) { // 中文   
					len += 2;
				} else { // 符号或控制字符   
					len++;
				}
			}
			
			if (len > length) {
				return true;
			}
		}
		return false;
	}
	
	public static String MD5Encrypt(String str) {
		String s = str;
		if (s == null) {
			return null;
		}
		String value = null;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException ex) {
			log.error("没有此类加密算法", ex);
			//Logger.getLogger(Util.class.getName()).log(Level.ERROR, "没有此类加密算法", ex);
		}
		sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
		try {
			value = baseEncoder.encode(md5.digest(s.getBytes("utf-8")));
		} catch (Exception ex) {
			log.error("加密算法执行失败", ex);
		}
		return value;
	}
	
	public static String zeroFormat(Integer number) {
		String pattern = "000000";
		java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
		return df.format(number);
	}
	
}
