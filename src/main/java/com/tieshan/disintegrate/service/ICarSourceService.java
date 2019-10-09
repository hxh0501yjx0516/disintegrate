package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.pojo.CarSurvey;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: renlei
 * @date: 2019/9/6 11:41
 * @version: 1.0
 * @modified By:
 */
public interface ICarSourceService {

    void addCarSource(Map<String, Object> params, HttpServletRequest request);

    List<Map<String, Object>>  findUserNameList();

    List<String> findBankNameList();

    List<Map<String, Object>> selectCarSourceList(String sourceType, String findMsg, Integer page, Integer pageSize, HttpServletRequest request);

    Map<String, List<String>> selectProcessingTypeAndProceduresType();

    void addCar(CarInfo carInfo, HttpServletRequest request);

    CarSource selectCarSource(Long id, HttpServletRequest request);

    List<Map<String, Object>> selectCarInfoList(Long id, HttpServletRequest request);

    CarInfo selectCarInfoById(Long id, HttpServletRequest request);

    void editCar(CarInfo carInfo);

    void deleteCarInfoById(Long id, HttpServletRequest request);

    CarSource selectCarSourceById(Long id, HttpServletRequest request);

    void editCarSource(Map<String, Object> params);

    void deleteCarSource(Long id, HttpServletRequest request);

    List<Map<String, Object>> selectCarInfoListApp(Long id, HttpServletRequest request, String state, String findMsg, Integer page, Integer pageSize);

    int  insertCarSurveyPart(String carNo, String selfWeight, String cardColor, HttpServletRequest request);

    Map<String, Object> selectCarInfoReason(Long id, HttpServletRequest request);

    List<Map<String, Object>> selectCarInfoByIsInitialSurvey(Integer page, Integer pageSize, String findMsg, HttpServletRequest request);

    Map<String, Object> selectCarInfoByIdAndCarEnter(Long id, HttpServletRequest request);

    CarSurvey selectCarSurveyByCarInfoId(Long id, HttpServletRequest request);

    void editCarSurvey(CarSurvey carSurvey, HttpServletRequest request);

    void editCarSurveyComplete(CarSurvey carSurvey, HttpServletRequest request);

    List<Map<String, Object>> selectCarInfoListByDisintegratePlantId(Integer page, Integer pageSize, String findMsg, HttpServletRequest request);

    List<Map<String, Object>> selectLocationListByPid(Long id, HttpServletRequest request);

    void editCarInfoLocation(CarInfo carInfo);

    List<Map<String, Object>> selectCarInfoListByIsVerify(Integer page, Integer pageSize, HttpServletRequest request, String findMsg);

    int selectCarInfoNumByCarNo(String carNo, HttpServletRequest request);

    int selectCarInfoCountByCarNo(String carNo, HttpServletRequest request);

    List<Map<String, Object>> selectCarInfoIsApproachList(HttpServletRequest request, Integer page, Integer pageSize, String findMsg, String status);

//    List<Map<String, Object>> selectCarSourceListApp(HttpServletRequest request);

//    void addCarSourceApp(CarSource carSource, HttpServletRequest request);
}
