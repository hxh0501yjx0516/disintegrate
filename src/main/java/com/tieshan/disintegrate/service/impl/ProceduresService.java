package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.*;
import com.tieshan.disintegrate.exception.CustomException;
import com.tieshan.disintegrate.pojo.*;
import com.tieshan.disintegrate.service.IProceduresService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.vo.ProceduresVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/19 11:40
 * @modified By：
 * @version: 1.0.0
 */
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

    @Override
    @Transactional
    public void add(ProceduresVo proceduresVo, SysUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("disintegratePlantId", user.getCompany_id());
        if (StringUtils.isEmpty(proceduresVo.getCarInfoId())) {
            throw new CustomException("车辆编码不能为空");
        }
        map.put("id", proceduresVo.getCarInfoId());
        CarInfo carInfo = carInfoMapper.selectOneByMap(map);
        if (carInfo == null) {
            throw new CustomException("未知的车辆编码");
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
    public void update(ProceduresVo proceduresVo, SysUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("disintegratePlantId", user.getCompany_id());
        map.put("id", proceduresVo.getId());
        CarIdentity carIdentity = carIdentityMapper.selectOneByMap(map);
        if (carIdentity.getStatus() == 2) {
            throw new CustomException("已登记完成！不能修改");
        }
        proceduresVo.setDisintegratePlantId(user.getCompany_id());
        carIdentityMapper.updateCarIdentity(proceduresVo);
        if (carIdentity.getStatus() == 1) {
            Map<String, Object> param = new HashMap<>();
            param.put("disintegratePlantId", user.getCompany_id());
            param.put("car_info_id", proceduresVo.getCarInfoId());
            CarProcessing carProcessing = carProcessingMapper.selectOneByMap(param);

            carProcessing.setIsRegister(proceduresVo.getStatus());
            //登记时间
            carProcessing.setRegisterTime(new Date());
            //登记人
            carProcessing.setRegisterUserId(user.getId());
            //未查询
            carProcessing.setIsQuery(1);
            carProcessingMapper.updateCarProcessing(carProcessing);
        }
    }

    @Override
    public ProceduresVo query(Long id, SysUser user) {
        Map<String, Object> map = new HashMap<>();
        map.put("disintegratePlantId", user.getCompany_id());
        map.put("id", id);
        CarIdentity carIdentity = carIdentityMapper.selectOneByMap(map);
        ProceduresVo proceduresVo = new ProceduresVo();
        BeanUtils.copyProperties(carIdentity, proceduresVo);
        return proceduresVo;
    }

    /**
     * @param params carProcessingId
     *               carProcedureLogId
     *               state
     *               result
     *               remark
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
        if (carProcessing.getIsQuery() != 1) {
            throw new CustomException("已录入查询结果！");
        }
        Integer state = Integer.valueOf(params.get("state").toString());
        CarProcedureLog carProcedurelog = new CarProcedureLog();
        carProcedurelog.setCarInfoId(carProcessing.getCarInfoId());

        carProcedurelog.setDisintegratePlantId(user.getCompany_id());
        carProcedurelog.setType(1);
        carProcedurelog.setResult(params.get("result").toString());
        carProcedurelog.setRemark(params.get("remark").toString());
        carProcedurelog.setCreateTime(new Date());
        carProcedurelog.setOperatorId(user.getId());
        carProcedurelog.setOperator(user.getUser_name());
        carProcedurelog.setState(state);


        if (StringUtils.isEmpty(params.get("carProcedureLogId"))) {
            IdWorker idWorker = new IdWorker(1, 1, 1);
            carProcedurelog.setId(idWorker.nextId());
            carProcedureLogMapper.insertCarProcedureLog(carProcedurelog);
        } else {
            carProcedurelog.setId(Long.valueOf(params.get("carProcedureLogId").toString()));
            carProcedureLogMapper.updateCarProcedureLog(carProcedurelog);
        }

        carProcessing.setIsQuery(state);
        carProcessing.setQueryResult(params.get("result").toString());
        carProcessing.setQueryTime(new Date());
        carProcessing.setQueryUserId(user.getId());
        carProcessingMapper.updateCarProcessing(carProcessing);
    }

    /**
     * @param params carProcessingId
     *               carProcedureLogId
     *               state
     *               result
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
        carProcedurelog.setResult(params.get("result").toString());
        carProcedurelog.setRemark(params.get("remark").toString());
        carProcedurelog.setCreateTime(new Date());
        carProcedurelog.setOperatorId(user.getId());
        carProcedurelog.setOperator(user.getUser_name());
        carProcedurelog.setState(state);


        if (StringUtils.isEmpty(params.get("carProcedureLogId"))) {
            IdWorker idWorker = new IdWorker(1, 1, 1);
            carProcedurelog.setId(idWorker.nextId());
            carProcedureLogMapper.insertCarProcedureLog(carProcedurelog);
        } else {
            carProcedurelog.setId(Long.valueOf(params.get("carProcedureLogId").toString()));
            carProcedureLogMapper.updateCarProcedureLog(carProcedurelog);
        }

        carProcessing.setIsVerify(state);
        carProcessing.setVerifyResult(params.get("result").toString());
        carProcessing.setVerifyTime(new Date());
        carProcessing.setVerifyUserId(user.getId());
        carProcessingMapper.updateCarProcessing(carProcessing);
    }

    @Override
    @Transactional
    public void recordQueryCustomerResult(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        params.put("id", params.get("carProcessingId"));
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
        carProcedurelog.setProcedureLogId(Long.valueOf(params.get("carProcedureLogId").toString()));

        carProcedurelog.setType(3);
        carProcedurelog.setResult(params.get("result").toString());
        carProcedurelog.setRemark(params.get("remark").toString());
        carProcedurelog.setCreateTime(new Date());
        carProcedurelog.setOperatorId(user.getId());
        carProcedurelog.setOperator(user.getUser_name());
        carProcedurelog.setState(state);

        IdWorker idWorker = new IdWorker(1, 1, 1);
        if (StringUtils.isEmpty(params.get("carProcedureLogId"))) {
            carProcedurelog.setId(idWorker.nextId());
            carProcedureLogMapper.insertCarProcedureLog(carProcedurelog);
        } else {
            carProcedurelog.setId(Long.valueOf(params.get("carProcedureLogId").toString()));
            carProcedureLogMapper.updateCarProcedureLog(carProcedurelog);
        }
        if (state == 2) {
            carProcessing.setIsQuery(1);
            carProcessing.setQueryResult(null);
            carProcessing.setQueryTime(null);
            carProcessing.setQueryUserId(null);
            carProcessingMapper.updateQueryCarProcessing(carProcessing);
        } else if (state == 3) {//退车
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
        params.put("id", params.get("carProcessingId"));
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
        carProcedurelog.setProcedureLogId(Long.valueOf(params.get("carProcedureLogId").toString()));

        carProcedurelog.setType(3);
        carProcedurelog.setResult(params.get("result").toString());
        carProcedurelog.setRemark(params.get("remark").toString());
        carProcedurelog.setCreateTime(new Date());
        carProcedurelog.setOperatorId(user.getId());
        carProcedurelog.setOperator(user.getUser_name());
        carProcedurelog.setState(state);

        IdWorker idWorker = new IdWorker(1, 1, 1);
        if (StringUtils.isEmpty(params.get("carProcedureLogId"))) {
            carProcedurelog.setId(idWorker.nextId());
            carProcedureLogMapper.insertCarProcedureLog(carProcedurelog);
        } else {
            carProcedurelog.setId(Long.valueOf(params.get("carProcedureLogId").toString()));
            carProcedureLogMapper.updateCarProcedureLog(carProcedurelog);
        }
        if (state == 2) {
            carProcessing.setIsVerify(1);
            carProcessing.setVerifyResult(null);
            carProcessing.setVerifyTime(null);
            carProcessing.setVerifyUserId(null);
            carProcessingMapper.updateQueryCarProcessing(carProcessing);
        } else if (state == 3) {//退车
            CarBack carBack = new CarBack();
            carBack.setId(idWorker.nextId());
            carBack.setCarInfoId(carProcessing.getCarInfoId());
            carBack.setDisintegratePlantId(user.getCompany_id());
            carBack.setCreateTime(new Date());
            carBack.setCarBackReason(params.get("remark").toString());
            carBackMapper.insertCarBack(carBack);
        }
    }
}
