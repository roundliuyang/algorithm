package com.yly.algorithm.面试日常.design.策略;

public class CreditPaymentStrategy implements PaymentStrategy{
    @Override
    public void pay(int amount){
        System.out.println("credit pay");
    }
}
