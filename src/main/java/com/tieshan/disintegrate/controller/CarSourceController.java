package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.ICarSourceService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;


/**
 * @description: 测试控制类, 分页
 * @author: ningfeng
 * @date: Created in 2019/9/6 11:27
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping("/carSource")
public class CarSourceController {

    @Autowired
    private ICarSourceService carSourceService;

    /**
     *  PC： 查询某拆解厂的指定的车源状态或者搜索条件查询车源的信息
     * @param sourceType    车源状态
     * @param findMsg   搜索条件
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/selectCarSourceList")
    public PageInfo<Map<String, Object>> selectCarSourceList(@RequestParam(value = "sourceType",required = false, defaultValue = "1") String sourceType,
                                                             @RequestParam(value = "findMsg", required = false) String findMsg,
                                                             @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                                             @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                                             HttpServletRequest request) {
        List<Map<String, Object>> mapList = carSourceService.selectCarSourceList(sourceType, findMsg, page, pageSize, request);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(mapList);
        return pageInfo;
    }


//    /**
//     * APP端：查询某拆解厂的某业务员的车源列表管理    再查一次数据 放在开发文档上
//     *
//     * @return
//     */
//    @GetMapping("/selectCarSourceListApp")
//    public RestResult selectCarSourceListApp(HttpServletRequest request) {
//        return new RestResult("查询成功", carSourceService.selectCarSourceListApp(request), ResultCode.SUCCESS.code());
//    }

    /**
     * 查询所有的用户的姓名和id （业务员）    2    过
     *
     * @return
     */
    @GetMapping(value = "/findUserNameList")
    public RestResult findUserNameList() {
        return new RestResult("查询成功", carSourceService.findUserNameList(), ResultCode.SUCCESS.code());
    }

    /**
     * 查询所有银行的信息    3     过
     *
     * @return
     */
    @GetMapping(value = "/findBankNameList")
    public RestResult findBankNameList() {
        return new RestResult("查询成功", carSourceService.findBankNameList(), ResultCode.SUCCESS.code());
    }

    /**
     * 查询指定车源的信息   4    过
     *
     * @param id 车源的id
     * @return
     */
    @GetMapping(value = "selectCarSource")
    public RestResult selectCarSource(Long id, HttpServletRequest request) {
        return new RestResult("查询成功", carSourceService.selectCarSource(id, request), ResultCode.SUCCESS.code());
    }

    /**
     *   查询指定车源下的车辆    5    过
     * @param id   车源主键id
     * @param request
     * @param isVerify  核档状态 1:未核档；2：已核档；3:核档不通过
     * @return
     */
    @GetMapping(value = "selectCarInfoList")
    public RestResult selectCarInfoList(Long id, HttpServletRequest request,
                                        @RequestParam(value = "isVerify",required = false, defaultValue = "1") Integer isVerify) {
        return new RestResult("查询成功", carSourceService.selectCarInfoList(id, request, isVerify), ResultCode.SUCCESS.code());
    }


    /**
     * PC：增加车源     6    对象中的对象接受不到前端传过来的数据     过
     *
     * @param carSource
     * @param request
     * @return
     */
    @PostMapping(value = "/addCarSource")
    public RestResult addCarSource(@RequestBody CarSource carSource, HttpServletRequest request) {
        try {
            carSourceService.addCarSource(carSource, request);
        } catch (Exception e) {
            log.info("添加车源失败", e);
            return new RestResult("添加车源失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("添加车源成功", null, ResultCode.SUCCESS.code());
    }

//    /**
//     * APP: 增加车源    前端不用返回业务员id
//     * @param carSource
//     * @param request
//     * @return
//     */
//    @PostMapping(value = "addCarSourceApp")
//    public RestResult addCarSourceApp(@RequestBody CarSource carSource, HttpServletRequest request){
//        try{
//            carSourceService.addCarSourceApp(carSource, request);
//        }catch (Exception e){
//            log.info("添加车源失败", e);
//            return new RestResult("添加车源失败", "", ResultCode.ERROR.code());
//        }
//        return new RestResult("添加车源成功", "", ResultCode.SUCCESS.code());
//    }


    /**
     * 获得所有车辆处理方式和手续获取方式，以map结果放回    7     过
     *
     * @return
     */
    @GetMapping(value = "/selectProcessingTypeAndProceduresType")
    public RestResult selectProcessingTypeAndProceduresType() {
        return new RestResult("查询成功", carSourceService.selectProcessingTypeAndProceduresType(), ResultCode.SUCCESS.code());
    }

    /**
     * 添加车辆    8     日期转换    过
     *
     *
     * @param carInfo
     * @param carSource      车源主键id
     * @param request
     * @return
     */
    @PostMapping(value = "/addCar")
    public RestResult addCar(@RequestBody CarInfo carInfo, @RequestParam(value = "carSource") Long carSource, HttpServletRequest request) {
        try {
            carSourceService.addCar(carInfo, carSource, request);
        } catch (Exception e) {
            log.info("添加车辆失败", e);
            return new RestResult("添加失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("添加成功", null, ResultCode.SUCCESS.code());
    }

    /**
     *  通过主键id查询车辆信息   9   显示查询成功，但是没有数据     过
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "selectCarInfoById")
    public RestResult selectCarInfoById(Long id, HttpServletRequest request) {
        return new RestResult("查询成功", carSourceService.selectCarInfoById(id, request), ResultCode.SUCCESS.code());
    }


    /**
     * 编辑车辆信息   10    过
     *
     * @param carInfo
     * @return
     */
    @PostMapping(value = "/editCar")
    public RestResult editCar(@RequestBody CarInfo carInfo) {
        try{
            carSourceService.editCar(carInfo);
        }catch (Exception e){
            log.info("更新车辆信息失败", e);
            return new RestResult("更新失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("更新成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 删除车辆    11     过
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping(value = "/deleteCarInfoById")
    public RestResult deleteCarInfoById(Long id, HttpServletRequest request){
        try{
            carSourceService.deleteCarInfoById(id, request);
        }catch (Exception e){
            log.info("删除车辆失败", e);
            return  new RestResult("删除失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("删除成功", "", ResultCode.SUCCESS.code());
    }

    /**
     *  查询指定车源的信息和银行信息   12    过
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "/selectCarSourceById")
    public RestResult selectCarSourceById(Long id, HttpServletRequest request){
        return new RestResult("查询成功", carSourceService.selectCarSourceById(id, request), ResultCode.SUCCESS.code());
    }

    /**
     * 更新车源的信息  13      过
     * @param carSource
     * @return
     */
    @PostMapping(value = "/editCarSource")
    public RestResult editCarSource(@RequestBody CarSource carSource){
        try{
            carSourceService.editCarSource(carSource);
        }catch(Exception e){
            log.info("更新车源信息失败", e);
            return new RestResult("更新失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("更新成功", "", ResultCode.SUCCESS.code());
    }

//    /**
//     * 删除指定的车源     14
//     * @param id
//     * @param request
//     * @return
//     */
//    @DeleteMapping(value = "/deleteCarSourceById")
//    public RestResult deleteCarSourceById(Long id, HttpServletRequest request){
//        try{
//            carSourceService.deleteCarSource(id, request);
//        }catch(Exception e){
//            log.info("删除车源失败", e);
//            return new RestResult("删除失败", "", ResultCode.ERROR.code());
//        }
//        return new RestResult("删除成功", "", ResultCode.SUCCESS.code());
//    }
}
