package com.tieshan.disintegrate.controller;


import com.tieshan.disintegrate.service.ICarInformationService;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:车辆查询模块控制类
 * @author: Leavonson
 * @date: Created in 2019/9/18 14:51
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping("/car")
public class CarInformationController {

    @Autowired
    private ICarInformationService iCarInformationService;

    /**
     *@Description: 车辆查询页面表头数据
     * @param: carInfoId 车辆编号
     * @return: RestResult
     */
    @GetMapping("/findCarById")
    public RestResult findCarById(@RequestParam(value = "carInfoId", required = false) Long carInfoId) {
        if (PubMethod.isEmpty(carInfoId)||carInfoId<1) {
            return new RestResult("没有车辆编号", null, ResultCode.PARAM_IS_INVALID.code());
        }
        RestResult restResult = new RestResult(
                "车辆查询页面表头数据",
                iCarInformationService.findCarById(carInfoId),
                ResultCode.SUCCESS.code());
        return  restResult;
    }

    /**
     *@Description: 车辆查询页面-车辆信息信息数据查询
     * @param: carInfoId 车辆编号
     * @param: dicType 来自字典
     * @return: RestResult
     */
    @GetMapping("/findCarPicById")
    public RestResult findCarPicById(@RequestParam(value = "carInfoId", required = false) Long carInfoId) {

        if (PubMethod.isEmpty(carInfoId)||carInfoId<1) {
            return new RestResult("没有车辆编号", null, ResultCode.PARAM_IS_INVALID.code());
        }
        RestResult restResult = new RestResult("车辆信息，预处理、拓号 照片",iCarInformationService.findAll(carInfoId),ResultCode.SUCCESS.code());
        return  restResult;
    }

    /**
     *@Description: 车辆查询页面-手续信息数据查询
     * @param: carInfoId 车辆编号
     * @return: RestResult
     */
    @GetMapping("/findProcedureAll")
    public RestResult findProcedureAll(@RequestParam(value = "carInfoId", required = false) Long carInfoId) {
        if (PubMethod.isEmpty(carInfoId)||carInfoId<1) {
            return new RestResult("没有车辆编号", null, ResultCode.PARAM_IS_INVALID.code());
        }
        RestResult restResult = new RestResult("手续信息，手续处理周期",iCarInformationService.findProcedureAll(carInfoId),ResultCode.SUCCESS.code());
        return  restResult;
    }


    @GetMapping("/findCarBreak")
    public RestResult findCarBreak() {
        return null;
    }

    @GetMapping("/findCarSalvage")
    public RestResult findCarSalvage() {
        return null;
    }
}
