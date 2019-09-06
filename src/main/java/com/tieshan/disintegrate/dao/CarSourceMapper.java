package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.CarSource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description:车源信息表
 *
 * @author renlei
 * @date 2019-09-06 10:54:01
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarSourceMapper {

    void insertCarSource(CarSource carSource);

    void updateCarSource(CarSource carSource);

    List<CarSource> selectListByMap(Map map);

    CarSource selectOneByMap(Map map);

    void deleteByMap(Map map);
}
