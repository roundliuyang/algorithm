package com.yly.algorithm.面试日常.多线程.三个线程交替打印ABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABCLock {

    private static Lock lock;

    private static Condition condition;

    private static int TIMES = 3; // how many times "ABC" should print

    private static volatile int state = 0; // volatile is needed to ensure visibility

    private static Runnable printA = () -> {
        for (int i = 0; i < TIMES; i++) {
            try {
                lock.lock();
                while (state % 3 != 0) {
                    condition.await();
                }
                System.out.println("A");
                state = 1;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    };

    private static Runnable printB = () -> {
        for (int i = 0; i < TIMES; i++) {
            try {
                lock.lock();
                while (state % 3 != 1) {
                    condition.await();
                }
                System.out.println("B");
                state = 2;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    };

    private static Runnable printC = () -> {
        for (int i = 0; i < TIMES; i++) {
            try {
                lock.lock();
                while (state % 3 != 2) {
                    condition.await();
                }
                System.out.println("C");
                state = 0;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        lock = new ReentrantLock();
        condition = lock.newCondition();

        Thread t1 = new Thread(printA);
        Thread t2 = new Thread(printB);
        Thread t3 = new Thread(printC);

        t1.start();
        t2.start();
        t3.start();
    }
}
