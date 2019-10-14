package com.tieshan.disintegrate.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/9/26 14:19
 * @version: 1.0
 * @modified By:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarPartsData implements Serializable {
    private static final long serialVersionUID = -1281208248971156925L;
    /**车辆Id*/
    private Long carInfoId;
    /**车辆编号*/
    private String carCode;
    /**状态码，打印还是入库*/
    private Integer partsStatus;
    /**封装传入的数据，件编号、件名*/
    private List<Map<String,Object>> data;
}
