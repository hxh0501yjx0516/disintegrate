package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.CarsQuery;
import com.tieshan.disintegrate.service.CarsQueryService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:车辆查询页面展示
 * @author: Leavonson
 * @date: Created in 2019/9/17 16:36
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
public class CarsQueryController {

    @Autowired
    private CarsQueryService carsQueryService;

    /**
     * showdoc
     * @catalog 车辆查询
     * @title 车辆列表
     * @description 车辆列表首页查询接口
     * @method get
     * @url http://localhost:8002/doCarsQuery/doFindPageObjects
     * @param findMsg 可选 string 查询条件(姓名 / 电话 / 车型 / 车牌号 / 车辆编号 / 车架号 / 发动机号)
     * @param page 可选 int 当前的页码值
     * @param pageSize 可选 int 页面大小
     * @return {"pageNum": 1,"pageSize": 10,"size": 2,"startRow": 1,"endRow": 2,"total": 2,"pages": 1,"list": [{"id": 22,"carNo": "A5W44C","cardColor": "蓝色","carName": "车型","carKind": "车辆性质","natureUsege": "使用性质","carProperties": "'车辆性质","regTime": "2019-09-16T16:00:00.000+0000","issueTime": "2019-09-16T16:00:00.000+0000","owner": "张三","phone": "13345678910","contacts": "zhangsanfeng","contactsPhone": "13888888888","vin": "VIN码","engine": "发动机号码"}],"prePage": 0,"nextPage": 0,"isFirstPage": true,"isLastPage": true,"hasPreviousPage": false,"hasNextPage": false,"navigatePages": 8,"navigatepageNums": [1],"navigateFirstPage": 1,"navigateLastPage": 1,"lastPage": 1,"firstPage": 1}
     * @return_param id Long 车辆编号
     * @return_param carNo string 车牌号
     * @return_param cardColor string 车牌颜色
     * @return_param carName string 品牌型号
     * @return_param carKind string 车辆性质
     * @return_param natureUsege string 使用性质
     * @return_param carProperties string 车辆类型
     * @return_param regTime Date 注册日期
     * @return_param issueTime Date 发证日期
     * @return_param owner string 车主姓名
     * @return_param phone string 车主电话
     * @return_param contacts string 交车人姓名
     * @return_param contactsPhone string 交车人电话
     * @return_param vin string 车辆VIN码
     * @return_param engine string 发动机号
     * @remark 基于条件分页查询车辆信息
     * @number 1
     */

    @GetMapping("/doCarsQuery/doFindPageObjects")
    public PageInfo<CarsQuery> doFindPageObjects(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize
    ) {
        PageInfo<CarsQuery> pageInfo = null;
        try {
            List<CarsQuery> list = carsQueryService.findPageObjects(findMsg, page,pageSize);
            pageInfo = new PageInfo<>(list);
        } catch (Exception e) {
            log.info("获取车辆信息列表失败", e);
        }

        return pageInfo;
    }

}
