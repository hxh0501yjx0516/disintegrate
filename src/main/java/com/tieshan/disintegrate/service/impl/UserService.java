package com.tieshan.disintegrate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.tieshan.disintegrate.config.ShardedJedisUtils;
import com.tieshan.disintegrate.dao.SysUserMapper;
import com.tieshan.disintegrate.dao.UserDao;
import com.tieshan.disintegrate.pojo.Department_user;
import com.tieshan.disintegrate.pojo.SysUser;
import com.tieshan.disintegrate.service.IUserService;
import com.tieshan.disintegrate.token.TokenService;
import com.tieshan.disintegrate.util.IdWorker;
import com.tieshan.disintegrate.util.PubMethod;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private TokenService tokenService;

    @Override
    public List<Map<String, Object>> getUser(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        PageHelper.orderBy("id desc");
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
            user.setUser_status("1");
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
        sysUserMapper.updateDepartment_user(user);
        int num = sysUserMapper.updateUser(user);
        return num;
    }

    @Override
    @Transactional
    public int updatePassword(SysUser user) {
        user.setUser_password(DigestUtils.md5Hex(user.getLogin_name() + user.getUser_password()));
        int num = sysUserMapper.updatePassword(user);
        return num;
    }


    @Override
    @Transactional
    public int delUser(String id) {
        sysUserMapper.delDepartment_user(id);

        int num = sysUserMapper.delUser(id);
        return num;
    }

    @Override
    public void loginlog(SysUser user) {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        user.setId(idWorker.nextId());
        sysUserMapper.loginlog(user);
    }

    @Override
    public void loginoutlog(SysUser user) {
        IdWorker idWorker = new IdWorker(1, 1, 1);
        user.setId(idWorker.nextId());
        sysUserMapper.loginoutlog(user);
    }

    @Override
    public SysUser getUserByid(String id) {
        return sysUserMapper.getUserByid(id);
    }

    @Override
    public int findUserByPassword(String login_name, String password, String id) {
        String old_password = DigestUtils.md5Hex(login_name + password);
        return sysUserMapper.findUserByPassword(old_password, id);
    }

    @Override
    @Transactional
    public int upName(String user_name, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        int num = sysUserMapper.upName(sysUser.getId() + "", user_name);
        SysUser user = sysUserMapper.getUserByid(sysUser.getId() + "");
        tokenService.save(token, user);//跟新token
        return num;
    }

    @Override
    public int upHeadUrl(String id, String head_url, HttpServletRequest request) {
        String token = request.getHeader("token");
        SysUser sysUser = tokenService.getToken(token);
        int num = sysUserMapper.upHeadUrl(sysUser.getId() + "", head_url);
        SysUser user = sysUserMapper.getUserByid(sysUser.getId() + "");
        tokenService.save(token, user);//跟新token
        return num;
    }

//    @Override
//    public List<SysUser> findUserList() {
//        return sysUserMapper.findUserList();
//    }


}
