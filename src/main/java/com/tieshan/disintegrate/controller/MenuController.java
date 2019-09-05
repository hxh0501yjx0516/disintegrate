package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.pojo.Menu;
import com.tieshan.disintegrate.pojo.Resource;
import com.tieshan.disintegrate.service.IResourceService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    @PostMapping(value = "/addResource")
    public RestResult addResource(@RequestBody Resource resource) {
        int num = resourceService.add(resource);
        if (num == 1) {

            return new RestResult("添加成功", null, ResultCode.SUCCESS.code());
        }
        return new RestResult("添加失败", null, ResultCode.ERROR.code());
    }

    /**
     * 获取所有资源
     *
     * @return
     */
    @GetMapping(value = "/resourceTree")
    public RestResult resourceTree() {
        RestResult restResult = new RestResult("返回tree", resourceService.getResourceTree(), ResultCode.SUCCESS.code());
        return restResult;
    }

    /**
     * 修改部门和资源的关系
     *
     * @return
     */
    @PostMapping(value = "/updateDR")
    public RestResult updateDR(String department_id, String resource_ids) {
        int num = resourceService.updateDR(department_id, resource_ids);
        if (num != 0) {

            return new RestResult("修改成功", null, ResultCode.SUCCESS.code());
        }
        return new RestResult("修改失败", null, ResultCode.ERROR.code());
    }

    /**
     * 获取部门的资源
     *
     * @return
     */
    @GetMapping(value = "/getResourceByDpartId")
    public RestResult getResourceByDpartId(String department_id) {
        RestResult restResult = new RestResult("获取部门资源", resourceService.getResourceByDepartId(department_id), ResultCode.SUCCESS.code());
        return restResult;
    }

}
