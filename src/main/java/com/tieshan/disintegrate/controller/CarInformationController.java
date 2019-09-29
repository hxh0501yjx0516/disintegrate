package com.tieshan.disintegrate.controller;


import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.ICarInformationService;
import com.tieshan.disintegrate.util.PubMethod;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:车辆查询模块控制类
 * @author: Leavonson
 * @date: Created in 2019/9/18 14:51
 * @version: 1.0
 * @modified By:
 */
@CommonsLog
@RestController
@RequestMapping("/car")
public class CarInformationController {

    @Autowired
    private ICarInformationService iCarInformationService;

    /**
     * showdoc
     * @catalog 解体厂-PC/车辆查询/车辆列表
     * @title 车辆查询页面表头数据
     * @description 车辆查询页面表头数据
     * @method get
     * @url http://localhost:8002/car/findCarById
     * @param carInfoId 必选 Long 车辆编号(随点击时传入)
     * @return {"msg": "查询数据成功","data": [{"car_name": "川崎Z1000R","engine": "发动机号码","car_no": "京A5W44C","card_color": "蓝色","vin": "VIN码","id": 24}],"ret_code": "0"}
     * @return_param car_name string 品牌型号
     * @return_param engine string 发动机号
     * @return_param car_no string 车牌号
     * @return_param card_color string 车牌颜色
     * @return_param vin string 车架号
     * @return_param id Long 车辆编号
     * @return_param msg string 描述信息
     * @return_param data List 数据
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @remark 车辆查询页面表头数据
     * @number 2
     */
    @GetMapping("/findCarById")
    public RestResult findCarById(@RequestParam(value = "carInfoId", required = false) Long carInfoId,@LoginUser SysUser user) {

        RestResult restResult = new RestResult(
                "查询数据成功",
                iCarInformationService.findCarById(carInfoId,user.getCompany_id()),
                ResultCode.SUCCESS.code());
        return  restResult;
    }

    /**
     * showdoc
     * @catalog 解体厂-PC/车辆查询/车辆信息
     * @title 车辆查询页面-车辆信息，预处理、拓号 照片数据查询
     * @description 车辆查询页面-车辆信息，预处理、拓号 照片数据查询
     * @method get
     * @url http://localhost:8002/car/findCarPicById
     * @param carInfoId 必选 Long 车辆编号(随点击时传入)
     * @return {"msg": "车辆信息，预处理、拓号 照片","data": [{"carPrePic": [{"id": 1177421900620632064,"file_name": "左前45度","first_type": "pre_pic","two_type": "left45","file_url": "www.asdasdasd"}],"carInfo": [{"motor_count": 3,"car_info_id": 24,"create_time": "2019-09-19 13:49:40","battery_count": 2,"self_weight": "serw","cistern_count": 3,"alloy_rim_count": 1,"remark": "aerer","create_operator": "erar","chair_count": 1,"is_delete": 2,"disintegrate_plant_id": 3333,"dismantling_way": 2,"door_count": 1,"tyre_count": 1,"catalytic_converter_count": 2,"car_degree": 12,"card_color": "蓝色","id": 1,"conditioner_count": 2,"condition_pump_count": 1,"electrical_machinery_count": 4,"create_operator_id": 1,"plate_count": 12}],"carTuoPic": [{"file_url": "www.yyyyyyyyyyyyy","create_time": "2019-09-27 12:19:40","file_name": "拓号图2","operator": "任磊"}]}],"ret_code": "0"}
     * @return_param msg string 描述信息
     * @return_param data List 数据
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @return_param carPrePic Map 预处理图片集合
     * @return_param carInfo Map 车辆信息集合
     * @return_param carTuoPic Map 拓号图片集合
     * @return_param motor_count int 马达
     * @return_param car_info_id Long 车辆编号
     * @return_param create_time date 车辆入场时间
     * @return_param battery_count int 电池
     * @return_param self_weight string 自重
     * @return_param cistern_count int 水箱
     * @return_param alloy_rim_count int 铝圈
     * @return_param remark string 备注
     * @return_param create_operator string 创建人
     * @return_param chair_count int 座椅
     * @return_param is_delete int 是否删除，是1 否2
     * @return_param disintegrate_plant_id Long 解体厂id
     * @return_param dismantling_way int '拆解方式 1:粗拆 ,2:点拆 , 3:包拆 ,4:精拆',
     * @return_param electrical_count int 电机数量
     * @return_param door_count int 车门数量
     * @return_param tyre_count int 轮胎数量
     * @return_param catalytic_converter_count int 三元催化器数量
     * @return_param car_degree int 新旧程度 1:正常 ,2:火灾 , 3:碰撞 ,4:水淹
     * @return_param id Long 返回的数据库主键(不用管)
     * @return_param conditioner_count int 空调数量
     * @return_param condition_pump_count int 空调泵数量
     * @return_param electrical_machinery_count int 电机数量
     * @return_param create_operator_id Long 创建人id
     * @return_param plate_count int 车牌数量
     * @return_param id long carPrePic:id-->图片Id
     * @return_param file_name string 照片名称
     * @return_param first_type string 照片的一级类型
     * @return_param two_type string 照片的二级类型
     * @return_param file_url string 照片链接
     * @return_param operator string 拓号操作人
     * @remark 车辆查询页面-车辆信息，预处理、拓号 照片数据查询
     * @number 3
     */

