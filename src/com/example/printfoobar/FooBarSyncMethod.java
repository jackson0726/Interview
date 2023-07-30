package com.example.printfoobar;

import java.util.Scanner;

class FooBarSyncMethod {
    private final int n;
    boolean isPrintFoo = true;

    public FooBarSyncMethod(int n) {
        this.n = n;
    }

    public synchronized void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!isPrintFoo) wait();
        	printFoo.run();
            isPrintFoo = false;
            notifyAll();
        }
    }

    public synchronized void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (isPrintFoo) wait();
        	printBar.run();
            isPrintFoo = true;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Số lần print: ");
        int n = scanner.nextInt();
        scanner.close();

        FooBarSyncMethod fooBarSyncMethod = new FooBarSyncMethod(n);

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