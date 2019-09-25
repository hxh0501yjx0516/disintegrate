package com.tieshan.disintegrate.controller;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.annotation.LoginUser;
import com.tieshan.disintegrate.pojo.CarIdentity;
import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IProceduresService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.RestResult;
import com.tieshan.disintegrate.util.ResultCode;
import com.tieshan.disintegrate.vo.ProceduresVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 添加手续
     * @param proceduresVo
     * @param user
     * @return
     */
    @PostMapping(value = "/addProcedures")
    public RestResult addCarInfo(@RequestBody ProceduresVo proceduresVo, @LoginUser SysUser user) {
        proceduresService.add(proceduresVo, user);
        return new RestResult("添加成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 修改手续
     * @param proceduresVo
     * @param user
     * @return
     */
    @PostMapping(value = "/updateProcedures")
    public RestResult updateCarInfo(@RequestBody ProceduresVo proceduresVo, @LoginUser SysUser user) {
        proceduresService.update(proceduresVo, user);
        return new RestResult("修改成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 查询手续
     * @param id
     * @param user
     * @return
     */
    @PostMapping(value = "/queryProcedures")
    public RestResult queryProcedures(@RequestBody Long id, @LoginUser SysUser user) {
        ProceduresVo proceduresVo = proceduresService.query(id, user);
        return new RestResult("修改成功", proceduresVo, ResultCode.SUCCESS.code());
    }

    /**
     * 档案查询结果
     * @param params carProcessingId 手续id
     *               queryResultId  查询结果id
     *               state          状态 1:未完成(暂存);2完成;3:不通过;
     *               remark         备注
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
     * @param params carProcessingId 手续id
     *               verificationResultId  档案核验结果id
     *               state          状态 1:未完成(暂存);2完成;3:不通过;
     *               remark         备注
     * @param user
     * @return
     */
    @PostMapping(value = "/saveVerificationResult")
    public RestResult saveVerificationResult(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.recordVerificationResult(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 档案查询客服处理结果
     * @param params carProcessingId 手续id
     *               queryResultId  查询结果id
     *               recordQueryResultId  档案查询客服处理结果id
     *               state          状态 1:未完成(暂存);2完成;3:不通过；4:退车;
     *               remark         备注
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
     * @param params carProcessingId 手续id
     *               verificationResultId  查询结果id
     *               recordVerificationResultId  档案核验客服处理结果id
     *               state          状态 1:未完成(暂存);2完成;3:不通过;4:退车;
     *               remark         备注
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
     * @param params carProcessingId 手续id
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
     * @param params carProcessingId 手续id
     * @param user
     * @return
     */
    @PostMapping(value = "/saveUploadShangWeiDataRecord")
    public RestResult saveUploadShangWeiDataRecord(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        proceduresService.saveUploadShangWeiDataRecord(params, user);
        return new RestResult("保存成功", "", ResultCode.SUCCESS.code());
    }

    /**
     * 查询手续查询录列表
     * @param params page int 页码
     *               pageSize int  页面大小
     *               isQuery int
     * @param user
     * @return
     */
    @PostMapping(value = "/queryQueryResultList")
    public RestResult queryQueryResultList(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        PageInfo<CarInfo> carInfoPageInfo = proceduresService.queryQueryResultList(params, user);
        return new RestResult("查询成功", carInfoPageInfo, ResultCode.SUCCESS.code());
    }

    /**
     * 查询核档记录列表
     * @param params page int 页码
     *               pageSize int  页面大小
     *               isVerify int
     * @param user
     * @return
     */
    @PostMapping(value = "/queryVerificationList")
    public RestResult queryVerificationList(@RequestBody Map<String, Object> params, @LoginUser SysUser user) {
        PageInfo<CarInfo> carInfoPageInfo = proceduresService.queryVerificationList(params, user);
        return new RestResult("查询成功", carInfoPageInfo, ResultCode.SUCCESS.code());
    }


}
