package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarPic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 车辆相关图片
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarPicMapper {

    CarPic selectOneByMap(Map<String, Object> map);

    List<CarPic> selectListByMap(Map<String, Object> map);

    void insertCarPic(CarPic CarPic);

    void insertBatchCarPic(Map<String, Object> map);

    void updateCarPic(CarPic CarPic);

    void updateBatchCarPic(Map<String, Object> map);

    void deleteCarPicByMap(Map<String, Object> map);

    void batchDeleteCarPicByMap(Map<String, Object> map);
    void batchDeleteCarPic(Long disintegratePlantId,Long carInfoId);
    void batchDeleteTuoPic(Long disintegratePlantId,Long carInfoId);
    void batchDeleteBreakPic(Long disintegratePlantId,Long carInfoId);
}
