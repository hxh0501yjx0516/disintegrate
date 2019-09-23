package com.tieshan.disintegrate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @description: 车辆查询页面-手续信息数据查询
 * @author: Leavonson
 * @date: Created in 2019/9/20 13:52
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarInformationIdentityDao {

    /**
     *@Description: 手续信息查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select iden.*,info.contacts,info.contacts_phone,info.contacts_address,info.driv_license,info.regist_license " +
            "from ts_car_identity iden,ts_car_info info " +
            "where iden.car_info_id=info.id and info.id=#{carInfoId}")
    List<Map<String,Object>> findCarIdentityById(@Param("carInfoId") Long carInfoId);

    /**
     *@Description: 手续处理周期查询
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return: List
     */
    @Select("select create_time,operator,remark from ts_car_procedure_log where car_info_id=#{carInfoId} and type in(2,3,4)")
    List<Map<String,Object>> findProcedureById(@Param("carInfoId") Long carInfoId);

}
