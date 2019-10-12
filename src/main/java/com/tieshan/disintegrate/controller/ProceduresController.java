package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IProceduresService;
import com.tieshan.disintegrate.service.IReceiveRecordService;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import com.tieshan.disintegrate.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/17 19:31
 * @modified By：
 * @version: 1.0.0
 */
@RestController
@RequestMapping("/procedures")
public class ProceduresController {

    @Autowired
    private IProceduresService proceduresService;
    @Autowired
    private IReceiveRecordService receiveRecordService;

    /**
     * 添加手续
     * @param proceduresVo
     * @param user
     * @return
     */
    /*@PostMapping(value = "/addProcedures")
    public RestResult addCarInfo(@RequestBody ProceduresVo proceduresVo, @LoginUser SysUser user) {
        proceduresService.add(proceduresVo, user);
        return new RestResult("添加成功", "", ResultCode.SUCCESS.code());
    }*/

    /**
     * 手续登记
     * @param proceduresVo
     * @param user
     * @return
     */
    @PostMapping(value = "/saveProcedures")
    public RestResult saveProcedures(@RequestBody ProceduresVo proceduresVo, @LoginUser SysUser user) {
        proceduresService.saveProcedures(proceduresVo, user);
        return new RestResult("修改成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 查询手续
     * @param params carInfoId
     * @param user
     * @return
     */
    @PostMapping(value = "/queryProcedures")
    public RestResult queryProcedures(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        ProceduresVo proceduresVo = proceduresService.query(params, user);
        return new RestResult("查询成功", proceduresVo, ResultCode.SUCCESS.code());
    }

    /**
     * 档案查询结果
     * @param params carProcessingId    手续id
     *               queryResultId      查询结果id
     *               state              状态 1:未完成(暂存);2完成;3:不通过;
     *               remark             备注
     *               recordNumber       档案号
     * @param user
     * @return
     */
    @PostMapping(value = "/saveQueryResult")
    public RestResult saveQueryResult(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.recordQueryResult(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 档案核验结果
     * @param params carProcessingId        手续id
     *               verificationResultId   档案核验结果id
     *               state                  状态 1:未完成(暂存);2完成;3:不通过;
     *               result                 结果
     *               remark                 备注
     * @param user
     * @return
     */
    @PostMapping(value = "/saveVerificationResult")
    public RestResult saveVerificationResult(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.recordVerificationResult(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 保存手续查询客服处理结果
     * @param params carProcessingId        手续id
     *               queryResultId          查询结果id
     *               recordQueryResultId    档案查询客服处理结果id
     *               state                  状态 1:未完成(暂存);2完成;3:不通过；4:退车;
     *               remark                 备注
     * @param user
     * @return
     */
    @PostMapping(value = "/saveQueryCustomerResult")
    public RestResult saveQueryCustomerResult(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.recordQueryCustomerResult(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }
    /**
     * 保存档案核验客服处理结果
     * @param params carProcessingId                手续id
     *               verificationResultId           查询结果id
     *               recordVerificationResultId     档案核验客服处理结果id
     *               state                          状态 1:未完成(暂存);2完成;3:不通过;4:退车;
     *               remark                         备注
     * @param user
     * @return
     */
    @PostMapping(value = "/saveVerificationCustomerResult")
    public RestResult saveVerificationCustomerResult(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.recordVerificationCustomerResult(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 保存第一次打印核档单记录
     * @param params carProcessingId    手续id
     * @param user
     * @return
     */
    @PostMapping(value = "/savePrintVerificationRecord")
    public RestResult savePrintVerificationRecord(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.savePrintVerificationRecord(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 保存商委数据上传状态
     * @param params carProcessingId    手续id
     * @param user
     * @return
     */
    @PostMapping(value = "/saveUploadShangWeiDataRecord")
    public RestResult saveUploadShangWeiDataRecord(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.saveUploadShangWeiDataRecord(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 保存打印回收证明状态
     * @param params carInfoId    车辆id
     * @param user
     * @return
     */
    @PostMapping(value = "/savePrintRecycleRecord")
    public RestResult savePrintRecycleRecord(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.savePrintRecycleRecord(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }
    /**
     * 保存注销时间
     * @param params carInfoId      车辆id
     *               logoutTime     注销时间
     * @param user
     * @return
     */
    @PostMapping(value = "/saveLogoutTimeRecord")
    public RestResult saveLogoutTimeRecord(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.saveLogoutTimeRecord(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }
    /**
     * 保存商委注销时间状态
     * @param params carInfoId      车辆id
     *               appointTime    商委注销时间
     * @param user
     * @return
     */
    @PostMapping(value = "/saveAppointLogoutTimeRecord")
    public RestResult saveAppointLogoutTimeRecord(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.saveAppointLogoutTimeRecord(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 分页查询手续客服列表
     * @param params pageNum int    页码
     *               pageSize int   页面大小
     *               searchInfo     查询条件
     * @param user
     * @return
     */
    @PostMapping(value = "/queryCarCustomerList")
    public RestResult queryCarCustomerList(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        PageInfo<CarCustomerListVo> carCustomerListVoPageInfo = proceduresService.queryCarCustomerList(params, user);
        return new RestResult("查询成功", carCustomerListVoPageInfo, ResultCode.SUCCESS.code());
    }

    /**
     * app-查询核档记录列表
     * @param params pageNum int    页码
     *               pageSize int   页面大小
     *               isVerify int   1:未核档；2：已核档；3:核档不通过
     * @param user
     * @return
     */
    @PostMapping(value = "/queryAppVerificationList")
    public RestResult queryAppVerificationList(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        PageInfo<AppCarBaseVo> carInfoPageInfo = proceduresService.queryAppVerificationList(params, user);
        return new RestResult("查询成功", carInfoPageInfo, ResultCode.SUCCESS.code());
    }

    /**
     * app-查询核档记录
     * @param params carInfoId
     * @param user
     * @return
     */
    @PostMapping(value = "/queryAppVerification")
    public RestResult queryAppVerification(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        CarCustomerInfoVo carCustomerInfoVo = proceduresService.queryCarCustomerInfo(params, user);
        return new RestResult("查询成功", carCustomerInfoVo, ResultCode.SUCCESS.code());
    }

    /**
     * web-查询一条客服记录
     * @param params carInfoId
     * @param user
     * @return
     */
    @PostMapping(value = "/queryCustomerHandle")
    public RestResult queryCustomerHandle(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        CarCustomerInfoVo carCustomerInfoVo = proceduresService.queryCarCustomerInfo(params, user);
        return new RestResult("查询成功", carCustomerInfoVo, ResultCode.SUCCESS.code());
    }
    /**
     * web-查询手续领取记录列表
     * @param params pageNum            页码
     *               pageSize           页面大小
     *               searchInfo         查询条件
     *               isProcedureIssue   1：未领取手续；2：已领取手续；
     *               isGetSalvage       1：未领取残值；2：已领取残值；
     * @param user
     * @return
     */
    @PostMapping(value = "/queryProcedureIssueVoList")
    public RestResult queryProcedureIssueVoList(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        PageInfo<CarProcedureIssueVo> carProcedureIssueVoPageInfo = proceduresService.queryProcedureIssueVoList(params, user);
        return new RestResult("查询成功", carProcedureIssueVoPageInfo, ResultCode.SUCCESS.code());
    }
    /**
     * web-查询商委上传所需数据
     * @param params carInfoId
     * @param user
     * @return
     */
    @PostMapping(value = "/queryShangWeiData")
    public RestResult queryShangWeiData(@RequestBody Map<String, Object> params, @LoginUser SysUser user) throws ExecutionException, InterruptedException {
        ShangWeiDataVo shangWeiDataVo = proceduresService.queryShangWeiData(params, user);
        return new RestResult("查询成功", shangWeiDataVo, ResultCode.SUCCESS.code());
    }
    /**
     * web-手续发放
     * @param params result     领取方式
     *               receiver   领取人
     *               carInfoId  车辆id
     *               remark     备注
     * @param user
     * @return
     */
    @PostMapping(value = "/provideProcedureIssue")
    public RestResult provideProcedureIssue(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        receiveRecordService.save(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }
    /**
     * web-查询手续管理列表
     * @param params pageNum            页码
     *               pageSize           页面大小
     *               searchInfo         查询条件
     * @param user
     * @return
     */
    @PostMapping(value = "/queryProcedureVoList")
    public RestResult queryProcedureVoList(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        PageInfo<CarProcedureListVo> carProcedureListVoPageInfo = proceduresService.queryProcedureVoList(params, user);
        return new RestResult("查询成功", carProcedureListVoPageInfo, ResultCode.SUCCESS.code());
    }

    /**
     * 分页查询核档单列表
     * @param params
     * @param user
     * @return
     */
    @PostMapping(value = "/queryVerifyOrderList")
    public RestResult queryVerifyOrderList(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        PageInfo<CarVerifyOrderVo> carVerifyOrderVoPageInfo = proceduresService.queryVerifyOrderList(params, user);
        return new RestResult("查询成功", carVerifyOrderVoPageInfo, ResultCode.SUCCESS.code());
    }
}
