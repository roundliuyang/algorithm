package com.yly.algorithm.面试日常.design.工厂方法;

// 抽象的工厂
abstract class Factory<T extends Product> {
    abstract T createProduct();
}