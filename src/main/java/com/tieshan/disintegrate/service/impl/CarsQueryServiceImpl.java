package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.CarsQueryDao;
import com.tieshan.disintegrate.exception.CustomException;
import com.tieshan.disintegrate.pojo.CarsQuery;
import com.tieshan.disintegrate.pojo.PageObject;
import com.tieshan.disintegrate.service.CarsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public PageObject<CarsQuery> findPageObjects(String findMsg, Integer pageCurrent) {
        //验证参数合法性
        if (pageCurrent == null || pageCurrent < 1)
            throw new IllegalArgumentException("当前页码不正确");
        //基于条件查询总记录数
        int rowCount = carsQueryDao.getRowCount(findMsg);
        if (rowCount == 0)
            throw new CustomException("系统没有查到对应记录");
        //基于条件查询当前页记录(pageSize定义为3)
        int pageSize = 3;
        int startIndex = (pageCurrent - 1) * pageSize;
        List<CarsQuery> records =
                carsQueryDao.findPageObjects(findMsg, startIndex, pageSize);
        //对分页信息以及当前页记录进行封装
        PageObject<CarsQuery> pageObject = new PageObject<>();
        //封装数据
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(rowCount);
        pageObject.setRecords(records);
        pageObject.setPageCount((rowCount - 1) / pageSize + 1);
        //返回封装结果。
        return pageObject;
    }
}
