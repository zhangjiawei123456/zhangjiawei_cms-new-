/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CmcException.java 
 * @Prject: zhangjiawei_cms
 * @Package: com.zhangjiawei.common 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月15日
 * @version: V1.0   
 */
package com.zhangjiawei.common;

/** 
 * @ClassName: CmcException 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月15日 
 */
public class CmcException extends RuntimeException {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -5029681305074980530L;
	
	public CmcException(String msg) {
		super(msg);
	}
	

}
