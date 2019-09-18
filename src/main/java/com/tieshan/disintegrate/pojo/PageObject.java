package com.tieshan.disintegrate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: Leavonson
 * @date: Created in 2019/9/17 20:46
 * @version: 1.0
 * @modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageObject<T> implements Serializable {
    private static final long serialVersionUID = 3533503218786489839L;
    /**
     * 当前页的页码值
     */
    private Integer pageCurrent = 1;
    /**
     * 页面大小
     */
    private Integer pageSize = 3;
    /**
     * 总行数(通过查询获得)
     */
    private Integer rowCount = 0;
    /**
     * 总页数(通过计算获得)
     */
    private Integer pageCount = 0;
    /**
     * 当前页记录
     */
    private List<T> records;
}
