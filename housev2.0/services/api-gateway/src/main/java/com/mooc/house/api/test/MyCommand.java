package com.mooc.house.api.test;

import java.util.Date;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1016:18
 */
public class MyCommand implements Runnable {
    private String name;

    public MyCommand(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" ," +
                "name: "+name+","+new Date());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException execption) {
            execption.printStackTrace();
        }
    }
}
