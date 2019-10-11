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

    /**状态码，暂存还是完成*/
    private String parentName;
    /**封装传入的图片*/
    private List<String> sonName;
}
