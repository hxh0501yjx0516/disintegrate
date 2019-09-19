package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.tieshan.disintegrate.dao.CarsQueryDao;
import com.tieshan.disintegrate.pojo.CarsQuery;
import com.tieshan.disintegrate.service.CarsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/9/17 20:50
 * @version: 1.0
 * @modified By:
 */
@Service
public class CarsQueryServiceImpl implements CarsQueryService {

    @Autowired
    private CarsQueryDao carsQueryDao;

    @Override
    public List<CarsQuery> findPageObjects(String findMsg, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("reg_time desc");
        return carsQueryDao.findPageObjects(findMsg);
    }


}
