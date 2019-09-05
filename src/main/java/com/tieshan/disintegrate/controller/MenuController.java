package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.pojo.Menu;
import com.tieshan.disintegrate.pojo.Resource;
import com.tieshan.disintegrate.service.IResourceService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
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

    @GetMapping("/menus")
    public RestResult menus() {
        RestResult restResult = new RestResult("返回目录", resourceService.menus(), ResultCode.SUCCESS.code());
        return restResult;
    }

    @GetMapping(value = "/treeList")
    public RestResult treeList() {
        RestResult restResult = new RestResult("返回tree", resourceService.treeList(), ResultCode.SUCCESS.code());
        return restResult;
    }
}
