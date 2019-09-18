package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IUserService;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * @description: 用户管理
 * @author: huxuanhua
 * @date: Created in 2019/8/28 18:07
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 添加用户
     *
     * @param sysUser
     * @param request
     * @return
     */
    @PostMapping(value = "/addUser")
    public RestResult addUser(@RequestBody SysUser sysUser, HttpServletRequest request) {
        RestResult restResult = null;
        if (PubMethod.isEmpty(sysUser) || PubMethod.isEmpty(sysUser.getLogin_name())
                || PubMethod.isEmpty(sysUser.getUser_name()) || PubMethod.isEmpty(sysUser.getUser_password())
                || PubMethod.isEmpty(sysUser.getDepart_id()) || PubMethod.isEmpty(sysUser.getPhone())) {

            restResult = new RestResult("参数不能为空", null, ResultCode.ERROR.code());
        }
        try {
            int num = userService.insert(sysUser);
            if (num > 0) {
                restResult = new RestResult("添加成功", null, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("添加失败", null, ResultCode.ERROR.code());
            }
        } catch (Exception e) {
            log.info("添加员工失败异常------->", e);
            restResult = new RestResult("添加失败", null, ResultCode.ERROR.code());
            return restResult;

        }
        return restResult;

    }

    /**
     * 获取员工列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getUser")
    public PageInfo<Map<String, Object>> getUser(@RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) int page,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) int pageSize) {
        PageInfo<Map<String, Object>> pageInfo = null;
        try {
            List<Map<String, Object>> mapList = userService.getUser(page, pageSize);
            pageInfo = new PageInfo<>(mapList);
        } catch (Exception e) {
            log.info("获取员工列表失败------->", e);
        }

        return pageInfo;


    }

    /**
     * 修改用户信息
     *
     * @param sysUser
     * @param request
     * @return
     */
    @PostMapping(value = "/updateUser")
    public RestResult updateUser(@RequestBody SysUser sysUser, HttpServletRequest request) {
        try {
            int num = userService.updateUser(sysUser);
            if (num > 0) {
                return new RestResult("更新成功", null, ResultCode.SUCCESS.code());

            } else {
                return new RestResult("更新失败", null, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("修改用户失败------->", e);
            return new RestResult("更新失败", null, ResultCode.ERROR.code());

        }
    }

    /**
     * 删除员工信息（逻辑删除）
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "/delUser")
    public RestResult delUser(String id, HttpServletRequest request) {
        RestResult restResult = null;
        try {
            int num = userService.delUser(id);

            if (num == 1) {
                restResult = new RestResult("删除成功", null, ResultCode.SUCCESS.code());

            } else {
                restResult = new RestResult("删除失败", null, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("删除员工失败---->", e);
            return new RestResult("删除失败", null, ResultCode.ERROR.code());

        }
        return restResult;

    }

    /**
     * 通过用户主键id查询用户信息
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "/getUserByid")
    public RestResult getUserByid(String id, HttpServletRequest request) {
        RestResult restResult = null;
        try {
            Map<String,Object>resultMap = userService.getUserByid(id);
            restResult = new RestResult("获取用户信息", resultMap, ResultCode.SUCCESS.code());
        } catch (Exception e) {
            log.info("通过用户主键id获取用户失败---->", e);
            return new RestResult("获取失败", null, ResultCode.ERROR.code());

        }
        return restResult;

    }
}
