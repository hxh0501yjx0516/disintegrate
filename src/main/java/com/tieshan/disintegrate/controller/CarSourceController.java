package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.pojo.CarSource;
import com.tieshan.disintegrate.pojo.CarSurvey;
import com.tieshan.disintegrate.service.DictionaryService;
import com.tieshan.disintegrate.service.ICarSourceService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * PC： 查询某拆解厂的指定的车源状态或者搜索条件查询车源的信息
     *
     * @param sourceType 车源状态
     * @param findMsg    搜索条件
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/selectCarSourceList")
    public RestResult selectCarSourceList(@RequestParam(value = "sourceType", required = false, defaultValue = "1") String sourceType,
                                          @RequestParam(value = "findMsg", required = false) String findMsg,
                                          @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                          HttpServletRequest request) {
        PageInfo pageInfo = null;/*PageInfo<Map<String, Object>>*/
        try {
            List<Map<String, Object>> mapList = carSourceService.selectCarSourceList(sourceType, findMsg, page, pageSize, request);
            pageInfo = new PageInfo<>(mapList);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", pageInfo, ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", pageInfo, ResultCode.SUCCESS.code());
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
        List<Map<String, Object>> list = null;
        try {
            list = carSourceService.findUserNameList();
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", list, ResultCode.SUCCESS.code());
    }

    /**
     * 查询所有银行的信息    3     过
     *
     * @return
     */
    @GetMapping(value = "/findBankNameList")
    public RestResult findBankNameList() {
        List<String> list = null;
        try {
            list = carSourceService.findBankNameList();
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", list, ResultCode.SUCCESS.code());
    }

    /**
     * 查询指定车源的信息   4    过
     *
     * @param id 车源的id
     * @return
     */
    @GetMapping(value = "selectCarSource")
    public RestResult selectCarSource(Long id, HttpServletRequest request) {
        CarSource carSource = null;
        try {
            carSource = carSourceService.selectCarSource(id, request);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", carSource, ResultCode.SUCCESS.code());
    }

    /**
     * 查询指定车源下的车辆    5    过
     *
     * @param id      车源主键id
     * @param request
     * @return
     */
    @GetMapping(value = "selectCarInfoList")
    public RestResult selectCarInfoList(Long id, HttpServletRequest request) {
        List<Map<String, Object>> list = null;
        try {
            list = carSourceService.selectCarInfoList(id, request);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", list, ResultCode.SUCCESS.code());
    }


    /**
     * PC：增加车源     6    对象中的对象接受不到前端传过来的数据     过
     *
     * @param params
     * @param request
     * @return
     */
    @PostMapping(value = "/addCarSource")
    public RestResult addCarSource(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        try {
            carSourceService.addCarSource(params, request);
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
        Map<String, List<String>> map = null;
        try {
            map = carSourceService.selectProcessingTypeAndProceduresType();
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", map, ResultCode.SUCCESS.code());
    }

    /**
     * 添加车辆    8     日期转换    过
     *
     * @param carInfo
     * @param
     * @param request
     * @return , @RequestParam(value = "carSource") Long carSource
     */
    @PostMapping(value = "/addCar")
    public RestResult addCar(@RequestBody CarInfo carInfo, HttpServletRequest request) {
        try {
            carSourceService.addCar(carInfo, request);
        } catch (Exception e) {
            log.info("添加车辆失败", e);
            return new RestResult("添加失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("添加成功", null, ResultCode.SUCCESS.code());
    }

    /**
     * 通过主键id查询车辆信息   9   显示查询成功，但是没有数据     过         APP端的原型图还没有出来，所有showdoc需要加一个接口
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "selectCarInfoById")
    public RestResult selectCarInfoById(Long id, HttpServletRequest request) {
        CarInfo carInfo = null;
        try {
            carInfo = carSourceService.selectCarInfoById(id, request);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", carInfo, ResultCode.SUCCESS.code());
    }


    /**
     * 编辑车辆信息   10    过
     *
     * @param carInfo
     * @return
     */
    @PostMapping(value = "/editCar")
    public RestResult editCar(@RequestBody CarInfo carInfo) {
        try {
            carSourceService.editCar(carInfo);
        } catch (Exception e) {
            log.info("更新车辆信息失败", e);
            return new RestResult("更新失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("更新成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 删除车辆    11     过
     *
     * @param id
     * @param request
     * @return
     */
    @DeleteMapping(value = "/deleteCarInfoById")
    public RestResult deleteCarInfoById(Long id, HttpServletRequest request) {
        try {
            carSourceService.deleteCarInfoById(id, request);
        } catch (Exception e) {
            log.info("删除车辆失败", e);
            return new RestResult("删除失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("删除成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 查询指定车源的信息和银行信息   12    过
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "/selectCarSourceById")
    public RestResult selectCarSourceById(Long id, HttpServletRequest request) {
        CarSource carSource = null;
        try {
            carSource = carSourceService.selectCarSourceById(id, request);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", carSource, ResultCode.SUCCESS.code());
    }

    /**
     * 更新车源的信息  13      过
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/editCarSource")
    public RestResult editCarSource(@RequestBody Map<String, Object> params) {
        try {
            carSourceService.editCarSource(params);
        } catch (Exception e) {
            log.info("更新车源信息失败", e);
            return new RestResult("更新失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("更新成功", "", ResultCode.SUCCESS.code());
    }


    /**
     * 查询所有省份简称   过
     *
     * @return
     */
    @GetMapping("/selectProvinceCodeList")
    public RestResult selectProvinceCodeList() {
        List<String> list = null;
        try {
            list = dictionaryService.selectProvinceCodeList();
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", list, ResultCode.SUCCESS.code());
    }

    /**
     * 查询所有手续APP  过
     *
     * @return
     */
    @GetMapping("/selectProceduresTypeList")
    public RestResult selectProceduresTypeList() {
        List<String> list = null;
        try {
            list = dictionaryService.selectProceduresTypeList();
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", list, ResultCode.SUCCESS.code());
    }

    /**
     * APP 查询所有指定车源下的车辆
     *
     * @param id
     * @param request
     * @param state    1-待入场状态    2-待核档状态    3-待商委注销状态     4-待领取残值状态     5-待报废状态     6-报废成功     7-核档未通过
     * @param findMsg
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/selectCarInfoListApp")
    public RestResult selectCarInfoListApp(Long id, HttpServletRequest request,
                                           @RequestParam(value = "state", required = false) String state,
                                           @RequestParam(value = "findMsg", required = false) String findMsg,
                                           @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize) {
        PageInfo pageInfo = null;
        try {
            List<Map<String, Object>> resultList = carSourceService.selectCarInfoListApp(id, request, state, findMsg, page, pageSize);
            pageInfo = new PageInfo<>(resultList);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", pageInfo, ResultCode.SUCCESS.code());
    }

    /**
     * 查询某拆解厂下某业务员下核档完成的所有车辆（搜索的是车牌号，车型，vin，车辆编号）    过
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/selectCarInfoListByIsVerify")
    public RestResult selectCarInfoListByIsVerify(HttpServletRequest request,
                                                  @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                                  @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                                  @RequestParam(value = "findMsg", required = false) String findMsg) {
        PageInfo pageInfo = null;
        try {
            List<Map<String, Object>> resultList = carSourceService.selectCarInfoListByIsVerify(request, findMsg);
            pageInfo = new PageInfo<>(resultList);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", pageInfo, ResultCode.SUCCESS.code());
    }


    /**
     * 查询车辆核档不通过的原因     过   但是还没有在showdoc中保存，因为原型图还没有
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping(value = "/selectCarInfoReason")
    public RestResult selectCarInfoReason(Long id, HttpServletRequest request) {
        Map<String, Object> map = null;
        try {
            map = carSourceService.selectCarInfoReason(id, request);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", map, ResultCode.SUCCESS.code());
    }

    /**
     * 查询车牌颜色    过
     *
     * @return
     */
    @GetMapping(value = "/selectLicensePlateColorList")
    public RestResult selectLicensePlateColorList() {
        List<String> list = null;
        try {
            list = dictionaryService.selectLicensePlateColorList();
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", list, ResultCode.SUCCESS.code());
    }

    /**
     * 添加部分初检车俩信息    过
     *
     * @param carNo
     * @param selfWeight
     * @param cardColor
     * @return
     */
    @PostMapping(value = "/insertCarSurveyPart")
    public RestResult insertCarSurveyPart(String carNo, String selfWeight, String cardColor, HttpServletRequest request) {
        System.out.println(carNo);
        System.out.println(selfWeight);
        System.out.println(cardColor);
        RestResult restResult = null;
        try {
            int num = carSourceService.insertCarSurveyPart(carNo, selfWeight, cardColor, request);
            if (num == 1) {
                restResult = new RestResult("添加成功", "", ResultCode.SUCCESS.code());
            } else {
                restResult = new RestResult("添加失败", " ", ResultCode.ERROR.code());
            }
        } catch (Exception e) {
            log.info("添加失败", e);
            return new RestResult("添加失败", " ", ResultCode.ERROR.code());
        }
        return restResult;
    }

    /**
     * 分页查询所有未初检的车辆信息(包括搜索条件)   过
     *
     * @param page
     * @param pageSize
     * @param findMsg
     * @param request
     * @return
     */
    @GetMapping(value = "/selectCarInfoByIsInitialSurvey")
    public RestResult selectCarInfoByIsInitialSurvey(@RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                                     @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                                     @RequestParam(value = "findMsg", required = false) String findMsg,
                                                     HttpServletRequest request) {
        PageInfo pageInfo = null;
        try {
            List<Map<String, Object>> mapList = carSourceService.selectCarInfoByIsInitialSurvey(page, pageSize, findMsg, request);
            pageInfo = new PageInfo<>(mapList);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", pageInfo, ResultCode.SUCCESS.code());
    }

    /**
     * 通过id查询车辆信息和部分车辆入场信息(基本信息)   过
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping("selectCarInfoByIdAndCarEnter")
    public RestResult selectCarInfoByIdAndCarEnter(Long id, HttpServletRequest request) {
        Map<String, Object> map = null;
        try {
            map = carSourceService.selectCarInfoByIdAndCarEnter(id, request);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", map, ResultCode.SUCCESS.code());
    }

    /**
     * 通过车辆id查询该初检车辆的信息      过
     *
     * @param id      车辆的id
     * @param request
     * @return
     */
    @GetMapping(value = "/selectCarSurveyByCarInfoId")
    public RestResult selectCarSurveyByCarInfoId(Long id, HttpServletRequest request) {
        CarSurvey carSurvey = null;
        try {
            carSurvey = carSourceService.selectCarSurveyByCarInfoId(id, request);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", carSurvey, ResultCode.SUCCESS.code());
    }

    /**
     * 车辆初检的暂存   过
     *
     * @param carSurvey
     * @return
     */
    @PostMapping(value = "/editCarSurvey")
    public RestResult editCarSurvey(CarSurvey carSurvey, HttpServletRequest request) {
        try {
            carSourceService.editCarSurvey(carSurvey, request);
        } catch (Exception e) {
            log.info("修改失败", e);
            return new RestResult("修改失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("修改成功", null, ResultCode.SUCCESS.code());
    }


    /**
     * 初检完成    过
     *
     * @param carSurvey
     * @param request
     * @return
     */
    @PostMapping(value = "/editCarSurveyComplete")
    public RestResult editCarSurveyComplete(CarSurvey carSurvey, HttpServletRequest request) {
        try {
            carSourceService.editCarSurveyComplete(carSurvey, request);
        } catch (Exception e) {
            log.info("修改失败", e);
            return new RestResult("修改失败", "", ResultCode.ERROR.code());
        }
        return new RestResult("修改成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * .
     * 查询该解体厂下的所有车辆     过
     *
     * @param page
     * @param pageSize
     * @param findMsg
     * @param request
     * @return
     */
    @GetMapping("/selectCarInfoListByDisintegratePlantId")
    public RestResult selectCarInfoListByDisintegratePlantId(@RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                                             @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                                             @RequestParam(value = "findMsg", required = false) String findMsg,
                                                             HttpServletRequest request) {
        PageInfo pageInfo = null;
        try {
            List<Map<String, Object>> mapList = carSourceService.selectCarInfoListByDisintegratePlantId(page, pageSize, findMsg, request);
            pageInfo = new PageInfo<>(mapList);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", pageInfo, ResultCode.SUCCESS.code());
    }

    /**
     * 查询所有的位置信息
     *
     * @param id
     * @param request
     * @return
     */
    @GetMapping("/selectLocationListByPid")
    public RestResult selectLocationListByPid(@RequestParam(value = "id", required = false, defaultValue = "0") Long id, HttpServletRequest request) {
        List<Map<String, Object>> list = null;
        try {
            list = carSourceService.selectLocationListByPid(id, request);
        } catch (Exception e) {
            log.info("查询失败", e);
            return new RestResult("查询失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("查询成功", list, ResultCode.SUCCESS.code());
    }

    /**
     * 更新车辆的信息，添加车辆的存放位置
     *
     * @param carInfo
     * @return
     */
    @PostMapping("/editCarInfoLocation")
    public RestResult editCarInfoLocation(CarInfo carInfo) {
        try {
            carSourceService.editCarInfoLocation(carInfo);
        } catch (Exception e) {
            log.info("添加车辆存放位置失败", e);
            return new RestResult("添加车辆存放位置失败", null, ResultCode.ERROR.code());
        }
        return new RestResult("添加车辆存放位置成功", null, ResultCode.SUCCESS.code());
    }

    /**
     * 查询车牌号是否存在，若以存在
     *
     * @param carNo
     * @param request
     */
    @GetMapping("/selectCarInfoByCarNo")
    public RestResult selectCarInfoByCarNo(String carNo, HttpServletRequest request) {
        RestResult restResult = null;
        try{
            int num = carSourceService.selectCarInfoByCarNo(carNo, request);
            if (num == 1){
                restResult = new RestResult("是否保存该车辆信息", "", ResultCode.SUCCESS.code());
            }else {
                restResult = new RestResult("该车牌号已存在，不能重复保存", "", ResultCode.SUCCESS.code());
            }
        }catch (Exception e){
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return restResult;
    }

    /**
     *      先判断车牌号是否存在，若不存在，则提示“该车牌号不存在，请先添加该车辆的相关信息！”
     *                            若存在，判断该车牌号的入场状态，
     *                                      若已入场，则提示"该车辆已入场，不能重复入场！"，若未入场，则提示"是否确定入场"
     * @param carNo
     * @param request
     * @return
     */
    @GetMapping("/selectCarInfoCountByCarNo")
    public RestResult selectCarInfoCountByCarNo(String carNo, HttpServletRequest request){
        RestResult restResult = null;
        try{
            int num = carSourceService.selectCarInfoByCarNo(carNo, request);
            if (num == 1){     //
                restResult = new RestResult("该车牌号不存在，请先添加该车辆的相关信息！", "", ResultCode.SUCCESS.code());
            }else {
                int count = carSourceService.selectCarInfoCountByCarNo(carNo, request);
                if (count == 1){     // 已入场
                    restResult = new RestResult("该车辆已入场，不能重复入场！", "", ResultCode.SUCCESS.code());
                }else {     // 未入场
                    restResult = new RestResult("是否确定入场", "", ResultCode.SUCCESS.code());
                }
            }
        }catch (Exception e){
            log.info("查询失败", e);
            return new RestResult("查询失败", "", ResultCode.ERROR.code());
        }
        return restResult;
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
