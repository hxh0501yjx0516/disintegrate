package com.tieshan.disintegrate.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.pojo.User;
import com.tieshan.disintegrate.service.ICarInfoService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/17 19:31
 * @modified By：
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/carInfo")
public class CarInfoController {

    @Autowired
    private ICarInfoService carInfoService;

    /**
     * 添加一个
     * @param carInfo
     * @param user
     * @return
     */
    @PostMapping(value = "/add")
    public RestResult addCarInfo(@RequestBody CarInfo carInfo, @LoginUser SysUser user) {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        carInfo.setId(idWorker.nextId());
        carInfo.setOperator(user.getUser_name());
        carInfo.setDisintegratePlantId(user.getCompany_id());
        carInfoService.add(carInfo);
        return new RestResult("添加成功", carInfo, ResultCode.SUCCESS.code());
    }


    /**
     * 修改
     * @param carInfo
     * @param user
     * @return
     */
    @PostMapping(value = "/update")
    public RestResult updateCarInfo(@RequestBody CarInfo carInfo, @LoginUser SysUser user) {
        carInfo.setDisintegratePlantId(user.getCompany_id());
        carInfoService.update(carInfo);
        return new RestResult("修改成功", carInfo, ResultCode.SUCCESS.code());
    }


    /**
     * 查询多个
     * @param params
     * @param user
     * @return
     */
    @PostMapping(value = "/list")
    public RestResult list(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        PageInfo<CarInfo> carInfoPageInfo = carInfoService.queryPage(params);
        return new RestResult("", carInfoPageInfo, ResultCode.SUCCESS.code());
    }

    /**
     * 根据id查询CarInfo
     * @param id
     * @param user
     * @return
     */
    @PostMapping(value = "/info")
    public RestResult info(@RequestBody Long id, @LoginUser SysUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("disintegratePlantId", user.getCompany_id());
        CarInfo carInfo = carInfoService.queryOne(map);
        return new RestResult("", carInfo, ResultCode.SUCCESS.code());
    }
}
