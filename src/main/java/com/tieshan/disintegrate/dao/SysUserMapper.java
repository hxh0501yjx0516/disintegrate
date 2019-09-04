package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.pojo.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/2 16:14
 * @version: 1.0
 * @modified By:
 */
public interface SysUserMapper {
   SysUser selectOne(@Param("user") SysUser user);
   SysUser selectByUsername(@Param("username")String  username);
   int insert(@Param("user") SysUser user);
}
