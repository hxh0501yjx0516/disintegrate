package com.tieshan.disintegrate.pojo;

import lombok.Data;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/2 16:02
 * @version: 1.0
 * @modified By:
 */
@Data
public class SysUser implements java.io.Serializable {
    private Long id;
    private String login_name;
    private String user_name;
    private String user_password;
    private String phone;
    private String department_name;
    private String company_name;
    private String company_code;
    private String company_id;
    private String user_status;
    private String operator;
    private String login_time;
    private String loginout_time;
    private Long depart_id;
}
