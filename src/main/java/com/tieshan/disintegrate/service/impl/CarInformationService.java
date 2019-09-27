package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.*;
import com.tieshan.disintegrate.exception.CustomException;
import com.tieshan.disintegrate.service.ICarInformationService;
import com.tieshan.disintegrate.util.PubMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, Object>> findCarById(Long carInfoId) {
        return carInformationDao.findCarById(carInfoId);
    }

    @Override
    public List<Object> findAll(Long carInfoId) {
        List<Object> list = new LinkedList<>();
        if (!PubMethod.isEmpty(carInformationDao.findCarInfoById(carInfoId))) {
            list.add(carInformationDao.findCarInfoById(carInfoId));
        } else {
            list.add(null);
            //throw new CustomException("没有查询到车辆信息");
        }
        if (!PubMethod.isEmpty(carInformationDao.findCarPrePicById(carInfoId))) {
            list.add(carInformationDao.findCarPrePicById(carInfoId));
        } else {
            list.add(null);
            //throw new CustomException("没有查询到预处理图片");
        }
        if (!PubMethod.isEmpty(carInformationDao.findCarTuoPicById(carInfoId))) {
            list.add(carInformationDao.findCarTuoPicById(carInfoId));
        } else {
            list.add(null);
            //throw new CustomException("没有查询到拓号图片");
        }
        return list;
    }


    @Override
    public List<Object> findProcedureAll(Long carInfoId) {
        List<Object> list = new LinkedList<>();
        if (!PubMethod.isEmpty(carInformationIdentityDao.findCarIdentityById(carInfoId))) {
            list.add(carInformationIdentityDao.findCarIdentityById(carInfoId));
        } else {
            list.add(null);
            //throw new CustomException("没有查询到手续信息");
        }
        if (!PubMethod.isEmpty(carInformationIdentityDao.findProcedureById(carInfoId))) {
            list.add(carInformationIdentityDao.findProcedureById(carInfoId));
        } else {
            list.add(null);
            //throw new CustomException("没有查询到手续处理周期信息");
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findSourceAll(Long carInfoId) {
        List<Map<String, Object>> resultList = new LinkedList<>();
        Map<String, Object> resultMap = new HashMap<>();
        if (!PubMethod.isEmpty(carInformationSourceDao.findCarSourceById(carInfoId))) {
//            list.add(carInformationSourceDao.findCarSourceById(carInfoId));
            resultMap.put("carInfo", carInformationSourceDao.findCarSourceById(carInfoId));
        } else {
            resultList.add(null);
            //throw new CustomException("没有查询到车源信息");
        }
        if (!PubMethod.isEmpty(carInformationSourceDao.findCarSourceLogById(carInfoId))) {
//            list.add(carInformationSourceDao.findCarSourceLogById(carInfoId));
            resultMap.put("carLog", carInformationSourceDao.findCarSourceById(carInfoId));

        } else {
            resultList.add(null);
            //throw new CustomException("没有查询到车源日志信息");
        }
        resultList.add(resultMap);
        return resultList;
    }

    @Override
    public List<Object> findBreakAll(Long carInfoId) {
        List<Object> list = new LinkedList<>();
        if (!PubMethod.isEmpty(carInformationBreakDao.findCarBreakById(carInfoId))) {
            list.add(carInformationBreakDao.findCarBreakById(carInfoId));
        } else {
            list.add(null);
            //throw new CustomException("没有查询到拆解信息");
        }
        if (!PubMethod.isEmpty(carInformationBreakDao.findCarBreakPicById(carInfoId))) {
            list.add(carInformationBreakDao.findCarBreakPicById(carInfoId));
        } else {
            list.add(null);
            //throw new CustomException("没有查询到拆解图片");
        }
        if (!PubMethod.isEmpty(carInformationBreakDao.findCarBreakLogById(carInfoId))) {
            list.add(carInformationBreakDao.findCarBreakLogById(carInfoId));
        } else {
            list.add(null);
            //throw new CustomException("没有查询到拆解日志");
        }
        return list;

    }

    @Override
    public List<Object> findSalvageAll(Long carInfoId) {
        List<Object> list = new LinkedList<>();
        if (!PubMethod.isEmpty(carInformationSalvageDao.findCarBreakById(carInfoId))) {
            list.add(carInformationSalvageDao.findCarBreakById(carInfoId));
        } else {
            list.isEmpty();
            list.add(null);
            //throw new CustomException("没有查询到残值信息");
        }
        if (!PubMethod.isEmpty(carInformationSalvageDao.findCarBreakLogById(carInfoId))) {
            list.add(carInformationSalvageDao.findCarBreakLogById(carInfoId));
        } else {
            list.isEmpty();
            list.add(null);
            //throw new CustomException("没有查询到残值日志信息");
        }
        return list;
    }

}
