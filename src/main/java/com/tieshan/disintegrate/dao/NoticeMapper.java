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
    /**
     * 获取头部信息app
     *
     * @param company_id
     * @return
     */
    List<Map<String, Object>> getTop(@Param("company_id") String company_id);

    /**
     * 插入通知pc
     *
     * @param notice
     * @return
     */
    int insertNotice(@Param("notice") Notice notice);

    /**
     * 通知列表app
     *
     * @param type
     * @param device_type
     * @param disintegrate_plant_id
     * @return
     */
    List<Map<String, Object>> selNotice(@Param("type") String type,
                                        @Param("device_type") String device_type
            , @Param("disintegrate_plant_id") String disintegrate_plant_id);

    /**
     * 通知列表pc
     *
     * @param disintegrate_plant_id
     * @return
     */
    List<Map<String, Object>> selNoticePC(
            @Param("disintegrate_plant_id") String disintegrate_plant_id);

    /**
     * 查看通知 app
     *
     * @param id
     * @return
     */
    Map<String, Object> selNoticeById(@Param("id") String id);

    /**
     * 查询通知 pc
     *
     * @param id
     * @return
     */
    Map<String, Object> selNoticeByIdPC(@Param("id") String id);

    /**
     * 获取通知消息
     *
     * @param id
     * @return
     */
    Notice pushNotice(@Param("id") String id);

    /**
     * 删除通知 pc
     *
     * @param id
     * @return
     */
    int delNoticeById(@Param("id") String id);

    /**
     * 编辑通知 pc
     *
     * @param notice
     * @return
     */
    int upNotieById(@Param("notice") Notice notice);


    /**
     * 推送消息 app
     *
     * @param id
     * @return
     */
    int push(@Param("id") String id);


}
