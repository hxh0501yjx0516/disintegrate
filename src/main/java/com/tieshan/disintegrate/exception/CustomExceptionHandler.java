package com.tieshan.disintegrate.exception;

import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 * @author ：ren lei
 * @date ：Created in 2019/9/16 9:41
 * @description：
 * @modified By：
 * @version: 1.0.0
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public RestResult handleRRException(CustomException e){
        return RestResult.error(ResultCode.ERROR, e.getMsg());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public RestResult handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return RestResult.error(ResultCode.ERROR, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public RestResult handleDuplicateKeyException(DuplicateKeyException e){
        logger.error(e.getMessage(), e);
        return RestResult.error("数据库中已存在该记录");
    }

    @ExceptionHandler(AuthorizationException.class)
    public RestResult handleAuthorizationException(AuthorizationException e){
        logger.error(e.getMessage(), e);
        return RestResult.error("没有权限，请联系管理员授权");
    }

    @ExceptionHandler(Exception.class)
    public RestResult handleException(Exception e){
        logger.error(e.getMessage(), e);
        return RestResult.error("未知异常");
    }
}
