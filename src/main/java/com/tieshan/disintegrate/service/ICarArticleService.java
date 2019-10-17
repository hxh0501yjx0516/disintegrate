package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.vo.CarArticleVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author ningfeng
 * @description:
 * @date Created in 9:46 2019/10/17
 * @version: 1.0
 * @modified By:
 */
public interface ICarArticleService {

    List<CarArticleVo> selectArticleAll();

    int insertArticle(CarArticleVo articleVo, HttpServletRequest request);

    int editArticle(CarArticleVo articleVo, HttpServletRequest request);

    CarArticleVo selectArticleById(Long id);

    int deleteArticleById(Long id);
}
