package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarSurvey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 车辆初检表
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarSurveyMapper {

    CarSurvey selectOneByMap(Map<String, Object> map);

    List<CarSurvey> selectListByMap(Map<String, Object> map);

    void insertCarSurvey(CarSurvey CarSurvey);

    void insertBatchCarSurvey(Map<String, Object> map);

    void updateCarSurvey(CarSurvey CarSurvey);

    void updateBatchCarSurvey(Map<String, Object> map);

    void deleteCarSurveyByMap(Map<String, Object> map);

    void batchDeleteCarSurveyByMap(Map<String, Object> map);
}
