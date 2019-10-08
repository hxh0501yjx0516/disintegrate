package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.vo.CarBreakInfoVo;

import java.util.List;

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
}
