package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.dao.*;
import com.tieshan.disintegrate.exception.CustomException;
import com.tieshan.disintegrate.pojo.*;
import com.tieshan.disintegrate.service.IProceduresService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.vo.*;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/19 11:40
 * @modified By：
 * @version: 1.0.0
 */
@CommonsLog
@Service
public class ProceduresService implements IProceduresService {

    @Autowired
    private CarIdentityMapper carIdentityMapper;
    @Autowired
    private CarProcedureLogMapper carProcedureLogMapper;
    @Autowired
    private CarInfoMapper carInfoMapper;
    @Autowired
    private CarProcessingMapper carProcessingMapper;
    @Autowired
    private ServiceDealMapper serviceDealMapper;
    @Autowired
    private CarBackMapper carBackMapper;
    @Autowired
    private CarPicMapper carPicMapper;

    @Override
    @Transactional
    public void add(ProceduresVo proceduresVo, SysUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("disintegratePlantId", user.getCompany_id());
        if (StringUtils.isEmpty(proceduresVo.getCarInfoId())) {
            throw new CustomException("车辆编码不能为空");
        }
        map.put("carInfoId", proceduresVo.getCarInfoId());
        List<CarIdentity> carIdentities = carIdentityMapper.selectListByMap(map);
        if (carIdentities != null && carIdentities.size() >= 1) {
            throw new CustomException("该车辆已录入手续");
        }
        IdWorker idWorker = new IdWorker(1, 1, 1);
        proceduresVo.setId(idWorker.nextId());
        proceduresVo.setDisintegratePlantId(user.getCompany_id());
        carIdentityMapper.insertCarIdentity(proceduresVo);

        CarProcessing carProcessing = new CarProcessing();
        carProcessing.setCarInfoId(proceduresVo.getCarInfoId());
        carProcessing.setDisintegratePlantId(user.getCompany_id());
        carProcessing.setIsRegister(proceduresVo.getStatus());
        carProcessing.setId(idWorker.nextId());

        if (proceduresVo.getStatus() == 2) {
            //登记时间
            carProcessing.setRegisterTime(new Date());
            //登记人
            carProcessing.setRegisterUserId(user.getId());
            //未查询
            carProcessing.setIsQuery(1);
        }
        carProcessingMapper.insertCarProcessing(carProcessing);
    }

    @Override
    @Transactional
    public void saveProcedures(ProceduresVo proceduresVo, SysUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("disintegratePlantId", user.getCompany_id());
        map.put("carInfoId", proceduresVo.getCarInfoId());
        ProceduresVo OldProceduresVo = carIdentityMapper.selectOneByMap(map);
        if (OldProceduresVo.getStatus() == 2) {
            throw new CustomException("已登记完成！不能修改");
        }
        proceduresVo.setDisintegratePlantId(user.getCompany_id());
        carIdentityMapper.updateCarIdentity(proceduresVo);

        Map<String, Object> param = new HashMap<>();
        param.put("disintegratePlantId", user.getCompany_id());
        param.put("carInfoId", proceduresVo.getCarInfoId());
        CarProcessing carProcessing = carProcessingMapper.selectOneByMap(param);

        carProcessing.setIsRegister(proceduresVo.getStatus());

        if (proceduresVo.getStatus() == 2) {
            //登记时间
            carProcessing.setRegisterTime(new Date());
            //登记人
            carProcessing.setRegisterUserId(user.getId());
        }
        carProcessingMapper.updateCarProcessing(carProcessing);

        CarInfo carInfo = new CarInfo();
        carInfo.setId(proceduresVo.getCarInfoId());
        carInfo.setDisintegratePlantId(user.getCompany_id());
        carInfo.setDrivLicense(proceduresVo.getDrivLicense());
        carInfo.setRegistLicense(proceduresVo.getRegistLicense());
        carInfo.setContacts(proceduresVo.getContacts());
        carInfo.setContactsPhone(proceduresVo.getContactsPhone());
        carInfo.setContactsAddress(proceduresVo.getContactsAddress());

        carInfoMapper.updateCarInfo(carInfo);
    }

