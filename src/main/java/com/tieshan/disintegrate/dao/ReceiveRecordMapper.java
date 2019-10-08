package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.ReceiveRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 领取记录表
 *
 * @author ren lei
 * @date 2019-09-29 10:50:35
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface ReceiveRecordMapper {

    ReceiveRecord selectOneByMap(Map<String, Object> map);

    List<ReceiveRecord> selectListByMap(Map<String, Object> map);

    void insertReceiveRecord(ReceiveRecord ReceiveRecord);

    void insertBatchReceiveRecord(Map<String, Object> map);

    void updateReceiveRecord(ReceiveRecord ReceiveRecord);

    void updateBatchReceiveRecord(Map<String, Object> map);

    void deleteReceiveRecordByMap(Map<String, Object> map);

    void batchDeleteReceiveRecordByMap(Map<String, Object> map);

}
