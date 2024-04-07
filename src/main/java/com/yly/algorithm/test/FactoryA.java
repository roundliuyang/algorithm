package com.yly.algorithm.test;

public  class FactoryA extends Factory {

    @Override
    public Product createProc() {
        return new ProductA();
    }
}
