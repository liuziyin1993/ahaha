package com.aisile.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.aisile.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{
    //根据id修改对应文章状态为已审核
    @Modifying
    @Query("update Article a set a.state='1' where a.id=?1")
    public void examine(String id);

    //根据id将文章点赞次数加一
    @Modifying
    @Query("update Article a set a.thumbup=a.thumbup+1 where a.id=?1")
    public void updateThumbup(String id);
}
