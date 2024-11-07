package com.yly.test;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Solution {

    private static Lock lock;

    private static Condition condition;

    private static volatile int state = 0; // volatile is needed to ensure visibility

    
    private static Runnable printA = () -> {
        for(int i = 0; i < 3; i++){
            lock.lock();
          
        }
        
    };


    private static Runnable printB = () -> {
        for(int i = 0; i < 3; i++){

        }

    };

    private static Runnable printC = () -> {
        for(int i = 0; i < 3; i++){

        }

    };
    
}

