package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.service.ICarSourceService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @description: 测试控制类, 分页
 * @author: ningfeng
 * @date: Created in 2019/9/6 11:27
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping("/carSource")
public class CarSourceController {

    @Autowired
    private ICarSourceService carSourceService;

    /**
     * 车源列表
     * @return
     */
    @GetMapping("/selectCarSourceList")
    public RestResult selectCarSourceList(@RequestParam(value = "sourceType", defaultValue = "1") String sourceType,
                                          @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) int page,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) int pageSize){
        return new RestResult("查询成功", carSourceService.selectCarSourceList(sourceType, page, pageSize), ResultCode.SUCCESS.code());
    }

    /**
     * 查询所有的用户的姓名和id （业务员）
     * @return
     */
    @GetMapping(value = "/findUserList")
    public RestResult findUserNameList(){
        return new RestResult("查询成功", carSourceService.findUserNameList(), ResultCode.SUCCESS.code());
    }

    /**
     * 查询所有银行的信息
     * @return
     */
    @GetMapping(value = "/findBankNameList")
    public RestResult findBankUserNameList(){
        return new RestResult("查询成功", carSourceService.findBankNameList(), ResultCode.SUCCESS.code());
    }

    /**
     * 查询指定车源的信息
     * @param id  车源的id
     * @return
     */
    @GetMapping(value = "selectCarSource")
    public RestResult selectCarSource(Long id){
        return new RestResult("查询成功", carSourceService.selectCarSource(id), ResultCode.SUCCESS.code());
    }


    /**
     * 增加车源
     * @param carSource
     * @param request
     * @return
     */
    @PostMapping(value = "/add")
    public RestResult addDepart(@RequestBody CarSource carSource, HttpServletRequest request) {
        try {
            carSourceService.add(carSource, request);
        }catch (Exception e){
            log.info("添加车源失败", e);
            return new RestResult("添加车源失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("添加车源成功", null, ResultCode.SUCCESS.code());
    }

    /**
     * 获得所有车辆处理方式和手续获取方式，以map结果放回
     * @return
     */
    @GetMapping(value = "/selectProcessingTypeAndProceduresType")
    public RestResult selectProcessingTypeAndProceduresType(){
        return new RestResult("查询成功", carSourceService.selectProcessingTypeAndProceduresType(), ResultCode.SUCCESS.code());
    }

    /**
     * 添加车辆
     * @param carInfo
     * @param id   车源主键id
     * @param request
     * @return
     */
    @PostMapping(value = "/addCar")
    public RestResult addCar(@RequestBody CarInfo carInfo,@RequestParam(value = "id") Long id, HttpServletRequest request){
        try{
            carSourceService.addCar(carInfo, id, request);
        }catch (Exception e){
            log.info("添加车辆失败", e);
            return new RestResult("添加失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("添加成功", null, ResultCode.SUCCESS.code());
    }
}
