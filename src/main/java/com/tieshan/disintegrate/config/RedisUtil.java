package com.tieshan.disintegrate.config;

import com.alibaba.fastjson.JSONObject;
import com.tieshan.disintegrate.pojo.SysUser;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @description:
 * @author: huxuanhua
 * @date: Created in 2019/9/3 11:47
 * @version: 1.0
 * @modified By:
 */

@Component
public class RedisUtil {
    public static void set(String key, String value) {
        ShardedJedis shardedJedis = ShardedJedisUtils.getShardedJedis(1);
        String keys = key.substring(0, key.lastIndexOf("-")) + "*";
        del(keys);
        shardedJedis.set(key, value);
        shardedJedis.close();
    }

    public static void setex(String key, String value, long seconds) {
        ShardedJedis shardedJedis = ShardedJedisUtils.getShardedJedis(1);
        String keys = key.substring(0, key.lastIndexOf("-")) + "*";
        del(keys);
//        shardedJedis.del(key);
        shardedJedis.set(key, value, "NX", "EX", seconds);
        shardedJedis.close();
    }

    public static boolean get(String key) {
        ShardedJedis shardedJedis = ShardedJedisUtils.getShardedJedis(1);
        boolean isLive = shardedJedis.exists(key);
        shardedJedis.close();
        return isLive;
    }

    public static SysUser getToken(String key) {
        ShardedJedis shardedJedis = ShardedJedisUtils.getShardedJedis(1);
        String tokens = shardedJedis.get(key);
        JSONObject jsonObject = JSONObject.parseObject(tokens);
        SysUser sysUser = JSONObject.toJavaObject(jsonObject, SysUser.class);
        shardedJedis.close();
        return sysUser;
    }

    public static void close(ShardedJedis shardedJedis) {
//        ShardedJedis shardedJedis = ShardedJedisUtils.getShardedJedis(1);
        shardedJedis.close();
    }

    public static void del(String delkey) {
        Set<String> set = getByPrefix(delkey);
        ShardedJedis shardedJedis = ShardedJedisUtils.getShardedJedis(1);

        System.err.println(set);

        for (String key : set) {
            shardedJedis.del(key);
        }

    }

    public static void remove(String delkey) {
        ShardedJedis shardedJedis = ShardedJedisUtils.getShardedJedis(1);
        long num = shardedJedis.del(delkey);
        shardedJedis.close();
    }

    public static Set<String> getByPrefix(String key) {
        Set<String> setResult = new HashSet<>();
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = ShardedJedisUtils.getShardedJedis(1);
            Iterator<Jedis> jedisIterator = shardedJedis.getAllShards().iterator();
            while (jedisIterator.hasNext()) {
                setResult = jedisIterator.next().keys(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shardedJedis.close();
        }
        return setResult;
    }

}

