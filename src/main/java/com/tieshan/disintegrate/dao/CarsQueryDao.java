package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarsQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/9/17 16:31
 * @version: 1.0
 * @modified By:
 */
public interface CarsQueryDao {

    /**
     * 基于条件分页查询车辆信息
     *
     * @param findMsg    查询条件(姓名/电话/车型/车牌号/车辆编号/车架号/发动机号)
     * @param startIndex 当前页的起始位置
     * @param pageSize   当前页的页面大小
     * @return 当前页的日志记录信息
     * 数据库中每条日志信息封装到一个CarsQuery对象中
     */
    List<CarsQuery> findPageObjects(
            @Param("findMsg") String findMsg,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);

    /**
     * 基于条件查询总记录数
     *
     * @param findMsg 查询条件(姓名/电话/车型/车牌号/车辆编号/车架号/发动机号)
     * @return 总记录数
     */
    int getRowCount(@Param("findMsg") String findMsg);

}
