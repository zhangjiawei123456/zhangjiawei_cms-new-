/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: Md5.java 
 * @Prject: zhangjiawei_cms
 * @Package: com.zhangjiawei.common 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月15日
 * @version: V1.0   
 */
package com.zhangjiawei.common;

import org.apache.commons.codec.digest.DigestUtils;

/** 
 * @ClassName: Md5 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月15日 
 */
public class Md5 {
	
	public static String password(String password, String salt) {
		//MD5:给密码加密: 把简单的密码变为32位字符串形式
		return DigestUtils.md5Hex(password + "::::" +  salt);
	}
}
