package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IUserService;
import com.tieshan.disintegrate.token.Dto;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:登录
 * @author: huxuanhua
 * @date: Created in 2019/9/2 14:12
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping(value = "/auth")
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    public RestResult submitLogin(String username, String password, HttpServletRequest request) {
        if (PubMethod.isEmpty(username) || PubMethod.isEmpty(password)) {
            return new RestResult("用户名或密码不能为空", null, ResultCode.ERROR.code());
        }
//        Dto dto = new Dto();
        RestResult restResult = null;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            SysUser sysUser = userService.login(username, password);
            if (sysUser != null) {
                String type = null;
                String userAgent = request.getHeader("user-agent");
                UserAgent userAgent1 = UserAgent.parseUserAgentString(userAgent);
                if (userAgent1.getOperatingSystem().isMobileDevice()) {
                    type = "MOBILE-";
                } else {
                    type = "PC-";
                }
                String token = this.tokenService.generateToken(type, sysUser);
                this.tokenService.save(token, sysUser);
                resultMap.put("token",token);
                resultMap.put("login_name",sysUser.getLogin_name());
                resultMap.put("user_name",sysUser.getUser_name());
                resultMap.put("department_name",sysUser.getDepartment_name());
            }
            userService.loginlog(sysUser);
            restResult = new RestResult("登录成功", resultMap, ResultCode.SUCCESS.code());

        } catch (Exception e) {
            log.info("登录接口报错----->" + e);
            return new RestResult("登录失败", resultMap, ResultCode.ERROR.code());

        }
        return restResult;

    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/loginout")
    public RestResult loginout(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            SysUser sysUser = tokenService.getToken(token);
            this.tokenService.remove(token);
            userService.loginoutlog(sysUser);
            return new RestResult("退出成功", null, ResultCode.SUCCESS.code());
        } catch (Exception e) {
            log.info("退出失败报错----->", e);
        }
        return new RestResult("退出失败", null, ResultCode.ERROR.code());

    }

    /**
     * 修改密码
     *
     * @param sysUser
     * @return
     */
    @PostMapping(value = "/uppssword")
    public RestResult updatePassword(@RequestBody SysUser sysUser) {
        RestResult restResult = null;
        try {
            int num = userService.updatePassword(sysUser);
            if (num > 0) {
                restResult = new RestResult("修改成功", null, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("修改失败", null, ResultCode.ERROR.code());
            }
        } catch (Exception e) {
            log.info("修改密码报错----->", e);
        }
        return restResult;
    }

}
