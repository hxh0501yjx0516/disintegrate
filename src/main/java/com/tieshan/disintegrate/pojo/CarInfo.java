package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Id;

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
	@Id
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
	 * 交车人
	 */
	private String contacts;
	/**
	 * 交车人手机号
	 */
	private String contactsPhone;
	/**
	 * 交车人地址
	 */

	private String contactsAddress;
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
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date processingDate;
	/**
	 * 行驶证
	 */
	private Integer drivLicense;
	// 登记证
	private Integer registLicense;
	// 身份证复印件
	private Integer peopleLicense;
	// 营业执照复印件
	private Integer busineseLicense;
	// 车辆报废表
	private Integer breakLicense;
	// 车辆事故证明
	private Integer exceptionLicense;
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
	// 车辆编号，格式：TSXXX190900001    添加车辆时加入
	private String carCode;
	// 车辆编码，前缀：TSXXX
	private String codeFront;
	// 车辆编码：中间日期：1909
	private String codeIn;
	// 车辆编码：后面的数字，1，查询的时候不够补0
	private Integer codeAfter;
	// 车辆所在的区
	private String carLocationArea;
	// 车辆所在区的排
	private String carLocationRow;
	// 车辆所在排的列
	private String carLocationColumn;
	// 车辆所在列的号
	private String carLocationNumber;
}
