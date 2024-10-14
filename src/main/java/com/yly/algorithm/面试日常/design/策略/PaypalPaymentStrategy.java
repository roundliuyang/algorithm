package com.yly.algorithm.面试日常.design.策略;

public class PaypalPaymentStrategy implements PaymentStrategy{
    @Override
    public void pay(int amount) {
        System.out.println("paypal pay");
    }
}
