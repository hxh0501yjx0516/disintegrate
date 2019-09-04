package com.tieshan.disintegrate.token;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tieshan.disintegrate.config.RedisUtil;
import com.tieshan.disintegrate.pojo.SysUser;
import nl.bitwalker.useragentutils.UserAgent;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @description: 生成token
 * @author: huxuanhua
 * @date: Created in 2019/9/3 11:55
 * @version: 1.0
 * @modified By:
 */
@Service
public class TokenService {
    @Autowired
    Environment env;
    @Autowired
    private RedisUtil redisUtil;
    //生成token(格式为token:设备-加密的用户名-时间-六位随机数)
    public String generateToken(String type, SysUser user) {
        StringBuilder token = new StringBuilder();
        //设备
         token.append(type);
        //加密的用户名
        token.append(DigestUtils.md5Hex(user.getLogin_name()+user.getUser_password())+user.getId()+"-");
        //时间
        token.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
//        //六位随机字符串
        token.append(new Random().nextInt(999999 - 111111 + 1) + 111111 );
        System.err.println("token-->" + token.toString());
        return token.toString();
    }

    //把token存到redis中
    public void save(String token, SysUser user) {
        if (token.startsWith("PC")) {

            redisUtil.setex(token, JSON.toJSONString(user),  Long.parseLong(env.getProperty("pc_token")));
        } else {
            redisUtil.setex(token, JSONObject.toJSONString(user),Long.parseLong(env.getProperty("app_token")));
        }
    }


}
