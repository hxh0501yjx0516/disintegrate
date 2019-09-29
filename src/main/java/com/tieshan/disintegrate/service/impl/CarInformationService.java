package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.*;
import com.tieshan.disintegrate.exception.CustomException;
import com.tieshan.disintegrate.service.CarsQueryService;
import com.tieshan.disintegrate.service.ICarInformationService;
import com.tieshan.disintegrate.util.PubMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description: 车辆信息查询
 * @author: Leavonson
 * @date: Created in 2019/9/19 11:54
 * @version: 1.0
 * @modified By:
 */
@Service
public class CarInformationService implements ICarInformationService {

    @Autowired
    private CarInformationDao carInformationDao;
    @Autowired
    private CarInformationIdentityDao carInformationIdentityDao;
    @Autowired
    private CarInformationSourceDao carInformationSourceDao;
    @Autowired
    private CarInformationBreakDao carInformationBreakDao;
    @Autowired
    private CarInformationSalvageDao carInformationSalvageDao;
    @Autowired
    private CarsQueryService carsQueryService;

    @Override
    public List<Map<String, Object>> findCarById(Long carInfoId, Long companyId) {
        return carInformationDao.findCarById(carInfoId, companyId);
    }

    @Override
    public Map<String, Object> findAll(Long carInfoId, Long companyId) {
        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("carInfo", carInformationDao.findCarInfoById(carInfoId, companyId));
        resultMap.put("carPrePic", carsQueryService.doFindCars(carInfoId, companyId));
        resultMap.put("carTuoPic", carInformationDao.findCarTuoPicById(carInfoId, companyId));
        return resultMap;
    }

    @Override
    public Map<String, Object> findProcedureAll(Long carInfoId, Long companyId) {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("carProcedureInfo", carInformationIdentityDao.findCarIdentityById(carInfoId, companyId));
        resultMap.put("carProcedureLog", carInformationIdentityDao.findProcedureById(carInfoId, companyId));
        return resultMap;
    }

    @Override
    public Map<String, Object> findSourceAll(Long carInfoId, Long companyId) {
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("carSource", carInformationSourceDao.findCarSourceById(carInfoId, companyId));
        resultMap.put("carSourceLog", carInformationSourceDao.findCarSourceLogById(carInfoId, companyId));
        return resultMap;
    }

    @Override
    public Map<String, Object> findBreakAll(Long carInfoId, Long companyId) {

        Map<String, Object> resultMap = new HashMap<>(3);
        resultMap.put("carBreakInfo", carInformationBreakDao.findCarBreakById(carInfoId, companyId));
        resultMap.put("carBreakPic", carInformationBreakDao.findCarBreakPicById(carInfoId, companyId));
        resultMap.put("carBreakLog", carInformationBreakDao.findCarBreakLogById(carInfoId, companyId));
        return resultMap;
    }

    @Override
    public Map<String, Object> findSalvageAll(Long carInfoId,Long companyId) {

        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("carSalvageInfo",carInformationSalvageDao.findCarBreakById(carInfoId,companyId));
        resultMap.put("carSalvageInfoLog",carInformationSalvageDao.findCarBreakLogById(carInfoId,companyId));
        return resultMap;
    }

}
