package com.tieshan.disintegrate.vo;

import com.tieshan.disintegrate.pojo.CarIdentity;
import lombok.Data;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/19 10:13
 * @modified By：
 * @version: 1.0.0
 */
@Data
public class ProceduresVo extends CarIdentity {

    /**
     * 交车人
     */
    private String contacts;
    /**
     * 手机号
     */
    private String contactsPhone;
    /**
     * 交车人地址
     */
    private String contactsAddress;
    /**
     * 行驶证
     */
    private Integer drivLicense;
    // 登记证
    private Integer registLicense;


}
