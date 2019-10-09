package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.vo.CarSalvageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}
