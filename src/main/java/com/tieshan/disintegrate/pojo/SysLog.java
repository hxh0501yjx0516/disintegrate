package com.tieshan.disintegrate.pojo;

import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

/**
 * @description: 系统日志表
 * @author: huxuanhua
 * @date: Created in 2019/9/24 19:55
 * @version: 1.0
 * @modified By:
 */
@Component
@Data
public class SysLog implements java.io.Serializable {
    private Long id;
    private Long disintegrate_plant_id;
    private Long operator_id;
    private String depart_name;
    private String operator;
    private String method_url;
    private String create_time;
}
