package com.example.printorder;

import java.util.concurrent.atomic.AtomicInteger;

public class Synchronized {

    public static void main(String[] args) throws InterruptedException {
        Target target = new Target();

        Thread t1 = new Thread(() -> {
            try {
                System.out.println("Thread 1: " + Thread.currentThread());
                target.printFirst();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("Thread 2: " + Thread.currentThread());
                target.printSecond();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                System.out.println("Thread 3: " + Thread.currentThread());
                target.printThird();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.join();
        t2.join();
        t3.join();

        t3.start();
        t1.start();
        t2.start();
    }

    static class Target {

        final Object LOCK = new Object();

        AtomicInteger count = new AtomicInteger(0);

        void printFirst() throws InterruptedException {
            System.out.println("Target: " + Thread.currentThread());
            synchronized (LOCK) {
                System.out.println("first");
                count.incrementAndGet();
                notifyAll();
            }
        }

        void printSecond() throws InterruptedException {
            System.out.println("Target: " + Thread.currentThread());
            synchronized (LOCK) {
                while (count.get() < 1) wait();
                System.out.println("second");
                count.incrementAndGet();
                notifyAll();
            }
        }

        void printThird() throws InterruptedException {
            System.out.println("Target: " + Thread.currentThread());
            synchronized (LOCK) {
                while (count.get() < 2) wait();
                System.out.println("third");
                notifyAll();
            }
        }

    }
}
