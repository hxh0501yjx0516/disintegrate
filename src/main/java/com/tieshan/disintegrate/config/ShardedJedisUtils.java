package com.tieshan.disintegrate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import redis.clients.jedis.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 切片模式链接redis
 * @author: huxuanhua
 * @date: Created in 2019/8/29 14:59
 * @version: 1.0
 * @modified By:
 */
@Configuration
public class ShardedJedisUtils {
    public static ShardedJedisPool shardedJedisPool;//切片连接池
    private static ReentrantLock lockJedis = new ReentrantLock();

    @Autowired
    Environment env;

    @PostConstruct
    public ShardedJedisPool init() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(500);
        config.setMaxIdle(50);
        config.setMinIdle(8);
        config.setMaxWaitMillis(10000);
        config.setMinIdle(10);
        config.setTimeBetweenEvictionRunsMillis(30000);
        config.setNumTestsPerEvictionRun(10);
        config.setMinEvictableIdleTimeMillis(60000);
        config.setTestWhileIdle(true);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);

        // slave链接
        List<JedisShardInfo> shards = new ArrayList<>();
        JedisShardInfo jedisShardInfo = new JedisShardInfo(env.getProperty("spring.redis.host"), Integer.parseInt(env.getProperty("spring.redis.port")));
        jedisShardInfo.setPassword(env.getProperty("spring.redis.password"));
        shards.add(jedisShardInfo);
        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
        return shardedJedisPool;
    }


    public static ShardedJedis getShardedJedis(int num) {
        lockJedis.lock();
        if (shardedJedisPool == null) {
            new ShardedJedisUtils().init();
        }
        ShardedJedis shardedJedis = null;
        try {
            if (shardedJedisPool != null) {
                shardedJedis = shardedJedisPool.getResource();
                Collection<Jedis> collection = shardedJedis.getAllShards();
                Iterator<Jedis> jedis = collection.iterator();
                while (jedis.hasNext()) {
                    jedis.next().select(num);
                }
            }
        } finally {
            lockJedis.unlock();
        }

        return shardedJedis;
    }

    public static void cloneShardedJedis() {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        if (shardedJedis != null) {
            shardedJedis.close();
        }
    }
}
