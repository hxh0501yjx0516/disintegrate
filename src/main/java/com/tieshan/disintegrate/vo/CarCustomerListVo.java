package com.tieshan.disintegrate.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/25 15:23
 * @modified By：
 * @version: 1.0.0
 */
@Data
public class CarCustomerListVo implements Serializable {
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
     * 1:未查询;2:已查询;3:查询不通过
     */
    private Integer isQuery;
    /**
     * 1:未核档；2：已核档；3:核档不通过
     */
    private Integer isVerify;
    /**
     * 异常日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date exceptionTime;
    /**
     * 异常原因
     */
    private String exceptionResult;
    /**
     * 客服上次处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date handleTime;
    /**
     * 客服上次处理备注
     */
    private String remark;
}
