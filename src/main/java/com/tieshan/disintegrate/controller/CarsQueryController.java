package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.CarsQuery;
import com.tieshan.disintegrate.service.CarsQueryService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description:车辆查询页面展示
 * @author: Leavonson
 * @date: Created in 2019/9/17 16:36
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
public class CarsQueryController {

    @Autowired
    private CarsQueryService carsQueryService;

    /**
     * @Description:基于条件分页查询车辆信息
     * @param: findMsg查询条件(姓名 / 电话 / 车型 / 车牌号 / 车辆编号 / 车架号 / 发动机号)
     * @param: pageCurrent  当前的页码值
     * @return: RestResult(msg, data, code)
     */
    @GetMapping("/doCarsQuery/doFindPageObjects")
    public PageInfo<CarsQuery> doFindPageObjects(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize
    ) {
        PageInfo<CarsQuery> pageInfo = null;
        try {
            List<CarsQuery> list = carsQueryService.findPageObjects(findMsg, page,pageSize);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            log.info("获取车辆信息列表失败", e);
        }

        return pageInfo;
    }

}
