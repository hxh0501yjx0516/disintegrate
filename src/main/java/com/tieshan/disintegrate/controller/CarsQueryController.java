package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.constant.ConStants;
import com.tieshan.disintegrate.vo.CaiPrePicVo;
import com.tieshan.disintegrate.vo.CarPicData;
import com.tieshan.disintegrate.pojo.CarsQuery;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.CarsQueryService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
     *
     * @param findMsg  可选 string 查询条件(姓名 / 电话 / 车型 / 车牌号 / 车辆编号 / 车架号 / 发动机号)
     * @param page     可选 int 当前的页码值
     * @param pageSize 可选 int 页面大小
     * @return {"msg": "查询车辆列表信息成功","data": {"pageNum": 1,"pageSize": 10,"size": 3,"startRow": 1,"endRow": 3,"total": 3,"pages": 1,"list": [{"carInfoId": 1176367626889334784,"carNo": "晋A88888","cardColor": "蓝色","carName": "8888","carKind": "车辆性质","natureUsege": "使用性质自用","carProperties": "'车辆性质","regTime": "2019-09-29 00:00:00","issueTime": "2019-09-29 00:00:00","owner": "任磊","phone": "13888888888","contacts": "李五","contactsPhone": "13355558888","vin": "55555555555","engine": "6666"}],"prePage": 0,"nextPage": 0,"isFirstPage": true,"isLastPage": true,"hasPreviousPage": false,"hasNextPage": false,"navigatePages": 8,"navigatepageNums": [1],"navigateFirstPage": 1,"navigateLastPage": 1,"firstPage": 1,"lastPage": 1},"ret_code": "0"}
     * @catalog 解体厂-PC/车辆查询/车辆列表
     * @title 车辆列表
     * @description 车辆列表首页查询接口
     * @method get
     * @url http://localhost:8002/doCarsQuery/doFindPageObjects
     * @return_param msg string 描述信息
     * @return_param data List 数据
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @return_param carInfoId Long 车辆编号
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
    public RestResult doFindPageObjects(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
            @LoginUser SysUser user
    ) {
        PageInfo<CarsQuery> pageInfo = null;
        List<CarsQuery> list = carsQueryService.findPageObjects(findMsg, page, pageSize, user.getCompany_id());
            pageInfo = new PageInfo<>(list);
        RestResult restResult = new RestResult("查询车辆列表信息成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }

    /**
     * showdoc
     *
     * @param findMsg 可选 string 查询条件(车牌号 / 车型 / 车辆编号)
     * @return {"msg": "查询车辆预处理车辆信息成功","data": [{"car_name": "川崎Z1000R","car_info_id": 24,"approach_time": "2019-09-23 19:59:19","car_no": "京A5W44C","car_code": "TSCAR190900001"},{"car_name": "本田CBR1000RR","car_info_id": 25,"approach_time": "2019-09-23 19:59:19","car_no": "川A5W44C","car_code": "TSCAR190900001"}],"ret_code": "0"}
     * @title 车辆预处理首页查询接口
     * @description App端查询车辆预处理车辆信息
     * @method get
     * @url http://localhost:8002/doCarsQuery/findPretreatmentCars
     * @return_param msg string 描述信息
     * @return_param car_name string 车型
     * @return_param car_info_id Long 车辆Id
     * @return_param approach_time date 入场时间
     * @return_param car_no string 车牌
     * @return_param car_code string 车辆编号
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark 基于条件查询车辆信息
     * @number 1
     */

    @GetMapping("/doCarsQuery/findPretreatmentCars")
    public RestResult findPretreatmentCars(@RequestParam(value = "findMsg", required = false) String findMsg,
                                           @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                           @LoginUser SysUser user) {
        PageInfo pageInfo = null;
        List<Map<String, Object>> mapList = carsQueryService.findPretreatmentCars(findMsg,page,pageSize,user.getCompany_id());
        pageInfo = new PageInfo<>(mapList);
        RestResult restResult = new RestResult("查询车辆预处理车辆信息成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }

    /**
     * showdoc
     *
     * @param carInfoId 必选 Long 车辆Id，从之前返回数据中带过来
     * @return {"msg": "查询车辆预处理数据成功","data": [{"id": 1177398571025174528,"file_name": "左前45度","first_type": "pre_pic","two_type": "left45","file_url": "www.asdasdasd"},{"id": 1177398571025174529,"file_name": "右后45度","first_type": "pre_pic","two_type": "right45","file_url": "www.ggggggg"},{"id": 1177398571025174530,"file_name": "检字","first_type": "pre_pic","two_type": "check","file_url": "www.hhhhhhhhhhh"},{"id": 1177398571025174531,"file_name": "车架号","first_type": "pre_pic","two_type": "vin","file_url": "www.rrrrrr"},{"id": 1177398571025174532,"file_name": "车辆铭牌","first_type": "pre_pic","two_type": "mingpai","file_url": "www.gaaaaeeed"},{"id": null,"file_name": "发动机号","first_type": "pre_pic","two_type": "engine","file_url": "https://ts-disintegrate.oss-cn-beijing.aliyuncs.com/fadongjihao.png"}],"ret_code": "0"}
     * @catalog 拆解厂-APP/手续部-工作台/车辆预处理
     * @title 车辆预处理图片接口
     * @description App端查询车辆预处理车辆图片
     * @method get
     * @url http://localhost:8002/doCarsQuery/doFindCars
     * @return_param msg string 描述信息
     * @return_param id long 图片Id
     * @return_param file_name string 照片名称
     * @return_param first_type string 照片的一级类型
     * @return_param two_type string 照片的二级类型
     * @return_param file_url string 照片链接
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark App端根据carInfoId查询车辆预处理照片
     * @number 1
     */
    @GetMapping("/doCarsQuery/doFindCars")
    public RestResult doFindCars(@RequestParam(value = "carInfoId", required = true) Long carInfoId, @LoginUser SysUser user) {

        List<CaiPrePicVo> list = carsQueryService.doFindCars(carInfoId, user.getCompany_id());
        RestResult restResult = null;
        if (null == list || list.size() == 0) {
            restResult = new RestResult("未查询到车辆预处理数据", new Object(), ResultCode.SUCCESS.code());
        }
        restResult = new RestResult("查询车辆预处理数据成功", list, ResultCode.SUCCESS.code());
        return restResult;
    }

    /**
     * showdoc
     *
     * @param
     * @return {"msg": "查询车辆预处理拍照名称成功","data": [{"first_type": "pre_pic","two_type": "left45","filed_name": "左前45度"},{"first_type": "pre_pic","two_type": "right45","filed_name": "右后45度"},{"first_type": "pre_pic","two_type": "check","filed_name": "检字"},{"first_type": "pre_pic","two_type": "vin","filed_name": "车架号"},{"first_type": "pre_pic","two_type": "mingpai","filed_name": "车辆铭牌"},{"first_type": "pre_pic","two_type": "engine","filed_name": "发动机号"}],"ret_code": "0"}
     * @catalog 拆解厂-APP/手续部-工作台/车辆预处理
     * @title 车辆预处理拍照名称接口
     * @description App端查询车辆预处理拍照名称
     * @method get
     * @url http://localhost:8002/doCarsQuery/findPrePicNameCars
     * @return_param msg string 描述信息
     * @return_param first_type string 图片一级类型
     * @return_param two_type string 图片二级类型
     * @return_param filed_name string 图片名称
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark App端查询车辆预处理拍照名称
     * @number 1
     */
    @GetMapping("/doCarsQuery/findPrePicNameCars")
    public RestResult findPrePicNameCars() {

        RestResult restResult = new RestResult("查询车辆预处理拍照名称成功", carsQueryService.findPrePicNameCars(), ResultCode.SUCCESS.code());
        return restResult;
    }

    /**
     * showdoc
     *
     * @param carInfoId  必选 Long 车辆Id，从之前返回数据中带过来
     * @param status     必选 int 暂存与完成状态码，1 暂存 2 完成
     * @param data       必选 图片信息 List<String,Object>
     * @param first_type 必选 string 图片一级类型
     * @param two_type   必选 string 图片二级类型
     * @param filed_name 必选 string 图片名称
     * @param fileUrl    必选 string 图片路径
     * @return {"msg": "暂存图片成功","data": "1","ret_code": "0"}
     * @catalog 拆解厂-APP/手续部-工作台/车辆预处理
     * @title 车辆预处理-暂存与完成按钮接口
     * @description App端根据车辆预处理-暂存与完成按钮接口
     * @method post
     * @url http://localhost:8002/doCarsQuery/addPrePic
     * @json_param {"carInfoId":"24","status":"2","data":[{"first_type": "pre_pic","two_type": "left45","filed_name": "左前45度","fileUrl": "www.asdasdasd"},{"first_type": "pre_pic","two_type": "right45","filed_name": "右后45度","fileUrl": "www.ggggggg"},{"first_type": "pre_pic","two_type": "check","filed_name": "检字","fileUrl": "www.hhhhhhhhhhh"},{"first_type": "pre_pic","two_type": "vin","filed_name": "车架号","fileUrl": "www.rrrrrr"},{"first_type": "pre_pic","two_type": "mingpai","filed_name": "车辆铭牌","fileUrl": "www.gaaaaeeed"},{"first_type": "pre_pic","two_type": "engine","filed_name": "发动机号","fileUrl": "www.kkkkkkkkk"}]}
     * @return_param msg string 描述信息
     * @return_param data int 插入图片的记录数
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark App端根据车辆预处理-暂存与完成按钮接口
     * @number 1
     */
    @PostMapping("/doCarsQuery/addPrePic")
    public RestResult addPrePic(@RequestBody CarPicData carPicData, @LoginUser SysUser user) {

        RestResult restResult = null;
        try {
            carsQueryService.addPrePic(carPicData, user);
            if (carPicData.getStatus() == 1) {
                restResult = new RestResult("暂存图片成功", "1", ResultCode.SUCCESS.code());
            }
            if (carPicData.getStatus() == 2) {
                restResult = new RestResult("预处理完成", "1", ResultCode.SUCCESS.code());
            }
        } catch (Exception e) {
            log.info("插入图片失败", e);
            return new RestResult("插入图片失败", new Object(), ResultCode.ERROR.code());
        }
        return restResult;

    }

    /**
     * showdoc
     *
     * @param findMsg 可选 string 查询条件(车牌号 / 车型 / 车辆编号)
     * @return {"msg": "查询预拓号车辆信息成功","data": [{"car_name": "川崎Z1000R","car_info_id": 24,"approach_time": "2019-09-23 19:59:19","car_no": "京A5W44C","car_code": "TSCAR190900001"},{"car_name": "本田CBR1000RR","car_info_id": 25,"approach_time": "2019-09-23 19:59:19","car_no": "川A5W44C","car_code": "TSCAR190900001"}],"ret_code": "0"}
     * @title 预拓号首页车辆信息查询接口
     * @description App端查询车辆预拓号车辆信息
     * @method get
     * @url http://localhost:8002/doCarsQuery/findCopyNumberCars
     * @return_param msg string 描述信息
     * @return_param car_name string 车型
     * @return_param car_info_id Long 车辆Id
     * @return_param approach_time date 入场时间
     * @return_param car_no string 车牌
     * @return_param car_code string 车辆编号
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark 基于条件查询车辆信息
     * @number 1
     */
    @GetMapping("/doCarsQuery/findCopyNumberCars")
    public RestResult findCopyNumberCars(@RequestParam(value = "findMsg", required = false) String findMsg,
                                         @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                         @LoginUser SysUser user) {
        PageInfo pageInfo = null;
        List<Map<String, Object>> mapList = carsQueryService.findCopyNumberCars(findMsg,page,pageSize,user.getCompany_id());
        pageInfo = new PageInfo<>(mapList);
        RestResult restResult = null;
        if (null == mapList || mapList.size() == 0) {
            restResult = new RestResult("未查询到预拓号车辆信息", new Object(), ResultCode.SUCCESS.code());
        } else {
            restResult = new RestResult("查询预拓号车辆信息成功", pageInfo, ResultCode.SUCCESS.code());
        }
        return restResult;

    }

    /**
     * showdoc
     *
     * @param carInfoId 必选 Long 车辆Id，从之前返回数据中带过来
     * @return {"msg": "查询车辆拓号数据成功","data": [{"file_url": "www.yyyyyyyyyyyyy","first_type": "tuo_pic","two_type": "tuopic","file_name": "拓号图1","id": 1177207692306026496}],"ret_code": "0"}
     * @catalog 拆解厂-APP/手续部-工作台/等待拓号
     * @title 车辆拓号图片接口
     * @description App端根据carInfoId查询车辆拓号数据
     * @method get
     * @url http://localhost:8002/doCarsQuery/findCpTuoPic
     * @return_param msg string 描述信息
     * @return_param id long 图片Id
     * @return_param file_name string 照片名称
     * @return_param first_type string 照片的一级类型
     * @return_param two_type string 照片的二级类型
     * @return_param file_url string 照片链接
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark App端根据carInfoId查询车辆拓号数据
     * @number 1
     */
    @GetMapping("/doCarsQuery/findCpTuoPic")
    public RestResult findCpTuoPic(@RequestParam(value = "carInfoId", required = false) Long carInfoId, @LoginUser SysUser user) {
        List<Map<String, Object>> list = carsQueryService.findCpTuoPic(carInfoId, user.getCompany_id());
        RestResult restResult = null;
        if (null == list || list.size() == 0) {
            restResult = new RestResult("未查询到车辆拓号数据", new Object(), ResultCode.SUCCESS.code());
        } else {
            restResult = new RestResult("查询车辆拓号数据成功", list, ResultCode.SUCCESS.code());
        }
        return restResult;
    }


    /**
     * showdoc
     *
     * @param
     * @return {"msg": "查询车辆预拓号拍照名称成功","data": [{"first_type": "tuo_pic","two_type": "tuopic","filed_name": "拓号"}],"ret_code": "0"}
     * @catalog 拆解厂-APP/手续部-工作台/等待拓号
     * @title 车辆预拓号拍照名称接口
     * @description App端查询车辆预拓号拍照名称
     * @method get
     * @url http://localhost:8002/doCarsQuery/findCpPicNameCars
     * @return_param msg string 描述信息
     * @return_param first_type string 图片一级类型
     * @return_param two_type string 图片二级类型
     * @return_param filed_name string 图片名称
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark App端查询车辆预处理拍照名称
     * @number 1
     */
    @GetMapping("/doCarsQuery/findCpPicNameCars")
    public RestResult findCpPicNameCars() {

        RestResult restResult = new RestResult("查询车辆预拓号拍照名称成功", carsQueryService.findCpPicNameCars(), ResultCode.SUCCESS.code());
        return restResult;
    }


    /**
     * showdoc
     *
     * @param carInfoId  必选 Long 车辆Id，从之前返回数据中带过来
     * @param status     必选 int 暂存与完成状态码，1 暂存 2 完成
     * @param data       必选 图片信息 List<String,Object>
     * @param first_type 必选 string 图片一级类型
     * @param two_type   必选 string 图片二级类型
     * @param filed_name 必选 string 图片名称
     * @param fileUrl    必选 string 图片路径
     * @return {"msg": "暂存图片成功","data": "1","ret_code": "0"}
     * @catalog 拆解厂-APP/手续部-工作台/等待拓号
     * @title 车辆预处理-暂存与完成按钮接口
     * @description pp端插入车辆预拓号-暂存与完成按钮接口
     * @method post
     * @url http://localhost:8002/doCarsQuery/addTuoPic
     * @json_param {"carInfoId":"24","status":"1","data":[{"first_type": "tuo_pic","two_type": "tuopic","filed_name": "拓号图2","fileUrl": "www.yyyyyyyyyyyyy"}]}
     * @return_param msg string 描述信息
     * @return_param data int 插入图片的记录数
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark App端插入车辆预拓号-暂存与完成按钮接口
     * @number 1
     */
    @PostMapping("/doCarsQuery/addTuoPic")
    public RestResult addTuoPic(@RequestBody CarPicData carPicData, @LoginUser SysUser user) {

        RestResult restResult = null;
        try {
            carsQueryService.addTuoPic(carPicData, user);
            if (carPicData.getStatus() == 1) {
                restResult = new RestResult("暂存图片成功", "1", ResultCode.SUCCESS.code());
            }
            if (carPicData.getStatus() == 2) {
                restResult = new RestResult("拓号完成", "1", ResultCode.SUCCESS.code());
            }
        } catch (Exception e) {
            log.info("插入图片失败", e);
            return new RestResult("插入图片失败", new Object(), ResultCode.ERROR.code());
        }
        return restResult;
    }

    /**
     * showdoc
     *
     * @param findMsg 可选 string 查询条件(车牌号 / 车型 / 车辆编号)
     * @return {"msg": "查询预拆解车辆信息成功","data": [{"car_name": "川崎Z1000R","car_info_id": 24,"approach_time": "2019-09-23 19:59:19","car_no": "京A5W44C","car_code": "TSCAR190900001"},{"car_name": "本田CBR1000RR","car_info_id": 25,"approach_time": "2019-09-23 19:59:19","car_no": "川A5W44C","car_code": "TSCAR190900001"}],"ret_code": "0"}
     * @title 预拆解首页车辆信息查询接口
     * @description App端查询预拆解车辆信息
     * @method get
     * @url http://localhost:8002/doCarsQuery/findDismantleCars
     * @return_param msg string 描述信息
     * @return_param car_name string 车型
     * @return_param car_info_id Long 车辆Id
     * @return_param approach_time date 入场时间
     * @return_param car_no string 车牌
     * @return_param car_code string 车辆编号
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark 基于条件查询车辆信息
     * @number 1
     */
    @GetMapping("/doCarsQuery/findDismantleCars")
    public RestResult findDismantleCars(@RequestParam(value = "findMsg", required = false) String findMsg,
                                        @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
                                        @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
                                        @LoginUser SysUser user) {

        PageInfo pageInfo = null;
        List<Map<String, Object>> mapList = carsQueryService.findDismantleCars(findMsg,page,pageSize,user.getCompany_id());
        pageInfo = new PageInfo<>(mapList);
        RestResult restResult = null;
        if (null == mapList || mapList.size() == 0) {
            restResult = new RestResult("未查询到预拆解车辆信息", new Object(), ResultCode.SUCCESS.code());
        } else {
            restResult = new RestResult("查询预拆解车辆信息成功", pageInfo, ResultCode.SUCCESS.code());
        }
        return restResult;

    }

    /**
     * showdoc
     *
     * @param carInfoId 必选 Long 车辆Id，从之前返回数据中带过来
     * @return {"msg": "查询该车辆初检信息成功","data": [{"motor_count": 3,"battery_count": 2,"cistern_count": 3,"alloy_rim_count": 1,"remark": "aerer","chair_count": 1,"car_name": "川崎Z1000R","approach_time": "2019-09-23 19:59:19","engine": "发动机号码","door_count": 1,"tyre_count": 1,"catalytic_converter_count": 2,"car_no": "京A5W44C","car_degree": 12,"car_code": "TSCAR190900001","displacement": "汽车排量","vin": "VIN码","conditioner_count": 2,"condition_pump_count": 1,"electrical_machinery_count": 4}],"ret_code": "0"}
     * @title App端根据carInfoId查询车辆初检信息
     * @description App端根据carInfoId查询车辆初检信息
     * @method get
     * @url http://localhost:8002/doCarsQuery/findSurveyById
     * @return_param msg string 描述信息
     * @return_param data 返回数据集合
     * @return_param motor_count int 马达数量
     * @return_param battery_count int 电池数量
     * @return_param cistern_count int 水箱数量
     * @return_param alloy_rim_count int 铝圈数量
     * @return_param remark string 备注
     * @return_param chair_count int 座椅数量
     * @return_param car_name string 车型
     * @return_param approach_time Date 入场时间
     * @return_param engine string 发动机号码
     * @return_param door_count int 车门数量
     * @return_param tyre_count int 轮胎数量
     * @return_param catalytic_converter_count int 三元催化器数量
     * @return_param car_no string 车牌号
     * @return_param car_degree int '新旧程度 1:正常 ,2:火灾 , 3:碰撞 ,4:水淹'
     * @return_param displacement string 汽车排量
     * @return_param vin string 车架号
     * @return_param conditioner_count int 空调数量
     * @return_param condition_pump_count int 空调泵数量
     * @return_param electrical_machinery_count int 电机数量
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark 基于条件查询该车辆初检信息
     * @number 1
     */
    @GetMapping("/doCarsQuery/findSurveyById")
    public RestResult findSurveyById(@RequestParam(value = "carInfoId", required = false) Long carInfoId, @LoginUser SysUser user) {

        List<Map<String, Object>> list = carsQueryService.findSurveyById(carInfoId, user.getCompany_id());
        return new RestResult("查询该车辆初检信息成功", list, ResultCode.SUCCESS.code());
    }
    /**
     * showdoc
     *
     * @param carInfoId 必选 Long 车辆Id，从之前返回数据中带过来
     * @return {"msg": "查询车辆初检信息图片成功","data": [{"file_url": "www.yyyyyyyyyyyyy","first_type": "pre_pic","two_type": "left45","file_name": "左前45度","id": 1177207692306026496}],"ret_code": "0"}
     * @catalog 拆解厂-APP/手续部-工作台/车辆拆解
     * @title 查询车辆初检信息图片接口
     * @description App端根据carInfoId查询车辆初检信息图片
     * @method get
     * @url http://localhost:8002/doCarsQuery/findPrePicById"
     * @return_param msg string 描述信息
     * @return_param id long 图片Id
     * @return_param file_name string 照片名称
     * @return_param first_type string 照片的一级类型
     * @return_param two_type string 照片的二级类型
     * @return_param file_url string 照片链接
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark App端根据carInfoId查询车辆初检数据
     * @number 1
     */
    @GetMapping("/doCarsQuery/findPrePicById")
    public RestResult findPrePicById(@RequestParam(value = "carInfoId", required = false) Long carInfoId, @LoginUser SysUser user) {

        List<CaiPrePicVo> list = carsQueryService.findPrePicById(carInfoId, user.getCompany_id());
        RestResult restResult = null;
        if (null == list || list.size() == 0) {
            restResult = new RestResult("未查询到车辆初检信息图片", new Object(), ResultCode.SUCCESS.code());
        } else {
            restResult = new RestResult("查询车辆初检信息图片成功", list, ResultCode.SUCCESS.code());
        }
        return restResult;
    }

    /**
     * showdoc
     *
     * @param carInfoId 必选 Long 车辆Id，从之前返回数据中带过来
     * @param dismantleWay 必选 车辆初检拆解方式 1:粗拆 ,2:点拆 , 3:包拆 ,4:精拆，5:默认'
     * @return {"msg": "设置车辆初检拆解方式成功","data": 1,"ret_code": "0"}
     * @catalog 拆解厂-APP/手续部-工作台/车辆拆解
     * @title App端设置车辆初检拆解方式接口
     * @description 设置车辆初检拆解方式
     * @method get
     * @url http://localhost:8002/doCarsQuery/dismantleWay
     * @return_param msg string 描述信息
     * @return_param data int 插入成功结果
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark App端车辆初检拆解方式
     * @number 1
     */

    @GetMapping("/doCarsQuery/dismantleWay")
    public RestResult dismantleWay(@RequestParam(value = "carInfoId", required = false) Long carInfoId,
                                   @RequestParam(value = "dismantleWay", required = false) Integer status,
                                   @LoginUser SysUser user) {

        RestResult restResult = new RestResult("设置车辆初检拆解方式成功", carsQueryService.dismantleWay(carInfoId, status, user.getCompany_id()), ResultCode.SUCCESS.code());
        return restResult;
    }

/**------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    /***
     * App端-车辆待毁型
     */
    @GetMapping("/doCarsQuery/findPreBreakCars")
    public RestResult findPreBreakCars(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
            @LoginUser SysUser user
    ) {
        PageInfo pageInfo = null;
        List<Map<String,Object>> mapList = carsQueryService.findPreBreakCars(findMsg, page, pageSize, user.getCompany_id());
        pageInfo = new PageInfo<>(mapList);
        RestResult restResult = new RestResult("查询车辆列表信息成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }
    /***
     * App端-车辆已毁型
     */
    @GetMapping("/doCarsQuery/findBreakSuccessCars")
    public RestResult findBreakSuccessCars(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
            @LoginUser SysUser user
    ) {
        PageInfo pageInfo = null;
        List<Map<String,Object>> mapList = carsQueryService.findBreakSuccessCars(findMsg, page, pageSize, user.getCompany_id());
        pageInfo = new PageInfo<>(mapList);
        RestResult restResult = new RestResult("查询车辆列表信息成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }

    /***
     * App端根据carInfoId查询车辆待毁型数据/或者毁型后数据
     */
    @GetMapping("/doCarsQuery/findPreBreakCarsById")
    public RestResult findPreBreakCarsById(@RequestParam(value = "carInfoId", required = false) Long carInfoId, @LoginUser SysUser user) {

        List<Map<String, Object>> list = carsQueryService.findPreBreakCarsById(carInfoId, user.getCompany_id());
        return new RestResult("查询该车辆待毁型信息成功", list, ResultCode.SUCCESS.code());
    }
    /***
     * App端添加毁型照片
     */
    @PostMapping("/doCarsQuery/addBreakPic")
    public RestResult addBreakPic(@RequestBody CarPicData carPicData, @LoginUser SysUser user) {

        RestResult restResult = null;
        try {
            carsQueryService.addBreakPic(carPicData, user);
            if (carPicData.getStatus() == 1) {
                restResult = new RestResult("暂存图片成功", "1", ResultCode.SUCCESS.code());
            }
            if (carPicData.getStatus() == 2) {
                restResult = new RestResult("毁型完成", "1", ResultCode.SUCCESS.code());
            }
        } catch (Exception e) {
            log.info("插入图片失败", e);
            return new RestResult("插入图片失败", new Object(), ResultCode.ERROR.code());
        }
        return restResult;
    }

    /***
     * App端-查询报废证明照片车辆列表
     */
    @GetMapping("/doCarsQuery/findProCars")
    public RestResult findProCars(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
            @RequestParam(value = "status", required = false) Integer status,
            @LoginUser SysUser user
    ) {
        PageInfo pageInfo = null;
        List<Map<String,Object>> mapList = carsQueryService.findProCars(findMsg, page, pageSize, user.getCompany_id(),status);
        pageInfo = new PageInfo<>(mapList);
        RestResult restResult = new RestResult("查询车辆列表信息成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }

    /***
     * App端根据carInfoId查询报废证明照片数据
     */
    @GetMapping("/doCarsQuery/findProCarsById")
    public RestResult findProCarsById(@RequestParam(value = "carInfoId", required = false) Long carInfoId, @LoginUser SysUser user) {

        List<Map<String, Object>> list = carsQueryService.findProCarsById(carInfoId, user.getCompany_id());
        return new RestResult("查询该车辆报废证明信息成功", list, ResultCode.SUCCESS.code());
    }
    /***
     * App端添加报废证明照片
     */
    @PostMapping("/doCarsQuery/addProPic")
    public RestResult addProPic(@RequestBody CarPicData carPicData, @LoginUser SysUser user) {

        RestResult restResult = null;
        try {
            carsQueryService.addProPic(carPicData, user);
                restResult = new RestResult("暂存图片成功", "1", ResultCode.SUCCESS.code());

        } catch (Exception e) {
            log.info("插入图片失败", e);
            return new RestResult("插入图片失败", new Object(), ResultCode.ERROR.code());
        }
        return restResult;
    }

    /***
     * App端-查询本部已处理车辆列表
     */
    @GetMapping("/doCarsQuery/findIsHandle")
    public RestResult findIsHandle(
            @RequestParam(value = "findMsg", required = false) String findMsg,
            @RequestParam(value = "page", required = false, defaultValue = ConStants.PAGE) Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = ConStants.PAGESIZE) Integer pageSize,
            @LoginUser SysUser user
    ) {
        PageInfo pageInfo = null;
        List<Map<String,Object>> mapList = carsQueryService.findIsHandle(findMsg, page, pageSize, user.getCompany_id());
        pageInfo = new PageInfo<>(mapList);
        RestResult restResult = new RestResult("查询车辆列表信息成功", pageInfo, ResultCode.SUCCESS.code());
        return restResult;
    }



}
