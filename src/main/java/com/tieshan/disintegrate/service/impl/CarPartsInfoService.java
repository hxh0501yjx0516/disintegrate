package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.tieshan.disintegrate.dao.CarPartsInfoDao;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.ICarPartsInfoService;
import com.tieshan.disintegrate.vo.PartsInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description: 库管模块业务层
 * @author: Leavonson
 * @date: Created in 2019/10/12 17:19
 * @version: 1.0
 * @modified By:
 */
@Service
public class CarPartsInfoService implements ICarPartsInfoService {
    @Autowired
    CarPartsInfoDao carPartsInfoDao;
    @Override
    public List<PartsInfoVo> selectPreParts(String findMsg, Integer page, Integer pageSize, Long companyId) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("time desc");
        return carPartsInfoDao.selectPreParts(companyId,findMsg);
    }

    @Override
    public List<PartsInfoVo> selectIsParts(String findMsg, Integer page, Integer pageSize, Long companyId) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("time desc");
        return carPartsInfoDao.selectIsParts(companyId,findMsg);
    }

    @Override
    public int updateIsParts(Long carInfoId, SysUser user,String partsCode) {

        Long createOperatorId = user.getId();
        String createOperator = user.getUser_name();
        Long companyId = user.getCompany_id();
        return carPartsInfoDao.updateIsParts(createOperatorId,createOperator,carInfoId,companyId,partsCode);
    }
}
