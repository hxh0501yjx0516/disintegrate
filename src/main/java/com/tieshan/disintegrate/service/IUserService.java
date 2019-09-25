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
    /**
     * 获取用户列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> getUser(int page, int pageSize);

    SysUser selectByUsername(String username);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int insert(SysUser user);

    /**
     * 登录
     *
     * @param login_name
     * @param password
     * @return
     */
    SysUser login(String login_name, String password);

    /**
     * 用户修改
     *
     * @param user
     * @return
     */
    int updateUser(SysUser user);

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    int updatePassword(SysUser user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
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

    /**
     * 通过用户主键id获取用户信息
     *
     * @param id
     * @return
     */
    Map<String, Object> getUserByid(String id);

    /**
     * 判断密码是否正确
     *
     * @param password
     * @param id
     * @return
     */
    int findUserByPassword(String login_name, String password, String id);

    /**
     * 修改名字
     *
     * @param id
     * @param user_name
     * @return
     */
    int upName(String id, String user_name);

    /**
     * 修改头像
     *
     * @param id
     * @param head_url
     * @return
     */
    int upHeadUrl(String id, String head_url);


}
