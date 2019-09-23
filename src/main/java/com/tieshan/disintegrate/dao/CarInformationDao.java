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
    @Select("select info.id,info.car_no,iden.card_color,info.car_name,iden.vin,iden.engine\n" +
            "        from ts_car_identity iden,ts_car_info info\n" +
            "        where iden.car_info_id=info.id and info.id=#{carId}")
    List<Map<String, Object>> findCarById(@Param("carId") Long carInfoId);

    /**
     * @Description: 车辆信息查询
     * @param: id car_info的主键(车辆编号ID)
     * @return: CarSurvey
     */
    @Select("select * from ts_car_survey where car_info_id=#{carId}")
    List<Map<String, Object>> findCarInfoById(@Param("carId") Long carInfoId);

    /**
     * @Description: 查询预处理 照片
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return:
     */
    @Select("select pic.file_name,pic.file_url,pic.create_time,pic.operator from ts_car_pic pic " +
            "where pic.car_info_id=#{carId} and pic.first_type='pre_pic'")
    List<Map<String, Object>> findCarPrePicById(@Param("carId") Long carInfoId);

    /**
     * @Description: 查询拓号 照片
     * @param: carInfoId car_info的主键(车辆编号ID)
     * @return:
     */
    @Select("select pic.file_name,pic.file_url,pic.create_time,pic.operator from ts_car_pic pic " +
            "where pic.car_info_id=#{carId} and pic.first_type='tuo_pic'")
    List<Map<String, Object>> findCarTuoPicById(@Param("carId") Long carInfoId);



}
