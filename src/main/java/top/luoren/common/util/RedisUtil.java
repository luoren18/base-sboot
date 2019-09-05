package top.luoren.common.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author luoren
 * @date 2019/9/4 08:31
 */

@Component
public class RedisUtil {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisUtil(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, JsonUtil.objectToJson(value), expire, TimeUnit.SECONDS);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, JsonUtil.objectToJson(value));
    }


    public <T> T get(String key, Class<T> clazz) {
        String value = redisTemplate.opsForValue().get(key);
        return value == null ? null : JsonUtil.fromJson(value, clazz);
    }

    public String getJson(String key) {
        return redisTemplate.opsForValue().get(key);
    }


}