    @GetMapping("/findCarPicById")
    public RestResult findCarPicById(@RequestParam(value = "carInfoId", required = false) Long carInfoId,@LoginUser SysUser user) {

        if (PubMethod.isEmpty(carInfoId)||carInfoId<1) {
            return new RestResult("没有车辆编号", null, ResultCode.PARAM_IS_INVALID.code());
        }
        RestResult restResult = new RestResult("车辆查询页面-车辆信息预处理、拓号 照片查询成功！",iCarInformationService.findAll(carInfoId,user.getCompany_id()),ResultCode.SUCCESS.code());
        return  restResult;
    }

    /**
     * showdoc
     * @catalog 解体厂-PC/车辆查询/手续信息
     * @title 车辆查询页面-手续信息数据查询
     * @description 车辆查询页面-手续信息数据查询
     * @method get
     * @url http://localhost:8002/car/findProcedureAll
     * @param carInfoId 必选 Long 车辆编号(随点击时传入)
     * @return{"msg": "车辆查询页面-手续信息、处理周期查询成功！","data": {"carProcedureLog": [{"create_time": "2019-09-26 14:07:37","remark": "有违章","operator": "王码字"},{"create_time": "2019-09-27 14:07:41","remark": "违章已处理","operator": "孙武"},{"create_time": "2019-09-28 14:08:38","operator": "呵呵"},{"create_time": "2019-09-21 14:03:55","remark": "可以了","operator": "李大钊"}],"carProcedureInfo": [{"contacts_phone": "13888888888","agend_idcard": "620503155505036316","regist_license": 2,"id_card": "620503155505036316","car_type": "车辆型号","car_properties": "'车辆性质","engine": "发动机号码","reg_time": "2019-09-17","car_kind": "车辆性质","vin": "VIN码","displacement": "汽车排量","id": 1,"driv_license": 2,"fuel_type": "燃油性质","car_weight": "整车质量(车本上的质量)","owner": "宁峰","issue_size": "外廓尺寸","car_info_id": 24,"nature_usege": "使用性质","ascription": "车辆归宿","owner_address": "车主地址","owner_zipcode": "车主邮编","contacts_address": "2","frame_number": "车架号码","is_delete": 2,"disintegrate_plant_id": 3333,"colour": "蓝色","agend": "代理人1","phone": "13345678910","carrying_capacity": "核定载人数","issue_time": "2019-09-17","remarks": "备注","contacts": "zhangsanfeng"}]},"ret_code": "0"}
     * @return_param msg string 描述信息
     * @return_param data List 数据
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @return_param carProcedureLog Map 日志信息封装的数据
     * @return_param carProcedureInfo Map 车辆手续封装的数据
     * @return_param contacts_phone String 交车人手机号
     * @return_param agend_idcard Long 代理人身份证号
     * @return_param regist_license int '登记证。1.没有，2.有'
     * @return_param id_card String 车主身份证号（组织结构代码）
     * @return_param car_type String 车辆型号
     * @return_param car_properties String 车辆类型小轿车，大卡车。。
     * @return_param engine String 发动机号码
     * @return_param reg_time Date 注册时间
     * @return_param car_kind string 车辆性质
     * @return_param vin string 车辆识别代码(vin）
     * @return_param displacement string 汽车排量
     * @return_param id Long 数据库自增主键
     * @return_param driv_license int 行驶本。1.没有，2.有
     * @return_param fuel_type string 燃油性质
     * @return_param car_weight string 整车质量(车本上的质量)
     * @return_param owner string 车主姓名（或组织结构名称）
     * @return_param issue_size string 外廓尺寸
     * @return_param car_info_id Long 车辆编号
     * @return_param nature_usege string 使用性质营运非营运
     * @return_param ascription string 车辆归宿
     * @return_param owner_address string 车主地址
     * @return_param owner_zipcode string 车主邮编
     * @return_param contacts_address string 交车人地址
     * @return_param frame_number string 车架号码
     * @return_param is_delete int 是否删除。1.删除，2,正常
     * @return_param disintegrate_plant_id Long 解体厂id
     * @return_param colour string 车辆颜色
     * @return_param agend string 代理人
     * @return_param phone string 车主电话
     * @return_param card_color string 车牌颜色蓝色，黄色
     * @return_param issue_time Date 发证日期
     * @return_param remarks string 手续信息备注
     * @return_param contacts string 交车人
     * @return_param create_time string 日志创建时间
     * @return_param remark string 手续处理周期日志备注
     * @return_param operator string 操作人
     * @remark 车辆查询页面-手续信息数据查询
     * @number 4
     */

