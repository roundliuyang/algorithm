package com.yly.algorithm.面试日常.多线程;

public class 三个线程交替打印1到99 {
    private static final Object lock = new Object();
    private static int count = 1;
    private static final int MAX = 99;

    public static void main(String[] args) {
        Thread t1 = new Thread(new PrintTask(1), "Thread-1");
        Thread t2 = new Thread(new PrintTask(2), "Thread-2");
        Thread t3 = new Thread(new PrintTask(0), "Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }

    static class PrintTask implements Runnable {
        private final int threadId;

        public PrintTask(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    // 检查是否需要当前线程打印
                    if (count > MAX) {
                        break;
                    }
                    if (count % 3 == threadId) {
                        System.out.println(Thread.currentThread().getName() + ": " + count);
                        count++;
                        lock.notifyAll(); // 唤醒其他线程
                    } else {
                        try {
                            lock.wait(); // 当前线程等待
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }
}
