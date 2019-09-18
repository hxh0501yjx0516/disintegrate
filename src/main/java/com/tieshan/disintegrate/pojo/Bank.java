package com.tieshan.disintegrate.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:   车源的银行信息
 * @author ningfeng
 * @date 2019-09-18:17:05
 * @version: 1.0
 * @modified By:
 */
@Data
public class Bank implements Serializable {
    private Long id;
    // 解体厂的id
    private Long disintegratePlantId;
    // 银行名称
    private String bankName;
    // 支行名称
    private String bankBranch;
    // 银行账户
    private String account;
    // 账户姓名
    private String payee;
    // 创建时间
    private Date createTime;
    // 操作人id
    private Long operatorId;
    // 操作人
    private String operator;
    // 是否删除。1.删除，2,正常
    private Integer isDelete;
}
