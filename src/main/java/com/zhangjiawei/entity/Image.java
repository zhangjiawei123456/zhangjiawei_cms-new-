
package com.zhangjiawei.entity;

/** 
 * @ClassName: Image 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月22日 
 * 
 * 上传图片专题的实体类
 */
public class Image {
	//图片路径
	private String url;
	//图片描述
	private String desc;
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/* (non Javadoc) 
	 * @Title: toString
	 * @Description: TODO
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "Image [url=" + url + ", desc=" + desc + "]";
	}
	
}
