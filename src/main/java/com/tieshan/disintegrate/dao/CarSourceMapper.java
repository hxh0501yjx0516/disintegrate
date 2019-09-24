package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.Bank;
import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.pojo.CarSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description:车源信息表
 *
 * @author ren lei
 * @date 2019-09-06 10:54:01
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarSourceMapper {

    void insertCarSource(CarSource carSource);

    void updateCarSource(CarSource carSource);

    List<CarSource> selectListByMap(Map map);

    CarSource selectOneByMap(Map map);

    void deleteByMap(Map map);

    void insertBank(Bank bank);

    List<Map<String,Object>> selectCarSourceList(Long disintegratePlantId, String sourceType, String findMsg);

    List<String> selectProcessingTypeOrProceduresType(String firstName);

    void addCar(CarInfo carInfo);

    CarSource selectCarSource(Long id, Long disintegratePlantId);

    List<Map<String, Object>> selectCarInfoList(@Param(value = "carSource") Long carSource,@Param(value = "disintegratePlantId") Long disintegratePlantId);

    CarInfo selectCarInfoById(Long id, Long disintegratePlantId);

    void editCar(CarInfo carInfo);

    void deleteCarInfoById(Long id, Long disintegratePlantId);

    CarSource selectCarSourceById(Long id, Long disintegratePlantId);

    void editCarSource(CarSource carSource);

    Bank selectBankById(Long bankId);

    void updateBank(Bank bank);

    void deleteCarSourceById(Long id, Long disintegratePlantId);

    List<Map<String, Object>> selectCarSourceListApp(@Param(value = "disintegratePlantId") Long disintegratePlantId,
                                                     @Param(value = "userId") Long id,
                                                     @Param(value = "loginName") String login_name);

    List<Long> selectCarInfoIdList(Integer isVerify, Long disintegratePlantId);

    List<Map<String, Object>> selectCarInfoListByIds(Long id, List<Long> carInfoIds, Long disintegratePlantId);
}
