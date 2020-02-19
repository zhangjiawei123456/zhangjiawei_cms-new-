
package com.zhangjiawei.service;

import java.util.List;

import com.zhangjiawei.entity.Category;


/** 
 * @ClassName: CategoryService 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月14日 
 */
public interface CategoryService {

	/** 
	 * @Title: listByChannelId 
	 * @Description: TODO
	 * @param chnId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> listByChannelId(int chnId);

}
