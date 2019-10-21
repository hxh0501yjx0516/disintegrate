package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.pojo.AppVersion;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IAppVersionService;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 分页查询多个
     *  @param params pageNum int    页码
     *                pageSize int   页面大小
     *                searchInfo     查询条件
     * @return
     */
    @PostMapping(value = "/list")
    public RestResult list(@RequestBody Map<String, Object> params) {
        PageInfo<AppVersion> appVersionPageInfo = appVersionService.queryPage(params);
        return new RestResult("查询成功", appVersionPageInfo, ResultCode.SUCCESS.code());
    }

    /**
     * 查询一个
     * @param params    id
     * @return
     */
    @PostMapping(value = "/query")
    public RestResult query(@RequestBody Map<String, Object> params) {
        AppVersion query = appVersionService.query(params);
        return new RestResult("查询成功", query, ResultCode.SUCCESS.code());
    }

    /**
     * 添加一个
     * @param   appVersion
     * @return
     */
    @PostMapping(value = "/add")
    public RestResult add(@RequestBody AppVersion appVersion, @LoginUser SysUser user) {
        appVersionService.add(appVersion, user);
        return new RestResult("添加成功", null, ResultCode.SUCCESS.code());
    }
    /**
     * 修改
     * @param   appVersion
     * @return
     */
    @PostMapping(value = "/update")
    public RestResult update(@RequestBody AppVersion appVersion) {
        appVersionService.update(appVersion);
        return new RestResult("修改成功", null, ResultCode.SUCCESS.code());
    }
    /**
     * 删除
     * @param   params  id
     * @return
     */
    @PostMapping(value = "/del")
    public RestResult del(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        appVersionService.del(params,user);
        return new RestResult("删除成功", null, ResultCode.SUCCESS.code());
    }
}
