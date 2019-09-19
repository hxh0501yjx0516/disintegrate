package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.CarsQuery;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/9/17 20:49
 * @version: 1.0
 * @modified By:
 */
public interface CarsQueryService {
    /**
     * 实现分页查询操作
     *
     * @param findMsg     基于条件查询时的参数名
     * @param page 当前的页码值
     * @param pageSize 当前页个数
     * @return 当前页记录+分页信息
     */
    List<CarsQuery> findPageObjects(
            String findMsg,
            Integer page,
            Integer pageSize);

}

