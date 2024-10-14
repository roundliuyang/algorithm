package com.yly.algorithm.面试日常.design.策略;

public class ShoppingCart {
    public void pay(PaymentStrategy strategy) {
        strategy.pay(10);
    }

}
