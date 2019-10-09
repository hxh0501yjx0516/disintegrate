package com.tieshan.disintegrate.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tieshan.disintegrate.pojo.CarPic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商委所需数据
 */
@Data
public class ShangWeiDataVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 车辆归属
     */
    private String ascription;
    /**
     * car_info主键id
     */
    private Long carInfoId;
    /**
     * 车主姓名（或组织结构名称）
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
     * 车主地址
     */
    private String ownerAddress;
    /**
     * 车主邮编
     */
    private String ownerZipcode;
    /**
     * 车辆性质(来之字典表) 小轿车，大卡车。。。
     */
    private String carProperties;
    /**
     * 车辆性质(来之字典表)
     */
    private String carKind;
    /**
     * 车型
     */
    private String carName;
    /**
     * 车辆型号
     */
    private String carType;
    /**
     * 使用性质(来之字典表),营运非营运。。。
     */
    private String natureUsege;
    /**
     * 车牌号码
     */
    private String carNo;
    /**
     * 汽车排量
     */
    private String displacement;
    /**
     * 燃油性质(来之字典表）
     */
    private String fuelType;
    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date regTime;
    /**
     * 车辆识别代码(vin）
     */
    private String vin;
    /**
     * 发动机号码
     */
    private String engine;
    /**
     * 整车质量(车本上的质量)
     */
    private String carWeight;
    /**
     * 外廓尺寸
     */
    private String issueSize;
    /**
     * 核定载人数
     */
    private String carryingCapacity;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 档案号
     */
    private String recordNo;
    /**
     * 预处理照片
     */
    private List<CarPic> prePics;
    /**
     * 拓号照片
     */
    private List<CarPic> tuoPic;
    /**
     * 毁形照片
     */
    private List<CarPic> breakPics;
}
