package com.tieshan.disintegrate.resolver;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.config.RedisUtil;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.pojo.User;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.RedisUtils;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/11 15:16
 * @description： 有@LoginUser注解的方法参数，注入当前登录用户
 * @modified By：
 * @version: 1.0.0
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return  parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getHeader("token");
        if(object == null){
            return null;
        }
        //获取用户信息
        SysUser user = JSONObject.parseObject(redisUtils.get(object.toString()), SysUser.class);
        return user;
    }
}
