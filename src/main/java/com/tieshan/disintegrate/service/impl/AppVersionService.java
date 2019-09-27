package com.tieshan.disintegrate.service.impl;

import com.tieshan.disintegrate.dao.AppVersionMapper;
import com.tieshan.disintegrate.service.IAppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
