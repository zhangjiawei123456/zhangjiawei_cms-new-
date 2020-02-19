package com.zhangjiawei.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.zhangjiawei.dao.ArticleMapper;
import com.zhangjiawei.dao.ArticleResp;
import com.zhangjiawei.entity.Article;
public class ArticleListener implements MessageListener<String, String>{
	//注入ArticleMapper
	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	ArticleResp articleResp;
	//收消息的方法
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		String jsonString = data.value();
		System.err.println("=========="+jsonString);
		//你是怎么知道这个消息是修改操作或者是接收的文章呢?
		if(jsonString.startsWith("update")) {
			System.err.println(jsonString);
			//如果是update开头的消息,我们就认为是通知修改的操作
			redisTemplate.delete("hot_articles");
			//es数据据库修改
			String[] split = jsonString.split("=");
			articleResp.save(JSON.parseObject(split[1],Article.class));
		}else if(jsonString.startsWith("hits")) {
			System.err.println("收到了消息!"+jsonString);
			//是更新点击量操作
			String[] split = jsonString.split("=");
			int id = Integer.parseInt(split[1]);
			//根据id查询文章
			Article article = articleMapper.getById(id);
			//修改点击量+1
			article.setHits(article.getHits()+1);
			//更新数据库
			articleMapper.updateHits(article);
			
		}
		
		else if(jsonString.startsWith("del")) {
			System.err.println(jsonString);
			redisTemplate.delete("hot_articles");
			String[] split = jsonString.split("=");
			//es数据据库删除
			articleResp.deleteById(Integer.parseInt(split[1]));
		}else if(jsonString.startsWith("add")) {
			System.err.println(jsonString);
			//说明是添加操作
			redisTemplate.delete("hot_articles");
			//es数据据库添加
			String[] split = jsonString.split("=");
			articleResp.save(JSON.parseObject(split[1],Article.class));
		}
		
		else {
			System.err.println("收到了消息:........");
			Article article = JSON.parseObject(jsonString, Article.class);
			//保存到mysql
			articleMapper.add(article);
		}
	}

}
