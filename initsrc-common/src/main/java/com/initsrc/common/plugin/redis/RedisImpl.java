package com.initsrc.common.plugin.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/**
 * redis实现类
 * @author INITSRC
 */
@Service
public class RedisImpl {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key) {
        return StringUtils.isBlank(key) ? null : redisTemplate.opsForValue().get(key);
    }


    public boolean set(String key, Object value) {
        if (StringUtils.isBlank(key) || value == null) {
            return false;
        } else {
            try {
                redisTemplate.opsForValue().set(key, value);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    public boolean remove(String... key) {
       return  redisTemplate.delete(key[0]);
    }

    public void delMulti(String keyStart, String keyEnd) {
        Set<String> keys = StringUtils.isBlank(keyEnd) ? null : redisTemplate.keys(keyStart + "*" + keyEnd );
        redisTemplate.delete(keys);
    }

    public Long increment(String key) {
       return redisTemplate.opsForValue().increment(key);
    }

    public Long increment(String key,Long count) {
        return redisTemplate.opsForValue().increment(key,count);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
}
