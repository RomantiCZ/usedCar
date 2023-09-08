package cn.ikun.carshop.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper mapper;

    public <T> void opsSetValue(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object opsGetValue(String key) {
        var obj = redisTemplate.opsForValue().get(key);
        return obj;
    }

    public <T> void opsSetHash(String key, String hashKey, T obj) {
        redisTemplate.opsForHash().put(key, hashKey, obj);
    }

    public Object opsGetAllHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Object opsGetHashCol(String key,String hashKey){
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    public void deleteHashByHashKey(String key,String hashKey){
        redisTemplate.opsForHash().delete(key,hashKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return (List<T>) redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    public void deleteAllHash(String key){
        Set<Object> fields = redisTemplate.opsForHash().keys(key);
        if (!fields.isEmpty()){
            redisTemplate.opsForHash().delete(key,fields.toArray());
        }

    }


}
