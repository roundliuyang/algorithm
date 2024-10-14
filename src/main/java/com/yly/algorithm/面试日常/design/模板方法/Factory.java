package com.yly.algorithm.面试日常.design.模板方法;


public abstract class Factory {
    public void createProduct() {
        // 准备材料
        prepareMaterials();
        // 组装产品
        assembleProduct();
    }

    public abstract void prepareMaterials();

    public abstract void assembleProduct();
}
