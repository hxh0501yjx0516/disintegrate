package com.tieshan.disintegrate.service;


import com.tieshan.disintegrate.pojo.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @description:用户逻辑
 * @author: huxuanhua
 * @date: Created in 2019/8/28 18:07
 * @version: 1.0
 * @modified By:
 */
public interface IUserService {
    public List<Map<String, Object>> getUser();

    SysUser selectByUsername(String username);

    int insert(SysUser user);

    SysUser login(String login_name, String password);

    int updateUser(SysUser user);

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    int updatePassword(SysUser user);

    int delUser(String id);

    /**
     * 登录日志
     *
     * @param user
     */
    void loginlog(SysUser user);

    /**
     * 退出日志
     *
     * @param user
     */
    void loginoutlog(SysUser user);


}
