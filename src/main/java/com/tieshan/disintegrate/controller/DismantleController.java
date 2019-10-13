package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IDismantleService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import com.tieshan.disintegrate.vo.CarBreakInfoVo;
import com.tieshan.disintegrate.vo.CarPartsData;
import com.tieshan.disintegrate.vo.PartsListVo;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @description: 拆解管理模块控制层
 * @author: Leavonson
 * @date: Created in 2019/9/29 17:31
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
public class DismantleController {

    @Autowired
    private IDismantleService iDismantleService;
    /**
     * showdoc
     *
     * @param findMsg  可选 string 查询条件(姓名 / 电话 / 车型 / 车牌号 / 车辆编号)
     * @param page     可选 int 当前的页码值
     * @param pageSize 可选 int 页面大小
     * @return {"msg": "查询拆解车辆列表信息成功","data": {"pageNum": 1,"pageSize": 1,"size": 1,"startRow": 0,"endRow": 0,"total": 1,"pages": 1,"list": [{"carCode": "TSXXX190902236","carNo": "京A01949","carName": "红旗001","contacts": "王大人","contactsPhone": "13888888888","prePic": [{"file_url": "www.asdasdasd"},{"file_url": "www.ggggggg"},{"file_url": "www.hhhhhhhhhhh"},{"file_url": "www.rrrrrr"},{"file_url": "www.gaaaaeeed"},{"file_url": "www.kkkkkkkkk"}],"breakPic": [{"file_url": "https://aaaaa"},{"file_url": "https://aaaaa"}]}],"prePage": 0,"nextPage": 0,"isFirstPage": true,"isLastPage": true,"hasPreviousPage": false,"hasNextPage": false,"navigatePages": 8,"navigatepageNums": [1],"navigateFirstPage": 1,"navigateLastPage": 1,"lastPage": 1,"firstPage": 1},"ret_code": "0"}
     * @catalog 解体厂-PC/拆解管理
     * @title 查询车辆列表
     * @description 拆解管理模块首页查询接口
     * @method get
     * @url http://localhost:8002/doCarsQuery/findDismantleObject
     * @return_param msg string 描述信息
     * @return_param data List 数据
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @return_param carCode Long 车辆编号
     * @return_param carNo string 车牌号
     * @return_param carName string 品牌型号
     * @return_param contacts string 交车人姓名
     * @return_param contactsPhone string 交车人电话
     * @return_param prePic List 拆解前照片集合
     * @return_param breakPic List 拆解后照片集合
     * @return_param file_url string 图片路径
     * @remark 基于条件分页查询拆解车辆信息
     * @number 1
     */

