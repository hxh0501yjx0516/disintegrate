package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarProcedureLog;
import com.tieshan.disintegrate.vo.CarCustomerListVo;
import com.tieshan.disintegrate.vo.RecordHandleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 车辆手续日志表
 *
 * @author ren lei
 * @date 2019-09-19 17:44:04
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarProcedureLogMapper {

    CarProcedureLog selectOneByMap(Map<String, Object> map);

    List<CarProcedureLog> selectListByMap(Map<String, Object> map);

    CarProcedureLog selectAppOneByMap(Map<String, Object> map);

    void insertCarProcedureLog(CarProcedureLog CarProcedureLog);

    void insertBatchCarProcedureLog(Map<String, Object> map);

    void updateCarProcedureLog(CarProcedureLog CarProcedureLog);

    void updateBatchCarProcedureLog(Map<String, Object> map);

    void deleteCarProcedureLogByMap(Map<String, Object> map);

    void batchDeleteCarProcedureLogByMap(Map<String, Object> map);

    List<RecordHandleVo> selectQueryVo(Long carInfoId);

    List<RecordHandleVo> selectVerifyVo(Long carInfoId);

    CarProcedureLog selectCustomerVo(Long procedureLogId);

    List<CarCustomerListVo> selectCustomerVoList(Map<String, Object> map);

}
