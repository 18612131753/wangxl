package com.ray.thread;
/**
 * 一般关键词synchronized的用法
指定加锁对象:对给定对象加锁，进入同步代码前需要活的给定对象的锁。
直接作用于实例方法:相当于对当前实例加锁，进入同步代码前要获得当前实例的锁。
直接作用于静态方法:相当于对当前类加锁，进入同步代码前要获得当前类的锁。
 * */
public class TestSync2 implements Runnable {
    int b = 100;          

    synchronized void m1() throws InterruptedException {
        b = 1000;
        Thread.sleep(500); //6
        System.out.println("b=" + b);
    }

    synchronized void m2() throws InterruptedException {
        Thread.sleep(250); //5
        b = 2000;
    }

    public static void main(String[] args) throws InterruptedException {
        TestSync2 tt = new TestSync2();
        Thread t = new Thread(tt);  //1
        t.start(); //2:当调用start方法，这个线程才正真被启动，进入runnable状态，runnable状态表示可以执行，一切准备就绪了，但是并不表示一定在cpu上面执行，有没有真正执行取决服务cpu的调度。在这里当执行3步骤必定是先获得锁
 
        tt.m2(); //3
        System.out.println("main thread b=" + tt.b); //4
    }

    @Override
    public void run() {
        try {
            m1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

 }