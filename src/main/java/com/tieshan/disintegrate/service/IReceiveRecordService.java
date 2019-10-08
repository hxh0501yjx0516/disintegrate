package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.SysUser;

import java.util.Map;

/**
 * @author ：ren lei
 * @date ：Created in 2019/9/29 11:21
 * @modified By：
 * @version: 1.0.0
 */
public interface IReceiveRecordService {

    void save(Map<String, Object> params, SysUser user);
}
