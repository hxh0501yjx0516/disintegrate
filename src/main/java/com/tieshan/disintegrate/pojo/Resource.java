package com.tieshan.disintegrate.pojo;

import lombok.Data;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/8/30 17:27
 * @version: 1.0
 * @modified By:
 */
@Data
public class Resource implements java.io.Serializable {

/*    CREATE TABLE `resource` (
            `id` int(11) NOT NULL,
  `resource_name` varchar(64) DEFAULT NULL COMMENT '菜单名称',
            `resource_url` varchar(255) DEFAULT NULL COMMENT '菜单链接',
            `seq` int(11) DEFAULT NULL COMMENT '菜单排序',
            `pid` int(11) DEFAULT NULL COMMENT '父id',
            `resource_type` char(1) DEFAULT NULL COMMENT '菜单状态1:正常；2：删除',
            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
            `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
            `operator` varchar(64) DEFAULT NULL COMMENT '操作人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单';*/
    private String id;
    private String resource_name;
    private String resource_pname;
    private String resource_url;
    private String resource_describe;
    private int seq;
    private String pid;
    private String resource_type;
    private String create_time;
    private Integer modify_time;
    private String operator;
    private String department_id;
    private String department_name;
    private String isHaving;

}
