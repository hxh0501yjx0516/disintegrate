package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.CarSourceMapper;
import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.service.ICarSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: renlei
 * @date: 2019/9/6 11:41
 * @version: 1.0
 * @modified By:
 */
@Service
public class CarSourceService implements ICarSourceService {

    @Autowired
    private CarSourceMapper carSourceMapper;


    @Override
    public void add(CarSource carSource) {
        carSourceMapper.insertCarSource(carSource);
    }
}
