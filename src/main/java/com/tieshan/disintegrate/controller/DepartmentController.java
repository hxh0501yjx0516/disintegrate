package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.pojo.Department;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IDepartmentService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
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
@RestController
@RequestMapping(value = "depart")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    /**
     * 查询所有部门
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/allDepartment")
    public RestResult allDepartment(HttpServletRequest request) {
        List<Department> departmentList = departmentService.allDepartment();
        return new RestResult("部门信息", departmentList, ResultCode.SUCCESS.code());

    }

    @PostMapping(value = "/addDepart")
    public RestResult addDepart(@RequestBody Department department, HttpServletRequest request) {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        department.setId(idWorker.nextId());
        SysUser sysUser = (SysUser) request.getSession().getAttribute("user");
        department.setOperator(sysUser.getLogin_name());
        department.setDepartment_status("1");
        int num = departmentService.addDepart(department);
        if (num == 1) {
            return new RestResult("部门添加成功", null, ResultCode.SUCCESS.code());
        }
        return new RestResult("部门添加失败", null, ResultCode.ERROR.code());


    }

    @GetMapping(value = "/getDepart")
    public RestResult getDepart(String depart_name, HttpServletRequest request) {
        Department department = departmentService.getDepart(depart_name);
        if (PubMethod.isEmpty(department)) {
            return new RestResult("可以添加此部门", department, ResultCode.SUCCESS.code());
        }
        return new RestResult("部门已存在", department, ResultCode.ERROR.code());


    }

    @PostMapping(value = "/updateDepart")
    public RestResult updateDepart(@RequestBody Department department, HttpServletRequest request) {
        int num = departmentService.updateDepart(department);
        if (num == 1) {
            return new RestResult("部门更新完成", null, ResultCode.SUCCESS.code());
        }
        return new RestResult("部门更新失败", null, ResultCode.ERROR.code());


    }

    @GetMapping(value = "/delDepart")
    public RestResult delDepart(String id, HttpServletRequest request) {
        int num = departmentService.delDepart(Long.parseLong(id));
        if (num == 1) {
            return new RestResult("部门删除成功", null, ResultCode.SUCCESS.code());
        }
        return new RestResult("部门删除失败", null, ResultCode.ERROR.code());


    }
}
