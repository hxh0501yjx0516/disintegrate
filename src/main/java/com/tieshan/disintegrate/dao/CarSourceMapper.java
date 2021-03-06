package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description:车源信息表
 *
 * @author ningfeng
 * @date 2019-09-06 10:54:01
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarSourceMapper {

    void insertCarSource(CarSource carSource);

    List<CarSource> selectListByMap(Map map);

    CarSource selectOneByMap(Map map);

    void insertBank(Bank bank);

    List<Map<String,Object>> selectCarSourceList(@Param(value = "disintegratePlantId") Long disintegratePlantId,
                                                 @Param(value = "sourceType") String sourceType,
                                                 @Param(value = "findMsg") String findMsg);

    List<String> selectProcessingTypeOrProceduresType(String firstName);

    void addCar(CarInfo carInfo);

    CarSource selectCarSource(@Param(value = "id") Long id,
                              @Param(value = "disintegratePlantId") Long disintegratePlantId);

    List<Map<String, Object>> selectCarInfoList(@Param(value = "carSource") Long carSource,
                                                @Param(value = "disintegratePlantId") Long disintegratePlantId);

    Map<String, Object> selectCarInfo(@Param(value = "carCode") String carCode,
                          @Param(value = "disintegratePlantId") Long disintegratePlantId);

    void editCar(CarInfo carInfo);

    void deleteCarInfoById(@Param(value = "id") Long id,
                           @Param(value = "disintegratePlantId") Long disintegratePlantId);

    CarSource selectCarSourceById(@Param(value = "id") Long id,
                                  @Param(value = "disintegratePlantId") Long disintegratePlantId);

    void editCarSource(CarSource carSource);

    Bank selectBankById(Long bankId);

    void updateBank(Bank bank);

    void deleteCarSourceById(@Param(value = "id") Long id,
                             @Param(value = "disintegratePlantId") Long disintegratePlantId);

    List<Map<String, Object>> selectCarSourceListApp(@Param(value = "disintegratePlantId") Long disintegratePlantId,
                                                     @Param(value = "userId") Long id,
                                                     @Param(value = "findMsg") String findMsg);

//    List<Long> selectCarInfoIdList(Long disintegratePlantId);

//    List<Map<String, Object>> selectCarInfoListByIds(@Param(value = "id") Long id,@Param(value = "disintegratePlantId") Long disintegratePlantId);

    void insertCarEnter(CarEnter carEnter);

    List<Map<String, Object>> selectCarInfoListApp(@Param(value = "id") Long id,
                                                   @Param(value = "disintegratePlantId") Long disintegratePlantId,
                                                   @Param(value = "states") String state,
                                                   @Param(value = "findMsg") String findMsg);

    Map<String, Object> selectCarInfoByCarNo(@Param(value = "carNo") String carNo,
                                             @Param(value = "disintegratePlantId") Long disintegratePlantId);


    void insertCarSurveyPart(CarSurvey carSurvey);

//    void insertCarPic(@Param(value = "id") Long id,
//                      @Param(value = "carInfoId") Long carInfoId,
//                      @Param(value = "picUrl") String picUrl);

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

    Map<String, Object> selectCarCode(@Param(value = "disintegratePlantId") Long disintegratePlantId,
                         @Param(value = "codeFront") String codeFront,
                         @Param(value = "codeIn") String codeIn);

    List<Map<String, Object>> selectCarInfoListByIsVerify(@Param(value = "userId") Long userId,
                                                          @Param(value = "loginName") String loginName,
                                                          @Param(value = "disintegratePlantId") Long disintegratePlantId,
                                                          @Param(value = "findMsg") String findMsg);

    int selectCarInfoCountByCarNo(@Param(value = "carNo") String carNo,
                                       @Param(value = "disintegratePlantId") Long disintegratePlantId);

    int selectCarInfoNumByCarNo(@Param(value = "carNo") String carNo,
                                @Param(value = "disintegratePlantId") Long disintegratePlantId);

    List<Map<String, Object>> selectCarInfoCompanyList(@Param(value = "findMsg") String findMsg,
                                                          @Param(value = "status") String status,
                                                          @Param(value = "disintegratePlantId") Long disintegratePlantId);

    int updateDismantleWay(@Param(value = "carInfoId") Long carInfoId,
                           @Param(value = "dismantlingWay") Integer dismantleWay,
                           @Param(value = "disintegratePlantId") Long disintegratePlantId);

    List<Map<String, Object>> selectHomePage(@Param(value = "state") String state,
                                             @Param(value = "disintegratePlantId") Long disintegratePlantId);



    void insertCarProcessing(CarProcessing carProcessing);



    void insertCarIdentity(CarIdentity carIdentity);

//    Map<String, Object> selectCarInfo(@Param(value = "carCode") String carCode,
//                                      @Param(value = "disintegratePlantId") Long disintegratePlantId);

//    List<String> selectPrePic(@Param(value = "carCode") String carCode,
//                              @Param(value = "disintegratePlantId") Long disintegratePlantId);
//
//    List<String> selectDestructivePic(@Param(value = "carCode") String carCode,
//                                      @Param(value = "disintegratePlantId") Long disintegratePlantId);
//
//    List<String> selectProPicList(@Param(value = "carCode") String carCode,
//                                  @Param(value = "disintegratePlantId") Long disintegratePlantId);
//
//    List<String> selectTuoPicList(@Param(value = "carCode") String carCode,
//                                  @Param(value = "disintegratePlantId") Long disintegratePlantId);

    List<Map<String, Object>> selectPicList(@Param(value = "carInfoId") Long carInfoId,
                               @Param(value = "disintegratePlantId") Long disintegratePlantId,
                               @Param(value = "firstType") String firstType);

    List<Map<String, Object>> selectCarInfoCount(@Param(value = "disintegratePlantId") Long disintegratePlantId);

    void insertCarSalvage(CarSalvage carSalvage);

    List<Map<String, Object>> selectCarInfoCountAPP(@Param(value = "disintegratePlantId") Long disintegratePlantId,
                                                    @Param(value = "userId") Long userId);

    CarInfo selectCarInfoById(@Param(value = "id") Long id,
                              @Param(value = "disintegratePlantId") Long disintegratePlantId);

//    Integer selectSoftPrintCount(@Param(value = "disintegratePlantId") Long disintegratePlantId);

//    Integer selectDismantlingWayCount(@Param(value = "disintegratePlantId") Long disintegratePlantId);
//
//    Integer selectDismantleCount(@Param(value = "disintegratePlantId") Long disintegratePlantId);
//
//    Integer selectExceptionCount(@Param(value = "disintegratePlantId") Long disintegratePlantId);


//    int selectCarInfoCountByCarSourceId(Long id);
}
