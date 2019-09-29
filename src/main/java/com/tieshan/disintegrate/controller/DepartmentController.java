package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.Department;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IDepartmentService;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @description: 测试控制类, 分页
 * @author: huxuanhua
 * @date: Created in 2019/8/28 18:07
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping(value = "depart")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询所有部门
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/allDepartment")
    public RestResult allDepartment(HttpServletRequest request,
                                    @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) int page,


                                    @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) int pageSize) {
        RestResult restResult = null;
//        PageInfo<Department> pageInfo = null;
        try {
            List<Department> departmentList = departmentService.allDepartment(page, pageSize);
//            pageInfo = new PageInfo<>(departmentList);
            restResult = new RestResult("", departmentList, ResultCode.SUCCESS.code());
        } catch (Exception e) {
            log.info("获取部门列表失败------->", e);
        }
        return restResult;

    }

    /**
     * 添加部门
     *
     * @param department
     * @param request
     * @return
     */
    @PostMapping(value = "/addDepart")
    public RestResult addDepart(@RequestBody Department department, HttpServletRequest request) {
        RestResult restResult = null;
        try {
            String token = request.getHeader("token");
            SysUser sysUser = tokenService.getToken(token);
            department.setOperator(sysUser.getLogin_name());
            department.setCompany_code(sysUser.getCompany_code());
            int num = departmentService.addDepart(department);
            if (num > 0) {
                restResult = new RestResult("部门添加成功", null, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("部门添加失败", null, ResultCode.ERROR.code());
            }
        } catch (Exception e) {
            log.info("添加部门列表失败------->", e);
            return new RestResult("部门添加失败", null, ResultCode.ERROR.code());

        }
        return restResult;

    }

    /**
     * 获取部门
     *
     * @param depart_name
     * @param request
     * @return
     */
    @GetMapping(value = "/getDepart")
    public RestResult getDepart(String depart_name, HttpServletRequest request) {
        Department department = null;
        RestResult restResult = null;
        try {
            department = departmentService.getDepart(depart_name);
            if (PubMethod.isEmpty(department)) {
                restResult = new RestResult("可以添加", null, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("部门已存在", null, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("获取部门失败------->", e);
            return new RestResult("获取部门失败", null, ResultCode.ERROR.code());
        }
        return restResult;


    }

    /**
     * 修改部门
     *
     * @param department
     * @param request
     * @return
     */

    @PostMapping(value = "/updateDepart")
    public RestResult updateDepart(@RequestBody Department department, HttpServletRequest request) {
        RestResult restResult = null;
        try {
            int num = departmentService.updateDepart(department);
            if (num > 0) {
                restResult = new RestResult("更新完成", null, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("更新失败", null, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("修改部门失败------->", e);
            return new RestResult("更新失败", null, ResultCode.ERROR.code());
        }

        return restResult;


    }

    /**
     * 删除部门
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "/delDepart")
    public RestResult delDepart(String id, HttpServletRequest request) {
        RestResult restResult = null;
        try {
            int num = departmentService.delDepart(Long.parseLong(id));
            if (num > 0) {
                restResult = new RestResult("删除成功", null, ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("删除失败", null, ResultCode.ERROR.code());

            }
        } catch (Exception e) {
            log.info("修改部门失败------->", e);
            return new RestResult("删除失败", null, ResultCode.ERROR.code());
        }

        return restResult;


    }
}
