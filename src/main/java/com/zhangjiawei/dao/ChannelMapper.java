/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ChannelMapper.java 
 * @Prject: zhangjiawei_cms
 * @Package: com.zhangjiawei.dao 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhangjiawei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zhangjiawei.entity.Channel;


/** 
 * @ClassName: ChannelMapper 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月14日 
 */
public interface ChannelMapper {

	/** 
	 * @Title: list 
	 * @Description: TODO
	 * @return
	 * @return: List<Channel>
	 */
	
	@Select("SELECT * FROM cms_channel ORDER BY id")
	List<Channel> list();

}
