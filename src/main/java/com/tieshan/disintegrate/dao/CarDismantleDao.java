package com.tieshan.disintegrate.dao;

import org.apache.ibatis.annotations.*;

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

    /**更改拆解状态*/
    @Update("update ts_car_processing set is_dismantle=2,dismantle_user_id=#{operatorId},dismantle_time=now() " +
            "where car_info_id=#{carInfoId} and disintegrate_plant_id=#{companyId}")
    void updateDismantle(@Param("operatorId") Long operatorId,@Param("carInfoId") Long carInfoId,@Param("companyId") Long companyId);

    /**拆车-查询打印配件名称*/
    @Select("select filed_name from ts_dictionary where first_type='car_parts'")
    List<Map<String,Object>> findPartsNameList();

    @Insert("INSERT into ts_car_parts(id,car_info_id,disintegrate_plant_id,oe,parts_name,parts_status,print_operator_id,print_operator,print_time) " +
            "VALUES(#{id},#{carInfoId},#{companyId},#{oe},#{partsName},#{partsStatus},#{printOperatorId},#{printOperator},now())")
    int addCarParts(@Param("id")Long id,
                    @Param("carInfoId")Long carInfoId,
                    @Param("companyId")Long companyId,
                    @Param("oe")String oe,
                    @Param("partsName")String partsName,
                    @Param("partsStatus")Integer partsStatus,
                    @Param("printOperatorId")Long printOperatorId,
                    @Param("printOperator")String printOperator);
    /**拆车-查询二级分类列表*/
    @Select("select id,parts_category_name from ts_car_parts_category")
    List<Map<String,Object>> findPartsParentList();
    /**拆车-查询二级分类列表*/
    @Select("select id,parts_name from ts_car_parts_dictionary where parts_category_id=#{parentId}")
    List<Map<String,Object>> findPartsNameListByParentId(@Param("parentId")Long parentId);



}
