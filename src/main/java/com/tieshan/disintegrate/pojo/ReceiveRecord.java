package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 领取记录表
 *
 * @author ren lei
 * @date 2019-09-29 10:50:35
 * @version: 1.0
 * @modified By:
 */
@Data
public class ReceiveRecord implements Serializable {
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
	 * 车辆信息主键id
	 */
	private Long carInfoId;
	/**
	 * 1：手续领取记录
	 */
	private Integer type;
	/**
	 * 领取方式
	 */
	private String result;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 领取时间
	 */
	private Date receiveTime;
	/**
	 * 操作人id
	 */
	private Long operatorId;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 领取人
	 */
	private String receiver;
	/**
	 * 是否删除。1.删除，2,正常
	 */
	private Integer isDelete;

}
