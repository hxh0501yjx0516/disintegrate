package com.tieshan.disintegrate.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 客服处理
 * @author ：ren lei
 * @date ：Created in 2019/9/25 15:23
 * @modified By：
 * @version: 1.0.0
 */
@Data
public class WebCarCustomerInfoVo implements Serializable {
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
     * 车辆归属
     */
    private String ascription;
    /**
     * 车牌颜色
     */
    private String cardColor;
    /**
     * 车主姓名（或组织机构名称）
     */
    private String owner;
    /**
     * 车主电话
     */
    private String phone;
    /**
     * 车主身份证号（组织结构代码）
     */
    private String idCard;
    /**
     * 车主邮编
     */
    private String ownerZipcode;
    /**
     * 车主地址
     */
    private String ownerAddress;
    /**
     * 代理人
     */
    private String agend;
    /**
     * 代理人身份证号
     */
    private String agendIdcard;
    /**
     * 交车人姓名
     */
    private String contacts;
    /**
     * 交车人电话
     */
    private String contactsPhone;
    /**
     * 交车人地址
     */
    private String contactsAddress;
    /**
     * 行驶本。1.没有，2.有
     */
    private Integer drivLicense;
    /**
     * 登记证。1.没有，2.有
     */
    private Integer registLicense;
    /**
     * 车型
     */
    private String carName;
    /**
     * 整车质量
     */
    private String carWeight;
    /**
     * 车辆性质
     */
    private String carKind;
    /**
     * 燃油性质
     */
    private String fuelType;
    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date regTime;
    /**
     * 发证日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date issueTime;
    /**
     * 使用性质
     */
    private String natureUsege;
    /**
     * 车辆类型
     */
    private String carProperties;
    /**
     * vin
     */
    private String vin;
    /**
     * 发动机号码
     */
    private String engine;
    /**
     * 外廓尺寸
     */
    private String issueSize;
    /**
     * 汽车排量
     */
    private String displacement;
    /**
     * 核定载人数
     */
    private String carryingCapacity;
    /**
     * 备注
     */
    private String remarks;

    private String recordNumber;


    private List<WebRecordHandleVo> queryRecord;

}
