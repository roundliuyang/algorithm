package com.yly.algorithm.面试日常.多线程;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 现在有T1、T2、T3三个线程，你怎样保证T2在T1执行完后执行，T3在T2执行完后执行
 */
public class 三个线程顺序执行 {

    // 使用 join
    public static void main(String[] args) {

        final Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("t1");
            }
        });
        final Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    // 引用t1线程，等待t1线程执行完
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2");
            }
        });
        Thread t3 = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    // 引用t2线程，等待t2线程执行完
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t3");
            }
        });
        t3.start();
        t2.start();
        t1.start();
    }
    
    // 使用 CountDownLatch  —— 最常见、最直观
    public static void main2(String[] args) {
        CountDownLatch latch12 = new CountDownLatch(1); // T1 ➜ T2
        CountDownLatch latch23 = new CountDownLatch(1); // T2 ➜ T3

        Thread t1 = new Thread(() -> {
            System.out.println("t1");
            latch12.countDown();      // 允许 T2 继续
        });

        Thread t2 = new Thread(() -> {
            try {
                latch12.await();      // 等 T1
            } catch (InterruptedException ignored) {}
            System.out.println("t2");
            latch23.countDown();      // 允许 T3 继续
        });

        Thread t3 = new Thread(() -> {
            try {
                latch23.await();      // 等 T2
            } catch (InterruptedException ignored) {}
            System.out.println("t3");
        });

        t3.start(); t2.start(); t1.start();
    }
    
    // ReentrantLock + Condition —— 细粒度控制
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition t1Done = lock.newCondition();
    private static final Condition t2Done = lock.newCondition();
    private static volatile boolean isT1Over = false;
    private static volatile boolean isT2Over = false;

    public static void main3(String[] args) {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("t1");
                isT1Over = true;
                t1Done.signal();      // 通知 T2
            } finally { lock.unlock(); }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                while (!isT1Over) t1Done.await();
                System.out.println("t2");
                isT2Over = true;
                t2Done.signal();      // 通知 T3
            } catch (InterruptedException ignored) {}
            finally { lock.unlock(); }
        });

        Thread t3 = new Thread(() -> {
            lock.lock();
            try {
                while (!isT2Over) t2Done.await();
                System.out.println("t3");
            } catch (InterruptedException ignored) {}
            finally { lock.unlock(); }
        });

        t3.start(); t2.start(); t1.start();
    }
}
