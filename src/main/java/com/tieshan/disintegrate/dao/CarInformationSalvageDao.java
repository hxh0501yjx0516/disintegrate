package com.tieshan.disintegrate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @description: 车辆查询页面-残值信息数据查询
 * @author: Leavonson
 * @date: Created in 2019/9/20 13:52
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarInformationSalvageDao {
    /**
     *@Description: 残值信息查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select salvage,is_get_salvage,operator,get_salvage_time from ts_car_salvage where car_info_id=#{carInfoId}")
    List<Map<String,Object>> findCarBreakById(@Param("carInfoId") Long carInfoId);

    /**
     *@Description: 残值处理日志查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select create_time,operator,remark from ts_car_procedure_log where car_info_id=#{carInfoId} and type=7")
    List<Map<String,Object>> findCarBreakLogById(@Param("carInfoId") Long carInfoId);


}
