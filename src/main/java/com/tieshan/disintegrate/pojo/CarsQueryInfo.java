package com.tieshan.disintegrate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @description:车辆查询对应实体
 * @author: Leavonson
 * @date: Created in 2019/9/17 17:28
 * @version: 1.0
 * @modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CarsQueryInfo {
    /**
     * 车辆编号-->car_info.id
     * */
    private Long id;
    /**
     * 车牌号-->car_info.车牌号
     * */
    private String carNo;
    /**
     * 车牌颜色-->ts_car_identity.车牌颜色(来之字典表)
     * */
    private String cardColor;
    /**
     * 品牌型号car_info.车型
     * */
    private String carName;
    /**
     * 车辆识别代码(vin）-->ts_car_identity.车辆识别代码(vin）
     * */
    private String vin;
    /**
     * 发动机号码-->ts_car_identity.发动机号码
     * */
    private String engine;

}