    @Override
    public ProceduresVo query(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        ProceduresVo proceduresVo = carIdentityMapper.selectOneByMap(params);
        return proceduresVo;
    }

    /**
     * @param params carProcessingId
     *               carProcedureLogId
     *               state
     *               remark
     *               recordNumber
     * @param user
     */
    @Override
    @Transactional
    public void recordQueryResult(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        params.put("id", params.get("carProcessingId"));
        CarProcessing carProcessing = carProcessingMapper.selectOneByMap(params);
        if (carProcessing == null) {
            throw new CustomException("查询失败！");
        }
        if (carProcessing.getIsRegister() != 2) {
            throw new CustomException("未登记完成！");
        }
        if (carProcessing.getIsQuery() == 2) {
            throw new CustomException("已录入查询结果！");
        }
        Integer state = Integer.valueOf(params.get("state").toString());
        CarProcedureLog carProcedurelog = new CarProcedureLog();
        carProcedurelog.setCarInfoId(carProcessing.getCarInfoId());

        carProcedurelog.setDisintegratePlantId(user.getCompany_id());
        carProcedurelog.setType(1);
        if (!StringUtils.isEmpty(params.get("remark"))) {
            carProcedurelog.setRemark(params.get("remark").toString());
        } else if (state == 3) {
            throw new CustomException("查询不通过需要在备注里填写相关原因！");
        }

        carProcedurelog.setCreateTime(new Date());
        carProcedurelog.setOperatorId(user.getId());
        carProcedurelog.setOperator(user.getUser_name());
        carProcedurelog.setState(state);


        if (StringUtils.isEmpty(params.get("queryId")) && StringUtils.isEmpty(params.get("queryResultId"))) {
            IdWorker idWorker = new IdWorker(1, 1, 1);
            carProcedurelog.setId(idWorker.nextId());
            carProcedureLogMapper.insertCarProcedureLog(carProcedurelog);
        } else {
            if (!StringUtils.isEmpty(params.get("queryId"))) {
                carProcedurelog.setId(Long.valueOf(params.get("queryId").toString()));
            } else {
                carProcedurelog.setId(Long.valueOf(params.get("queryResultId").toString()));
            }
            carProcedureLogMapper.updateCarProcedureLog(carProcedurelog);
        }

        carProcessing.setIsQuery(state);
        carProcessing.setQueryTime(new Date());
        carProcessing.setQueryUserId(user.getId());
        if (!StringUtils.isEmpty(params.get("recordNumber"))) {
            carProcessing.setRecordNumber(params.get("recordNumber").toString());
        } else if (StringUtils.isEmpty(params.get("recordNumber")) && state == 2) {
            throw new CustomException("查询完成需要填写档案号");
        }

        carProcessingMapper.updateCarProcessing(carProcessing);
    }

    /**
     * @param params carInfoId
     *               carProcedureLogId
     *               state
     *               remark
     * @param user
     */
    @Override
    @Transactional
    public void recordVerificationResult(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        params.put("id", params.get("carProcessingId"));
        CarProcessing carProcessing = carProcessingMapper.selectOneByMap(params);
        if (carProcessing == null) {
            throw new CustomException("查询失败！");
        }
        if (carProcessing.getIsRegister() != 2) {
            throw new CustomException("未登记完成！");
        }
        if (carProcessing.getIsQuery() != 2) {
            throw new CustomException("未录入查询结果！");
        }
        Integer state = Integer.valueOf(params.get("state").toString());
        CarProcedureLog carProcedurelog = new CarProcedureLog();
        carProcedurelog.setCarInfoId(carProcessing.getCarInfoId());

        carProcedurelog.setDisintegratePlantId(user.getCompany_id());
        carProcedurelog.setType(2);
        if (!StringUtils.isEmpty(params.get("remark"))) {
            carProcedurelog.setRemark(params.get("remark").toString());
        } else if (state == 3) {
            throw new CustomException("核档不通过需要在备注里填写相关原因！");
        }
        carProcedurelog.setCreateTime(new Date());
        carProcedurelog.setOperatorId(user.getId());
        carProcedurelog.setOperator(user.getUser_name());
        carProcedurelog.setState(state);


        if (StringUtils.isEmpty(params.get("queryId")) && StringUtils.isEmpty(params.get("verificationResultId"))) {
            IdWorker idWorker = new IdWorker(1, 1, 1);
            carProcedurelog.setId(idWorker.nextId());
            carProcedureLogMapper.insertCarProcedureLog(carProcedurelog);
        } else {
            if (!StringUtils.isEmpty(params.get("queryId"))) {
                carProcedurelog.setId(Long.valueOf(params.get("queryId").toString()));
            } else {
                carProcedurelog.setId(Long.valueOf(params.get("verificationResultId").toString()));
            }
            carProcedureLogMapper.updateCarProcedureLog(carProcedurelog);
        }

        carProcessing.setIsVerify(state);
        carProcessing.setVerifyTime(new Date());
        carProcessing.setVerifyUserId(user.getId());
        carProcessingMapper.updateCarProcessing(carProcessing);
    }

