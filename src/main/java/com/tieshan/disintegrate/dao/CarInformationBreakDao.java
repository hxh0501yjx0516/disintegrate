package com.tieshan.disintegrate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @description: 车辆查询页面-拆解信息数据查询
 * @author: Leavonson
 * @date: Created in 2019/9/20 13:52
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarInformationBreakDao {

    /**
     *@Description: 拆解信息查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select IFNULL( dismantle_way, '' ) AS dismantle_way," +
            "IFNULL( dismantle_time, '' ) AS dismantle_time," +
            "IFNULL( dismantle_user_name, '' ) AS dismantle_user_name " +
            "from ts_car_enter where car_info_id=#{carInfoId} and disintegrate_plant_id=#{companyId}")
    List<Map<String,Object>> findCarBreakById(@Param("carInfoId") Long carInfoId,@Param("companyId")Long companyId);

    /**
     *@Description: 拆解照片查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select IFNULL( pic.file_name, '' ) AS file_name," +
            "IFNULL( pic.file_url, '' ) AS file_url," +
            "IFNULL( pic.create_time, '' ) AS create_time," +
            "IFNULL( pic.operator, '' ) AS operator" +
            " from ts_car_pic pic " +
            "where pic.car_info_id=#{carInfoId} and pic.first_type='break_pic' and disintegrate_plant_id=#{companyId}")
    List<Map<String,Object>> findCarBreakPicById(@Param("carInfoId") Long carInfoId,@Param("companyId")Long companyId);

    /**
     *@Description: 拆解处理日志查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select IFNULL( create_time, '' ) AS create_time," +
            "IFNULL( operator, '' ) AS operator," +
            "IFNULL( remark, '' ) AS remark " +
            "from ts_car_procedure_log where car_info_id=#{carInfoId} and type=6 and disintegrate_plant_id=#{companyId}")
    List<Map<String,Object>> findCarBreakLogById(@Param("carInfoId") Long carInfoId,@Param("companyId")Long companyId);
}
