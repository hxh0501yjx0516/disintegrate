package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车辆退车表
 *
 * @author ren lei
 * @date 2019-09-20 17:18:06
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarBack implements Serializable {
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
	 * 车辆主键id
	 */
	private Long carInfoId;
	/**
	 * 1：未打印退车单；2：已打印退车单；
	 */
	private Integer isPrintCarOrder;
	/**
	 * 打印退车单的时间
	 */
	private Date printCarOrderTime;
	/**
	 * 打印退车的人（用户主键id)
	 */
	private Long printCarOrderUserId;
	/**
	 * 1：未领取车辆手续；2：已领取车辆手续；
	 */
	private Integer isGetProcedure;
	/**
	 * 领取车辆手续时间
	 */
	private Date getProcedureTime;
	/**
	 * 领取车辆手续人(用户主键id)
	 */
	private Long getProcedureUserId;
	/**
	 * 1：未领取车辆；2：已领取车辆；
	 */
	private Integer isGetCar;
	/**
	 * 领取车辆时间
	 */
	private Date getCarTime;
	/**
	 * 领取人
	 */
	private Long getCarUser;
	/**
	 * 1：未上传退车单照片；2：已上传退车单照片；
	 */
	private Integer isPicCarOrder;
	/**
	 * 退车单照片上传时间
	 */
	private Date picCarOrderTime;
	/**
	 * 退车单照片上传人
	 */
	private Long picCarOrderUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 退车原因
	 */
	private String carBackReason;
	/**
	 * 退车单地址
	 */
	private String carOrderAddress;
	/**
	 * 是否删除。1.删除，2,正常
	 */
	private Integer isDelete;

}
