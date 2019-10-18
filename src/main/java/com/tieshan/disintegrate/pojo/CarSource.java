package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @description:车源信息表
 * 
 * @author ningfeng
 * @date 2019-09-06 10:17:22
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarSource implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	// 解体厂id
	private Long disintegratePlantId;
	// 车辆台次
	private String count;
	// 联系人
	private String contacts;
	// 联系人电话
	private String phone;
	// 车源位置
	private String carLocation;
	// 业务员id
	private Long userId;
	// 打款银行id
	private String bankId;
	// 1:银行打款;2:现金结算
	private String payType;
	// 车源状态    （1：待入场;2:已入场;3:异常)
	private String sourceType;
	// 1:已打款;2:待打款
	private String payStatus;
	// 付款金额
	private String payAmount;
	// 1：WEB;2：APP
	private String createSource;
	// 创建人
	private String createOperator;
	// 创建人id
	private Long createOperatorId;
	// 创建时间
	private Date createTime;
	// 是否删除  1.删除，2,正常
	private Integer isDelete;
	// 银行信息
	private Bank bank = new Bank();
}
