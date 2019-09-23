package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.CarInformationDao;
import com.tieshan.disintegrate.dao.CarInformationIdentityDao;
import com.tieshan.disintegrate.dao.CarInformationSourceDao;
import com.tieshan.disintegrate.exception.CustomException;
import com.tieshan.disintegrate.service.ICarInformationService;
import com.tieshan.disintegrate.util.PubMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Object> findSourceAll(Long carInfoId) {
        List<Object> list = new LinkedList<>();
        if (!PubMethod.isEmpty(carInformationSourceDao.findCarSourceById(carInfoId))) {
            list.add(carInformationSourceDao.findCarSourceById(carInfoId));
        } else {
            list.add(null);
            //throw new CustomException("没有查询到车源信息");
        }
        if (!PubMethod.isEmpty(carInformationSourceDao.findCarSourceLogById(carInfoId))) {
            list.add(carInformationSourceDao.findCarSourceLogById(carInfoId));
        } else {
            list.add(null);
            //throw new CustomException("没有查询到车源日志信息");
        }
        return list;
    }
}
