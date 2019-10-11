package com.tieshan.disintegrate.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 退车单
 * @author ：ren lei
 * @date ：Created in 2019/9/24 10:34
 * @modified By：
 * @version: 1.0.0
 */
@Data
public class CarBackOrderVo implements Serializable {
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
     * 交车人地址
     */
    private String contactsAddress;
    /**
     * vin
     */
    private String vin;
    /**
     * 1：未打印退车单；2：已打印退车单；
     */
    private Integer isPrintCarOrder;
}
