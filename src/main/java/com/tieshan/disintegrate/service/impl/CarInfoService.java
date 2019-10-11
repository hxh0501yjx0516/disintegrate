package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.dao.CarInfoMapper;
import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.service.ICarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/17 19:30
 * @modified By：
 * @version: 1.0.0
 */
@Service
public class CarInfoService implements ICarInfoService {

    @Autowired
    private CarInfoMapper carInfoMapper;

    @Override
    public void add(CarInfo carInfo) {
        carInfoMapper.insertCarInfo(carInfo);
    }

    @Override
    public void addBatch(List<CarInfo> carInfos) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", carInfos);
        carInfoMapper.insertBatchCarInfo(map);
    }

    @Override
    public void update(CarInfo carInfo) {
        carInfoMapper.updateCarInfo(carInfo);
    }

    @Override
    public void updateBatch(Map<String, Object> params) {
        carInfoMapper.updateBatchCarInfo(params);
    }

    @Override
    public PageInfo<CarInfo> queryPage(Map<String, Object> params) {
        PageHelper.startPage(
                StringUtils.isEmpty(params.get("page")) ? 1 : Integer.parseInt(String.valueOf(params.get("page"))),
                StringUtils.isEmpty(params.get("pageSize")) ? 10 : Integer.parseInt(String.valueOf(params.get("pageSize"))));
        List<CarInfo> carInfos = carInfoMapper.selectListByMap(params);
        PageInfo<CarInfo> pageInfo = new PageInfo<>(carInfos);
        return pageInfo;
    }

    @Override
    public CarInfo queryOne(Map<String, Object> map) {
        return carInfoMapper.selectOneByMap(map);
    }

    @Override
    public void delete(Map<String, Object> map) {
        carInfoMapper.deleteCarInfoByMap(map);
    }

    @Override
    public void deleteBatch(Map<String, Object> map) {
        carInfoMapper.batchDeleteCarInfoByMap(map);
    }
}