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
    public RestResult submitLogin(String username, String password, String type, HttpServletRequest request) {
        if (PubMethod.isEmpty(username) || PubMethod.isEmpty(password)) {
            return new RestResult("用户名或密码不能为空", null, ResultCode.ERROR.code());
        }
//        Dto dto = new Dto();
        RestResult restResult = null;
        Map<String, Object> resultMap = new HashMap<>();
        try {
            SysUser sysUser = userService.login(username, password);
            if (sysUser != null) {
//                String type = null;
////                String userAgent = request.getHeader("user-agent");
////                UserAgent userAgent1 = UserAgent.parseUserAgentString(userAgent);
                if ("1".equals(type)) {
                    type = "app-";
                } else {
                    type = "PC-";
                }
                String token = this.tokenService.generateToken(type, sysUser);
                this.tokenService.save(token, sysUser);
                resultMap.put("token", token);
                resultMap.put("login_name", sysUser.getLogin_name());
                resultMap.put("user_name", sysUser.getUser_name());
                resultMap.put("department_name", sysUser.getDepartment_name());
                resultMap.put("depart_code", sysUser.getDepart_code());
                resultMap.put("head_url", sysUser.getHead_url());
                resultMap.put("phone", sysUser.getPhone());
                resultMap.put("id", sysUser.getId());
            }
            userService.loginlog(sysUser);
            restResult = new RestResult("登录成功", resultMap, ResultCode.SUCCESS.code());

        } catch (Exception e) {
            log.info("登录接口报错----->" + e);
            return new RestResult("用户名或密码错误", resultMap, ResultCode.ERROR.code());

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
     * @param password
     * @param oldPassword
     * @return
     */
    @PostMapping(value = "/uppssword")
    public RestResult updatePassword(String password, String oldPassword, HttpServletRequest request) {
        RestResult restResult = null;
        try {
            String token = request.getHeader("token");
            SysUser sysUser = tokenService.getToken(token);
            int isLive = userService.findUserByPassword(sysUser.getLogin_name(), oldPassword, sysUser.getId() + "");
            if (isLive > 0) {
                sysUser.setUser_password(password);
                int num = userService.updatePassword(sysUser);
                if (num > 0) {
                    this.tokenService.remove(token);
                    restResult = new RestResult("修改成功", null, ResultCode.SUCCESS.code());
                } else {
                    restResult = new RestResult("修改失败", null, ResultCode.ERROR.code());
                }
            } else {
                restResult = new RestResult("原密码不正确", null, ResultCode.ERROR.code());

            }

        } catch (Exception e) {
            log.info("修改密码报错----->", e);
        }
        return restResult;
    }


}
