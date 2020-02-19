/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleController.java 
 * @Prject: zhangjiawei_cms
 * @Package: com.zhangjiawei.controller 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhangjiawei.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.zhangjiawei.common.CmsAssert;
import com.zhangjiawei.common.MsgResult;
import com.zhangjiawei.entity.Article;
import com.zhangjiawei.entity.Category;
import com.zhangjiawei.entity.Comment;
import com.zhangjiawei.entity.Image;
import com.zhangjiawei.entity.TypeEnum;
import com.zhangjiawei.service.ArticleService;
import com.zhangjiawei.service.CategoryService;


/** 
 * @ClassName: ArticleController 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月14日 
 */

@RequestMapping("article")
@Controller
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CategoryService catService; 
	
	@Autowired
	RedisTemplate redisTemplate;
	
	//注入spring线程池的类
	@Autowired
	ThreadPoolTaskExecutor executor;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate; 
	
	/**
	 * 
	 * @Title: showDetail 
	 * @Description: 查看文章
	 * @param request
	 * @param id
	 * @return
	 * @return: String
	 */
	@RequestMapping("showdetail")
	public String showDetail(HttpServletRequest request,Integer id) {
		
		Article article = articleService.getById(id); 
		
		//=====================================================BBBBB===================================
		
		/**
		 * 当用户浏览文章时，往Kafka发送文章ID，在消费端获取文章ID，再执行数据库加1操作。
		 */
		
		kafkaTemplate.send("articles","hits="+id+"");
		System.err.println("发送kafka成功!"+id);
		//=====================================================BBBBB===================================
		/**
		 * 。现在请你利用Redis提高性能，当用户浏览文章时，将“Hits_${文章ID}_${用户IP地址}”为key，
		 * 查询Redis里有没有该key，如果有key，则不做任何操作。如果没有，
		 * 则使用Spring线程池异步执行数据库加1操作，
		 * 并往Redis保存key为Hits_${文章ID}_${用户IP地址}，
		 * value为空值的记录，而且有效时长为5分钟。
		 */

		String ip = request.getRemoteAddr();
		//1.准备redis的key,现在请你利用Redis提高性能，当用户浏览文章时，将“Hits_${文章ID}_${用户IP地址}”为key，
		
		String key = "Hits_"+id+"_"+ip;
		
		//2.查询Redis里有没有该key，如果有key，则不做任何操作
		String redisData = (String) redisTemplate.opsForValue().get(key);
		
		if(redisData==null) {
			//则使用Spring线程池异步执行数据库加1操作，
			//1.spring-beans.xml文件里加入spring线程池的配置
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					//Spring线程池异步执行数据库加1操作，
					article.setHits(article.getHits()+1);
					
					//修改数据库
					articleService.updateHits(article);
					//并往Redis保存key为Hits_${文章ID}_${用户IP地址},value为空值的记录，而且有效时长为5分钟。
					redisTemplate.opsForValue().set(key, "",5,TimeUnit.MINUTES);
					System.err.println("点击量已经加1了"+"=="+key);
				}
			});
		}
		
		//===================================AAAAAAAAAAAAA============================================
		
		CmsAssert.AssertTrueHtml(article!=null, "文章不存在");
		
		request.setAttribute("article",article);
		if(article.getArticleType()==TypeEnum.HTML) {
			return "article/detail";
		}else {
			//获取json转换器
			Gson gson = new Gson();
			//文章内容转换成集合对象
			List<Image> imgs = gson.fromJson(article.getContent(), List.class);
			article.setImgList(imgs);
			System.out.println("article "+article);
			return "article/detailimg";
			
		}
		
		
	}
	/**
	 * 
	 * @Title: getCategoryByChannel 
	 * @Description: 获取频道信息
	 * @param chnId
	 * @return
	 * @return: MsgResult
	 */
	@RequestMapping("getCategoryByChannel")
	@ResponseBody
	public MsgResult getCategoryByChannel(int chnId) {
		//List<Category> categories =  
		List<Category> categories = catService.listByChannelId(chnId);
		return new MsgResult(1, "",  categories);
		
	}
	/**
	 * 
	 * @Title: commentlist 
	 * @Description: 获取该用户的所有评论
	 * @param request
	 * @param id
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("commentlist")
	//@ResponseBody
	public String commentlist(HttpServletRequest request, int id,
			@RequestParam(defaultValue="1") int page) {
		
		PageInfo<Comment> pageComment =  articleService.commentlist(id,page);
		request.setAttribute("pageComment", pageComment);
		return "article/comments";
		//return new MsgResult(1,"获取成功",pageComment);
		
	}
	
}
