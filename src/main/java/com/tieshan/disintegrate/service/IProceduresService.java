package com.tieshan.disintegrate.service;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.vo.AppCarBaseVo;
import com.tieshan.disintegrate.vo.CarCustomerInfoVo;
import com.tieshan.disintegrate.vo.ProceduresVo;

import java.util.Map;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/19 9:31
 * @modified By：
 * @version: 1.0.0
 */
public interface IProceduresService {

    void add(ProceduresVo proceduresVo, SysUser user);

    void update(ProceduresVo proceduresVo, SysUser user);

    ProceduresVo query(Map<String, Object> params, SysUser user);

    void recordQueryResult(Map<String, Object> params, SysUser user);

    void recordVerificationResult(Map<String, Object> params, SysUser user);

    void recordQueryCustomerResult(Map<String, Object> params, SysUser user);

    void recordVerificationCustomerResult(Map<String, Object> params, SysUser user);

    void savePrintVerificationRecord(Map<String, Object> params, SysUser user);

    void saveUploadShangWeiDataRecord(Map<String, Object> params, SysUser user);

   // PageInfo<CarInfo> queryQueryResultList(Map<String, Object> params, SysUser user);

    PageInfo<AppCarBaseVo> queryAppVerificationList(Map<String, Object> params, SysUser user);

    CarCustomerInfoVo queryCarCustomerInfo(Map<String, Object> params, SysUser user);

}
