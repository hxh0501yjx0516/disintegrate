package com.tieshan.disintegrate.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @description: 个人中心的各个统计
 * @author: huxuanhua
 * @date: Created in 2019/10/10 10:51
 * @version: 1.0
 * @modified By:
 */
public interface CensusMapper {
    /**
     * 外勤部门（个人中心），档案统计：待核档，核档未通过，已核档
     * 50001
     *
     * @return
     */
    Map<String, Object> filesCensus(@Param("id") String id);

    /**
     * 拆解部（个人中心），拆解统计：待拆车辆，拆过的车辆，已拆件
     * 40001
     *
     * @return
     */
    Map<String, Object> disassembleCensus(@Param("id") String id);

    /**
     * 业务部（个人中心），拆解统计：我的车源，核档未通过，报废完成
     * 20001
     *
     * @return
     */
    Map<String, Object> businessCensus(@Param("id") String id);

    /**
     * 手续部（个人中心） 所有车源，核档未通过，报废完成
     * 30001
     *
     * @return
     */
    Map<String, Object> proceduresCensus(@Param("id") String id);

    /**
     * 技术部（个人中心） 所有车源，核档未通过，报废完成
     * 10001
     *
     * @return
     */
    Map<String, Object> technologyCensus(@Param("id") String id);

    /**
     * 库管部（个人中心） 待入库，已入库
     * 60001
     *
     * @return
     */
    Map<String, Object> commissionaireCensus(@Param("id") String id);
}