    @GetMapping("/findProcedureAll")
    public RestResult findProcedureAll(@RequestParam(value = "carInfoId", required = false) Long carInfoId,@LoginUser SysUser user) {
        if (PubMethod.isEmpty(carInfoId)||carInfoId<1) {
            return new RestResult("没有车辆编号", null, ResultCode.PARAM_IS_INVALID.code());
        }
        RestResult restResult = new RestResult("车辆查询页面-手续信息、处理周期查询成功！",iCarInformationService.findProcedureAll(carInfoId,user.getCompany_id()),ResultCode.SUCCESS.code());
        return  restResult;
    }


    /**
     * showdoc
     * @catalog 解体厂-PC/车辆查询/车源信息
     * @title 车辆查询页面-车源信息数据查询
     * @description 车辆查询页面-车源信息数据查询
     * @method get
     * @url http://localhost:8002/car/findSourceAll
     * @param carInfoId 必选 Long 车辆编号(随点击时传入)
     * @return {"msg": "车辆查询页面-车源信息数据、日志查询成功!","data": {"carSourceLog": [{"create_time": "2019-09-20 14:56:40","remark": "1","operator": "李大钊"}],"carSource": [{"contacts_phone": "13888888888","regist_license": 2,"processing_date": "2019-09-17 17:54:40","break_license": 2,"contacts_address": "2","exception_license": 2,"car_name": "宝马S1000RR","car_no": "A5W44C","procedures_type": "手续获取方式","driv_license": 2,"people_license": 2,"contacts": "zhangsanfeng","processing_type": "车辆回收方式","remarks": "备注","businese_license": 2}]},"ret_code": "0"}
     * @return_param msg string 描述信息
     * @return_param data List 数据
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @return_param carSourceLog Map 日志信息封装的数据
     * @return_param carSource Map 车源信息封装的数据
     * @return_param contacts_phone string 交车人手机号
     * @return_param regist_license int 登记证。1.没有，2.有
     * @return_param break_license int 车辆报废表。1.没有，2.有
     * @return_param businese_license int 营业执照复印件。1.没有，2.有
     * @return_param exception_license int 车辆事故证明。1.没有，2.有
     * @return_param processing_date Date 处理日期
     * @return_param contacts_address string 交车人地址
     * @return_param car_name string 车型
     * @return_param car_no string 车牌号
     * @return_param procedures_type string 手续获取方式
     * @return_param driv_license int 行驶本。1.没有，2.有
     * @return_param people_license int 身份证复印件。1.没有，2.有
     * @return_param contacts string 交车人
     * @return_param remarks string 车源信息备注
     * @return_param processing_type string 车辆回收方式
     * @return_param create_time Date 日志发生时间
     * @return_param remark string 日志备注
     * @return_param operator string 日志操作人
     * @remark 车源信息数据查询
     * @number 5
     */

    @GetMapping("/findSourceAll")
    public RestResult findSourceAll(@RequestParam(value = "carInfoId", required = false) Long carInfoId,@LoginUser SysUser user) {
        if (PubMethod.isEmpty(carInfoId)||carInfoId<1) {
            return new RestResult("没有车辆编号", null, ResultCode.PARAM_IS_INVALID.code());
        }
        RestResult restResult = new RestResult("车辆查询页面-车源信息数据、日志查询成功!",iCarInformationService.findSourceAll(carInfoId,user.getCompany_id()),ResultCode.SUCCESS.code());
        return  restResult;
    }

