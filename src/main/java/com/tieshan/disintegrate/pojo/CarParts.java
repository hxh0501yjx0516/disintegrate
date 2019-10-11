package com.tieshan.disintegrate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description: 车辆拆件表
 * @author: Leavonson
 * @date: Created in 2019/10/11 9:41
 * @version: 1.0
 * @modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarParts {
/*    CREATE TABLE `ts_car_parts` (
            `id` bigint(36) NOT NULL,
  `car_info_id` bigint(36) DEFAULT NULL COMMENT 'car_info主键id',
            `disintegrate_plant_id` bigint(36) DEFAULT NULL COMMENT '解体厂id',
            `oe` varchar(255) DEFAULT NULL COMMENT 'oe号',
            `parts_name` varchar(255) DEFAULT NULL COMMENT '拆车件名称',
            `parts_stauts` int(1) DEFAULT NULL COMMENT '拆件状态(1：已打印，2：已入库)',
            `print_operator_id` bigint(36) DEFAULT NULL COMMENT '打印操作人id',
            `print_operator` varchar(255) DEFAULT NULL COMMENT '打印操作人姓名',
            `print_time` datetime DEFAULT NULL COMMENT '打印时间',
            `create_operator_id` bigint(36) DEFAULT NULL COMMENT '入库操作人id',
            `create_operator` varchar(255) DEFAULT NULL COMMENT '入库操作人姓名',
            `create_time` datetime DEFAULT NULL COMMENT '入库时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车辆拆件表';*/

    private Long id;
    /**car_info主键id*/
    private Long car_info_id;
    /**解体厂id*/
    private Long disintegrate_plant_id;
    /**oe号*/
    private String oe;
    /**拆车件名称*/
    private String parts_name;
    /**拆件状态(1：已打印，2：已入库)*/
    private Integer parts_status;
    /**打印操作人id*/
    private Long print_operator_id;
    /**打印操作人姓名*/
    private String print_operator;
    /**打印时间*/
    private Date print_time;
    /**入库操作人id*/
    private Long create_operator_id;
    /**入库操作人姓名*/
    private String create_operator;
    /**入库时间*/
    private Date create_time;

}
