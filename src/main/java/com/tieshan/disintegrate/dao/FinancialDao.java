package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.vo.CarSalvageVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @description: 财务管理数据持久层
 * @author: Leavonson
 * @date: Created in 2019/10/8 18:11
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface FinancialDao {

    /**财务管理-价格导入模块查询*/
    @Select({"<script>" +
            "SELECT info.id,info.car_code carCode,info.car_no carNo,info.car_name carName,info.contacts,info.contacts_phone contactsPhone,sal.salvage,iden.owner,iden.phone\n" +
            "FROM ts_car_info AS info \n" +
            "join ts_car_salvage AS sal ON info.id=sal.car_info_id join ts_car_identity AS iden ON info.id=iden.car_info_id\n" +
            "WHERE info.disintegrate_plant_id=1\n" +
            "\t\t\t\t\t\t" +
            "<if test=\"findMsg!=null and findMsg!=''\">\n" +
            "and concat(info.car_code,info.car_no,info.car_name,info.contacts,info.contacts_phone)\n" +
            "like concat(\"%\",#{findMsg},\"%\")\n" +
            "</if>" +
            "</script>"})
    List<CarSalvageVo> findCarInfoAndSalvage(@Param("findMsg")String findMsg, @Param("companyId") Long companyId);

    /**财务管理-价格导入-手动修改残值*/
    @Update("UPDATE ts_car_salvage " +
            "SET salvage=#{salvage},modify_time=now(),modify_user_id=#{modifyUserId},modify_user=#{modifyUser} " +
            "WHERE car_info_id=#{carInfoId} AND disintegrate_plant_id=#{companyId}")
    int updateCarSalvage(@Param("carInfoId")Long carInfoId,
                         @Param("salvage") String salvage,
                         @Param("modifyUserId") Long modifyUserId,
                         @Param("modifyUser") String modifyUser,
                         @Param("companyId") Long companyId);

    /**财务管理-残值领取-查询列表*/
    @Select({"<script>" +
            "SELECT info.car_code,info.car_no,info.car_name,info.contacts,info.contacts_phone,iden.vin,sal.salvage,sal.get_salvage_time,sal.operator,sal.is_get_salvage\n" +
            "FROM ts_car_info AS info \n" +
            "JOIN ts_car_identity AS iden ON info.id=iden.car_info_id\n" +
            "JOIN ts_car_salvage AS sal ON info.id=sal.car_info_id\n" +
            "WHERE info.disintegrate_plant_id=#{companyId}\n" +
            "<if test=\"findMsg!=null and findMsg!=''\">\n" +
            "and concat(info.car_code,info.car_no,info.car_name,info.contacts,info.contacts_phone)\n" +
            "like concat(\"%\",#{findMsg},\"%\")\n" +
            "</if>" +
            "</script>"})
    List<Map<String,Object>> findCarInfoGetSalvage(@Param("findMsg")String findMsg, @Param("companyId") Long companyId);

    /**财务管理-残值领取-点击领取界面回显数据*/
    @Select("SELECT info.car_code,info.car_no,iden.ascription,sur.card_color,iden.owner,iden.phone,iden.id_card," +
            "iden.owner_zipcode,iden.owner_address,iden.agend,iden.agend_idcard,info.contacts,info.contacts_phone," +
            "info.contacts_address,info.driv_license,info.regist_license,info.car_name,iden.car_weight,iden.car_kind," +
            "iden.fuel_type,iden.reg_time,iden.issue_time,iden.nature_usege,iden.car_type,iden.vin,iden.engine," +
            "iden.issue_size,iden.displacement,iden.carrying_capacity,sal.salvage\n" +
            "FROM ts_car_info AS info \n" +
            "JOIN ts_car_identity AS iden ON info.id=iden.car_info_id\n" +
            "JOIN ts_car_salvage AS sal ON info.id=sal.car_info_id\n" +
            "JOIN ts_car_survey AS sur ON info.id=sur.car_info_id\n" +
            "WHERE info.id=#{carInfoId}\n" +
            "AND info.disintegrate_plant_id=#{companyId}\n")
    List<Map<String,Object>> findCarSalvageById(@Param("carInfoId")Long carInfoId, @Param("companyId") Long companyId);

    /**财务管理-残值领取-残值领取成功数据入库*/
    @Update("UPDATE ts_car_salvage SET get_way=2,get_salvage_time=now(),remark=#{remark},is_get_salvage=2,operator=#{operator}\n" +
            "WHERE car_info_id=#{carInfoId} AND disintegrate_plant_id=#{companyId}")
    int insertSalvageById();

    /**财务管理-残值领取-残值领取单数据*/
    //@Select("")
    List<Map<String,Object>> findDataSheet(@Param("carInfoId")Long carInfoId, @Param("companyId") Long companyId);



}
