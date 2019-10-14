package com.tieshan.disintegrate.controller;

import cn.jpush.api.push.model.PushPayload;
import com.tieshan.disintegrate.pojo.Notice;
import com.tieshan.disintegrate.service.INoticeService;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description: 公共部分
 * @author: huxuanhua
 * @date: Created in 2019/9/27 10:33
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping("notice")
public class NoticeController {
    @Autowired
    private INoticeService noticeService;


    /**
     * 获取app头部图片
     *
     * @param request
     * @return
     */
    @GetMapping("/getTop")
    public RestResult getTop(HttpServletRequest request) {
        RestResult restResult = null;
        try {
            List<Map<String, Object>> mapList = noticeService.getTop(request);
            restResult = new RestResult("获取成功", mapList, ResultCode.SUCCESS.code());
        } catch (Exception e) {
            log.info("获取失败------->", e);
            return new RestResult("获取失败", null, ResultCode.ERROR.code());
        }
        return restResult;

    }

    @PostMapping("/push")
    public RestResult push(@RequestBody Notice notice, HttpServletRequest request) {
        RestResult restResult = null;
        int num = noticeService.insertNotice(notice, request);
        restResult = new RestResult("推送成功", num, ResultCode.SUCCESS.code());

        return restResult;

    }

    @PostMapping("/selNotice")
    public RestResult selNotice(String type, String device_type, HttpServletRequest request) {
        RestResult restResult = null;
        try {
            List<Map<String, Object>> resultList = noticeService.selNotice(type, device_type, request);
            if (!PubMethod.isEmpty(resultList)) {
                restResult = new RestResult("通知列表", resultList, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("暂无通知", resultList, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("通知列表---------->", e);
        }
        return restResult;
    }

    @PostMapping("/selNoticeById")
    public RestResult selNoticeById(String id) {
        RestResult restResult = null;
        try {
            Map<String, Object> resultMap = noticeService.selNoticeById(id);
            if (!PubMethod.isEmpty(resultMap)) {
                restResult = new RestResult("通知详情", resultMap, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("通知详情", resultMap, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("通知详情---------->", e);
        }
        return restResult;
    }
}
