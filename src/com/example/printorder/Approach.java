package com.example.printorder;

import java.util.concurrent.atomic.AtomicInteger;

public class Approach {

    static class Target {

        AtomicInteger counter = new AtomicInteger(0);

        void printFirst() throws InterruptedException {
            System.out.println("Target: " + Thread.currentThread());
            System.out.println("first");
            counter.incrementAndGet();
        }

        void printSecond() throws InterruptedException {
            while (counter.get() != 1) {
                System.out.println("Target: " + Thread.currentThread() + " | Count = " + counter.get());
            }
            System.out.println("second");
            counter.incrementAndGet();
        }

        void printThird() throws InterruptedException {
            while (counter.get() != 2) {
                System.out.println("Target: " + Thread.currentThread() + " | Count = " + counter.get());
            }
            System.out.println("third");
        }

    }

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

        t3.start();
        t1.start();
        t2.start();
    }

}
