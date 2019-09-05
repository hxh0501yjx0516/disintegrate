package com.tieshan.disintegrate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tieshan.disintegrate.config.ShardedJedisUtils;
import com.tieshan.disintegrate.dao.SysUserMapper;
import com.tieshan.disintegrate.dao.UserDao;
import com.tieshan.disintegrate.pojo.Department_user;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IUserService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.PubMethod;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private Department_user department_user;

    @Override
    public List<Map<String, Object>> getUser() {
        return sysUserMapper.allUser();
    }

    @Override
    public SysUser selectByUsername(String username) {
        return sysUserMapper.selectByUsername(username);
    }

    @Override
    @Transactional
    public int insert(SysUser user) {
        SysUser sysUser = sysUserMapper.selectByUsername(user.getLogin_name());
        if (PubMethod.isEmpty(sysUser)) {
            IdWorker idWorker = new IdWorker(1, 1, 1);
            user.setId(idWorker.nextId());
            user.setUser_password(DigestUtils.md5Hex(user.getLogin_name() + user.getUser_password()));
            sysUserMapper.insertUser(user);
            department_user.setId(idWorker.nextId());
            department_user.setDepartment_id(user.getDepart_id());
            department_user.setUser_id(user.getId());
            return sysUserMapper.insertDepartment_user(department_user);
        }

        return 0;
    }

    @Override
    public SysUser login(String login_name, String password) {
        password = DigestUtils.md5Hex(login_name + password);
        SysUser sysUser = sysUserMapper.login(login_name, password);

        if (!PubMethod.isEmpty(sysUser)) {
            return sysUser;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public int updateUser(SysUser user) {
        user.setUser_password(DigestUtils.md5Hex(user.getLogin_name() + user.getUser_password()));
        sysUserMapper.updateDepartment_user(user);
        int num = sysUserMapper.updateUser(user);

        return num;
    }


    @Override
    @Transactional
    public int delUser(String id) {
        sysUserMapper.delDepartment_user(id);

        int num = sysUserMapper.delUser(id);
        return num;
    }


}
