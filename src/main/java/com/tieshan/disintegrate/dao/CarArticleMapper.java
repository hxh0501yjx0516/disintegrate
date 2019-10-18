package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ningfeng
 * @description:
 * @date Created in 10:03 2019/10/17
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarArticleMapper {

    List<CarArticle> selectArticleAll();

    int insertArticle(CarArticle carArticle);

    int editArticle(CarArticle carArticle);

    CarArticle selectArticleById(@Param(value = "id") Long id);

    int deleteArticleById(@Param(value = "id") Long id);
}
