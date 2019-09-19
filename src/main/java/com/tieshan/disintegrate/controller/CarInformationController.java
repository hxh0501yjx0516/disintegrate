package com.tieshan.disintegrate.controller;


import com.tieshan.disintegrate.service.ICarInformationService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:查询单个车辆信息详情
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

    @GetMapping("/findCarById")
    public RestResult findCarById(@RequestParam("carInfoId") Integer carInfoId) {
        RestResult restResult = new RestResult("车辆查询页面表头数据", iCarInformationService.findCarById(carInfoId),ResultCode.SUCCESS.code());
        return  restResult;
    }

    @GetMapping("/findCarInfoById")
    public RestResult findCarInfoById(@RequestParam("carInfoId") Integer carInfoId) {
        RestResult restResult = new RestResult("车辆信息查询", iCarInformationService.findCarInfoById(carInfoId),ResultCode.SUCCESS.code());
        return  restResult;
    }

    @GetMapping("/findCarPicById")
    public RestResult findCarPicById(@RequestParam("carInfoId") Integer carInfoId) {
        RestResult restResult = new RestResult("查询预处理、拓号 照片", iCarInformationService.findCarPicById(carInfoId),ResultCode.SUCCESS.code());
        return  restResult;
    }

    @GetMapping("/findCarSource")
    public RestResult findCarSource() {
        return null;
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
