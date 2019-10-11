package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.vo.CarBreakInfoVo;
import com.tieshan.disintegrate.vo.PartsListVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @description: 拆解管理模块服务层接口
 * @author: Leavonson
 * @date: Created in 2019/9/29 17:59
 * @version: 1.0
 * @modified By:
 */
public interface IDismantleService {

    /**拆解管理模块查询接口*/
    List<CarBreakInfoVo> findDismantleList(String findMsg,Integer page,Integer pageSize,SysUser user);

    /**更改拆解状态*/
    void updateDismantle(Long operatorId,Long carInfoId,Long companyId);

    /**拆车-查询打印配件名称接口*/
    List<Map<String,Object>> findPartsNameList();

    /**拆车-打印配件接口*/
    int addCarParts(Long carInfoId,SysUser user, List<Map<String,Object>> partsNameAndOeList, Integer partsStatus);

    /**拆车-查询二级分类列表接口*/
    List<PartsListVo> findPartsNameListByParentId();


}
