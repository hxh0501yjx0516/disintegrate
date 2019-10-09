package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IFinancialService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import com.tieshan.disintegrate.vo.CarSalvageVo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:财务管理模块控制类
 * @author: Leavonson
 * @date: Created in 2019/10/8 18:09
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
public class FinancialController {
    @Autowired
    private IFinancialService iFinancialService;
    /**
     * showdoc
     *
     * @param findMsg  可选 string 查询条件(姓名 / 电话 / 车型 / 车牌号 / 车辆编号)
     * @param page     可选 int 当前的页码值
     * @param pageSize 可选 int 页面大小
     * @return {"msg": "查询车辆残值信息成功","data": {"pageNum": 1,"pageSize": 10,"size": 1,"startRow": 1,"endRow": 1,"total": 1,"pages": 1,"list": [{"carCode": "TSXXX190902236","carNo": "京A01949","carName": "红旗001","contacts": "王大人","contactsPhone": "13888888888","owner": "宁峰","phone": "13345678910","salvage": "2000"}],"prePage": 0,"nextPage": 0,"isFirstPage": true,"isLastPage": true,"hasPreviousPage": false,"hasNextPage": false,"navigatePages": 8,"navigatepageNums": [1],"navigateFirstPage": 1,"navigateLastPage": 1,"lastPage": 1,"firstPage": 1},"ret_code": "0"}
     * @catalog 解体厂-PC/财务管理/价格导入
     * @title 基于条件分页查询车辆残值信息
     * @description 财务管理-价格导入模块查询接口
     * @method get
     * @url http://localhost:8002/doCarsQuery/findCarInfoAndSalvage
     * @return_param msg string 描述信息
     * @return_param data List 数据
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @return_param carCode Long 车辆编号
     * @return_param carNo string 车牌号
     * @return_param carName string 品牌型号
     * @return_param contacts string 交车人姓名
     * @return_param contactsPhone string 交车人电话
     * @return_param owner string 车主
     * @return_param phone string 车主电话
     * @return_param salvage string 残值金额
     * @remark 基于条件分页查询车辆残值信息
     * @number 1
     */

    /**财务管理价格导入模块查询接口*/
    @GetMapping("/doCarsQuery/findCarInfoAndSalvage")
    public RestResult findCarInfoAndSalvage(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
            @LoginUser SysUser user){
        PageInfo<CarSalvageVo> pageInfo = null;
        List<CarSalvageVo> list = iFinancialService.findCarInfoAndSalvage(findMsg,page, pageSize,user);
        pageInfo = new PageInfo<>(list);
        RestResult restResult = new RestResult("查询车辆残值信息成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }
}
