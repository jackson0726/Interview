package com.example.printorder;

import java.util.concurrent.Semaphore;

public class JavaSemaphore {

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

    static class Target {

        Semaphore printA = new Semaphore(0);
        Semaphore printB = new Semaphore(0);

        void printFirst() throws InterruptedException {
            System.out.println("first");
            printA.release();
        }

        void printSecond() throws InterruptedException {
            System.out.println("printSecond.availablePermits = " + printA.availablePermits());
            printA.acquire();
            System.out.println("second");
            printB.release();
        }

        void printThird() throws InterruptedException {
            System.out.println("printThird.availablePermits = " + printB.availablePermits());
            printB.acquire();
            System.out.println("third");
            printB.release();
        }

    }
}
