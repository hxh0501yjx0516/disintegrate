//package com.tieshan.disintegrate.service.impl;
//
//import com.tieshan.disintegrate.service.ILoginService;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//import org.springframework.stereotype.Service;
//
///**
// * @description:
// * @author: huxuanhua
// * @date: Created in 2019/9/2 14:14
// * @version: 1.0
// * @modified By:
// */
//@Service
//public class LoginService implements ILoginService {
//
//    @Override
//    public String authLogin(String username, String password) {
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        token.setRememberMe(true);
//        Subject currentUser = SecurityUtils.getSubject();
//        currentUser.login(token);
//    }
//}
