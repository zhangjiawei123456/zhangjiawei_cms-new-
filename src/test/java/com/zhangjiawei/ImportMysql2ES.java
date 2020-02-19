package com.zhangjiawei;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangjiawei.dao.ArticleMapper;
import com.zhangjiawei.dao.ArticleResp;
import com.zhangjiawei.entity.Article;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class ImportMysql2ES {
	
	@Autowired
	ArticleMapper articleMapper;
	
	//专门负责操作es的CRUD的
	@Autowired
	ArticleResp articleResp;
	//冲突解决方案:
	//1.java.lang.ClassNotFoundException: com.fasterxml.jackson.core.type.ResolvedType
	// 解决:找到pom文件,粘贴上<!-- 解决冲突的依赖 -->,等待加载完毕,就ok了
	//2.到pom文件里修改jetty版本为:9.4.9.v20180320
	//3.到pom文件里修改validate版本
	//    <validator.version>5.1.0.Final</validator.version>
	@Test
	public void testImport() {
		//从mysql中查询文章数据封装到list集合
		List<Article> list = articleMapper.findAll();
		//保存到es的索引库
		articleResp.saveAll(list);
	}
}
