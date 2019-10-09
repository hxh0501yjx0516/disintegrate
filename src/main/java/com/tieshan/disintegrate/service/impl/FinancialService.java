package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.tieshan.disintegrate.dao.FinancialDao;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IFinancialService;
import com.tieshan.disintegrate.vo.CarBreakInfoVo;
import com.tieshan.disintegrate.vo.CarSalvageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 财务管理模块服务层接口
 * @author: Leavonson
 * @date: Created in 2019/10/8 18:13
 * @version: 1.0
 * @modified By:
 */
@Service
public class FinancialService implements IFinancialService {

    @Autowired
    private FinancialDao financialDao;


    @Override
    public List<CarSalvageVo> findCarInfoAndSalvage(String findMsg, Integer page, Integer pageSize, SysUser user) {

        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("id desc");
        List<CarSalvageVo> list = financialDao.findCarInfoAndSalvage(findMsg,user.getCompany_id());
        return list;
    }
}
