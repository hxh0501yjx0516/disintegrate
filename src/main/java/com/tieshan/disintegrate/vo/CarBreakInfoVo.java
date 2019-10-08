package com.tieshan.disintegrate.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @description: 拆解管理对应封装实体类
 * @author: Leavonson
 * @date: Created in 2019/10/8 9:46
 * @version: 1.0
 * @modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarBreakInfoVo {

    /**车辆编号*/
    private String carCode;
    /**车牌号*/
    private String carNo;
    /**车型*/
    private String carName;
    /**交车人*/
    private String contacts;
    /**交车人电话*/
    private String contactsPhone;
    /**毁型前照片*/
    private List<Map<String,Object>> prePic;
    /**毁型后照片*/
    private List<Map<String,Object>> breakPic;

}
