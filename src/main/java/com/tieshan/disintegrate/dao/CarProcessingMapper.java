package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarProcessing;
import com.tieshan.disintegrate.vo.CarVerifyOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 手续管理
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarProcessingMapper {

    CarProcessing selectOneByMap(Map<String, Object> map);

    List<CarProcessing> selectListByMap(Map<String, Object> map);

    void insertCarProcessing(CarProcessing CarProcessing);

    void insertBatchCarProcessing(Map<String, Object> map);

    void updateCarProcessing(CarProcessing CarProcessing);

    void updateQueryCarProcessing(CarProcessing CarProcessing);

    void updateVerifyCarProcessing(CarProcessing CarProcessing);

    void updateBatchCarProcessing(Map<String, Object> map);

    void deleteCarProcessingByMap(Map<String, Object> map);

    void batchDeleteCarProcessingByMap(Map<String, Object> map);

    List<CarVerifyOrderVo> selectCarVerifyOrderVoList(Map<String, Object> map);
}
