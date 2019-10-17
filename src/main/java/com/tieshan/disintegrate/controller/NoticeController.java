package com.tieshan.disintegrate.controller;

import cn.jpush.api.push.model.PushPayload;
import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.constant.ConStants;
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

    /**
     * 推送消息
     *
     * @param id
     * @param request
     * @return
     */
    @PostMapping("/push")
    public RestResult push(String id, HttpServletRequest request) {
        RestResult restResult = null;
        int num = noticeService.push(id, request);
        if (num > 0) {
            restResult = new RestResult("推送成功", null, ResultCode.SUCCESS.code());
        } else {
            restResult = new RestResult("推送失败", null, ResultCode.SUCCESS.code());
        }

        return restResult;

    }

    /**
     * 插入数据库
     *
     * @param notice
     * @param request
     * @return
     */
    @PostMapping("/insertNotice")
    public RestResult insertNotice(@RequestBody Notice notice, HttpServletRequest request) {
        RestResult restResult = null;
        int num = noticeService.insertNotice(notice, request);
        if (num > 0) {
            restResult = new RestResult("添加通知成功", null, ResultCode.SUCCESS.code());

        } else {
            restResult = new RestResult("添加通知失败", null, ResultCode.SUCCESS.code());

        }

        return restResult;

    }

    /**
     * app消息列表
     *
     * @param type
     * @param device_type
     * @param request
     * @param page
     * @param pageSize
     * @return
     */
    @PostMapping("/selNotice")
    public RestResult selNotice(String type, String device_type, HttpServletRequest request,
                                @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) int page,
                                @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) int pageSize) {
        RestResult restResult = null;
        try {
            List<Map<String, Object>> resultList = noticeService.selNotice(type, device_type, request, page, pageSize);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(resultList);

            if (!PubMethod.isEmpty(resultList)) {
                restResult = new RestResult("通知列表", pageInfo, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("暂无通知", null, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("通知列表---------->", e);
        }
        return restResult;
    }

    /**
     * 获取详情app
     *
     * @param id
     * @return
     */
    @PostMapping("/selNoticeById")
    public RestResult selNoticeById(String id) {
        RestResult restResult = null;
        try {
            Map<String, Object> resultMap = noticeService.selNoticeById(id);
            if (!PubMethod.isEmpty(resultMap)) {
                restResult = new RestResult("通知详情", resultMap, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("获取详情失败", resultMap, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("获取详情失败---------->", e);
        }
        return restResult;
    }

    /**
     * 获取消息列表  pc
     *
     * @param request
     * @param page
     * @param pageSize
     * @return
     */
    @PostMapping("/selNoticePC")
    public RestResult selNoticePC(HttpServletRequest request,
                                  @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) int page,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) int pageSize) {
        RestResult restResult = null;
        try {
            List<Map<String, Object>> resultList = noticeService.selNoticePC(request, page, pageSize);
            PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(resultList);

            if (!PubMethod.isEmpty(resultList)) {
                restResult = new RestResult("通知列表", pageInfo, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("暂无通知", null, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("通知列表---------->", e);
        }
        return restResult;
    }

    /**
     * 获取详情 pc
     *
     * @param id
     * @return
     */
    @PostMapping("/selNoticeByIdPC")
    public RestResult selNoticeByIdPC(String id) {
        RestResult restResult = null;
        try {
            Map<String, Object> resultMap = noticeService.selNoticeByIdPC(id);
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

    /**
     * 删除通知 pc
     *
     * @param id
     * @return
     */
    @PostMapping("/delNoticeById")
    public RestResult delNoticeById(String id) {
        RestResult restResult = null;
        try {
            int num = noticeService.delNoticeById(id);
            if (num > 0) {
                restResult = new RestResult("删除成功", null, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("删除失败", null, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("删除通知---------->", e);
        }
        return restResult;
    }

    /**
     * 更新通知
     *
     * @param notice
     * @return
     */
    @PostMapping("/upNotieById")
    public RestResult upNotieById(@RequestBody Notice notice) {
        RestResult restResult = null;
        int num = noticeService.upNotieById(notice);
        if (num > 0) {
            restResult = new RestResult("更新成功", null, ResultCode.SUCCESS.code());

        } else {
            restResult = new RestResult("更新失败", null, ResultCode.SUCCESS.code());

        }
        return restResult;

    }
}
