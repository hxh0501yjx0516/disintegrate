package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.CarsQuery;
import com.tieshan.disintegrate.pojo.PageObject;

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
     * @param pageCurrent 当前的页码值
     * @return 当前页记录+分页信息
     */
    PageObject<CarsQuery> findPageObjects(
            String findMsg,
            Integer pageCurrent);
}

