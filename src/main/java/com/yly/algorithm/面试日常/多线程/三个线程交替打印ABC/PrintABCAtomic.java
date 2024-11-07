package com.yly.algorithm.面试日常.多线程.三个线程交替打印ABC;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintABCAtomic {

    private static final AtomicInteger state = new AtomicInteger(0);

    private static int TIMES = 3;

    private static Runnable printA = () -> {
        for (int i = 0; i < TIMES; i++) {
            while (state.get() % 3 != 0) {
                Thread.yield();
            }
            System.out.println("A");
            state.incrementAndGet();
        }
    };

    private static Runnable printB = () -> {
        for (int i = 0; i < TIMES; i++) {
            while (state.get() % 3 != 1) {
                Thread.yield();
            }
            System.out.println("B");
            state.incrementAndGet();
        }
    };

    private static Runnable printC = () -> {
        for (int i = 0; i < TIMES; i++) {
            while (state.get() % 3 != 2) {
                Thread.yield();
            }
            System.out.println("C");
            state.set(0);   // not use incrementAndGet() here to prevent Integer overflow
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(printA);
        Thread t2 = new Thread(printB);
        Thread t3 = new Thread(printC);

        t1.start();
        t2.start();
        t3.start();
    }
}
