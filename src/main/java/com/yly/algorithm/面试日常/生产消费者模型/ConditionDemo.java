package com.yly.algorithm.面试日常.生产消费者模型;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 下面是用Condition实现的生产者消费者问题
 */
public class ConditionDemo {
    /**
     * 容器
     */
    private LinkedList<String> buffer;
    /**
     * 容器的容量
     */
    private int maxSize;
    /**
     * 锁
     */
    private Lock lock;
    /**
     * 缓冲区不为空的条件
     */
    private Condition bufferNotEmpty;
    /**
     * 缓冲区不满的条件
     */
    private Condition bufferNotFull;

    public ConditionDemo(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<String>();
        lock = new ReentrantLock();
        bufferNotEmpty = lock.newCondition();
        bufferNotFull = lock.newCondition();
    }

    public void set(String string) throws InterruptedException {
        // 获取锁
        lock.lock();
        try {
            while (maxSize == buffer.size()) {
                bufferNotFull.await();       // 缓冲区满了，等待空间变得可用
            }

            buffer.add(string);
            bufferNotEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public String get() throws InterruptedException {
        String string;
        lock.lock();
        try {
            while (buffer.size() == 0) {
                bufferNotEmpty.await();
            }
            string = buffer.poll();
            bufferNotFull.signal();
        } finally {
            lock.unlock();
        }
        return string;
    }
}
