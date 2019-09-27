package com.tieshan.disintegrate.service;

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

}
