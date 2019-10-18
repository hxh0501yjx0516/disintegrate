package com.tieshan.disintegrate.pojo;

import lombok.Data;
import org.hibernate.exception.DataException;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ningfeng
 * @description: 车辆入场管理
 * @date Created in 11:27 2019/9/24
 * @version: 1.0
 * @modified By:
 */
@Data
public class CarEnter implements Serializable {
    // 车辆入场主键id
    private Long id;
    // 解体厂id
    private Long disintegratePlantId;
    // 车辆主键的id
    private Long carInfoId;
    // 车辆入场状态：  1:未入场；2：已入场
    private Integer isApproach;
    // 入场时间
    private Date approachTime;
    // 入场员工的主键
    private Long approachUserId;
    // 初检时间
    private Date initialSurveyTime;
    // 初检人的主键id
    private Long initialSurveyUserId;
    // 车辆初检的状态    1:未初检;2:已初检
    private Integer isInitialSurvey;
    // 打印软牌时间
    private Date printCodeTime;
    // 打印软牌的人主键id
    private Long printCodeUserId;
    // 车辆软牌的状态    1:未打印软牌;2:已打印软牌（应该是二维码贴在车上的
    private Integer softPrint;
    // 预处理的时间
    private Date pretreatmentTime;
    // 预处理的人id
    private Long pretreatmentUserId;
    // 车辆预处理的状态：  1:车辆未预处理;2:车辆预处理
    private Integer isPretreatment;
    // 拓号时间
    private Date copyNumberTime;
    // 拓号人
    private Long copyNumberUserId;
    // 括号的状态；  1：未拓号；2：已拓号；
    private Integer isCopyNumber;
    // 拆解人（用户表主键id)
    private Long dismantleUserId;
    // 确认拆解时间
    private Date dismantleTime;
    // 拆解方式(5:无 1:粗拆 2:点拆 3:包拆 4:精拆)
    private Integer dismantleWay;
    // 车辆位置
    private String carLocation;
    // 车辆位置id（待开发确定是否应用）
    private Long locationId;
    // 车辆是否出库(1:未出库;2:出库）
    private Integer isOutput;
    // 出库时间
    private Date outputTime;
    // 出库操作人
    private String outputUser;
    // 是否删除  1.删除，2,正常
    private Integer isDelete;
}
