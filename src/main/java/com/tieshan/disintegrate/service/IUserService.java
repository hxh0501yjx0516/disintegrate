package com.tieshan.disintegrate.service;


import com.tieshan.disintegrate.pojo.SysUser;


public interface IUserService {
    SysUser getUser(SysUser user);
    SysUser selectByUsername(String  username);
    int insert( SysUser user);
    SysUser login( String login_name,String password);

}
