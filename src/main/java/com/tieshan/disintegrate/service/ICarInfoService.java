package com.tieshan.disintegrate.service;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.pojo.CarInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/17 19:28
 * @modified By：
 * @version: 1.0.0
 */
public interface ICarInfoService {

    void add(CarInfo carInfo);

    void addBatch(List<CarInfo> carInfos);

    void update(CarInfo carInfo);

    void updateBatch(Map<String, Object> params);

    PageInfo<CarInfo> queryPage(Map<String, Object> params);

    CarInfo queryOne(Map<String, Object> map);

    void delete(Map<String, Object> map);

    void deleteBatch(Map<String, Object> map);
}
