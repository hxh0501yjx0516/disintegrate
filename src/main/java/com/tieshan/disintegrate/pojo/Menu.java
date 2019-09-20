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
public class Menu implements java.io.Serializable {
    private Long id;
    private String resource_url;
    private String resource_name;
    private String resource_pname;
    private String resource_type;
    private String isHaving;
    private Long pid;
    private List<Menu> children;

    public Menu() {
    }

    public Menu(Long id, String resource_url, String resource_name, Long pid, List<Menu> children) {
        this.id = id;
        this.resource_url = resource_url;
        this.resource_name = resource_name;
        this.pid = pid;
        this.children = children;
    }
}
