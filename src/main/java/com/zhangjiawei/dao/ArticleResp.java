package com.zhangjiawei.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.zhangjiawei.entity.Article;


//此时就自动具备了简单的CRUD操作
public interface ArticleResp extends ElasticsearchRepository<Article, Integer>{

	//根据标题来模糊查询(方法名称一定按照规范来写,否则报错!!!!!!!!!!!!!!!!!!!!!)
	List<Article> findByTitle(String key);
}
