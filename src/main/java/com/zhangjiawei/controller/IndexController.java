/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: IndexController.java 
 * @Prject: zhangjiawei_cms
 * @Package: com.zhangjiawei.controller 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月14日
 * @version: V1.0   
 */
package com.zhangjiawei.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.zhangjiawei.common.ConstantClass;
import com.zhangjiawei.common.HLUtils;
import com.zhangjiawei.dao.ArticleResp;
import com.zhangjiawei.entity.Article;
import com.zhangjiawei.entity.Category;
import com.zhangjiawei.entity.Channel;
import com.zhangjiawei.entity.Link;
import com.zhangjiawei.service.ArticleService;
import com.zhangjiawei.service.CategoryService;
import com.zhangjiawei.service.ChannelService;
import com.zhangjiawei.service.LinkService;


/**
 * @ClassName: IndexController
 * @Description: TODO
 * @作者: ZJW
 * @时间: 2019年11月14日
 */
@Controller
public class IndexController {
	/**
	 * 注入频道
	 */
	@Autowired
	ChannelService channelService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ArticleService articleService;

	@Autowired
	LinkService linkService;

	@Autowired
	ArticleResp articleResp;
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	/**
	 * 完成用es搜索的功能
	 */

	@GetMapping("index")
	public String search(Model model, String key, @RequestParam(defaultValue = "1") int page) {
		// 注入es的仓库
		if (page == 0) {
			page = 1;
		}
		long start = System.currentTimeMillis();
		// 根据标题来搜索
		// List<Article> list = articleResp.findByTitle(key);
		AggregatedPage<?> selectObjects = HLUtils.selectObjects(elasticsearchTemplate, Article.class, page,
				ConstantClass.PAGE_SIZE, new String[] { "title" }, "id", key);
		long end = System.currentTimeMillis();
		System.err.println("本次高亮搜索耗时:"+(end-start));
		List<Article> list = (List<Article>) selectObjects.getContent();
		PageInfo<Article> pageInfo = new PageInfo<>(list);
		pageInfo.setPageNum(page);// 当前页
		pageInfo.setPageSize(ConstantClass.PAGE_SIZE);// 每页显示多少条
		pageInfo.setTotal(selectObjects.getTotalElements());// 总条数
		int totalRecord = (int) selectObjects.getTotalElements();
		int pages = totalRecord % ConstantClass.PAGE_SIZE == 0 ? totalRecord / ConstantClass.PAGE_SIZE
				: totalRecord / ConstantClass.PAGE_SIZE + 1;
		pageInfo.setPages(pages);
		//如果当前页是最后一页了,我们就让当前页等于最后一页
		if (page == pages) {
			page = pages;
		}
		pageInfo.setPrePage(page - 1);
		pageInfo.setNextPage(page + 1);
		model.addAttribute("hotList", pageInfo);

		model.addAttribute("key", key);
		return "index";
	}

	/**
	 * 
	 * @Title: channel
	 * @Description: 根据id查询文章内容
	 * @param request
	 * @param chnId
	 * @param categoryId
	 * @param page
	 * @return
	 * @return: String
	 */
	@RequestMapping("channel")
	public String channel(HttpServletRequest request, @RequestParam(defaultValue = "1") int chnId,
			@RequestParam(defaultValue = "0") int categoryId, @RequestParam(defaultValue = "1") int page) {

		// 回传参数数值
		request.setAttribute("chnId", chnId);
		request.setAttribute("categoryId", categoryId);

		// 获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);

		// 获取这个频道下所以的分类
		List<Category> categories = categoryService.listByChannelId(chnId);
		request.setAttribute("categories", categories);

		// 文章分页
		PageInfo<Article> articles = articleService.listByCat(chnId, categoryId, page);
		request.setAttribute("articles", articles);
		// 跳转到用户页面
		return "channelindex";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = { "index", "/" })
	public String index(HttpServletRequest request, @RequestParam(defaultValue = "1") int page) {

		// 获取所有的频道
		List<Channel> channels = channelService.list();
		request.setAttribute("channels", channels);

		PageInfo<Article> hotList = articleService.hotList(page);

		List<Article> newArticles = articleService.getNewArticles(5);

		// 获取最新图片文章
		List<Article> imgArticles = articleService.getImgArticles(10);

		// 友情链接
		PageInfo<Link> info = linkService.list(1);
		List<Link> linkList = info.getList();

		request.setAttribute("hotList", hotList);
		request.setAttribute("newArticles", newArticles);
		request.setAttribute("imgArticles", imgArticles);
		request.setAttribute("linkList", linkList);

		return "index";
	}

}
