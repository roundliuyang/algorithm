package com.yly.algorithm.面试日常.design.工厂方法;

// 具体的工厂可以生产出相应的产品
class FactoryA extends Factory<ProductA> {
    @Override
    ProductA createProduct() {
        return new ProductA();
    }
}
