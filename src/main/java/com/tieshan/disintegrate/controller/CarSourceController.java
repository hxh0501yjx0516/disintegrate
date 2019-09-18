package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.pojo.User;
import com.tieshan.disintegrate.service.ICarSourceService;
import com.tieshan.disintegrate.service.impl.UserService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping(value = "/add")
    public RestResult addDepart(@RequestBody CarSource carSource) {
        try {
            carSourceService.add(carSource);
        }catch (Exception e){
            log.info("添加车源失败", e);
            return new RestResult("添加车源失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("添加车源成功", null, ResultCode.SUCCESS.code());
    }

}
