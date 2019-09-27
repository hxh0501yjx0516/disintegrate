package com.tieshan.disintegrate.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.tieshan.disintegrate.service.IAppVersionService;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/27 14:29
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping("app")
public class AppVersionController {

    @Autowired
    private IAppVersionService appVersionService;

    /**
     * 获取版本
     *
     * @param version_number
     * @param app_type
     * @return
     */
    @PostMapping(value = "getAppVersion")
    public RestResult getAppVersion(String version_number, String app_type) {
        RestResult restResult = null;
        try {
            Map<String, Object> resultMap = appVersionService.getAppVersion(version_number, app_type);
            if (!PubMethod.isEmpty(resultMap)) {
                restResult = new RestResult("获取版本信息", resultMap, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("当前为最新版本", resultMap, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("获取app版本异常--------->" + e);
        }
        return restResult;
    }
}
