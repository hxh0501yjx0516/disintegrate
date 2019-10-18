package com.tieshan.disintegrate.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ningfeng
 * @description:
 * @date Created in 16:19 2019/10/16
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarSalvage implements Serializable {
    // id
    @Id
    private Long id;
    // 解体场id
    private Long disintegratePlantId;
    // 车辆主键id
    private Long carInfoId;
    // 残值金额
    private String salvage;
    // 1：未领取残值；2：已领取残值
    private Integer isGetSalvage;
    // 残值领取时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date getSalvageTime;
    // 残值领取人（用户主键id)待确定
    private Long getSalvageUserId;
    // 残值领取人
    private String getSalvageUser;
    // 领取方式(1;现金；2：转账)
    private Integer getWay;
    // 修改时间
    private Date modifyTime;
    // 修改人（用户主键id)待确定
    private Long modifyUserId;
    // 修改人
    private String modifyUser;
    // 1:未写入2:已写入
    private Integer isUpdate;
    //  录入残值时间
    private Date updateTime;
    // 是否提前发放(1:未提前;2：提前）
    private Integer isAdvance;
    // 申请人(用户主键id)
    private Long advanceUserId;
    // 申请时间
    private Date advanceTime;
    // 申请状态(1：待审核;2:审核未通过;3:审核通过)
    private Integer auditStatus;
    // 审核人(用户主键id)
    private Long auditUserId;
    // 审核时间
    private Date auditTime;
    // 审核人
    private String auditUser;
    // 审核信息
    private String auditInfo;
    // 批量处理标记
    private String batchUum;
    // 批量处理时间
    private Date batchTime;
    // 备注
    private String remark;
    // 是否删除。1.删除，2,正常
    private Integer isDelete;
    // 残值发放人（操作员）
    private String operator;
}
