package com.tieshan.disintegrate.service;

import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.pojo.AppVersion;
import com.tieshan.disintegrate.pojo.SysUser;

import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/27 16:40
 * @version: 1.0
 * @modified By:
 */
public interface IAppVersionService {
    Map<String, Object> getAppVersion(String version_number, String app_type);

    PageInfo<AppVersion> queryPage(Map<String, Object> params);

    AppVersion query(Map<String, Object> params);

    void add(AppVersion appVersion, SysUser user);

    void update(AppVersion appVersion);

    void del(Map<String, Object> params, SysUser user);
}
