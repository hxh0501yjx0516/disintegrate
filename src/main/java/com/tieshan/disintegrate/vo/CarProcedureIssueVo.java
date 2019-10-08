package com.tieshan.disintegrate.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 手续发放映射类
 * @author ：ren lei
 * @date ：Created in 2019/9/27 9:40
 * @modified By：
 * @version: 1.0.0
 */
@Data
public class CarProcedureIssueVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 车辆id
     */
    private Long carInfoId;
    /**
     * 车辆编号
     */
    private String carCode;
    /**
     * 车牌号
     */
    private String carNo;
    /**
     * 车型
     */
    private String carName;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系人手机号
     */
    private String contactsPhone;
    /**
     * vin
     */
    private String vin;
    /**
     * 1：未领取残值；2：已领取残值；
     */
    private Integer isGetSalvage;
    /**
     * 1：未发放手续；2：已发放手续；
     */
    private Integer isProcedureIssue;
    /**
     * 发放时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date procedureIssueTime;

}
