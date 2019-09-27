package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.service.INoticeService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
