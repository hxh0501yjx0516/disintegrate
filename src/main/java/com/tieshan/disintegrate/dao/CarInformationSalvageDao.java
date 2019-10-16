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
    @Select("select IFNULL( sal.salvage, '' ) AS salvage," +
            "IFNULL( sal.is_get_salvage, '' ) AS is_get_salvage," +
            "IFNULL( sal.operator, '' ) AS operator," +
            "IFNULL( sal.get_salvage_time, '' ) AS get_salvage_time " +
            "from ts_car_salvage AS sal join ts_car_info AS info on sal.car_info_id=info.id " +
            "where sal.car_info_id=#{carInfoId} " +
            "and is_get_salvage=1 " +
            "and info.disintegrate_plant_id=#{companyId}")
    List<Map<String,Object>> findCarBreakById(@Param("carInfoId") Long carInfoId,@Param("companyId")Long companyId);

    /**
     *@Description: 残值处理日志查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select IFNULL( pro.create_time, '' ) AS create_time," +
            "IFNULL( sal.salvage, '' ) AS salvage," +
            "IFNULL( pro.operator, '' ) AS operator," +
            "IFNULL( pro.remark, '' ) AS remark " +
            "from ts_car_salvage sal join ts_car_procedure_log pro on sal.car_info_id=pro.car_info_id " +
            "where pro.car_info_id=#{carInfoId} and pro.type=7 and pro.disintegrate_plant_id=#{companyId}")
    List<Map<String,Object>> findCarBreakLogById(@Param("carInfoId") Long carInfoId,@Param("companyId")Long companyId);


}
