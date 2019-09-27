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
public class RecordHandleVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String result;

    private String remark;

    private String operator;

    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private CarProcedureLog customerRecord;
}
