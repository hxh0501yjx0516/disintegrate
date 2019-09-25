package com.tieshan.disintegrate.config;

import com.beust.jcommander.internal.Nullable;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RedisUtils;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/3 13:53
 * @version: 1.0
 * @modified By:
 */

@Component
public class CustomInterceptor implements HandlerInterceptor {
    @Autowired
    private Environment env;
@Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (PubMethod.isEmpty(token)) {
            returnJson(response, "未检出到token", null, ResultCode.TOKEN_NO.code());
            return false;
        } else {
            //boolean isLive = redisUtil.get(token);
            //redisUtil.close();
            String userStr = redisUtils.get(token);
            if (StringUtils.isEmpty(userStr)) {
                returnJson(response, "token失效", null, ResultCode.TOKEN_FAILURE.code());
                return false;
            }
        }

        return true;
    }

    private static void returnJson(HttpServletResponse response, String msg, Object object, String code) throws Exception {
        OutputStream outputStream = response.getOutputStream();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            RestResult restResult = new RestResult(msg, object, code);
            outputStream.write(restResult.toJsonString().getBytes());
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
        } finally {
            if (outputStream != null)
                outputStream.close();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }


}
