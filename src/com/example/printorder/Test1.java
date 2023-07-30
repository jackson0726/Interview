package com.example.printorder;


public class Test1 {

    /**
     * The same instance of Foo will be passed to three different threads.
     * Thread 1 will call printFirst(), thread 2 will call printSecond(), and thread 3 will call printThird().
     * Design a mechanism and modify the program to ensure that second()
     *      is executed after printFirst(), and printThird() is executed after printSecond().
     *
     * Example:
     * Output: "FirstSecondThird"
     */

    static class Foo {

        void printFirst() throws InterruptedException {
            System.out.print("First");
        }

        void printSecond() throws InterruptedException {
            System.out.print("Second");
        }

        void printThird() throws InterruptedException {
            System.out.print("Third");
        }

    }

    public static void main(String[] args) {
        Foo foo = new Foo();

        Thread t1 = new Thread(() -> {
            try {
                foo.printFirst();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                foo.printSecond();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            try {
                foo.printThird();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();
        t3.start();
        t1.start();
    }
}
