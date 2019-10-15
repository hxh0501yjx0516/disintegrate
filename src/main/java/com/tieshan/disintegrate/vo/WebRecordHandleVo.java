package com.tieshan.disintegrate.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tieshan.disintegrate.pojo.CarProcedureLog;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/26 9:10
 * @modified By：
 * @version: 1.0.0
 */
@Data
public class WebRecordHandleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long queryId;

    private String result;

    private String remark;

    private String operator;
    /**
     * 1：查询历史；2：核档历史；3：客服历史；
     */
    private Integer type;

    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date queryTime;

    private CarProcedureLog customerRecord;

    private Long customerHandleId;
    /**
     * 结果
     */
    private String customerHandleResult;
    /**
     * 备注
     */
    private String customerHandleRemark;
    /**
     * 发生时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date customerHandleTime;
    /**
     * 操作人
     */
    private String customerHandleOperator;
    /**
     * 1:未完成;2完成；3:暂存 4：退车（对应客服）
     */
    private Integer customerHandleState;
}
