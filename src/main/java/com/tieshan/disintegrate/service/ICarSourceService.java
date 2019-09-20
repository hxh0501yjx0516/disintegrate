package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.pojo.CarSource;

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

    void add(CarSource carSource, HttpServletRequest request);

    List<Map<String, Object>>  findUserNameList();

    List<String> findBankNameList();

    List<Map<String, Object>> selectCarSourceList(String sourceType, int page, int pageSize);

    Map<String, List<String>> selectProcessingTypeAndProceduresType();

    void addCar(CarInfo carInfo,Long id, HttpServletRequest request);

    CarSource selectCarSource(Long id);
}
