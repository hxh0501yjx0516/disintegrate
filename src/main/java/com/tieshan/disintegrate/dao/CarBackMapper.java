package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarBack;
import com.tieshan.disintegrate.vo.CarBackListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 车辆退车表
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarBackMapper {

    CarBack selectOneByMap(Map<String, Object> map);

    List<CarBack> selectListByMap(Map<String, Object> map);

    List<CarBackListVo> selectVoListByMap(Map<String, Object> map);

    void insertCarBack(CarBack CarBack);

    void insertBatchCarBack(Map<String, Object> map);

    void updateCarBack(CarBack CarBack);

    void updateBatchCarBack(Map<String, Object> map);

    void deleteCarBackByMap(Map<String, Object> map);

    void batchDeleteCarBackByMap(Map<String, Object> map);
}
