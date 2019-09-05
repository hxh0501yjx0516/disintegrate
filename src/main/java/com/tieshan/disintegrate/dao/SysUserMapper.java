package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.Department_user;
import com.tieshan.disintegrate.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/2 16:14
 * @version: 1.0
 * @modified By:
 */
public interface SysUserMapper {
    List<Map<String, Object>> allUser();

    SysUser selectByUsername(@Param("username") String username);

    SysUser login(@Param("username") String username, @Param("password") String password);

    int insertUser(@Param("user") SysUser user);

    int insertDepartment_user(@Param("department_user") Department_user department_user);

    int updateUser(@Param("user") SysUser user);

    int updateDepartment_user(@Param("user") SysUser user);

    int delUser(@Param("id") String id);

    int delDepartment_user(@Param("id") String id);
}
