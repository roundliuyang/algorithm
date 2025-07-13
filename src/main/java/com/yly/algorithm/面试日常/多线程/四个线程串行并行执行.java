package com.yly.algorithm.面试日常.多线程;


import java.util.concurrent.CountDownLatch;

/*
 “T1 执行完后，T2 和 T3 并行执行，等 T2 和 T3 都执行完后，T4 再执行”
 */
public class 四个线程串行并行执行 {
    public static void main(String[] args) {

        CountDownLatch latch1 = new CountDownLatch(1); // T1完成后释放T2/T3
        CountDownLatch latch2 = new CountDownLatch(2); // T2/T3都完成后释放T4

        Thread t1 = new Thread(() -> {
            System.out.println("T1");
            latch1.countDown(); // 通知T2/T3可以开始了
        });

        Thread t2 = new Thread(() -> {
            try {
                latch1.await(); // 等T1完成
                System.out.println("T2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch2.countDown(); // 通知T4
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                latch1.await(); // 等T1完成
                System.out.println("T3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch2.countDown(); // 通知T4
            }
        });

        Thread t4 = new Thread(() -> {
            try {
                latch2.await(); // 等T2和T3都完成
                System.out.println("T4");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 启动顺序无所谓
        t4.start();
        t3.start();
        t2.start();
        t1.start();
    }
}
