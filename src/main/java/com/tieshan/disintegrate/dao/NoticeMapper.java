package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/27 10:45
 * @version: 1.0
 * @modified By:
 */
public interface NoticeMapper {
    List<Map<String, Object>> getTop(@Param("company_id") String company_id);

    int insertNotice(@Param("notice") Notice notice);

    List<Map<String, Object>> selNotice(@Param("type") String type,
                                        @Param("device_type") String device_type
            , @Param("disintegrate_plant_id") String disintegrate_plant_id);

    Map<String, Object> selNoticeById(@Param("id") String id);
}
