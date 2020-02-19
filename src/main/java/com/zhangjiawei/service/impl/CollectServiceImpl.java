
package com.zhangjiawei.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangjiawei.dao.CollectMapper;
import com.zhangjiawei.entity.Collect;
import com.zhangjiawei.service.CollectService;

/** 
 * @ClassName: CollectServiceImpl 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月25日 
 */
@Service
public class CollectServiceImpl implements CollectService{

	@Autowired
	CollectMapper collectMapper;
		
	/* (non Javadoc) 
	 * @Title: list
	 * @Description: TODO
	 * @param id
	 * @param page
	 * @return 
	 * @see com.zhaojian.service.CollectService#list(java.lang.Integer, int) 
	 */
	@Override
	public PageInfo list(int userId, int page) {
		PageHelper.startPage(page,3);
		return new PageInfo<Collect>(collectMapper.list(userId));
	}

	/* (non Javadoc) 
	 * @Title: add
	 * @Description: TODO
	 * @param collect
	 * @return 
	 * @see com.zhaojian.service.CollectService#add(com.zhangjiawei.entity.Collect) 
	 */
	@Override
	public int add(Collect collect) {
		return collectMapper.add(collect);
	}

	/* (non Javadoc) 
	 * @Title: delete
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.CollectService#delete(int) 
	 */
	@Override
	public int delete(int id) {
		return collectMapper.delete(id);
	}

	/* (non Javadoc) 
	 * @Title: get
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.CollectService#get(int) 
	 */
	@Override
	public Collect get(int id) {
		return collectMapper.get(id);
	}

	/* (non Javadoc) 
	 * @Title: update
	 * @Description: TODO
	 * @param collect
	 * @return 
	 * @see com.zhaojian.service.CollectService#update(com.zhaangjiawei.entity.Collect) 
	 */
	@Override
	public int update(Collect collect) {
		return collectMapper.update(collect);
	}

}
