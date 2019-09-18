package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarProcedurelog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 车辆手续日志表
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarProcedurelogMapper {

    CarProcedurelog selectOneByMap(Map<String, Object> map);

    List<CarProcedurelog> selectListByMap(Map<String, Object> map);

    void insertCarProcedurelog(CarProcedurelog CarProcedurelog);

    void insertBatchCarProcedurelog(Map<String, Object> map);

    void updateCarProcedurelog(CarProcedurelog CarProcedurelog);

    void updateBatchCarProcedurelog(Map<String, Object> map);

    void deleteCarProcedurelogByMap(Map<String, Object> map);

    void batchDeleteCarProcedurelogByMap(Map<String, Object> map);
}
