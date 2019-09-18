package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 客服处理表
 *
 * @author ren lei
 * @date 2019-09-18 15:39:56
 * @version: 1.0
 * @modified By:
 */
@Data
public class ServiceDeal implements Serializable {
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
	 * 异常日期
	 */
	private Date anomalyDate;
	/**
	 * 异常原因
	 */
	private String anomalyCause;
	/**
	 * 处理人（用户主键id)
	 */
	private Long lastUserId;
	/**
	 * 处理人
	 */
	private String lastUser;
	/**
	 * 是否删除。1.删除，2,正常
	 */
	private Integer isDelete;

}
