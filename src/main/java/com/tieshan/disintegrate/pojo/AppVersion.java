package com.tieshan.disintegrate.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * app版本
 *
 * @author ren lei
 * @date 2019-10-17 10:36:44
 * @version: 1.0
 * @modified By:
 */
@Data
public class AppVersion implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 版本号
	 */
	private String versionInfo;
	/**
	 * 版本编码
	 */
	private Integer versionNumber;
	/**
	 * 是否更新  1:建议更新，2：强制升级
	 */
	private String versionType;
	/**
	 * app下载地址
	 */
	private String appUrl;
	/**
	 * 1:android;2:ios
	 */
	private String appType;
	/**
	 * 这次版本的描述
	 */
	private String versionDescribe;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 是否删除。1.删除，2,正常
	 */
	private Integer isDelete;
	/**
	 * 创建人
	 */
	private String operator;

}
