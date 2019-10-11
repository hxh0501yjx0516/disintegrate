package com.tieshan.disintegrate.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 手续查询列表
 * @author ：ren lei
 * @date ：Created in 2019/9/25 15:23
 * @modified By：
 * @version: 1.0.0
 */
@Data
public class CarProcedureListVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long carInfoId;
    /**
     * 车辆编号
     */
    private String carCode;
    /**
     * 车型
     */
    private String carName;
    /**
     * 车牌号
     */
    private String carNo;
    /**
     * 交车人
     */
    private String contacts;
    /**
     * 交车人手机号
     */
    private String contactsPhone;
    /**
     * vin
     */
    private String vin;
    /**
     * 1:未登记;2:已登记
     */
    private Integer isRegister;
    /**
     * 1:未查询;2:已查询;3:查询不通过
     */
    private Integer isQuery;
    /**
     * 1:未核档；2：已核档；3:核档不通过
     */
    private Integer isVerify;
    /**
     * 是否毁型。1.待毁型，2.已核档
     */
    private Integer isDestructive;
    /**
     * 1:未上传商委;2:已上传商委
     */
    private Integer isDataUpload;
    /**
     * 1：未打印回收证明；2：已打印回收证明；
     */
    private Integer isPrintRecycle;
    /**
     * 1:未录入注销时间;2:已录入注销时间
     */
    private Integer isLogout;
    /**
     * 1：未上传完成商委注销时间；2：已完成商委注销时间上传；
     */
    private Integer isAppointLogoutTime;
    /**
     * 1：未发放手续；2：已发放手续；
     */
    private Integer isProcedureIssue;
}