    @Override
    @Transactional
    public void recordQueryCustomerResult(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        CarProcessing carProcessing = carProcessingMapper.selectOneByMap(params);
        if (carProcessing == null) {
            throw new CustomException("查询失败！");
        }
        if (carProcessing.getIsRegister() != 2) {
            throw new CustomException("未登记完成！");
        }
        if (carProcessing.getIsQuery() != 3) {
            throw new CustomException("该查询不需要客服处理！");
        }
        Integer state = Integer.valueOf(params.get("state").toString());
        CarProcedureLog carProcedurelog = new CarProcedureLog();
        carProcedurelog.setCarInfoId(carProcessing.getCarInfoId());
        carProcedurelog.setDisintegratePlantId(user.getCompany_id());
        carProcedurelog.setProcedureLogId(Long.valueOf(params.get("queryId").toString()));

        carProcedurelog.setType(3);
        if (!StringUtils.isEmpty(params.get("remark"))) {
            carProcedurelog.setRemark(params.get("remark").toString());
        } else if (state == 4) {
            throw new CustomException("处理不通过需要在备注里填写相关原因！");
        }
        carProcedurelog.setCreateTime(new Date());
        carProcedurelog.setOperatorId(user.getId());
        carProcedurelog.setOperator(user.getUser_name());
        carProcedurelog.setState(state);

        IdWorker idWorker = new IdWorker(1, 1, 1);
        if (StringUtils.isEmpty(params.get("customerHandleId"))) {
            carProcedurelog.setId(idWorker.nextId());
            carProcedureLogMapper.insertCarProcedureLog(carProcedurelog);
        } else {
            carProcedurelog.setId(Long.valueOf(params.get("customerHandleId").toString()));
            carProcedureLogMapper.updateCarProcedureLog(carProcedurelog);
        }
        if (state == 2) {
            carProcessing.setIsQuery(1);
            carProcessing.setQueryResult(null);
            carProcessing.setQueryTime(null);
            carProcessing.setQueryUserId(null);
            carProcessingMapper.updateQueryCarProcessing(carProcessing);
        } else if (state == 4) {//退车
            CarBack carBack = new CarBack();
            carBack.setId(idWorker.nextId());
            carBack.setCarInfoId(carProcessing.getCarInfoId());
            carBack.setDisintegratePlantId(user.getCompany_id());
            carBack.setCreateTime(new Date());
            carBack.setCarBackReason(params.get("remark").toString());
            carBackMapper.insertCarBack(carBack);
        }


    }

