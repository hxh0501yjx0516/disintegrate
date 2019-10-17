package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.pojo.top_pic;
import com.tieshan.disintegrate.service.IBannerPicService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/10/17 10:46
 * @version: 1.0
 * @modified By:
 */
@RestController
public class BannerPicController {

    @Autowired
    IBannerPicService iBannerPicService;
    /***
     * PC端-查询Banner列表
     */
    @GetMapping("/doCarsQuery/findBannerPic")
    public RestResult findBannerPic(
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
            @LoginUser SysUser user
    ) {
        PageInfo pageInfo = null;
        List<top_pic> mapList = iBannerPicService.findBannerPic( page, pageSize,user);
        pageInfo = new PageInfo<>(mapList);
        RestResult restResult = new RestResult("查询列表成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }
    /***
     * PC端-添加Banner图
     */
    @GetMapping("/doCarsQuery/insertBannerPic")
    public RestResult insertBannerPic(
            @RequestParam(value = "topUrl") String topUrl,
            @RequestParam(value = "h5Url") String h5Url,
            @LoginUser SysUser user){
        int rows = iBannerPicService.insertBannerPic(topUrl,h5Url,user);
        RestResult restResult = new RestResult("添加成功", rows, ResultCode.SUCCESS.code());
        return restResult;
    }
    /***
     * PC端-修改Banner图
     */
    @GetMapping("/doCarsQuery/updateBannerPic")
    public RestResult updateBannerPic(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "topUrl") String topUrl,
            @RequestParam(value = "h5Url") String h5Url,
            @LoginUser SysUser user){
        int rows = iBannerPicService.updateBannerPic(id,topUrl,h5Url,user);
        RestResult restResult = new RestResult("修改成功", rows, ResultCode.SUCCESS.code());
        return restResult;
    }
    /***
     * PC端-修改Banner图状态，上架还是下架
     */
    @GetMapping("/doCarsQuery/updateBannerStatus")
    public RestResult updateBannerStatus(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "picType") char picType,
            @LoginUser SysUser user){
        int rows = iBannerPicService.updateBannerStatus(id,picType,user);
        RestResult restResult = new RestResult("修改成功", rows, ResultCode.SUCCESS.code());
        return restResult;
    }

    /***
     * PC端-删除Banner图
     */
    @GetMapping("/doCarsQuery/deleteBannerStatus")
    public RestResult deleteBannerStatus(
            @RequestParam(value = "id") Long id,
            @LoginUser SysUser user){
        int rows = iBannerPicService.deleteBannerStatus(id,user);
        RestResult restResult = new RestResult("删除成功", rows, ResultCode.SUCCESS.code());
        return restResult;
    }
}
