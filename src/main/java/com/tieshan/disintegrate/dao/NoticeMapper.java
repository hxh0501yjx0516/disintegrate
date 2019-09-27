package com.tieshan.disintegrate.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/27 10:45
 * @version: 1.0
 * @modified By:
 */
public interface NoticeMapper {
    Map<String, Object> getTop(@Param("company_id") String company_id);
}
