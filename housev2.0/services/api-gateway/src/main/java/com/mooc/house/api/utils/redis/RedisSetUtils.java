package com.mooc.house.api.utils.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1018:28
 */
public class RedisSetUtils {
    /**************************** redis 集合Set start***************************/
    /**Redis的Set是string类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。**/
    private static final JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
    /**
     * 成功,1L
     */
    private static final Long SUCCESS_STATUS_LONG = 1L;
    /**
     * 向集合添加一个或多个成员，返回添加成功的数量
     * @param key
     * @param members
     * @return Long
     */
    public static Long sadd(String key, String... members){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key, members);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 获取集合的成员数
     * @param key
     * @return
     */
    public static Long scard(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.scard(key);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回集合中的所有成员
     * @param key
     * @return Set<String>
     */
    public static Set<String> smembers(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.smembers(key);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 判断 member 元素是否是集合 key 的成员，在集合中返回True
     * @param key
     * @param member
     * @return Boolean
     */
    public static Boolean sIsMember(String key, String member){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sismember(key, member);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回给定所有集合的差集（获取第一个key中与其它key不相同的值，当只有一个key时，就返回这个key的所有值）
     * @param keys
     * @return Set<String>
     */
    public static Set<String> sdiff(String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sdiff(keys);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回给定所有集合的差集并存储在 targetKey中，类似sdiff，只是该方法把返回的差集保存到targetKey中
     * <li>当有差集时，返回true</li>
     * <li>当没有差集时，返回false</li>
     * @param targetKey
     * @param keys
     * @return
     */
    public static boolean sdiffStore(String targetKey, String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long statusCode = jedis.sdiffstore(targetKey, keys);
            if(SUCCESS_STATUS_LONG == statusCode){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 返回给定所有集合的交集（获取第一个key中与其它key相同的值，要求所有key都要有相同的值，如果没有相同，返回Null。当只有一个key时，就返回这个key的所有值）
     * @param keys
     * @return Set<String>
     */
    public static Set<String> sinter(String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sinter(keys);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回给定所有集合的交集并存储在 targetKey中，类似sinter
     * @param targetKey
     * @param keys
     * @return boolean
     */
    public static boolean sinterStore(String targetKey, String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long statusCode = jedis.sinterstore(targetKey, keys);
            if(SUCCESS_STATUS_LONG == statusCode){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 将 member 元素从 sourceKey 集合移动到 targetKey 集合
     * <li>成功返回true</li>
     * <li>当member不存在于sourceKey时，返回false</li>
     * <li>当sourceKey不存在时，也返回false</li>
     * @param sourceKey
     * @param targetKey
     * @param member
     * @return boolean
     */
    public static boolean smove(String sourceKey, String targetKey, String member){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long value = jedis.smove(sourceKey, targetKey, member);
            if(value > 0){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 移除并返回集合中的一个随机元素
     * <li>当set为空或者不存在时，返回Null</li>
     * @param key
     * @return String
     */
    public static String spop(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.spop(key);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回集合中一个或多个随机数
     * <li>当count大于set的长度时，set所有值返回，不会抛错。</li>
     * <li>当count等于0时，返回[]</li>
     * <li>当count小于0时，也能返回。如-1返回一个，-2返回两个</li>
     * @param key
     * @param count
     * @return List<String>
     */
    public static List<String> srandMember(String key, int count){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srandmember(key, count);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 移除集合中一个或多个成员
     * @param key
     * @param members
     * @return
     */
    public static boolean srem(String key, String... members){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //Integer reply, specifically: 1 if the new element was removed
            //0 if the new element was not a member of the set
            Long value = jedis.srem(key, members);
            if(value > 0){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 返回所有给定集合的并集，相同的只会返回一个
     * @param keys
     * @return
     */
    public static Set<String> sunion(String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sunion(keys);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 所有给定集合的并集存储在targetKey集合中
     * <li>注：合并时，只会把keys中的集合返回，不包括targetKey本身</li>
     * <li>如果targetKey本身是有值的，合并后原来的值是没有的，因为把keys的集合重新赋值给targetKey</li>
     * <li>要想保留targetKey本身的值，keys要包含原来的targetKey</li>
     * @param targetKey
     * @param keys
     * @return
     */
    public static boolean sunionStore(String targetKey, String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //返回合并后的长度
            Long statusCode = jedis.sunionstore(targetKey, keys);
            if(statusCode > 0){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    /**************************** redis 集合Set end***************************/
}
