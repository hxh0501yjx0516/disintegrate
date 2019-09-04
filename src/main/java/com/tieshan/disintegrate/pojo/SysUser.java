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
public class SysUser implements java.io.Serializable  {
    private Long id;
    private String login_name;
    private String user_name;
    private String user_password;
    private String phone;
    private String user_status;
    private String operator;
}
