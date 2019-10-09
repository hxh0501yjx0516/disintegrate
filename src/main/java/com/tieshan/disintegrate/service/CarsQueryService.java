package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.vo.CaiPrePicVo;
import com.tieshan.disintegrate.vo.CarPicData;
import com.tieshan.disintegrate.pojo.CarsQuery;
import com.tieshan.disintegrate.pojo.SysUser;

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
     * @param findMsg  基于条件查询时的参数名
     * @param page     当前的页码值
     * @param pageSize 当前页个数
     * @return 当前页记录+分页信息
     */
    List<CarsQuery> findPageObjects(
            String findMsg,
            Integer page,
            Integer pageSize,
            Long companyId);

    /***
     * App端查询车辆预处理车辆信息
     */
    List<Map<String, Object>> findPretreatmentCars(String findMsg,
                                                   Integer page,
                                                   Integer pageSize,
                                                   Long dpId);
    /**
     * App端根据carInfoId查询车辆预处理信息
     */
    List<CaiPrePicVo> doFindCars(Long carInfoId, Long companyId);
    /***
     * App端查询车辆预处理拍照名称
     */
    List<Map<String, Object>> findPrePicNameCars();
    /**
     * App端添加预处理车辆
     */
    void addPrePic(CarPicData carPicData, SysUser user);
    /***
     * App端查询预拓号车辆信息
     */
    List<Map<String, Object>> findCopyNumberCars(String findMsg,
                                                 Integer page,
                                                 Integer pageSize,
                                                 Long dpId);
    /***
     * App端查询车辆预拓号拍照名称
     */
    List<Map<String, Object>> findCpPicNameCars();
    /**
     * App端根据carInfoId查询车辆拓号数据
     */
    List<Map<String, Object>> findCpTuoPic(Long carInfoId, Long companyId);
    /**
     * App端添加预拓号车辆
     */
    void addTuoPic(CarPicData carPicData, SysUser user);
    /***
     * App端查询预拆解车辆信息
     */
    List<Map<String, Object>> findDismantleCars(String findMsg,
                                                Integer page,
                                                Integer pageSize,
                                                Long dpId);
    /***
     * App端根据carInfoId查询车辆初检信息
     */
    List<Map<String,Object>> findSurveyById(Long carInfoId, Long companyId);
    /***
     * App端根据carInfoId查询车辆初检数据
     */
    List<CaiPrePicVo> findPrePicById(Long carInfoId, Long companyId);
    /***
     * App端车辆初检拆解方式
     */
    int dismantleWay(Long carInfoId, Integer status, Long companyId);

}

