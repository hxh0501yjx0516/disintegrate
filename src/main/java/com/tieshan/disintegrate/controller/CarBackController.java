package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.ICarBackService;
import com.tieshan.disintegrate.service.impl.CarBackService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 退车管理
 *
 * @author ：ren lei
 * @date ：Created in 2019/9/20 16:43
 * @modified By：
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/carBack")
public class CarBackController {

    @Autowired
    private ICarBackService carBackService;

    /**
     * 保存第一次打印退车单记录
     *
     * @param params carBackId
     * @param user
     * @return
     */
    @PostMapping(value = "/savePrintCarBackRecord")
    public RestResult savePrintCarBackRecord(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        carBackService.savePrintCarBackRecord(params,user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 保存车辆领取记录
     * @param params carBackId
     * @param user
     * @return
     */
    @PostMapping(value = "/saveReceiveCarRecord")
    public RestResult saveReceiveCarRecord(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        carBackService.saveReceiveCarRecord(params,user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }
}
