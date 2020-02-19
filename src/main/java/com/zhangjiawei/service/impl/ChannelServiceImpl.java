
package com.zhangjiawei.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhangjiawei.dao.ChannelMapper;
import com.zhangjiawei.entity.Channel;
import com.zhangjiawei.service.ChannelService;


/** 
 * @ClassName: ChannelServiceImpl 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月14日 
 */
@Service
public class ChannelServiceImpl implements ChannelService{
	
	@Autowired
	ChannelMapper channelMapper;
	/* (non Javadoc) 
	 * @Title: list
	 * @Description: TODO
	 * @return 
	 * @see com.zhaojian.service.ChannelService#list() 
	 */
	@Override
	public List<Channel> list() {
		return channelMapper.list();
	}
	
}
