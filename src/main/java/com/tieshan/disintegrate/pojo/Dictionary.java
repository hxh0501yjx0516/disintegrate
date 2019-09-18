package com.tieshan.disintegrate.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author ningfeng
 * @description:  字典
 * @date Created in 19:04 2019/9/18
 * @version: 1.0
 * @modified By:
 */
@Data
public class Dictionary {
    private Long id;
    // 解体厂的id
    private Long disintegratePlantId;
    // 一级类型
    private String firstType;
    // 二级类型
    private String twoType;
    // 等级
    private Integer req;
    private Date createTime;
    // 字段名字
    private String filedName;
    // 字段描述
    private String filedDescribe;
    // 是否删除。1.删除，2,正常
    private Integer isDelete;
    // 创建人
    private String createOperator;
    // 创建人id
    private Long createOperatorId;
}