    @Override
    @Transactional
    public void recordVerificationCustomerResult(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        CarProcessing carProcessing = carProcessingMapper.selectOneByMap(params);
        if (carProcessing == null) {
            throw new CustomException("查询失败！");
        }
        if (carProcessing.getIsRegister() != 2) {
            throw new CustomException("未登记完成！");
        }
        if (carProcessing.getIsVerify() != 3) {
            throw new CustomException("该核档结果不需要客服处理！");
        }
        Integer state = Integer.valueOf(params.get("state").toString());
        CarProcedureLog carProcedurelog = new CarProcedureLog();
        carProcedurelog.setCarInfoId(carProcessing.getCarInfoId());
        carProcedurelog.setDisintegratePlantId(user.getCompany_id());
        carProcedurelog.setProcedureLogId(Long.valueOf(params.get("queryId").toString()));

        carProcedurelog.setType(3);
        if (!StringUtils.isEmpty(params.get("remark"))) {
            carProcedurelog.setRemark(params.get("remark").toString());
        } else if (state == 4) {
            throw new CustomException("处理不通过需要在备注里填写相关原因！");
        }
        carProcedurelog.setCreateTime(new Date());
        carProcedurelog.setOperatorId(user.getId());
        carProcedurelog.setOperator(user.getUser_name());
        carProcedurelog.setState(state);

        IdWorker idWorker = new IdWorker(1, 1, 1);
        if (StringUtils.isEmpty(params.get("customerHandleId"))) {
            carProcedurelog.setId(idWorker.nextId());
            carProcedureLogMapper.insertCarProcedureLog(carProcedurelog);
        } else {
            carProcedurelog.setId(Long.valueOf(params.get("customerHandleId").toString()));
            carProcedureLogMapper.updateCarProcedureLog(carProcedurelog);
        }
        if (state == 2) {
            carProcessing.setIsVerify(1);
            carProcessing.setVerifyResult(null);
            carProcessing.setVerifyTime(null);
            carProcessing.setVerifyUserId(null);
            carProcessingMapper.updateVerifyCarProcessing(carProcessing);
        } else if (state == 4) {//退车
            CarBack carBack = new CarBack();
            carBack.setId(idWorker.nextId());
            carBack.setCarInfoId(carProcessing.getCarInfoId());
            carBack.setDisintegratePlantId(user.getCompany_id());
            carBack.setCreateTime(new Date());
            carBack.setCarBackReason(params.get("remark").toString());
            carBackMapper.insertCarBack(carBack);
        }
    }

    @Override
    public void savePrintVerificationRecord(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        CarProcessing carProcessing = carProcessingMapper.selectOneByMap(params);
        if (carProcessing == null) {
            throw new CustomException("车辆信息不存在！");
        }
        carProcessing.setIsPrintVerifyBill(2);
        carProcessing.setPrintVerifyBillUserId(user.getId());
        carProcessing.setPrintVerifyBillTime(new Date());
        carProcessingMapper.updateCarProcessing(carProcessing);
    }

    @Override
    public void saveUploadShangWeiDataRecord(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        CarProcessing sourceCarProcessing = carProcessingMapper.selectOneByMap(params);
        if (sourceCarProcessing == null) {
            throw new CustomException("车辆信息不存在！");
        }
        CarProcessing carProcessing = new CarProcessing();
        carProcessing.setId(sourceCarProcessing.getId());
        carProcessing.setDisintegratePlantId(user.getCompany_id());
        carProcessing.setIsDataUpload(2);
        carProcessing.setDataUploadUserId(user.getId());
        carProcessing.setDataUploadTime(new Date());
        carProcessingMapper.updateCarProcessing(carProcessing);
    }

    @Override
    public void savePrintRecycleRecord(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        CarProcessing sourceCarProcessing = carProcessingMapper.selectOneByMap(params);
        if (sourceCarProcessing == null) {
            throw new CustomException("车辆信息不存在！");
        }
        CarProcessing carProcessing = new CarProcessing();
        carProcessing.setDisintegratePlantId(user.getCompany_id());
        carProcessing.setId(sourceCarProcessing.getId());
        carProcessing.setIsPrintRecycle(2);
        carProcessing.setPrintRecycleUserId(user.getId());
        carProcessing.setPrintRecycleTime(new Date());
        carProcessingMapper.updateCarProcessing(carProcessing);
    }

