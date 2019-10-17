package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.Notice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/27 11:11
 * @version: 1.0
 * @modified By:
 */
public interface INoticeService {
    /**
     * 获取头部 app
     *
     * @param request
     * @return
     */
    List<Map<String, Object>> getTop(HttpServletRequest request);

    /**
     * 推送 app
     *
     * @param id
     * @param request
     * @return
     */
    int push(String id, HttpServletRequest request);

    /**
     * 添加推送
     *
     * @param notice
     * @param request
     * @return
     */
    int insertNotice(Notice notice, HttpServletRequest request);

    /**
     * 查询列表app
     *
     * @param type
     * @param device_type
     * @param request
     * @param page
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> selNotice(String type, String device_type, HttpServletRequest request, int page, int pageSize);

    /**
     * 查询详情app
     *
     * @param id
     * @return
     */
    Map<String, Object> selNoticeById(String id);


    List<Map<String, Object>> selNoticePC(HttpServletRequest request, int page, int pageSize);

    /**
     * 查询通知 pc
     *
     * @param id
     * @return
     */
    Map<String, Object> selNoticeByIdPC(String id);

    /**
     * 删除通知
     *
     * @param id
     * @return
     */
    int delNoticeById(String id);

    /**
     * 更新通知
     *
     * @param notice
     * @return
     */
    int upNotieById(Notice notice);


}
