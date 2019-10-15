package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.ICarPartsInfoService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import com.tieshan.disintegrate.vo.PartsInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description: 库管模块控制层
 * @author: Leavonson
 * @date: Created in 2019/10/12 17:17
 * @version: 1.0
 * @modified By:
 */
@RestController
public class CarPartsInfoController {

    @Autowired
    ICarPartsInfoService iCarPartsInfoService;
    /***
     * App端-查询待入库列表
     */
    @GetMapping("/doCarsQuery/selectPreParts")
    public RestResult selectPreParts(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
            @LoginUser SysUser user
    ) {
        PageInfo pageInfo = null;
        List<PartsInfoVo> mapList = iCarPartsInfoService.selectPreParts(findMsg, page, pageSize, user.getCompany_id());
        pageInfo = new PageInfo<>(mapList);
        RestResult restResult = new RestResult("查询待入库列表信息成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }

    /***
     * App端-查询已入库列表
     */
    @GetMapping("/doCarsQuery/selectIsParts")
    public RestResult selectIsParts(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
            @LoginUser SysUser user
    ) {
        PageInfo pageInfo = null;
        List<PartsInfoVo> mapList = iCarPartsInfoService.selectIsParts(findMsg, page, pageSize, user.getCompany_id());
        pageInfo = new PageInfo<>(mapList);
        RestResult restResult = new RestResult("查询已拆件列表信息成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }

    /***
     * App端-更改入库状态
     */
    @GetMapping("/doCarsQuery/updateIsParts")
    public RestResult updateIsParts(
            @RequestParam(value = "carInfoId") Long carInfoId,
            @RequestParam(value = "partsCode") String partsCode,
            @LoginUser SysUser user
    ) {
        int rows = iCarPartsInfoService.updateIsParts(carInfoId,user,partsCode);
        RestResult restResult = new RestResult("入库成功"+rows+"件", rows, ResultCode.SUCCESS.code());
        return restResult;
    }

}
