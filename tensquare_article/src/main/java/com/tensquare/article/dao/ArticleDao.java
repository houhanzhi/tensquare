package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying//增刪改都需要加该注解
    @Query(value = "update tb_article set state = 1 where id = ?", nativeQuery = true)
//    @Query(value = "udpate tb_article set state = 1 where id = ?1", nativeQuery = true)//?1--->表示传入多个参数时，1代表占位符
    public void updateState(String id);


    @Modifying
    @Query(value = "update tb_article set thumbup = thumbup + 1 where id = ?", nativeQuery = true)
    public void addThumbup(String id);

}
