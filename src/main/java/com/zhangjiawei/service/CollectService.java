
package com.zhangjiawei.service;

import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.zhangjiawei.entity.Collect;

/** 
 * @ClassName: collectService 
 * @Description: TODO
 * @作者: ZJ 
 * @时间: 2019年11月25日 
 */
public interface CollectService {

	/** 
	 * @Title: list 
	 * @Description: TODO
	 * @param id
	 * @param page
	 * @return
	 * @return: PageInfo
	 */
	PageInfo list(int userId, int page);
	
	/** 
	 * @Title: add 
	 * @Description: TODO
	 * @param collect
	 * @return: void
	 */
	int add(Collect collect);
	/** 
	 * @Title: delete 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: int
	 */
	int delete(int id);
	
	/** 
	 * @Title: get 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: Object
	 */
	Collect get(int id);

	/** 
	 * @Title: update 
	 * @Description: TODO
	 * @param collect
	 * @return: void
	 */
	int update(@Valid Collect collect);

	

	
	
}
