package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 车辆身份表
 *
 * @author ren lei
 * @date 2019-09-19 16:30:14
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarIdentity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 解体厂id
	 */
	private Long disintegratePlantId;
	/**
	 * 车辆归宿(来自字典表)
	 */
	private String ascription;
	/**
	 * car_info主键id
	 */
	private Long carInfoId;
	/**
	 * 车辆颜色(来自字典表)
	 */
	private String colour;
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
	 * 代理人
	 */
	private String agend;
	/**
	 * 代理人身份证号
	 */
	private String agendIdcard;
	/**
	 * 车辆性质(来之字典表)
	 */
	private String carKind;
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
	 * 发证日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date issueTime;
	/**
	 * 使用性质(来之字典表),营运非营运。。。
	 */
	private String natureUsege;
	/**
	 * 车辆型号
	 */
	private String carType;
	/**
	 * 车辆识别代码(vin）
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
	/**
	 * 车架号码
	 */
	private String frameNumber;
	/**
	 * 整车质量(车本上的质量)
	 */
	private String carWeight;
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
	 * 是否删除。1.删除，2,正常
	 */
	private Integer isDelete;
	/**
	 * 车牌颜色(来之字典表),蓝色，黄色。。。
	 */
	private String cardColor;
	/**
	 * 1:暂存，2:提交（登记完成）
	 */
	private Integer status;
	/**
	 * 档案号
	 */
	private String recordNo;

}
