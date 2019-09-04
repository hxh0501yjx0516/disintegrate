package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IUserService;
import com.tieshan.disintegrate.token.Dto;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/2 14:12
 * @version: 1.0
 * @modified By:
 */
@RestController
@RequestMapping(value = "/auth")
public class LoginController {
    @Autowired
    private IUserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public RestResult submitLogin(String username, String password, HttpServletRequest request) {
        Dto dto = new Dto();
        SysUser sysUser = userService.login(username, password);
        if (sysUser != null) {
            String type = null;
            String userAgent = request.getHeader("user-agent");
            UserAgent userAgent1 = UserAgent.parseUserAgentString(userAgent);
            System.err.println(userAgent);
            if (userAgent1.getOperatingSystem().isMobileDevice()) {
//                token.append("MOBILE-");
                type = "MOBILE-";
            } else {
//                token.append("PC-");
                type = "PC-";

            }
            String token = this.tokenService.generateToken(type, sysUser);
            this.tokenService.save(token, sysUser);


            dto.setIsLogin("true");
            dto.setToken(token);
            long time = System.currentTimeMillis();
            dto.setTokenCreatedDate(time);
            dto.setTokenExpiryDate(time + 24 * 60 * 1000);


        } else {
            dto.setIsLogin("false");
        }
        return new RestResult("登录成功", dto, ResultCode.SUCCESS.code());

    }

    @GetMapping(value = "go")
    public RestResult allUser() {
        return null;
    }


}
