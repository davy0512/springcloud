package com.mooc.house.api.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TestRedis {
    /**
     * @param args
     */
    public static void main(String[] args) {
        List arrayList = new ArrayList();
        String format = String.format("dinner.message.%s", "121212121212");
        System.out.println(format);
        int count = 1000;
        long start = System.currentTimeMillis();
        withoutPipeline(1000);
        long end = System.currentTimeMillis();
        System.out.println("withoutPipeline: " + (end-start));
        start = System.currentTimeMillis();
        usePipeline(1000);
        end = System.currentTimeMillis();
        System.out.println("usePipeline: " + (end-start));
    }

    private static void withoutPipeline(int count){
        Jedis jr = null;
        try {
            jr = new Jedis("192.168.237.129", 6379);
            for(int i =0; i<count; i++){
                jr.incr("testKey3");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(jr!=null){
                jr.disconnect();
            }
        }
    }
    private static void usePipeline(int count){
        Jedis jr = null;
        try {
            jr = new Jedis("192.168.237.129", 6379);
            Pipeline pl = jr.pipelined();
            for(int i =0; i<count; i++){
                pl.incr("testKey4");
            }
            pl.sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(jr!=null){
                jr.disconnect();
            }
        }
    }
}