package com.tieshan.disintegrate.pojo;

import lombok.Data;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/4 15:42
 * @version: 1.0
 * @modified By:
 */
@Data
public class Department {
    private long id;
    private String department_name;
    private String department_status;
    private String department_describe;
    private String operator;
    private String create_time;
    private String update_time;
    private String company_code;
    private String seq;
}
