package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarsQuery;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

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
     * @param findMsg    查询条件(姓名/电话/车型/车牌号/车辆编号/车架号/发动机号)
     */
    List<CarsQuery> findPageObjects(@Param("findMsg") String findMsg);



}
