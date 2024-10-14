package com.yly.algorithm.面试日常.design.模板方法;


public class FactoryA extends Factory {

    @Override
    public void prepareMaterials() {
        System.out.println("准备材料...");
    }

    @Override
    public void assembleProduct() {
        System.out.println("组装产品");
    }
}
