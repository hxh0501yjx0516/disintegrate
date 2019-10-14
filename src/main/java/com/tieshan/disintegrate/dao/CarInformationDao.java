package com.tieshan.disintegrate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @description: 车辆查询页面-车辆信息信息数据查询
 * @author: Leavonson
 * @date: Created in 2019/9/18 16:27
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarInformationDao {

    /**
     * @Description: 车辆查询页面头数据
     * @param: id car_info的主键(车辆编号ID)
     * @return: CarsQueryInfo
     */
    @Select("select iden.car_info_id,info.car_code,info.car_no,sur.card_color,info.car_name,iden.vin,iden.engine " +
            "from ts_car_identity AS iden join ts_car_info AS info on iden.car_info_id=info.id " +
            "join ts_car_survey AS sur on sur.car_info_id=info.id " +
            "and iden.disintegrate_plant_id=#{companyId} " +
            "where info.id=#{carInfoId}")
    List<Map<String, Object>> findCarById(@Param("carInfoId") Long carInfoId,@Param("companyId")Long companyId);

    /**
     * @Description: 车辆信息查询
     * @param: id car_info的主键(车辆编号ID)
     * @return: CarSurvey
     */
    @Select("select * from ts_car_survey where car_info_id=#{carId} and disintegrate_plant_id=#{companyId}")
    List<Map<String, Object>> findCarInfoById(@Param("carId") Long carInfoId,@Param("companyId")Long companyId);

    /**
     * @Description: 查询预处理 照片
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return:
     */
    @Select("select pic.file_name,pic.file_url,pic.create_time,pic.operator from ts_car_pic pic " +
            "where pic.car_info_id=#{carId} and pic.first_type='pre_pic' and pic.disintegrate_plant_id=#{companyId}")
    List<Map<String, Object>> findCarPrePicById(@Param("carId") Long carInfoId,@Param("companyId")Long companyId);

    /**
     * @Description: 查询拓号 照片
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return:
     */
    @Select("select pic.file_name,pic.file_url,pic.create_time,pic.operator from ts_car_pic pic " +
            "where pic.car_info_id=#{carId} and pic.first_type='tuo_pic' and pic.disintegrate_plant_id=#{companyId}")
    List<Map<String, Object>> findCarTuoPicById(@Param("carId") Long carInfoId,@Param("companyId")Long companyId);



}
