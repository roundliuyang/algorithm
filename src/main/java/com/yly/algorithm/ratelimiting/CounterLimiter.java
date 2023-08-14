package com.yly.algorithm.ratelimiting;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器固定窗口算法
 * 计数器固定窗口算法时最基础也是最简单的一种限流算法。原理就是对一段固定时间窗口内的请求进行计数，如果请求数超过了阈值，则舍弃该请求；
 * 如果没有达到设定的阈值，则接受该请求，且计数加1。当时间窗口结束时，重置计数器为0。
 */
public class CounterLimiter {

    private int windowSize;   // 窗口大小，毫秒为单位
    private int limit;        // 窗口内限流大小
    private AtomicInteger count;  // 当前窗口的计数器

    public CounterLimiter() {
    }

    public CounterLimiter(int windowSize, int limit) {
        this.windowSize = windowSize;
        this.limit = limit;
        this.count = new AtomicInteger(0);

        // 开启一个线程，达到窗口结束时清空 count
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    count.set(0);
                    try {
                        Thread.sleep(windowSize);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 请求到达后先调用本方法，若返回 true,则请求通过，否则限流
     * 多线程由 AtomicInteger 保证线程安全
     */
    public boolean tryAcquire() {
        int newCount = this.count.addAndGet(1);
        if (newCount > limit) {
            return false;
        } else {
            return true;
        }
    }

    // 测试
    public static void main(String[] args) throws InterruptedException {

        // 每秒 20 个请求
        CounterLimiter counterLimiter = new CounterLimiter(1000, 20);
        int count = 0;
        // 模拟50次请求，看多少能通过
        for (int i = 0; i < 50; i++) {
            if (counterLimiter.tryAcquire()) {
                count++;
            }
        }
        System.out.println("第一拨50次请求中通过：" + count + ",限流：" + (50 - count));
        //过一秒再请求
        Thread.sleep(1000);
        //模拟50次请求，看多少能通过
        count = 0;
        for (int i = 0; i < 50; i++) {
            if (counterLimiter.tryAcquire()) {
                count++;
            }
        }
        System.out.println("第二拨50次请求中通过：" + count + ",限流：" + (50 - count));
    }
}
