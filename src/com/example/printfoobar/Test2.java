package com.example.printfoobar;


import java.util.Scanner;

public class Test2 {

    /**
     * The same instance of FooBar will be passed to two different threads:
     *
     * thread A will call foo(), while thread B will call bar().
     *
     * Modify the given program to output "foobar" n times.
     *
     * Example 1:
     * Input: n = 1
     * Output: "FooBar"
     * Explanation: There are two threads being fired asynchronously. One of them calls foo(), while the other calls bar().
     * "FooBar" is being output 1 time.
     *
     * Example 2:
     * Input: n = 2
     * Output: "FooBarFooBar"
     * Explanation: "FooBar" is being output 2 times.
     */

    static class FooBar {
        private final int n;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                printFoo.run();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                printBar.run();
            }
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Số lần print: ");
        int n = scanner.nextInt();
        scanner.close();

        FooBar fooBar = new FooBar(n);

        Thread A = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("Foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread B = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("Bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        A.start();
        B.start();

    }

}
