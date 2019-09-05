package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IUserService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * @description: 测试控制类, 分页
 * @author: huxuanhua
 * @date: Created in 2019/8/28 18:07
 * @version: 1.0
 * @modified By:
 */
@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping(value = "/addUser")
    public RestResult addUser(@RequestBody SysUser sysUser, HttpServletRequest request) {
        SysUser user = (SysUser) request.getSession().getAttribute("user");
        System.err.println(user);
        int num = userService.insert(sysUser);
        if (num == 1) {
            return new RestResult("添加成功", null, ResultCode.SUCCESS.code());

        } else {
            return new RestResult("添加失败", null, ResultCode.ERROR.code());

        }
    }

    @GetMapping(value = "/getUser")
    public RestResult getUser(HttpServletRequest request) {
        List<Map<String, Object>> mapList = userService.getUser();

        return new RestResult("用户信息", mapList, ResultCode.SUCCESS.code());


    }

    @PostMapping(value = "/updateUser")
    public RestResult updateUser(@RequestBody SysUser sysUser, HttpServletRequest request) {
        int num = userService.updateUser(sysUser);
        if (num == 1) {
            return new RestResult("更新成功", null, ResultCode.SUCCESS.code());

        } else {
            return new RestResult("更新失败", null, ResultCode.ERROR.code());

        }
    }

    @GetMapping(value = "/delUser")
    public RestResult delUser(String id, HttpServletRequest request) {
        int num = userService.delUser(id);

        if (num == 1) {
            return new RestResult("删除成功", null, ResultCode.SUCCESS.code());

        } else {
            return new RestResult("删除失败", null, ResultCode.ERROR.code());

        }


    }
}
