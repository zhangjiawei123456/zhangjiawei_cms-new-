/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: CategoryMapper.java 
 * @Prject: zhangjiawei_cms
 * @Package: com.zhangjiawei.dao 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhangjiawei.dao;

import java.util.List;

import com.zhangjiawei.entity.Category;


/** 
 * @ClassName: CategoryMapper 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public interface CategoryMapper {

	/** 
	 * @Title: listByChannelId 
	 * @Description: 获取分类
	 * @param chnId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> listByChannelId(int chnId);
	
}
