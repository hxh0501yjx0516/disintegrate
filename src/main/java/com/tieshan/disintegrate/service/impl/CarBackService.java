package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.dao.CarBackMapper;
import com.tieshan.disintegrate.dao.CarInfoMapper;
import com.tieshan.disintegrate.exception.CustomException;
import com.tieshan.disintegrate.pojo.CarBack;
import com.tieshan.disintegrate.pojo.CarInfo;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.ICarBackService;
import com.tieshan.disintegrate.vo.CarBackVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/20 16:53
 * @modified By：
 * @version: 1.0.0
 */
@Service
public class CarBackService implements ICarBackService {

    @Autowired
    private CarBackMapper carBackMapper;
    @Autowired
    private CarInfoMapper carInfoMapper;

    @Override
    public void savePrintCarBackRecord(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        params.put("id", params.get("carBackId"));
        CarBack carBack = carBackMapper.selectOneByMap(params);
        if (carBack == null) {
            throw new CustomException("退车记录不存在！");
        }
        carBack.setIsPrintCarOrder(2);
        carBack.setIsGetCar(1);
        carBack.setPrintCarOrderTime(new Date());
        carBack.setPrintCarOrderUserId(user.getId());
        carBackMapper.updateCarBack(carBack);
    }

    @Override
    public void saveReceiveCarRecord(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        params.put("id", params.get("carBackId"));
        CarBack carBack = carBackMapper.selectOneByMap(params);
        if (carBack == null) {
            throw new CustomException("退车记录不存在！");
        }
        carBack.setIsGetCar(2);
        carBack.setGetCarTime(new Date());
        carBack.setGetCarUser(user.getId());
        carBackMapper.updateCarBack(carBack);
    }

    @Override
    public PageInfo<CarBackVo> queryCarBackVoList(Map<String, Object> params, SysUser user) {
        params.put("disintegratePlantId", user.getCompany_id());
        PageHelper.startPage(
                StringUtils.isEmpty(params.get("pageNum")) ? 1 : Integer.parseInt(String.valueOf(params.get("pageNum"))),
                StringUtils.isEmpty(params.get("pageSize")) ? 10 : Integer.parseInt(String.valueOf(params.get("pageSize"))));
        PageHelper.orderBy("b.create_time desc");
        List<CarBackVo> carBackVos = carBackMapper.selectVoListByMap(params);
        PageInfo<CarBackVo> pageInfo = new PageInfo<>(carBackVos);
        return pageInfo;
    }
}
