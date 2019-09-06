package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.pojo.Department;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.ICarSourceService;
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
 * @author: renlei
 * @date: Created in 2019/9/6 11:27
 * @version: 1.0
 * @modified By:
 */
@RestController
@RequestMapping("/carSource")
public class CarSourceController {

    @Autowired
    private ICarSourceService carSourceService;


    @PostMapping(value = "/add")
    public RestResult addDepart(@RequestBody CarSource carSource, HttpServletRequest request) {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        carSource.setId(idWorker.nextId());
        carSourceService.add(carSource);
        return new RestResult("添加成功", null, ResultCode.SUCCESS.code());
    }

}
