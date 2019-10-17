package com.tieshan.disintegrate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tieshan.disintegrate.dao.AppVersionMapper;
import com.tieshan.disintegrate.pojo.AppVersion;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IAppVersionService;
import com.tieshan.disintegrate.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/27 16:41
 * @version: 1.0
 * @modified By:
 */
@Service
public class AppVersionService implements IAppVersionService {
    @Autowired
    private AppVersionMapper appVersionMapper;

    @Override
    public Map<String, Object> getAppVersion(String version_number, String app_type) {
        return appVersionMapper.getAppVersion(Integer.parseInt(version_number), app_type);
    }

    @Override
    public PageInfo<AppVersion> queryPage(Map<String, Object> params) {
        PageHelper.startPage(
                StringUtils.isEmpty(params.get("pageNum")) ? 1 : Integer.parseInt(String.valueOf(params.get("pageNum"))),
                StringUtils.isEmpty(params.get("pageSize")) ? 10 : Integer.parseInt(String.valueOf(params.get("pageSize"))));
        PageHelper.orderBy("is_delete desc,create_time desc");
        List<AppVersion> appVersions = appVersionMapper.selectListByMap(params);
        PageInfo<AppVersion> pageInfo = new PageInfo<>(appVersions);
        return pageInfo;
    }

    @Override
    public AppVersion query(Map<String, Object> params) {
        return appVersionMapper.selectOneByMap(params);
    }

    @Override
    public void add(AppVersion appVersion, SysUser user) {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        appVersion.setId(idWorker.nextId());
        appVersion.setCreateTime(new Date());
        appVersion.setOperator(user.getUser_name());
        appVersionMapper.insertAppVersion(appVersion);
    }

    @Override
    public void update(AppVersion appVersion) {
        appVersionMapper.updateAppVersion(appVersion);
    }

    @Override
    public void del(Map<String, Object> params, SysUser user) {
        AppVersion appVersion = new AppVersion();
        appVersion.setId(Long.valueOf(params.get("id").toString()));
        appVersion.setIsDelete(1);
        appVersionMapper.updateAppVersion(appVersion);
    }
}