    @Override
    public void saveLogoutTimeRecord(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        CarProcessing sourceCarProcessing = carProcessingMapper.selectOneByMap(params);
        if (sourceCarProcessing == null) {
            throw new CustomException("车辆信息不存在！");
        }
        CarProcessing carProcessing = new CarProcessing();
        carProcessing.setDisintegratePlantId(user.getCompany_id());
        carProcessing.setId(sourceCarProcessing.getId());
        carProcessing.setIsLogout(2);
        carProcessing.setLogoutUserId(user.getId());
        try {
            carProcessing.setLogoutTime(DateUtils.parseDate(params.get("logoutTime").toString(), "yyyy-MM-dd"));
        } catch (ParseException e) {
            throw new CustomException("注销时间不能为空");
        }
        carProcessingMapper.updateCarProcessing(carProcessing);
    }

    @Override
    public void saveAppointLogoutTimeRecord(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        CarProcessing sourceCarProcessing = carProcessingMapper.selectOneByMap(params);
        if (sourceCarProcessing == null) {
            throw new CustomException("车辆信息不存在！");
        }
        CarProcessing carProcessing = new CarProcessing();
        carProcessing.setId(sourceCarProcessing.getId());
        carProcessing.setIsAppointLogoutTime(2);
        carProcessing.setIsAppointUserid(user.getId());
        carProcessing.setDisintegratePlantId(user.getCompany_id());
        try {
            carProcessing.setIsAppointTime(DateUtils.parseDate(params.get("appointTime").toString(), "yyyy-MM-dd"));
        } catch (ParseException e) {
            throw new CustomException("商委注销时间不能为空");
        }
        carProcessingMapper.updateCarProcessing(carProcessing);
    }

/*    @Override
    public PageInfo<CarInfo> queryQueryResultList(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        PageHelper.startPage(
                StringUtils.isEmpty(params.get("pageNum")) ? 1 : Integer.parseInt(String.valueOf(params.get("pageNum"))),
                StringUtils.isEmpty(params.get("pageSize")) ? 10 : Integer.parseInt(String.valueOf(params.get("pageSize"))));
        List<CarInfo> carInfos = carInfoMapper.selectListByProcessing(params);
        PageInfo<CarInfo> pageInfo = new PageInfo<>(carInfos);
        return pageInfo;
    }*/

    @Override
    public PageInfo<AppCarVerificationVo> queryAppVerificationList(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        PageHelper.startPage(
                StringUtils.isEmpty(params.get("pageNum")) ? 1 : Integer.parseInt(String.valueOf(params.get("pageNum"))),
                StringUtils.isEmpty(params.get("pageSize")) ? 10 : Integer.parseInt(String.valueOf(params.get("pageSize"))));
        PageHelper.orderBy("e.approach_time desc");
        List<AppCarVerificationVo> carInfos = carInfoMapper.selectAppVerificationList(params);
        PageInfo<AppCarVerificationVo> pageInfo = new PageInfo<>(carInfos);
        return pageInfo;
    }

    @Override
    public PageInfo<AppCarQueryVo> queryAppQueryList(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        PageHelper.startPage(
                StringUtils.isEmpty(params.get("pageNum")) ? 1 : Integer.parseInt(String.valueOf(params.get("pageNum"))),
                StringUtils.isEmpty(params.get("pageSize")) ? 10 : Integer.parseInt(String.valueOf(params.get("pageSize"))));
        PageHelper.orderBy("e.approach_time desc");
        List<AppCarQueryVo> appCarQueryVos = carInfoMapper.selectAppQueryList(params);
        PageInfo<AppCarQueryVo> pageInfo = new PageInfo<>(appCarQueryVos);
        return pageInfo;
    }

    @Override
    public CarCustomerInfoVo queryCarCustomerInfo(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        return carInfoMapper.selectCarCustomerInfo(params);
    }

    @Override
    public WebCarCustomerInfoVo queryWebCarCustomerInfo(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        return carInfoMapper.selectWebCarCustomerInfo(params);
    }

