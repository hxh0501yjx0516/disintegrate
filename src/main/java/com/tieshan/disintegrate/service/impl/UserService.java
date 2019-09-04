package com.tieshan.disintegrate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tieshan.disintegrate.config.ShardedJedisUtils;
import com.tieshan.disintegrate.dao.SysUserMapper;
import com.tieshan.disintegrate.dao.UserDao;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IUserService;
import com.tieshan.disintegrate.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description:测试业务实现层,redis
 * @author: huxuanhua
 * @date: Created in 2019/8/28 18:07
 * @version: 1.0
 * @modified By:
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser getUser(SysUser user) {
        return sysUserMapper.selectOne(user);
    }

    @Override
    public SysUser selectByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    @Override
    public int insert(SysUser user) {
        return sysUserMapper.insert(user);
    }

    @Override
    public SysUser login(String login_name, String password) {
        if ("admin".equals(login_name) && "111111".equals(password)){
            SysUser sysUser = new SysUser();
            IdWorker idWorker = new IdWorker(1,1,1);
            sysUser.setId(1168779606862467072L);
            sysUser.setLogin_name("tom");
            sysUser.setUser_password("123456");
            return sysUser;
        } else {
            return null;
        }
    }


}
