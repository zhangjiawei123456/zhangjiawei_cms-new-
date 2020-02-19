
package com.zhangjiawei;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhangjiawei.entity.Channel;
import com.zhangjiawei.service.ChannelService;


/** 
 * @ClassName: TestChannel 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月14日 
 */
public class TestChannel extends TestBase{
	@Autowired
	ChannelService channelService;
	
	@Test
	 public void testList() {
		List<Channel> list = channelService.list();
		 list.forEach(x->{
			 System.out.println(" 频道是 " + x);
		 });
	 }
}
