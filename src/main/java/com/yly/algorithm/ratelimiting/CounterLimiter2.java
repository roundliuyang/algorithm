package com.yly.algorithm.ratelimiting;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 计数器固定窗口算法2(单机版)
 */
public class CounterLimiter2 {
    /**
     * 一个时间窗口内最大请求数（限流最大请求数）
     */
    private static final Long MAX_NUM_REQUEST = 2L;

    /**
     * 一个时间窗口时间（毫秒）（限流时间）
     */
    private static final Long TIME_REQUEST = 1000L;

    /**
     * 一个时间窗口内请求的数量累计（限流请求数累计）
     */
    private AtomicInteger requestNum = new AtomicInteger(0);

    /**
     * 一个时间窗口开始时间（限流开始时间）
     */
    private AtomicLong requestTime = new AtomicLong(System.currentTimeMillis());

    public String index() {
        //  当前时间
        long nowTime = System.currentTimeMillis();
        //  判断是否在当前时间窗口
        if (nowTime < requestTime.longValue() + TIME_REQUEST) {
            //  判断是否达到最大限流请求数
            if (requestNum.longValue() < MAX_NUM_REQUEST) {
                //  在时间窗口内，且没有达到最大限流请求数，请求数 +1
                requestNum.incrementAndGet();
                String msg = String.format("请求成功，当前请求是 %s 次", requestNum.longValue());
                System.out.println(msg);
                return msg;
            }
        } else {
            //  超时后重置，开启一个新的时间窗口
            requestTime = new AtomicLong(nowTime);
            requestNum = new AtomicInteger(1);
            String msg = String.format("请求成功，当前请求是 %s 次", requestNum.longValue());
            System.out.println(msg);
            return msg;
        }
        requestNum.incrementAndGet();
        String msg = String.format("请求失败，被限流，当前请求是 %s 次", requestNum.longValue());
        System.out.println(msg);
        return msg;
    }
}
