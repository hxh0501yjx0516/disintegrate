package com.tieshan.disintegrate.service;

import java.util.List;
import java.util.Map;

/**
 * @description: 车辆信息查询
 * @author: Leavonson
 * @date: Created in 2019/9/19 11:53
 * @version: 1.0
 * @modified By:
 */
public interface ICarInformationService {
    /**
     *@Description: 车辆查询页面头数据
     * @param: id car_info的主键(车辆编号ID)
     * @return: List
     */
    List<Map<String, Object>> findCarById(Long carInfoId);

    /**
     *@Description: 车辆查询页面-车辆信息信息数据查询
     * @param: id car_info的主键(车辆编号ID)
     * @return: List
     */
    List<Object> findAll(Long carInfoId);

    /**
     *@Description: 车辆查询页面-手续信息数据查询
     * @param: id car_info的主键(车辆编号ID)
     * @return: List
     */
    List<Object> findProcedureAll(Long carInfoId);

    /**
     *@Description: 车辆查询页面-车源信息数据查询
     * @param: id car_info的主键(车辆编号ID)
     * @return: List
     */
    List<Object> findSourceAll(Long carInfoId);

    /**
     *@Description: 车辆查询页面-拆解信息数据查询
     * @param: id car_info的主键(车辆编号ID)
     * @return: List
     */
    List<Object> findBreakAll(Long carInfoId);

    /**
     *@Description: 车辆查询页面-残值信息数据查询
     * @param: id car_info的主键(车辆编号ID)
     * @return: List
     */
    List<Object> findSalvageAll(Long carInfoId);

}
