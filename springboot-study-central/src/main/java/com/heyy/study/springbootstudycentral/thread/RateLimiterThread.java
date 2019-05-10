package com.heyy.study.springbootstudycentral.thread;

/**
 * @Classname RateLimiterThread
 * @Description TODO
 * @Date 2019/5/10 21:50
 * @Created by Breeze
 */
public class RateLimiterThread implements Runnable {

    private int i;

    public RateLimiterThread(int i){
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("第"+i+"个");
    }
}
