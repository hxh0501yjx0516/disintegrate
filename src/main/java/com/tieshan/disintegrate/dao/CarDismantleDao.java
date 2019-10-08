package com.tieshan.disintegrate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @description: 拆解管理模块持久层
 * @author: Leavonson
 * @date: Created in 2019/10/8 10:48
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarDismantleDao {

    /**查询拆解管理模块车辆信息*/
    @Select({"<script>" +
            "SELECT id,car_code,car_no,car_name,contacts,contacts_phone " +
            "FROM ts_car_info " +
            "WHERE disintegrate_plant_id=#{companyId}" +
            "<if test=\"findMsg!=null and findMsg!=''\">\n" +
            "and concat(car_code,car_no,car_name,contacts,contacts_phone)\n" +
            "like concat(\"%\",#{findMsg},\"%\")\n" +
            "</if>" +
            "</script>"})
    List<Map<String,Object>> findCarInfo(@Param("findMsg")String findMsg,@Param("companyId") Long companyId);

    /**查询拆解管理模块车辆拆解前照片*/
    @Select("select file_url from ts_car_pic where car_info_id=#{carInfoId} and disintegrate_plant_id=#{companyId} and first_type='pre_pic'")
    List<Map<String,Object>> findPrePic(@Param("carInfoId") Long carInfoId,@Param("companyId") Long companyId);

    /**查询拆解管理模块车辆拆解后照片*/
    @Select("select file_url from ts_car_pic where car_info_id=#{carInfoId} and disintegrate_plant_id=#{companyId} and first_type='break_pic'")
    List<Map<String,Object>> findBreakPic(@Param("carInfoId") Long carInfoId,@Param("companyId") Long companyId);
}
