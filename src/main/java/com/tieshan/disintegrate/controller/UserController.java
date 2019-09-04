package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description: 测试控制类,分页
 * @author: huxuanhua
 * @date: Created in 2019/8/28 18:07
 * @version: 1.0
 * @modified By:
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

//    @GetMapping(value = "/user")
//    public void getUser() {
//        System.err.println(userService.getUser());
//    }
//    @GetMapping(value = "/getMenu",produces = MediaType.APPLICATION_JSON_VALUE)
//    public PageInfo<Map<String,Object>> getMenu(@RequestParam(value="page", required=false, defaultValue= ConStants.PAGE) int page,
//                                                @RequestParam(value="pageSize", required=false, defaultValue=ConStants.PAGESIZE) int pageSize) {
//
//        List<Map<String, Object>> mapList = userService.getMenu();
//        PageInfo<Map<String,Object>> pageInfo = new PageInfo<Map<String,Object>>(mapList);
//        return pageInfo;
//
//    }
}
