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
public class CarsQuery{
    /**
     * 车辆编号-->car_info.id
     * */
    private Long carInfoId;
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
     * 车辆性质(来之字典表)-->ts_car_identity.车辆性质
     * */
    private String carKind;
    /**
     * 使用性质(来之字典表),营运非营运-->ts_car_identity.使用性质
     * */
    private String natureUsege;
    /**
     * 车辆类型 小轿车，大卡车-->ts_car_identity.车辆性质(来之字典表)
     * */
    private String carProperties;
    /**
     * 注册时间-->ts_car_identity.注册时间
     * */
    private Date regTime;
    /**
     * 发证日期-->ts_car_identity.发证日期
     * */
    private Date issueTime;
    /**
     * 车主姓名（或组织结构名称）ts_car_identity.车主姓名
     * */
    private String owner;
    /**
     * 车主电话-->ts_car_identity.车主电话
     * */
    private String phone;
    /**
     * 交车人姓名-->car_info.车辆联系人
     * */
    private String contacts;
    /**
     * 交车人电话-->car_info.手机号
     * */
    private String contactsPhone;
    /**
     * 车辆识别代码(vin）-->ts_car_identity.车辆识别代码(vin）
     * */
    private String vin;
    /**
     * 发动机号码-->ts_car_identity.发动机号码
     * */
    private String engine;

}
