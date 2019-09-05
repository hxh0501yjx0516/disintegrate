package com.tieshan.disintegrate.pojo;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/5 9:55
 * @version: 1.0
 * @modified By:
 */
@Data
@Configuration
public class Department_user {
    private long id;
    private long user_id;
    private long department_id;
}
