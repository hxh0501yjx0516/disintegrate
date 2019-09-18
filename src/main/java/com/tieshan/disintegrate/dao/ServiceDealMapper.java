package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.ServiceDeal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客服处理表
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface ServiceDealMapper {

    ServiceDeal selectOneByMap(Map<String, Object> map);

    List<ServiceDeal> selectListByMap(Map<String, Object> map);

    void insertServiceDeal(ServiceDeal ServiceDeal);

    void insertBatchServiceDeal(Map<String, Object> map);

    void updateServiceDeal(ServiceDeal ServiceDeal);

    void updateBatchServiceDeal(Map<String, Object> map);

    void deleteServiceDealByMap(Map<String, Object> map);

    void batchDeleteServiceDealByMap(Map<String, Object> map);
}
