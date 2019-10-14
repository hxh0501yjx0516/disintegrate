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
public class PartsListVo implements Serializable {
    private static final long serialVersionUID = -1281208248971156925L;

    /**一级拆车件id*/
    private Long id;
    /**拆车件一级名称*/
    private String parts_name;
    /**拆车件编号*/
    private String parts_code;
}
