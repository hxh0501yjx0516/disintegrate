package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车辆信息表
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarInfo implements Serializable {
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
	 * 车源主键id
	 */
	private Long carSource;
	/**
	 * 车辆联系人
	 */
	private String contacts;
	/**
	 * 手机号
	 */
	private String contactsPhone;
	/**
	 * 车牌号
	 */
	private String carNo;
	/**
	 * 车辆回收方式（来自字典表)
	 */
	private String processingType;
	/**
	 * 处理日期
	 */
	private Date processingDate;
	/**
	 * 驾驶证 行驶本等等
	 */
	private String procedures;
	/**
	 * 手续获取方式
	 */
	private String proceduresType;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 操作人id
	 */
	private Long operatorId;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 车型
	 */
	private String carName;
	/**
	 * 是否删除。1.删除，2,正常
	 */
	private Integer isDelete;

}
