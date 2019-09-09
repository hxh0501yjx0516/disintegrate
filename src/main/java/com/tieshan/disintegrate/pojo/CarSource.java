package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description:车源信息表
 * 
 * @author renlei
 * @date 2019-09-06 10:17:22
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarSource implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private Long id;
	/**
	 * 解体厂id
	 */
	private Long factoryId;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 联系人电话
	 */
	private String contactPhone;
	/**
	 * 车辆台次
	 */
	private Integer carAmount;
	/**
	 * 车辆所在位置
	 */
	private String location;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 开户行
	 */
	private String bankOpenAddress;
	/**
	 * 银行账号
	 */
	private String bankAmount;
	/**
	 * 车牌号码
	 */
	private String carPlateNo;
	/**
	 * 处理方式 0:拖车,1:代驾,2:自送,3:上门开车 41
	 */
	private Integer handleWay;
	/**
	 * 处理日期
	 */
	private Date handleDate;
	/**
	 * 手续获取方式 0:随车,1:再次取,2:邮寄
	 */
	private Integer proceduresGetWay;
	/**
	 * 手续
	 */
	private String procedures;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