    /**
     * showdoc
     * @catalog 解体厂-PC/车辆查询/拆解信息
     * @title 车辆查询页面-拆解信息数据查询
     * @description 车辆查询页面-拆解信息数据查询
     * @method get
     * @url http://localhost:8002/car/findBreakAll
     * @param carInfoId 必选 Long 车辆编号(随点击时传入)
     * @return {"msg": "车辆查询页面-拆解信息数据查询成功!","data": {"carBreakLog": [{"create_time": "2019-09-20 14:56:40","remark": "1","operator": "李大钊"}],"carBreakInfo": [{"dismantle_time": "2019-09-22 20:01:45","dismantle_way": 2,"dismantle_user_id": 111111}],"carBreakPic": [{"file_url": "https://aaaaa","create_time": "2019-09-19 20:51:05","file_name": "毁型1","operator": "lisi"},{"file_url": "https://aaaaa","create_time": "2019-09-19 20:51:05","file_name": "毁型2","operator": "lisi"}]},"ret_code": "0"}
     * @return_param msg string 描述信息
     * @return_param data List 数据
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @return_param carBreakInfo Map 拆解信息封装的数据
     * @return_param carBreakPic Map 拆解图片封装的数据
     * @return_param carBreakLog Map 拆解日志封装的数据
     * @return_param dismantle_time Date 确定拆解方式时间
     * @return_param dismantle_way int 拆解方式
     * @return_param dismantle_user_id Long 拆解方式确认人
     * @return_param file_url string 拆解照片路径
     * @return_param create_time date 拆解照片时间
     * @return_param file_name string 拆解照片名称
     * @return_param operator string 操作人
     * @return_param create_time date 拆解日志时间
     * @return_param remark string 拆解日志信息备注
     * @remark 拆解信息数据查询
     * @number 6
     */
    @GetMapping("/findBreakAll")
    public RestResult findBreakAll(@RequestParam(value = "carInfoId", required = false) Long carInfoId,@LoginUser SysUser user) {
        if (PubMethod.isEmpty(carInfoId)||carInfoId<1) {
            return new RestResult("没有车辆编号", null, ResultCode.PARAM_IS_INVALID.code());
        }
        RestResult restResult = new RestResult("车辆查询页面-拆解信息数据查询成功!",iCarInformationService.findBreakAll(carInfoId,user.getCompany_id()),ResultCode.SUCCESS.code());
        return  restResult;
    }

    /**
     * showdoc
     * @catalog 解体厂-PC/车辆查询/残值信息
     * @title 车辆查询页面-残值信息数据查询
     * @description 车辆查询页面-残值信息数据查询
     * @method get
     * @url http://localhost:8002/car/findSalvageAll
     * @param carInfoId 必选 Long 车辆编号(随点击时传入)
     * @return {"msg": "车辆查询页面-残值信息数据查询成功！","data": {"carSalvageInfo": [{"is_get_salvage": 2,"get_salvage_time": "2019-09-23 20:45:01","salvage": "2000","operator": "马云"}],"carSalvageInfoLog": [{"create_time": "2019-09-20 14:56:40","remark": "1","operator": "李大钊"}]},"ret_code": "0"}
     * @return_param msg string 描述信息
     * @return_param data List 数据
     * @return_param ret_code int 状态码 0 成功 1 失败
     * @return_param carSalvageInfo Map 残值信息封装的数据
     * @return_param carSalvageInfoLog Map 残值处理日志封装的数据
     * @return_param is_get_salvage int 残值领取状态
     * @return_param get_salvage_time date 残值领取时间
     * @return_param salvage string 残值金额
     * @return_param operator string 残值发放人
     * @return_param create_time date 残值信息记录时间
     * @return_param remark string 备注
     * @return_param operator string 经办人(拆解厂)
     * @remark 残值信息数据查询
     * @number 6
     */
    @GetMapping("/findSalvageAll")
    public RestResult findSalvageAll(@RequestParam(value = "carInfoId", required = false) Long carInfoId,@LoginUser SysUser user) {
        if (PubMethod.isEmpty(carInfoId)||carInfoId<1) {
            return new RestResult("没有车辆编号", null, ResultCode.PARAM_IS_INVALID.code());
        }
        RestResult restResult = new RestResult("车辆查询页面-残值信息数据查询成功！",iCarInformationService.findSalvageAll(carInfoId,user.getCompany_id()),ResultCode.SUCCESS.code());
        return  restResult;
    }













}
