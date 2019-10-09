package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.vo.CarSalvageVo;

import java.util.List;
import java.util.Map;

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

    /**财务管理-价格导入-手动修改残值*/
    int updateCarSalvage(Long carInfoId,String salvage,SysUser user);

    /**财务管理-残值领取-查询列表接口*/
    List<Map<String,Object>> findCarInfoGetSalvage(String findMsg,Integer page,Integer pageSize,SysUser user);

    /**财务管理-残值领取-点击领取界面回显数据*/
    List<Map<String,Object>> findCarSalvageById(Long carInfoId,SysUser user);

    /**财务管理-残值领取-残值领取成功数据入库*/
    int insertSalvageById(Long carInfoId,Integer getWay,String remark,SysUser user);

    /**财务管理-残值领取-残值领取单数据*/
    List<Map<String,Object>> findDataSheet(Long carInfoId,SysUser user);
}
