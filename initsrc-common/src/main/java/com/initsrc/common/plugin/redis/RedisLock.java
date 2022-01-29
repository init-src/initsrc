package com.initsrc.common.plugin.redis;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.UUID;

/**
 * Redis可重入锁
 * @author INITSRC
 */
@Component
public class RedisLock {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private static final DefaultRedisScript<Long> LOCK_SCRIPT;
    private static final DefaultRedisScript<Object> UNLOCK_SCRIPT;

    static {
        // 加载释放锁的脚本
        LOCK_SCRIPT = new DefaultRedisScript<>();
        LOCK_SCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("/redislock/lock.lua")));
        LOCK_SCRIPT.setResultType(Long.class);

        // 加载释放锁的脚本
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setScriptSource(new ResourceScriptSource(new ClassPathResource("/redislock/unlock.lua")));
    }

    /**
     * 获取锁
     *
     * @param lockName    锁名称
     * @param releaseTime 超时时间(单位:秒)
     * @return key 解锁标识
     */
    public String tryLock(String lockName, String releaseTime) {
        // 存入的线程信息的前缀，防止与其它JVM中线程信息冲突
        String key = UUID.randomUUID().toString();
        Long currentTime = System.currentTimeMillis();
        // 执行脚本
        Long result = stringRedisTemplate.execute(
                LOCK_SCRIPT,
                Collections.singletonList(lockName),
                key + Thread.currentThread().getId(), releaseTime);

        // 判断结果
        if (result != null && result.intValue() == 1) {
            return key;
        } else {
            return null;
        }
    }


    /**
     * 获取凭证锁
     *
     * @param lockName    锁名称
     * @param releaseTime 超时时间(单位:秒)
     * @return key 解锁标识
     */
    public String tryTokenLock(String lockName, String releaseTime) {
        // 存入的线程信息的前缀，防止与其它JVM中线程信息冲突
        String key = UUID.randomUUID().toString();
        // 执行脚本
        Long result = stringRedisTemplate.execute(
                LOCK_SCRIPT,
                Collections.singletonList(lockName),
                key + Thread.currentThread().getId(), releaseTime);

        // 判断结果
        if (result != null && result.intValue() == 1) {
            return key;
        } else {
            return null;
        }
    }

    /**
     * 释放锁
     *
     * @param lockName 锁名称
     * @param key      解锁标识
     */
    public void unlock(String lockName, String key,String releaseTime) {
        if(releaseTime == null){
            releaseTime = "0";
        }
        // 执行脚本
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(lockName),
                key + Thread.currentThread().getId(), releaseTime);
    }
}
