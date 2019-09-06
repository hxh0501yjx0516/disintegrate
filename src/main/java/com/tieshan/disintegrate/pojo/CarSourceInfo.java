package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @description:车源细化信息表
 * 
 * @author renlei
 * @date 2019-09-06 10:07:22
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarSourceInfo implements Serializable {
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
	 * 
	 */
	private Long sourceId;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 联系人电话
	 */
	private String contactPhone;
	/**
	 * 车辆所在位置
	 */
	private String location;
	/**
	 * 车牌号码
	 */
	private String carPlateNo;
	/**
	 * 处理方式 0:拖车,1:代驾,2:自送,3:上门开车
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
	 * 行驶本 0:有,1:无
	 */
	private Integer hasDrivingBook;
	/**
	 * 登记证 0:有,1:无
	 */
	private Integer hasRegistrationCertificate;
	/**
	 * 身份证复印件 0:有,1:无
	 */
	private Integer hasIdentityDuplicate;
	/**
	 * 营业执照复印件 0:有,1:无
	 */
	private Integer hasBusinessDuplicate;
	/**
	 * 车辆报废表 0:有,1:无
	 */
	private Integer hasCarScrap;
	/**
	 * 车辆事故证明 0:有,1:无
	 */
	private Integer hasMalfunctionProve;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 
	 */
	private String operatorName;
	/**
	 * 
	 */
	private Long operatorId;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
