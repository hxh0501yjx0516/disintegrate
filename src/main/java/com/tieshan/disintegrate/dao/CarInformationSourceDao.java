package com.tieshan.disintegrate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @description: 车辆查询页面-车源信息数据查询
 * @author: Leavonson
 * @date: Created in 2019/9/20 13:52
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarInformationSourceDao {

    /**
     *@Description: 车源信息查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select contacts,contacts_phone,car_no,car_name,processing_type,processing_date,procedures_type,driv_license,regist_license," +
            "people_license,businese_license,break_license,exception_license,remarks,contacts_address from ts_car_info where id=#{carInfoId} " +
            "and disintegrate_plant_id=#{companyId}")
    List<Map<String,Object>> findCarSourceById(@Param("carInfoId") Long carInfoId,@Param("companyId")Long companyId);

    /**
     *@Description: 车源信息处理日志查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select create_time,operator,remark from ts_car_procedure_log where car_info_id=#{carInfoId} and type=4 and disintegrate_plant_id=#{companyId}")
    List<Map<String,Object>> findCarSourceLogById(@Param("carInfoId") Long carInfoId,@Param("companyId")Long companyId);

}
