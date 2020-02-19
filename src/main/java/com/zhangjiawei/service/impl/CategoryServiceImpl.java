
package com.zhangjiawei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangjiawei.dao.CategoryMapper;
import com.zhangjiawei.entity.Category;
import com.zhangjiawei.service.CategoryService;



/** 
 * @ClassName: CategoryServiceImpl 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月14日 
 */
@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryMapper categoryMapper;

	/* (non Javadoc) 
	 * @Title: listByChannelId
	 * @Description: TODO
	 * @param chnId
	 * @return 
	 * @see com.zhaojian.service.CategoryService#listByChannelId(int) 
	 */
	@Override
	public List<Category> listByChannelId(int chnId) {
		
		return categoryMapper.listByChannelId(chnId);
	}
}
