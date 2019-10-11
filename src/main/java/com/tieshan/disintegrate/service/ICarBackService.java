package com.tieshan.disintegrate.service;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.vo.CarBackListVo;

import java.util.Map;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/20 16:52
 * @modified By：
 * @version: 1.0.0
 */
public interface ICarBackService {

    void savePrintCarBackRecord(Map<String, Object> params, SysUser user);

    void saveReceiveCarRecord(Map<String, Object> params, SysUser user);

    PageInfo<CarBackListVo> queryCarBackVoList(Map<String, Object> params, SysUser user);
}
