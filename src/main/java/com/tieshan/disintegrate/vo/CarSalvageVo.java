package com.tieshan.disintegrate.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @description: 财务管理-价格导入对应封装实体类
 * @author: Leavonson
 * @date: Created in 2019/10/8 9:46
 * @version: 1.0
 * @modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarSalvageVo {

    /**车辆编号*/
    private String carCode;
    /**车牌号*/
    private String carNo;
    /**车型*/
    private String carName;
    /**交车人*/
    private String contacts;
    /**交车人电话*/
    private String contactsPhone;
    /**车主*/
    private String owner;
    /**车主电话*/
    private String phone;
    /**残值价格*/
    private String salvage;

}
