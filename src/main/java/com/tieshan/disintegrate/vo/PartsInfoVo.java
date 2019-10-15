package com.tieshan.disintegrate.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/9/26 14:19
 * @version: 1.0
 * @modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartsInfoVo implements Serializable {
    private static final long serialVersionUID = -1281208248971156925L;

    /**拆车件编号*/
    private String partsCode;
    /**拆车件名称*/
    private String partsName;
    /**车辆牌号*/
    private String carNo;
    /**车辆Vin码*/
    private String vin;
    /**打印/入库操作人*/
    private String operator;
    /**打印/入库时间*/
    private String time;
}
