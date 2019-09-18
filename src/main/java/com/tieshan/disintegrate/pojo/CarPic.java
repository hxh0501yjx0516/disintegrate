package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 车辆相关图片
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarPic implements Serializable {
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
	 * 图片名字
	 */
	private String fileName;
	/**
	 * 图片地址
	 */
	private String fileUrl;
	/**
	 * 一级类型（来之字典）
	 */
	private String firstType;
	/**
	 * 二级类型（来之字典)
	 */
	private String twoType;
	/**
	 * carinfo的主键id
	 */
	private Long carNo;
	/**
	 * 操作人id
	 */
	private Long operatorId;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 是否删除。1.删除，2,正常
	 */
	private Integer isDelete;

}