    @Override
    public PageInfo<CarProcedureIssueVo> queryProcedureIssueVoList(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        PageHelper.startPage(
                StringUtils.isEmpty(params.get("pageNum")) ? 1 : Integer.parseInt(String.valueOf(params.get("pageNum"))),
                StringUtils.isEmpty(params.get("pageSize")) ? 10 : Integer.parseInt(String.valueOf(params.get("pageSize"))));
        PageHelper.orderBy("create_time desc");
        List<CarProcedureIssueVo> carProcedureIssueVos = carInfoMapper.selectProcedureIssueVoList(params);
        PageInfo<CarProcedureIssueVo> pageInfo = new PageInfo<>(carProcedureIssueVos);
        return pageInfo;
    }

    @Override
    public PageInfo<CarCustomerListVo> queryCarCustomerList(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        PageHelper.startPage(
                StringUtils.isEmpty(params.get("pageNum")) ? 1 : Integer.parseInt(String.valueOf(params.get("pageNum"))),
                StringUtils.isEmpty(params.get("pageSize")) ? 10 : Integer.parseInt(String.valueOf(params.get("pageSize"))));
        PageHelper.orderBy("create_time desc");
        List<CarCustomerListVo> carCustomerListVos = carProcedureLogMapper.selectCustomerVoList(params);
        PageInfo<CarCustomerListVo> pageInfo = new PageInfo<>(carCustomerListVos);
        return pageInfo;
    }

    @Override
    public PageInfo<CarProcedureListVo> queryProcedureVoList(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        PageHelper.startPage(
                StringUtils.isEmpty(params.get("pageNum")) ? 1 : Integer.parseInt(String.valueOf(params.get("pageNum"))),
                StringUtils.isEmpty(params.get("pageSize")) ? 10 : Integer.parseInt(String.valueOf(params.get("pageSize"))));
        PageHelper.orderBy("i.create_time desc");
        List<CarProcedureListVo> carProcedureListVos = carInfoMapper.selectProcedureVoList(params);
        PageInfo<CarProcedureListVo> pageInfo = new PageInfo<>(carProcedureListVos);
        return pageInfo;
    }

    @Override
    public ShangWeiDataVo queryShangWeiData(Map<String, Object> params, SysUser user) throws ExecutionException, InterruptedException {
        params.put("disintegratePlantId", user.getCompany_id());
        Callable a = () -> {
            Map<String, Object> map1 = params;
            return carIdentityMapper.selectShangWeiDataByMap(map1);
        };
        Callable b = () -> {
            Map<String, Object> map2 = params;
            map2.put("firstType", "pre_pic");
            return carPicMapper.selectListByMap(map2);
        };
        Callable c = () -> {
            Map<String, Object> map3 = params;
            map3.put("firstType", "tuo_pic");
            return carPicMapper.selectListByMap(map3);
        };
        Callable d = () -> {
            Map<String, Object> map4 = params;
            map4.put("firstType", "break_pic");
            return carPicMapper.selectListByMap(map4);
        };
        FutureTask<ShangWeiDataVo> fa = new FutureTask(a);
        FutureTask<List<CarPic>> fb = new FutureTask(b);
        FutureTask<List<CarPic>> fc = new FutureTask(c);
        FutureTask<List<CarPic>> fd = new FutureTask(d);

        new Thread(fa).start();
        ShangWeiDataVo shangWeiDataVo = fa.get();
        new Thread(fb).start();
        shangWeiDataVo.setPrePics(fb.get());
        new Thread(fc).start();
        shangWeiDataVo.setTuoPic(fc.get());
        new Thread(fd).start();
        shangWeiDataVo.setBreakPics(fd.get());
        return shangWeiDataVo;
    }

    @Override
    public PageInfo<CarVerifyOrderVo> queryVerifyOrderList(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        PageHelper.startPage(
                StringUtils.isEmpty(params.get("page")) ? 1 : Integer.parseInt(String.valueOf(params.get("page"))),
                StringUtils.isEmpty(params.get("pageSize")) ? 10 : Integer.parseInt(String.valueOf(params.get("pageSize"))));
        PageHelper.orderBy("i.create_time DESC");
        List<CarVerifyOrderVo> carVerifyOrderVos = carProcessingMapper.selectCarVerifyOrderVoList(params);
        PageInfo<CarVerifyOrderVo> pageInfo = new PageInfo<>(carVerifyOrderVos);
        return pageInfo;
    }

}
