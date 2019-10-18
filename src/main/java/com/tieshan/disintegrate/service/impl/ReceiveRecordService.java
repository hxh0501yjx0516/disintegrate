package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.CarProcessingMapper;
import com.tieshan.disintegrate.dao.ReceiveRecordMapper;
import com.tieshan.disintegrate.exception.CustomException;
import com.tieshan.disintegrate.pojo.CarProcessing;
import com.tieshan.disintegrate.pojo.ReceiveRecord;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IReceiveRecordService;
import com.tieshan.disintegrate.util.IdWorker;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/29 11:22
 * @modified By：
 * @version: 1.0.0
 */
@Service
public class ReceiveRecordService implements IReceiveRecordService {

    @Autowired
    private ReceiveRecordMapper receiveRecordMapper;
    @Autowired
    private CarProcessingMapper carProcessingMapper;

    @Override
    @Transactional
    public void save(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        CarProcessing carProcessing = carProcessingMapper.selectOneByMap(params);
        ReceiveRecord sourceReceiveRecord = receiveRecordMapper.selectOneByMap(params);
        if(carProcessing==null){
            throw new CustomException("手续信息不存在");
        }
        if(sourceReceiveRecord!=null){
            throw new CustomException("手续发放已记录");
        }
        ReceiveRecord receiveRecord = new ReceiveRecord();
        IdWorker idWorker = new IdWorker(1, 1, 1);
        receiveRecord.setId(idWorker.nextId());
        receiveRecord.setCarInfoId(Long.valueOf(params.get("carInfoId").toString()));
        receiveRecord.setDisintegratePlantId(user.getCompany_id());
        receiveRecord.setType(1);
        receiveRecord.setReceiver(params.get("receiver").toString());
        receiveRecord.setReceiveTime(new Date());
        receiveRecord.setResult(params.get("result").toString());
        receiveRecord.setRemark(params.get("remark").toString());
        receiveRecord.setOperator(user.getUser_name());
        receiveRecord.setOperatorId(user.getId());
        receiveRecordMapper.insertReceiveRecord(receiveRecord);

        carProcessing.setProcedureIssueTime(receiveRecord.getReceiveTime());
        carProcessing.setProcedureIssueUserId(user.getId());
        carProcessing.setIsProcedureIssue(2);
        carProcessingMapper.updateCarProcessing(carProcessing);
    }

    @Override
    public ReceiveRecord query(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        return receiveRecordMapper.selectOneByMap(params);
    }
}
