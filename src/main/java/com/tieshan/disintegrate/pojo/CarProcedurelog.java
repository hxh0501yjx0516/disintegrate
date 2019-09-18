package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车辆手续日志表
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarProcedurelog implements Serializable {
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
	 * 客户处理主键id
	 */
	private Long serviceDealId;
	/**
	 * 车辆信息主键id
	 */
	private Long carInfoId;
	/**
	 * 1：查询历史；2：核档历史；3：客服历史；
	 */
	private String type;
	/**
	 * 结果
	 */
	private String result;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 查询时间
	 */
	private Date querytime;
	/**
	 * 操作人id
	 */
	private Long operatorId;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 1:未完成;2完成
	 */
	private String state;
	/**
	 * 是否删除。1.删除，2,正常
	 */
	private Integer isDelete;

}
