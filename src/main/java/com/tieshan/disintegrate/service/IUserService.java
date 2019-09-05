package com.tieshan.disintegrate.service;


import com.tieshan.disintegrate.pojo.SysUser;

import java.util.List;
import java.util.Map;


public interface IUserService {
    public List<Map<String, Object>> getUser();

    SysUser selectByUsername(String username);

    int insert(SysUser user);

    SysUser login(String login_name, String password);

    int updateUser(SysUser user);


    int delUser(String id);


}
