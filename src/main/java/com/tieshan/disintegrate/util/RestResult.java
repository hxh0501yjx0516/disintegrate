package com.tieshan.disintegrate.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
/**
 * @description: 返回固定结果集合
 * @author: huxuanhua
 * @date: Created in 2019/9/3 15:25
 * @version: 1.0
 * @modified By:
 */
public class RestResult {

    private static final Logger logger = LoggerFactory.getLogger(RestResult.class);

    private ObjectMapper mapper = new ObjectMapper();
    private static Map outMap = new HashMap();

    private String msg;

    private Object data;

    private String ret_code;

    public RestResult() {
    }

    public RestResult(String msg, Object data, String ret_code) {
        this.msg = msg;
        this.data = data;
        this.ret_code = ret_code;
    }

    public static RestResult success() {
        RestResult result = new RestResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(outMap);
        return result;
    }
    public static RestResult error() {
        RestResult result = new RestResult();
        result.setResultCode(ResultCode.ERROR);
        result.setData(outMap);
        return result;
    }

    public static RestResult error(String msg) {
        RestResult result = new RestResult();
        result.setResultCode(ResultCode.ERROR);
        result.setData(outMap);
        result.setMsg(msg);
        return result;
    }
    public static RestResult error(ResultCode resultCode, String msg) {
        RestResult result = new RestResult();
        result.setResultCode(resultCode);
        result.setData(outMap);
        result.setMsg(msg);
        return result;
    }

    public static RestResult success(Object data) {
        RestResult result = new RestResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static RestResult failure(ResultCode resultCode) {
        RestResult result = new RestResult();
        result.setResultCode(resultCode);
        return result;
    }

    public static RestResult failure(ResultCode resultCode, Object data) {
        RestResult result = new RestResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
    public void setResultCode(ResultCode code) {
        this.ret_code = code.code();
        this.msg = code.message();
    }
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    @Override
    public String toString() {
        return "RestResult{" +
                "msg='" + msg + '\'' +
                ", data=" + data +
                ", ret_code='" + ret_code + '\'' +
                '}';
    }

    public String toJsonString(){
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
