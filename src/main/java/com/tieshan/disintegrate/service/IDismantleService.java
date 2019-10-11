package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.vo.CarBreakInfoVo;
import com.tieshan.disintegrate.vo.CarPartsData;
import com.tieshan.disintegrate.vo.PartsListVo;

import javax.servlet.http.HttpServletRequest;
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
    /**
     * 查询监销和不监销车辆
     */
    List<Map<String, Object>> selectIsSuperviseSale(HttpServletRequest request, Integer page, Integer pageSize, String findMsg, Integer isSuperviseSale);
    /**查询待拆和已拆车辆*/
    List<Map<String, Object>> selectIsDismantle(HttpServletRequest request, Integer page, Integer pageSize, String findMsg, Integer isDismantle);

    /**查询所有已拆的件*/
    List<Map<String, Object>> selectCarParts(HttpServletRequest request, Integer page, Integer pageSize, String findMsg);

    /**更改拆解状态*/
    void updateDismantle(Long operatorId,Long carInfoId,Long companyId);

    /**拆车-查询打印配件名称接口*/
    List<Map<String,Object>> findPartsNameList();

    /**拆车-打印配件接口*/
    int addCarParts(CarPartsData carPartsData, SysUser user);

    /**拆车-查询二级分类列表接口*/
    List<PartsListVo> findPartsNameListByParentId();


}
