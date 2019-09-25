package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 车辆信息表
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarInfoMapper {

    CarInfo selectOneByMap(Map<String, Object> map);

    List<CarInfo> selectListByMap(Map<String, Object> map);

    void insertCarInfo(CarInfo CarInfo);

    void insertBatchCarInfo(Map<String, Object> map);

    void updateCarInfo(CarInfo CarInfo);

    void updateBatchCarInfo(Map<String, Object> map);

    void deleteCarInfoByMap(Map<String, Object> map);

    void batchDeleteCarInfoByMap(Map<String, Object> map);

    List<CarInfo> selectListByProcessing(Map<String, Object> map);
}
