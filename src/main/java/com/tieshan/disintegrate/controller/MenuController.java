package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.pojo.Menu;
import com.tieshan.disintegrate.pojo.Resource;
import com.tieshan.disintegrate.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:菜单控制类
 * @author: huxuanhua
 * @date: Created in 2019/8/30 17:08
 * @version: 1.0
 * @modified By:
 */
@RestController
@RequestMapping(value = "resource")
public class MenuController {
    @Autowired
    private IResourceService resourceService;
    @GetMapping( "/menus")
    public List<Menu> menus() {
        return resourceService.menus();
    }
    @GetMapping(value = "/treeList")
    public List<Resource> treeList() {
        return resourceService.treeList();
    }
}
