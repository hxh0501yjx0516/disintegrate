package com.tieshan.disintegrate.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tieshan.disintegrate.pojo.CarProcedureLog;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/25 17:35
 * @modified By：
 * @version: 1.0.0
 */
@Data
public class AppCarBaseVo implements Serializable {
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
     * 入场时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date approachTime;
    /**
     * 车牌号
     */
    private String carNo;
    /**
     * vin
     */
    private String vin;
    /**
     * 核档结果
     */
    private Integer isVerify;
    /**
     * 手续id
     */
    private Long carProcessingId;

    private CarProcedureLog verificationResult;
}
