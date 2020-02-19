
package com.zhangjiawei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangjiawei.dao.LinkMapper;
import com.zhangjiawei.entity.Link;
import com.zhangjiawei.service.LinkService;

/** 
 * @ClassName: LinkServiceImpl 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月27日  
 */
@Service
public class LinkServiceImpl implements LinkService {
	@Autowired
	private LinkMapper linkMapper;

	/*(non Javadoc) 
	 * @Title: list
	 * @Description: 列表
	 * @param page
	 * @return 
	 * @see com.zhaojian.service.LinkService#list(int) 
	 */
	 
	@Override
	public PageInfo list(int page) {
		PageHelper.startPage(page, 10);
		
		return new PageInfo<Link>(linkMapper.list());
	}

	/*(non Javadoc) 
	 * @Title: add
	 * @Description: TODO
	 * @param link 
	 * @see com.zhaojian.service.LinkService#add(com.zhangjiawei.entity.Link) 
	 */
	@Override
	public void add(Link link) {
		linkMapper.add(link);
	}

	/*(non Javadoc) 
	 * @Title: delete
	 * @Description: 删除链接
	 * @param id 
	 * @see com.zhaojian.service.LinkService#delete(int) 
	 */
	@Override
	public void delete(int id) {
		linkMapper.delete(id);
	}

	/*(non Javadoc) 
	 * @Title: get
	 * @Description: TODO
	 * @param id
	 * @return 
	 * @see com.zhaojian.service.LinkService#get(int) 
	 */
	@Override
	public Link get(int id) {
		return linkMapper.get(id);
	}

	/* (non Javadoc) 
	 * @Title: update
	 * @Description: TODO
	 * @param link 
	 * @see com.zhaojian.service.LinkService#update(com.zhangjiawei.entity.Link) 
	 */
	@Override
	public int update(Link link) {
		return linkMapper.update(link);
	}
}
