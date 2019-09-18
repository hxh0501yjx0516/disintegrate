package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.pojo.CarsQuery;
import com.tieshan.disintegrate.pojo.PageObject;
import com.tieshan.disintegrate.service.CarsQueryService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:车辆查询页面展示
 * @author: Leavonson
 * @date: Created in 2019/9/17 16:36
 * @version: 1.0
 * @modified By:
 */
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
    public RestResult doFindPageObjects(String findMsg, Integer pageCurrent) {

        PageObject<CarsQuery> pageObject =
                carsQueryService.findPageObjects(findMsg, pageCurrent);
        return new RestResult("ok", pageObject, ResultCode.SUCCESS.code());
    }

}
