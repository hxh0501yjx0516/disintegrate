package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.vo.*;
import org.apache.ibatis.annotations.Mapper;

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

    void updateCarInfo(CarInfo CarInfo);

    List<AppCarVerificationVo> selectAppVerificationList(Map<String, Object> map);

    List<AppCarQueryVo> selectAppQueryList(Map<String, Object> map);

    CarCustomerInfoVo selectCarCustomerInfo(Map<String, Object> map);

    WebCarCustomerInfoVo selectWebCarCustomerInfo(Map<String, Object> map);

    List<CarProcedureIssueVo> selectProcedureIssueVoList(Map<String, Object> map);

    List<CarProcedureListVo> selectProcedureVoList(Map<String, Object> map);

}
