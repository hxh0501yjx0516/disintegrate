package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.CarArticleMapper;
import com.tieshan.disintegrate.pojo.CarArticle;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.ICarArticleService;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.vo.CarArticleVo;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ningfeng
 * @description:
 * @date Created in 9:48 2019/10/17
 * @version: 1.0
 * @modified By:
 */
@Service
public class CarArticleService implements ICarArticleService {

    @Autowired
    private CarArticleMapper carArticleMapper;

    @Autowired
    private TokenService tokenService;

    /**
     * 获得登录着的信息
     * @param request
     * @return
     */
    public SysUser getSysUser(HttpServletRequest request){
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        return sysUser;
    }

    /**
     * 查询帮助文档
     *
     * @return
     */
    @Override
    public List<CarArticleVo> selectArticleAll() {
        List<CarArticleVo> carArticleVoList = new ArrayList<>();
        List<CarArticle> carArticleList = carArticleMapper.selectArticleAll();
        PubMethod.copyCollectionByAdd(carArticleList, carArticleVoList);
        return carArticleVoList;
    }

    /**
     * 添加操作
     * @param articleVo
     * @param request
     * @return
     */
    @Override
    public int insertArticle(CarArticleVo articleVo, HttpServletRequest request) {
        SysUser sysUser = getSysUser(request);
        CarArticle carArticle = new CarArticle();
        PubMethod.copyPropeties(articleVo, carArticle);
        carArticle.setId(new IdWorker(1, 1, 1).nextId());
        carArticle.setCreateUserId(sysUser.getId());
        carArticle.setCreateUser(sysUser.getLogin_name());
        carArticle.setCreateTime(new Date());
        return carArticleMapper.insertArticle(carArticle);
    }

    /**
     * 编辑操作
     * @param articleVo
     * @param request
     * @return
     */
    @Override
    public int editArticle(CarArticleVo articleVo, HttpServletRequest request) {
        CarArticle carArticle = new CarArticle();
        PubMethod.copyPropeties(articleVo, carArticle);
        return carArticleMapper.editArticle(carArticle);
    }

    /**
     * 查询指定的帮助文档
     * @param id
     * @return
     */
    @Override
    public CarArticleVo selectArticleById(Long id) {
        CarArticleVo carArticleVo = new CarArticleVo();
        CarArticle carArticle  = carArticleMapper.selectArticleById(id);
        PubMethod.copyPropeties(carArticle, carArticleVo);
        return carArticleVo;
    }

    /**
     * 删除指定的帮助文档
     * @param id
     * @return
     */
    @Override
    public int deleteArticleById(Long id) {
        return carArticleMapper.deleteArticleById(id);
    }
}
