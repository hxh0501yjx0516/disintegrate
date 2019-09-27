package com.tieshan.disintegrate.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/9/26 17:43
 * @version: 1.0
 * @modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarPreInfoVo {

    /**车型*/
    private String car_name;
    /**车辆Id*/
    private Long car_info_id;
    /**入场时间*/
    private Date approach_time;
    /**车牌号*/
    private String car_no;
    /**车辆编号*/
    private String car_code;
}
