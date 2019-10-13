package com.tieshan.disintegrate.config;

import com.beust.jcommander.internal.Nullable;
import com.tieshan.disintegrate.dao.SysUserMapper;
import com.tieshan.disintegrate.pojo.SysLog;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.*;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysLog sysLog;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        insertSysLog(request);
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


    /**
     * 全局操作日志
     *
     * @param request
     */
    private void insertSysLog(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            SysUser sysUser = tokenService.getToken(token);
            if (!PubMethod.isEmpty(sysUser)) {
                sysLog.setDepart_name(sysUser.getDepartment_name());
                sysLog.setDisintegrate_plant_id(sysUser.getCompany_id());
                sysLog.setOperator(sysUser.getLogin_name());
                sysLog.setOperator_id(sysUser.getId());
                sysLog.setMethod_url(request.getRequestURL().toString());
                sysLog.setIp_addr(getIpAddr(request));
                String param = getMonth(0);
                System.err.println("判断参数------------>>>>>>>>>>>>>" + param);
                System.err.println("请求------------>>>>>>>>>>>>>" + request.getRequestURL().toString());
                int num = sysUserMapper.existTable(param);
                if (num < 1) {
                    sysUserMapper.creatSyslog(param);//创建表
                    String delMonth = getMonth(-2);
                    int delnum = sysUserMapper.existTable(delMonth);
                    if (delnum > 0) {
                        sysUserMapper.delTable(getMonth(-2));//删除两个越前的历史表
                    }
                }
                sysUserMapper.syslog(param, sysLog);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String getMonth(int month) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, month);
        SimpleDateFormat sb = new SimpleDateFormat("yyyyMM");
        String retrunDate = sb.format(cal.getTime());
        return retrunDate;
    }


    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            if (ip.indexOf(",") != -1) {
                ip = ip.split(",")[0];
            }
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}