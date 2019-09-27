package com.tieshan.disintegrate.dao;

import java.util.Map;

/**
 * @description: APP版本数据层
 * @author: huxuanhua
 * @date: Created in 2019/9/27 16:10
 * @version: 1.0
 * @modified By:
 */
public interface AppVersionMapper {
    /**
     * 获取要更新的版本信息
     *
     * @param version_number
     * @param app_type
     * @return
     */
    Map<String, Object> getAppVersion(int version_number, String app_type);
}
