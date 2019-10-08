package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.PageHelper;
import com.tieshan.disintegrate.dao.CarDismantleDao;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IDismantleService;
import com.tieshan.disintegrate.vo.CarBreakInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 拆解管理模块服务层
 * @author: Leavonson
 * @date: Created in 2019/9/29 17:59
 * @version: 1.0
 * @modified By:
 */
@Service
public class DismantleService implements IDismantleService {
    @Autowired
    private CarDismantleDao carDismantleDao;

    @Override
    public List<CarBreakInfoVo> findDismantleList(String findMsg,Integer page,Integer pageSize,SysUser user) {
        List<CarBreakInfoVo> dismantleList = new ArrayList<>();
        CarBreakInfoVo breakInfoVo = null;
        //分页信息
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("id desc");
        //查询出基本信息封装为Map中，下一步遍历
        List<Map<String,Object>> carInfoList = carDismantleDao.findCarInfo(findMsg,user.getCompany_id());
        for (Map<String, Object> carInfoMap : carInfoList) {
            breakInfoVo = new CarBreakInfoVo();
            //获取当前CarInfoId，为后续查询图片使用
            String str=String.valueOf(carInfoMap.get("id"));
            Long carInfoId=Long.valueOf(str);
            //封装一行数据
            breakInfoVo.setCarCode(carInfoMap.get("car_code").toString());
            breakInfoVo.setCarNo(carInfoMap.get("car_no").toString());
            breakInfoVo.setCarName(carInfoMap.get("car_name").toString());
            breakInfoVo.setContacts(carInfoMap.get("contacts").toString());
            breakInfoVo.setContactsPhone(carInfoMap.get("contacts_phone").toString());
            breakInfoVo.setPrePic(carDismantleDao.findPrePic(carInfoId,user.getCompany_id()));
            breakInfoVo.setBreakPic(carDismantleDao.findBreakPic(carInfoId,user.getCompany_id()));
            //将封装好的一整行数据存入List
            dismantleList.add(breakInfoVo);
        }
        //返回List
        return dismantleList;
    }
}
