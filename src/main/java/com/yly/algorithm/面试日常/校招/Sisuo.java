package com.yly.algorithm.面试日常.校招;

public class Sisuo{

	public static void main(String[] args) {
	final Object a = new Object();
	final Object b = new Object();
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			synchronized (a) {
				System.out.println("获得a,想获得b");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (b) {
					System.out.println("获得b");
				}
			}
			
		}
	}).start();
	
	new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (b) {
					System.out.println("获得b，想获得a");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized (a) {
						System.out.println("获得a");
					}
				}
				
				
			}
		}).start();

	
		
	}
	


}
