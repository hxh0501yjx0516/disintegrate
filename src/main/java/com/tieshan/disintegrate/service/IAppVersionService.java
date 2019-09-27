package com.tieshan.disintegrate.service;

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
}
