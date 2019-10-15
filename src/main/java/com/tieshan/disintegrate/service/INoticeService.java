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
    List<Map<String, Object>> getTop(HttpServletRequest request);

    int insertNotice(Notice notice, HttpServletRequest request);

    List<Map<String, Object>> selNotice(String type, String device_type,HttpServletRequest request);

    Map<String, Object> selNoticeById(String id);


}
