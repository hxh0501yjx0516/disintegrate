package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.*;
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
                                                     @Param(value = "loginName") String loginName,
                                                     @Param(value = "findMsg") String findMsg);

    List<Long> selectCarInfoIdList(Long disintegratePlantId);

//    List<Map<String, Object>> selectCarInfoListByIds(@Param(value = "id") Long id,@Param(value = "disintegratePlantId") Long disintegratePlantId);

    void insertCarEnter(CarEnter carEnter);

    List<Map<String, Object>> selectCarInfoListApp(@Param(value = "id") Long id,
                                                   @Param(value = "disintegratePlantId") Long disintegratePlantId,
                                                   @Param(value = "states") String state,
                                                   @Param(value = "findMsg") String findMsg);

    Long selectCarInfoByCarNo(String carNo);


    void insertCarSurveyPart(CarSurvey carSurvey);

    void insertCarPic(@Param(value = "id") Long id,
                      @Param(value = "carInfoId") Long carInfoId,
                      @Param(value = "picUrl") String picUrl);

    Map<String, Object> selectCarInfoReason(@Param(value = "id") Long id,
                                            @Param(value = "disintegratePlantId") Long disintegratePlantId);

    List<Map<String, Object>> selectCarInfoByIsInitialSurvey(@Param(value = "disintegratePlantId") Long disintegratePlantId,
                                                        @Param(value = "findMsg") String findMsg);

    Map<String, Object> selectCarInfoByIdAndCarEnter(@Param(value = "id") Long id,
                                                      @Param(value = "disintegratePlantId") Long disintegratePlantId);

    CarSurvey selectCarSurveyByCarInfoId(@Param(value = "id") Long id,
                                         @Param(value = "disintegratePlantId") Long disintegratePlantId);

    void editCarSurvey(CarSurvey carSurvey);

    void updateCarEnterIsApproach(CarEnter carEnter);

    void updateCarEnterIsInitialSurvey(CarEnter carEnter);

    List<Map<String, Object>> selectCarInfoListByDisintegratePlantId(@Param(value = "disintegratePlantId") Long disintegratePlantId,
                                                                     @Param(value = "findMsg") String findMsg);

    List<Map<String, Object>> selectLocationListByPid(@Param(value = "pid") Long pid,
                                         @Param(value = "disintegratePlantId") Long disintegratePlantId);

    void editCarInfoLocation(CarInfo carInfo);

    Map<String, Object> selectCodeAfter(Long disintegratePlantId, String codeFront, String codeIn);

    List<Map<String, Object>> selectCarInfoListByIsVerify(@Param(value = "userId") Long userId,
                                                          @Param(value = "loginName") String loginName,
                                                          @Param(value = "disintegratePlantId") Long disintegratePlantId,
                                                          @Param(value = "findMsg") String findMsg);

//    int selectCarInfoCountByCarSourceId(Long id);
}
