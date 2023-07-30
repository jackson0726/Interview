package com.example.printfoobar;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

class FooBarSemaphore {
    private final int n;

    public FooBarSemaphore(int n) {
        this.n = n;
    }

    final Semaphore fooLock = new Semaphore(0);
    final Semaphore barLock = new Semaphore(1);

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            barLock.acquire();
        	printFoo.run();
            fooLock.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            fooLock.acquire();
        	printBar.run();
            barLock.release();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Số lần print: ");
        int n = scanner.nextInt();
        scanner.close();

        FooBarSemaphore fooBarSyncMethod = new FooBarSemaphore(n);

        Thread printFoo = new Thread(() -> {
            try {
                fooBarSyncMethod.foo(() -> System.out.print("Foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread printBar = new Thread(() -> {
            try {
                fooBarSyncMethod.bar(() -> System.out.print("Bar "));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        printBar.start();
        printFoo.start();
    }

}