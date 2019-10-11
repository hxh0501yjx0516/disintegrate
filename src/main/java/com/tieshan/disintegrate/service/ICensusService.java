package com.tieshan.disintegrate.service;

import com.tieshan.disintegrate.pojo.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/10/10 14:59
 * @version: 1.0
 * @modified By:
 */
public interface ICensusService {

    Map<String, Object> census(SysUser sysUser);

}
