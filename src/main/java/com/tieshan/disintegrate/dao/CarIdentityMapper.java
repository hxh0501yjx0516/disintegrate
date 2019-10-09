package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarIdentity;
import com.tieshan.disintegrate.vo.ShangWeiDataVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 车辆身份表
 *
 * @author ren lei
 * @date 2019-09-18 17:01:47
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarIdentityMapper {

    CarIdentity selectOneByMap(Map<String, Object> map);

    List<CarIdentity> selectListByMap(Map<String, Object> map);

    ShangWeiDataVo selectShangWeiDataByMap(Map<String, Object> map);

    void insertCarIdentity(CarIdentity CarIdentity);

    void insertBatchCarIdentity(Map<String, Object> map);

    void updateCarIdentity(CarIdentity CarIdentity);

    void updateBatchCarIdentity(Map<String, Object> map);

    void deleteCarIdentityByMap(Map<String, Object> map);

    void batchDeleteCarIdentityByMap(Map<String, Object> map);
}
