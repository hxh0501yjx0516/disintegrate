package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车辆初检表
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarSurvey implements Serializable {
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
	 * carinfo的id
	 */
	private Long carInfoId;
	/**
	 * 车牌数量
	 */
	private Integer plateCount;
	/**
	 * 新旧程度 1:正常 ,2:火灾 , 3:碰撞 ,4:水淹
	 */
	private Integer carDegree;
	/**
	 * 空调泵数量
	 */
	private Integer conditionPumpCount;
	/**
	 * 电池数量
	 */
	private Integer batteryCount;
	/**
	 * 马达数量
	 */
	private Integer motorCount;
	/**
	 * 车门数量
	 */
	private Integer doorCount;
	/**
	 * 铝圈数量
	 */
	private Integer alloyRimCount;
	/**
	 * 水箱数量
	 */
	private Integer cisternCount;
	/**
	 * 电机数量
	 */
	private Integer electricalMachineryCount;
	/**
	 * 轮胎数量
	 */
	private Integer tyreCount;
	/**
	 * 座椅数量
	 */
	private Integer chairCount;
	/**
	 * 空调数量
	 */
	private Integer conditionerCount;
	/**
	 * 三元催化器数量
	 */
	private Integer catalyticConverterCount;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 自重
	 */
	private String selfWeight;
	/**
	 * 拆解方式 1:粗拆 ,2:点拆 , 3:包拆 ,4:精拆
	 */
	private Integer dismantlingWay;
	/**
	 * 是否删除。1.删除，2,正常
	 */
	private Integer isDelete;
	/**
	 * 创建人
	 */
	private String createOperator;
	/**
	 * 创建人id
	 */
	private Long createOperatorId;
	// 车牌颜色
	private String cardColor;
	// 是否是铝圈：1。是，2.不是
	private Integer isAlloyRim;
}
