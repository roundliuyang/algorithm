package com.yly.algorithm.ratelimiting;

import java.util.Date;

/**
 * 令牌桶算法(单机版本)
 */
public class TokenBucketLimiter {

    /**
     * 令牌桶容量
     */
    private int capacity;

    /**
     * 令牌桶产生速率
     */
    private int rate;

    /**
     * 令牌数量
     */
    private int tokenAmount;

    public TokenBucketLimiter() {

    }

    public TokenBucketLimiter(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        tokenAmount = capacity;

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 以恒定速率放令牌
                while (true) {
                    synchronized (this) {
                        tokenAmount++;
                        if (tokenAmount > capacity) {
                            tokenAmount = capacity;
                        }
                    }
                    try {
                        Thread.sleep(1000 / rate);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public synchronized boolean tryAcquire(Request request) {
        if (tokenAmount > 0) {
            tokenAmount--;
            handleRequest(request);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 处理请求
     *
     * @param request
     */
    private void handleRequest(Request request) {
        request.setHandleTime(new Date());
        System.out.println(request.getCode() + "号请求被处理，请求发起时间："
                + request.getLaunchTime() + ",请求处理时间：" + request.getHandleTime() + ",处理耗时："
                + (request.getHandleTime().getTime() - request.getLaunchTime().getTime()) + "ms");
    }

    /**
     * 请求类，属性只包含一个名字字符串
     */
    static class Request {
        private int code;
        private Date launchTime;
        private Date handleTime;

        private Request() {
        }

        public Request(int code, Date launchTime) {
            this.launchTime = launchTime;
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Date getLaunchTime() {
            return launchTime;
        }

        public void setLaunchTime(Date launchTime) {
            this.launchTime = launchTime;
        }

        public Date getHandleTime() {
            return handleTime;
        }

        public void setHandleTime(Date handleTime) {
            this.handleTime = handleTime;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TokenBucketLimiter tokenBucketLimiter = new TokenBucketLimiter(5, 2);
        for (int i = 1; i <= 10; i++) {
            Request request = new Request(i, new Date());
            if (tokenBucketLimiter.tryAcquire(request)) {
                System.out.println(i + "号请求被接受");
            } else {
                System.out.println(i + "号请求被拒绝");
            }
        }
    }


}
