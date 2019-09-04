package com.tieshan.disintegrate.pojo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/8/30 17:10
 * @version: 1.0
 * @modified By:
 */
@Data
public class Menu implements java.io.Serializable{
    private Long id;
    private String resource_url;
    private String name;
    private List<Menu> children;
}
