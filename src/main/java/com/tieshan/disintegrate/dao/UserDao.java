package com.tieshan.disintegrate.dao;

import java.util.List;
import java.util.Map;

/**
 * @description:测试数据层
 * @author: huxuanhua
 * @date: Created in 2019/8/28 18:07
 * @version: 1.0
 * @modified By:
 */
public interface UserDao {
    String getUser();
    List<Map<String,Object>> getMenu();
}
