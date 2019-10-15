package com.tieshan.disintegrate.pojo;

import lombok.Data;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/10/14 11:30
 * @version: 1.0
 * @modified By:
 */
@Data
public class Notice {
    private Long id;
    private String msg_title;
    private String msg_content;
    private String type;
    private String device_type;
    private String datetime;
    private String operator;
    private String disintegrate_plant_id;
}
