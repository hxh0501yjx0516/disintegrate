package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.pojo.Resource;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IResourceService;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @description:菜单控制类
 * @author: huxuanhua
 * @date: Created in 2019/8/30 17:08
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping(value = "resource")
public class MenuController {
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询部门右测的资源
     *
     * @return
     */
    @GetMapping("/departTree")
    public RestResult departTree(String depart_id) {
        RestResult restResult = null;
        try {
            restResult = new RestResult("获取资源", resourceService.departTree(depart_id), ResultCode.SUCCESS.code());
        } catch (Exception e) {
            log.info("查询部门右测的资源失败------->", e);
            return new RestResult("查询部门右测的资源失败", null, ResultCode.ERROR.code());
        }
        return restResult;
    }

    /**
     * 查询登录人相关左侧菜单
     *
     * @return
     */
    @GetMapping(value = "/departMenus")
    public RestResult departMenus(HttpServletRequest request) {
        RestResult restResult = null;
        try {
            String token = request.getHeader("token");
            SysUser sysUser = tokenService.getToken(token);
            restResult = new RestResult("获取菜单", resourceService.departMenus(sysUser.getDepart_id() + ""), ResultCode.SUCCESS.code());
        } catch (Exception e) {
            log.info("获取菜单失败------->", e);
            return new RestResult("获取菜单失败", null, ResultCode.ERROR.code());
        }
        return restResult;
    }

    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    @PostMapping(value = "/addResource")
    public RestResult addResource(@RequestBody Resource resource, HttpServletRequest request) {
        RestResult restResult = null;
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        resource.setOperator(sysUser.getLogin_name());
        try {
            int num = resourceService.add(resource);
            if (num > 0) {
                restResult = new RestResult("添加成功", null, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("添加失败", null, ResultCode.ERROR.code());
            }
        } catch (Exception e) {
            log.info("添加资源接口报错----->" + e);
            restResult = new RestResult("添加失败", null, ResultCode.ERROR.code());
        }
        return restResult;
    }

    /**
     * 获取所有资源
     *
     * @return
     */
    @GetMapping(value = "/allTree")
    public RestResult resourceTree() {
        RestResult restResult = null;
        try {
            restResult = new RestResult("返回tree", resourceService.getResourceTree(), ResultCode.SUCCESS.code());
        } catch (Exception e) {
            log.info("获取资源失败----->" + e);
            return new RestResult("获取资源失败", null, ResultCode.ERROR.code());
        }
        return restResult;
    }

    /**
     * 修改部门和资源的关系
     *
     * @return
     */
    @PostMapping(value = "/updateDR")
    public RestResult updateDR(String depart_id, String resource_ids) {
        RestResult restResult = null;
        try {
            int num = resourceService.updateDR(depart_id, resource_ids);
            if (num > 0) {
                restResult = new RestResult("修改成功", null, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("修改失败", null, ResultCode.ERROR.code());
            }
        } catch (Exception e) {
            log.info("修改失败----->" + e);
            return new RestResult("修改失败", null, ResultCode.ERROR.code());
        }
        return restResult;
    }

    /**
     * 获取资源节点
     *
     * @return
     */
    @GetMapping(value = "/getNode")
    public RestResult getNode() {
        RestResult restResult = null;
        try {
            restResult = new RestResult("返回tree", resourceService.getNode(), ResultCode.SUCCESS.code());
        } catch (Exception e) {
            log.info("获取资源失败----->" + e);
            return new RestResult("获取资源失败", null, ResultCode.ERROR.code());
        }
        return restResult;
    }
}
