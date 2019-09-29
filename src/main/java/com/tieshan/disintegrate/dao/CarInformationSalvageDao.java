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
    @Select("select sal.salvage,sal.is_get_salvage,sal.operator,sal.get_salvage_time " +
            "from ts_car_salvage AS sal join ts_car_info AS info on sal.car_info_id=info.id " +
            "where sal.car_info_id=#{carInfoId} " +
            "and info.disintegrate_plant_id=#{companyId}")
    List<Map<String,Object>> findCarBreakById(@Param("carInfoId") Long carInfoId,@Param("companyId")Long companyId);

    /**
     *@Description: 残值处理日志查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select create_time,operator,remark from ts_car_procedure_log where car_info_id=#{carInfoId} and type=7 and disintegrate_plant_id=#{companyId}")
    List<Map<String,Object>> findCarBreakLogById(@Param("carInfoId") Long carInfoId,@Param("companyId")Long companyId);


}
