package com.yly.algorithm.test;

public  abstract class Factory {
    
    public void create(){
        createProc();
    }
    abstract Product createProc();
}
