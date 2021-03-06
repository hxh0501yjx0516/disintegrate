package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.Department_user;
import com.tieshan.disintegrate.pojo.SysLog;
import com.tieshan.disintegrate.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description: 用户的数据层
 * @author: huxuanhua
 * @date: Created in 2019/9/2 16:14
 * @version: 1.0
 * @modified By:
 */
public interface SysUserMapper {
    /**
     * 查询所有的用户
     *
     * @return
     */
    List<Map<String, Object>> allUser();

    /**
     * 通过登录名字查询用户信息
     *
     * @param username
     * @return
     */
    SysUser selectByUsername(@Param("username") String username);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    SysUser login(@Param("username") String username, @Param("password") String password);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int insertUser(@Param("user") SysUser user);

    /**
     * 登录日志
     *
     * @param user
     * @return
     */
    int loginlog(@Param("user") SysUser user);

    /**
     * 退出日志
     *
     * @param user
     * @return
     */

    int loginoutlog(@Param("user") SysUser user);

    /**
     * 添加部门用户的关系
     *
     * @param department_user
     * @return
     */
    int insertDepartment_user(@Param("department_user") Department_user department_user);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    int updateUser(@Param("user") SysUser user);

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    int updatePassword(@Param("user") SysUser user);

    /**
     * 更新用户和部门的关系
     *
     * @param user
     * @return
     */
    int updateDepartment_user(@Param("user") SysUser user);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int delUser(@Param("id") String id);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */

    int delDepartment_user(@Param("id") String id);

    /**
     * 通过员工主键id查询用户信息
     *
     * @param id
     * @return
     */
    SysUser getUserByid(@Param("id") String id);

    /**
     * 获取所有的用户
     *
     * @return
     */
    List<Map<String, Object>> findUserList();

    /**
     * 更新用户名字
     *
     * @param id
     * @param user_name
     * @return
     */
    int upName(@Param("id") String id, @Param("user_name") String user_name);

    /**
     * 更新用户名字
     *
     * @param id
     * @param head_url
     * @return
     */
    int upHeadUrl(@Param("id") String id, @Param("head_url") String head_url);


    /**
     * 修改密码
     *
     * @param password
     * @param id
     * @return
     */
    int findUserByPassword(@Param("password") String password, @Param("id") String id);

    /**
     * 创建系统日志表
     *
     * @param yearMonth
     * @return
     */
    int creatSyslog(@Param("yearMonth") String yearMonth);

    /**
     * 插入日志信息
     *
     * @param yearMonth
     * @return
     */
    int syslog(@Param("yearMonth") String yearMonth, @Param("sysLog") SysLog sysLog);

    /**
     * 判断表是否存在
     *
     * @param yearMonth
     * @return
     */
    int existTable(@Param("yearMonth") String yearMonth);

    /**
     * 删除表
     *
     * @param yearMonth
     * @return
     */

    int delTable(@Param("yearMonth") String yearMonth);
}
