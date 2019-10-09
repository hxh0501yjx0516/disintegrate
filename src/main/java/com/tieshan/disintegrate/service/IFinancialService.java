package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.vo.CarSalvageVo;

import java.util.List;

/**
 * @description: 财务管理模块服务层
 * @author: Leavonson
 * @date: Created in 2019/10/8 18:12
 * @version: 1.0
 * @modified By:
 */
public interface IFinancialService {

    /**财务管理-价格导入模块查询接口*/
    List<CarSalvageVo> findCarInfoAndSalvage(String findMsg, Integer page, Integer pageSize, SysUser user);
}
