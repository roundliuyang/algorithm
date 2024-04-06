package com.yly.algorithm.面试日常.生产消费者模型;

public class synchronizedDemo {
    private String name;
    // 标记库存是否有内容
    private boolean hasComputer = false;
    
    public synchronized  void putOne(String name){
        while(hasComputer){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        System.out.println("生产者...生产了 " + name);
        // 更新标记
        this.hasComputer = true;
        this.notifyAll();
    }
    
    public synchronized void takeOne(){
        // 若库存中没有内容，则消费线程阻塞等待生产完毕后继续
        while(!hasComputer){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者...消费了 " + name);
        this.hasComputer = false;
        this.notifyAll();
    }


    public static void main(String[] args) {
        // 用于通信的库存类
        synchronizedDemo computer = new synchronizedDemo();
        // 定义两个生产者和两个消费者
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    computer.putOne("Dell");
                }
            }
        });
        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    computer.putOne("Mac");
                }
            }
        });

        Thread c1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    computer.takeOne();
                }
            }
        });
        Thread c2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    computer.takeOne();
                }
            }
        });
        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
