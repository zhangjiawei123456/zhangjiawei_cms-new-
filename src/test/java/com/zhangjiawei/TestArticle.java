
package com.zhangjiawei;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.zhangjiawei.entity.Article;
import com.zhangjiawei.service.ArticleService;


/** 
 * @ClassName: TestArticle 
 * @Description: TODO
 * @作者: ZJW 
 * @时间: 2019年11月14日 
 */
public class TestArticle extends TestBase{
	@Autowired
	ArticleService articleService;
	
	@Test
	public void testHotList() {
		PageInfo<Article> hotList = articleService.hotList(1);
		List<Article> list = hotList.getList();
		list.forEach(a->{
			System.out.println(" a is " + a);
		});
	}
}
