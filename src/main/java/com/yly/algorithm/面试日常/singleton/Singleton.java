package com.yly.algorithm.面试日常.singleton;

public class Singleton {

    private static volatile Singleton singleton;

    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    new Singleton();
                }
            }
        }
        return singleton;
    }
}
