package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.pojo.SysUser;

import javax.persistence.criteria.CriteriaBuilder;
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

    void addCarSource(CarSource carSource, HttpServletRequest request);

    List<Map<String, Object>>  findUserNameList();

    List<String> findBankNameList();

    List<Map<String, Object>> selectCarSourceList(String sourceType, String findMsg, Integer page, Integer pageSize, HttpServletRequest request);

    Map<String, List<String>> selectProcessingTypeAndProceduresType();

    void addCar(CarInfo carInfo,Long carSource, HttpServletRequest request);

    CarSource selectCarSource(Long id, HttpServletRequest request);

    List<Map<String, Object>> selectCarInfoList(Long id, HttpServletRequest request, Integer isVerify);

    CarInfo selectCarInfoById(Long id, HttpServletRequest request);

    void editCar(CarInfo carInfo);

    void deleteCarInfoById(Long id, HttpServletRequest request);

    CarSource selectCarSourceById(Long id, HttpServletRequest request);

    void editCarSource(CarSource carSource);

    void deleteCarSource(Long id, HttpServletRequest request);

//    List<Map<String, Object>> selectCarSourceListApp(HttpServletRequest request);

//    void addCarSourceApp(CarSource carSource, HttpServletRequest request);
}
