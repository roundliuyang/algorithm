package com.yly.algorithm.面试日常.design.模板方法;


public class FactoryA extends Factory {

    @Override
    public Product createProc() {
        return new ProductA();
    }
}