    /** PC-拆解管理模块查询接口*/
    @GetMapping("/doCarsQuery/findDismantleObject")
    public RestResult findDismantleObject(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
            @LoginUser SysUser user){
        PageInfo<CarBreakInfoVo> pageInfo = null;
        List<CarBreakInfoVo> list = iDismantleService.findDismantleList(findMsg,page, pageSize,user);
        pageInfo = new PageInfo<>(list);
        RestResult restResult = new RestResult("查询拆解车辆列表信息成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }

    /**.
     * 查询监销和不监销车辆   (搜索条件：车辆编号，车牌号，车型)  isSuperviseSale: 1-监销，2-不监销
     * @param request
     * @return
     */
    @GetMapping("/doCarsQuery/selectIsSuperviseSale")
    public RestResult selectIsDestructive(HttpServletRequest request,
                                          @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                          @RequestParam(value = "findMsg", required = false) String findMsg,
                                          @RequestParam(value = "isSuperviseSale", required = false, defaultValue = "1") Integer isSuperviseSale){
        PageInfo pageInfo = null;
        try{
            List<Map<String, Object>> mapList = iDismantleService.selectIsSuperviseSale(request, page, pageSize, findMsg, isSuperviseSale);
            pageInfo = new PageInfo(mapList);
        }catch (Exception e){
            log.info("查询失败", e);
            return new RestResult("查询失败", pageInfo, ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", pageInfo, ResultCode.SUCCESS.code());
    }


    /**
     * 查询待拆和已拆车辆(搜索条件：车辆编号，车牌号，车型)     isDismantle： 1-带拆车，2-已拆车
     * @param request
     * @param page
     * @param pageSize
     * @param findMsg
     * @return
     */
    @GetMapping("/doCarsQuery/selectIsDismantle")
    public RestResult selectIsDismantle(HttpServletRequest request,
                                        @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                        @RequestParam(value = "findMsg", required = false) String findMsg,
                                        @RequestParam(value = "isDismantle", required = false, defaultValue = "1") Integer isDismantle){
        PageInfo pageInfo = null;
        try{
            List<Map<String, Object>> mapList = iDismantleService.selectIsDismantle(request, page, pageSize, findMsg, isDismantle);
            pageInfo = new PageInfo(mapList);
        }catch (Exception e){
            log.info("查询失败", e);
            return new RestResult("查询失败", pageInfo, ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", pageInfo, ResultCode.SUCCESS.code());
    }

    /**
     * 查询所有已拆的件（搜索条件：车辆编号，车牌号，车型）    查询该拆解厂下当前操作人所拆的件
     * @param request
     * @param page
     * @param pageSize
     * @param findMsg
     * @return
     */
    @GetMapping("/doCarsQuery/selectCarPartsList")
    public RestResult selectCarPartsList(HttpServletRequest request,
                                         @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                         @RequestParam(value = "findMsg", required = false) String findMsg){
        PageInfo pageInfo = null;
        try{
            List<Map<String, Object>> mapList = iDismantleService.selectCarParts(request, page, pageSize, findMsg);
            pageInfo = new PageInfo(mapList);
        }catch (Exception e){
            log.info("查询失败", e);
            return new RestResult("查询失败", pageInfo, ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", pageInfo, ResultCode.SUCCESS.code());
    }

    /**
     *@Description: APP-待拆车-确认拆解接口
     * @param: carInfoId
     * @param: user
     * @return:
     */
    @GetMapping("/doCarsQuery/updateDismantle")
    public RestResult updateDismantle(
            @RequestParam(value = "carInfoId") Long carInfoId,@LoginUser SysUser user){
        iDismantleService.updateDismantle(user.getId(),carInfoId,user.getCompany_id());
        RestResult restResult = new RestResult("确认拆解成功", null, ResultCode.SUCCESS.code());
        return restResult;
    }

    /**
     *@Description: APP-拆车-查询打印配件名称接口
     * @return:
     */
    @GetMapping("/doCarsQuery/findPartsNameList")
    public RestResult findPartsNameList(){
        List<Map<String,Object>> mapList = iDismantleService.findPartsNameList();
        RestResult restResult = new RestResult("查询成功", mapList, ResultCode.SUCCESS.code());
        return restResult;
    }

    /**
     *@Description: APP-拆车-打印配件后入库接口
     * @param: carPartsData 封装的数据
     * @param: user
     * @return:
     */
    @PostMapping("/doCarsQuery/addCarParts")
    public RestResult addCarParts(@RequestBody CarPartsData carPartsData,@LoginUser SysUser user){
        int rows  = iDismantleService.addCarParts(carPartsData,user);
        RestResult restResult = new RestResult("打印成功,打印了"+rows+"条数据", rows, ResultCode.SUCCESS.code());
        return restResult;
    }

    /**
     *@Description: APP-拆车-查询一级分类列表接口
     * @return:
     */
    @GetMapping("/doCarsQuery/findFirstPartsName")
    public RestResult findFirstPartsName(){
        List<PartsListVo> mapList = iDismantleService.findFirstPartsName();
        RestResult restResult = new RestResult("查询成功", mapList, ResultCode.SUCCESS.code());
        return restResult;
    }
    /**
     *@Description: APP-拆车-根据一级id查询二级分类列表接口
     * @return:
     */
    @GetMapping("/doCarsQuery/findSecondPartsName")
    public RestResult findSecondPartsName(@RequestParam("id")Long parentId){
        List<PartsListVo> mapList = iDismantleService.findSecondPartsName(parentId);
        RestResult restResult = new RestResult("查询成功", mapList, ResultCode.SUCCESS.code());
        return restResult;
    }


}
