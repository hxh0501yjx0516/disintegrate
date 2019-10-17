package com.tieshan.disintegrate.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/10/17 10:38
 * @version: 1.0
 * @modified By:
 */
@Data
@ToString
public class top_pic {
    /**
     * CREATE TABLE `ts_top_pic` (
     *   `id` bigint(36) NOT NULL,
     *   `top_url` varchar(255) DEFAULT NULL COMMENT '头部图片',
     *   `h5_url` varchar(255) DEFAULT NULL COMMENT '要跳转的界面',
     *   `pic_type` char(1) DEFAULT '1' COMMENT '1:显示;2:不显示',
     *   `company_id` bigint(64) DEFAULT NULL COMMENT '拆解厂id',
     *   `create_time` datetime DEFAULT NULL,
     *   `operator` varchar(64) DEFAULT NULL COMMENT '创建人',
     *   PRIMARY KEY (`id`)
     * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='top图片';
     * */

    /**table主键id*/
    private Long id;
    /**解体厂id*/
    private Long company_id;
    /**Banner图地址*/
    private String top_url;
    /**链接地址*/
    private String h5_url;
    /**状态：1.正常 2.不显示*/
    private char pic_type;
    /**创建时间*/
    private Date create_time;
    /**创建人*/
    private String operator;

}
