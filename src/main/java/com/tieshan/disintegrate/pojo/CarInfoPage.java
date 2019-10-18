package com.tieshan.disintegrate.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author ningfeng
 * @description:
 * @date Created in 11:29 2019/10/14
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarInfoPage implements Serializable {
    // 车辆id
    private Long id;
    // 车辆编号
    private String carCode;
    // 车牌号
    private String carNo;
    // 车型
    private String carName;
    // 汽车排量
    private String displacement;
    // 车辆的vin码
    private String vin;
    // 状态码  1未初检， 2已初检未预处理，3已预处理未拓号，4已拓号未存放车辆位置，5，已存放车辆位置未核档，6已核档未毁形，7已毁形未上传报废手续，8已上传报废手续
    private String status;
    // 车辆初检的信息      初检信息 status大于或等于2时有值，否则为空
    private CarSurvey carSurvey;
    // 车辆图片集   拓号/预处理/毁型/保费证明的图片
    private Map<String, List<Map<String, Object>>> map;
    // 车辆的存放位置
    private String carAddress;
    // 车辆核档的状态
    private Integer isVerify;
    // 车辆核档不通过的原因
    private String verifyReason;
}
