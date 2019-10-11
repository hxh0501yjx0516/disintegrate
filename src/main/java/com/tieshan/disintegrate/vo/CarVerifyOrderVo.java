package com.tieshan.disintegrate.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 核档单
 *
 * @author ：ren lei
 * @date ：Created in 2019/9/24 10:34
 * @modified By：
 * @version: 1.0.0
 */
@Data
public class CarVerifyOrderVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long carInfoId;
    /**
     * 车辆编号
     */
    private String carCode;
    /**
     * 车牌号
     */
    private String carNo;
    /**
     * 车型
     */
    private String carName;
    /**
     * 交车人
     */
    private String contacts;
    /**
     * 交车人手机号
     */
    private String contactsPhone;
    /**
     * vin 车辆识别码（车架号）
     */
    private String vin;
    /**
     * 1：未打印退车单；2：已打印退车单；
     */
    private Integer isPrintVerifyBill;

    /**
     * 车辆类型
     */
    private String carProperties;
    /**
     * 车辆颜色
     */
    private String colour;
    /**
     * 车辆性质
     */
    private String carKind;
    /**
     * 机动车所有人
     */
    private String owner;
    /**
     * 发动机号
     */
    private String engine;
    /**
     *
     */
    private Date printVerifyBillTime;
}
