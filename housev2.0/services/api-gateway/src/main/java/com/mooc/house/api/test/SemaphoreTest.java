package com.mooc.house.api.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * @author liwei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/1016:15
 */
public class SemaphoreTest {
    private final Executor exec;
    private final Semaphore semaphore;

    public SemaphoreTest(Executor exec,int bound){
        this.exec = exec;
        this.semaphore = new Semaphore(bound);
    }

    public void submitTask(final Runnable command) throws InterruptedException{
        try{
            semaphore.acquire();
            exec.execute(new Runnable(){
                @Override
                public void run() {
                    try{
                        command.run();
                    }finally{
                        System.out.println("执行完成 ，release...");
                        semaphore.release();
                    }
                }
            });
        }catch(RejectedExecutionException e){
            System.out.println("队列已满，拒绝执行");
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        //虽然线程池大小为4，但是Semaphore限制每次只能有两个任务被执行
        Executor exec = Executors.newFixedThreadPool(2);
        SemaphoreTest b = new SemaphoreTest(exec,7);

        MyCommand c1 = new MyCommand("c1");
        MyCommand c2 = new MyCommand("c2");
        MyCommand c3 = new MyCommand("c3");
        MyCommand c4 = new MyCommand("c4");
        MyCommand c5 = new MyCommand("c5");
        try {
            b.submitTask(c1);
            b.submitTask(c2);
            b.submitTask(c3);
            b.submitTask(c4);
            b.submitTask(c5);
        } catch (InterruptedException execption) {
            execption.printStackTrace();
        }
    }
}
