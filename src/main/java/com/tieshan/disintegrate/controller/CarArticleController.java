package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.service.ICarArticleService;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import com.tieshan.disintegrate.vo.CarArticleVo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * @author ningfeng
 * @description:
 * @date Created in 9:44 2019/10/17
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping("/carArticle")
public class CarArticleController {

    @Autowired
    private ICarArticleService carArticleService;

    /**
     * 查询所有帮助文档
     * @return
     */
    @GetMapping("/selectArticleAll")
    public RestResult selectArticleAll(){
        List<CarArticleVo> carArticleVoList = new ArrayList<>();
        try{
            carArticleVoList = carArticleService.selectArticleAll();
        }catch (Exception e){
            log.info("查询失败", e);
            return new RestResult("查询失败", carArticleVoList, ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", carArticleVoList, ResultCode.SUCCESS.code());
    }

    /**
     * 编辑文章内容
     * @param articleVo
     * @param request
     * @return
     */
    @PostMapping("/editArticle")
    public RestResult editArticle(@RequestBody CarArticleVo articleVo, HttpServletRequest request){
        RestResult restResult = null;
        int count = 0;
        try{
            if (PubMethod.isEmpty(articleVo.getId())){
                count = carArticleService.insertArticle(articleVo, request);
            }else {
                count = carArticleService.editArticle(articleVo, request);
            }
            if (count == 0){
                restResult = new RestResult("操作失败", "", ResultCode.ERROR.code());
            }else {
                restResult = new RestResult("操作成功", "", ResultCode.SUCCESS.code());
            }
        }catch (Exception e){
            log.info("操作失败", e);
            return new RestResult("操作失败", "", ResultCode.ERROR.code());
        }
        return restResult;
    }

    /**
     * 通过id查询帮助文档详情
     * @param id
     * @return
     */
    @GetMapping("/selectArticleById")
    public RestResult selectArticleById(Long id){
        CarArticleVo carArticleVo = null;
        try{
            carArticleVo = carArticleService.selectArticleById(id);
        }catch (Exception e){
            log.info("查询失败", e);
            return new RestResult("查询失败", carArticleVo, ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", carArticleVo, ResultCode.SUCCESS.code());
    }

    /**
     * 删除指定的文章内容
     * @param id
     * @return
     */
    @DeleteMapping(value = "/deleteArticleById")
    public RestResult deleteArticleById(Long id){
        RestResult restResult = null;
        try{
            int count = carArticleService.deleteArticleById(id);
            if (count == 0){
                restResult = new RestResult("操作失败", "", ResultCode.ERROR.code());
            }else {
                restResult = new RestResult("操作成功", "", ResultCode.SUCCESS.code());
            }
        }catch (Exception e){
            log.info("操作失败", e);
            return new RestResult("操作失败", "", ResultCode.ERROR.code());
        }
        return restResult;
    }

}
