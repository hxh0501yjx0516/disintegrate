package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.vo.PartsInfoVo;

import java.util.List;
import java.util.Map;

/**
 * @description: 库管模块业务层接口
 * @author: Leavonson
 * @date: Created in 2019/10/12 17:18
 * @version: 1.0
 * @modified By:
 */
public interface ICarPartsInfoService {

    /***
     * App端-查询待入库列表
     */
    List<PartsInfoVo> selectPreParts(String findMsg, Integer page, Integer pageSize, Long companyId);
    /***
     * App端-查询已入库列表
     */
    List<PartsInfoVo> selectIsParts(String findMsg, Integer page, Integer pageSize, Long companyId);
    /***
     * App端-更改库存状态
     */
    int updateIsParts(Long carInfoId,SysUser user,String partsCode);
}
