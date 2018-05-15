package com.mooc.house.api.utils.redis;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1018:34
 */
public class RedisHashUtils {
    /**************************** redis Hash start***************************/
    /***Redis hash 是一个string类型的field和value的映射表，hash特别适合用于存储对象。***/
    private static final JedisPool jedisPool = new JedisPool("192.168.237.129", 6379);
    /**
     * 成功,"OK"
     */
    private static final String SUCCESS_OK = "OK";
    /**
     * 成功,1L
     */
    private static final Long SUCCESS_STATUS_LONG = 1L;
    /**
     * 设置Hash的属性
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static boolean hset(String key, String field, String value){
        if(StringUtils.isBlank(key) || StringUtils.isBlank(field)){
            return false;
        }
        Jedis jedis = jedisPool.getResource();
        //If the field already exists, and the HSET just produced an update of the value, 0 is returned,
        //otherwise if a new field is created 1 is returned.
        Long statusCode = jedis.hset(key, field, value);
        jedis.close();
        if(statusCode > -1){
            return true;
        }
        return false;
    }

    /**
     * 批量设置Hash的属性
     * @param key
     * @param fields
     * @param values
     * @return
     */
    public static boolean hmset(String key, String[] fields, String[] values){
        if(StringUtils.isBlank(key) || fields != null || fields.length == 0 || values != null || values.length == 0 ){
            return false;
        }
        Jedis jedis = jedisPool.getResource();
        Map<String, String> hash = new HashMap<String, String>();
        for (int i=0; i<fields.length; i++) {
            hash.put(fields[i], values[i]);
        }
        String statusCode = jedis.hmset(key, hash);
        jedis.close();
        if(SUCCESS_OK.equalsIgnoreCase(statusCode)){
            return true;
        }
        return false;
    }

    /**
     * 批量设置Hash的属性
     * @param key
     * @param map Map<String, String>
     * @return
     */
    public static boolean hmset(String key, Map<String, String> map){
        if(StringUtils.isBlank(key) || map.isEmpty()){
            return false;
        }
        Jedis jedis = jedisPool.getResource();
        String statusCode = jedis.hmset(key, map);
        jedis.close();
        if(SUCCESS_OK.equalsIgnoreCase(statusCode)){
            return true;
        }
        return false;
    }

    /**
     * 仅当field不存在时设置值，成功返回true
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static boolean hsetNX(String key, String field, String value){
        if(StringUtils.isBlank(key) || StringUtils.isBlank(field)){
            return false;
        }
        Jedis jedis = jedisPool.getResource();
        //If the field already exists, 0 is returned,
        //otherwise if a new field is created 1 is returned.
        Long statusCode = jedis.hsetnx(key, field, value);
        jedis.close();
        if(SUCCESS_STATUS_LONG == statusCode){
            return true;
        }
        return false;
    }

    /**
     * 获取属性的值
     * @param key
     * @param field
     * @return
     */
    public static String hget(String key, String field){
        if(StringUtils.isBlank(key) || StringUtils.isBlank(field)){
            return null;
        }
        Jedis jedis = jedisPool.getResource();
        String value = jedis.hget(key, field);
        jedis.close();
        return value;
    }

    /**
     * 批量获取属性的值
     * @param key
     * @param fields String...
     * @return
     */
    public static List<String> hmget(String key, String... fields){
        if(StringUtils.isBlank(key) || fields != null || fields.length == 0){
            return null;
        }
        Jedis jedis = jedisPool.getResource();
        List<String> values = jedis.hmget(key, fields);
        jedis.close();
        return values;
    }

    /**
     * 获取在哈希表中指定 key 的所有字段和值
     * @param key
     * @return Map<String, String>
     */
    public static Map<String, String> hgetAll(String key){
        if(StringUtils.isBlank(key)){
            return null;
        }
        Jedis jedis = jedisPool.getResource();
        Map<String, String> map = jedis.hgetAll(key);
        jedis.close();
        return map;
    }

    /**
     * 删除hash的属性
     * @param key
     * @param fields
     * @return
     */
    public static boolean hdel(String key, String... fields){
        if(StringUtils.isBlank(key) || fields != null || fields.length == 0){
            return false;
        }
        Jedis jedis = jedisPool.getResource();
        jedis.hdel(key, fields);
        jedis.close();
        //System.out.println("statusCode="+statusCode);
        return true;
    }

    /**
     * 查看哈希表 key 中，指定的字段是否存在。
     * @param key
     * @param field
     * @return
     */
    public static boolean hexists(String key, String field){
        if(StringUtils.isBlank(key) || StringUtils.isBlank(field)){
            return false;
        }
        Jedis jedis = jedisPool.getResource();
        boolean result = jedis.hexists(key, field);
        jedis.close();
        return result;
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment 。
     * @param key
     * @param field
     * @param increment 正负数、0、正整数
     * @return
     */
    public static long hincrBy(String key, String field, long increment){
        Jedis jedis = jedisPool.getResource();
        long result = jedis.hincrBy(key, field, increment);
        jedis.close();
        return result;
    }

    /**
     * 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。(注：如果field不存在时，会设置新的值)
     * @param key
     * @param field
     * @param increment，可以为负数、正数、0
     * @return
     */
    public static Double hincrByFloat(String key, String field, double increment){
        Jedis jedis = jedisPool.getResource();
        Double result = jedis.hincrByFloat(key, field, increment);
        jedis.close();
        return result;
    }

    /**
     * 获取所有哈希表中的字段
     * @param key
     * @return Set<String>
     */
    public static Set<String> hkeys(String key){
        Jedis jedis = jedisPool.getResource();
        Set<String> result = jedis.hkeys(key);
        jedis.close();
        return result;
    }

    /**
     * 获取哈希表中所有值
     * @param key
     * @return List<String>
     */
    public static List<String> hvals(String key){
        Jedis jedis = jedisPool.getResource();
        List<String> result = jedis.hvals(key);
        jedis.close();
        return result;
    }

    /**
     * 获取哈希表中字段的数量，当key不存在时，返回0
     * @param key
     * @return
     */
    public static Long hlen(String key){
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hlen(key);
        jedis.close();
        return result;
    }

    /**
     * 迭代哈希表中的键值对。
     * @param key
     * @param cursor
     * @return ScanResult<Entry<String, String>>
     */
    public static ScanResult<Map.Entry<String, String>> hscan(String key, String cursor){
        Jedis jedis = jedisPool.getResource();
        ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan(key, cursor);
        jedis.close();
        //System.out.println(scanResult.getResult());
        return scanResult;
    }

    /**************************** redis Hash end***************************/


    public static void main(String[] args) {
        hset("123456","999663222","0000001");
    }
}
