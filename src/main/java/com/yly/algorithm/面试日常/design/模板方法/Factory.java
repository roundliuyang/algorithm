package com.yly.algorithm.面试日常.design.模板方法;


public abstract class Factory {

    public void create() {
        createProc();
    }

    abstract Product createProc();
}
