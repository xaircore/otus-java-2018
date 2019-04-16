package com.xairlab.otus.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private int[] array2 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    private AtomicInteger counter = new AtomicInteger();
    int index = 0;
    boolean reverse = false;

    private void action(int current) {
        while (true) {
            if (counter.compareAndSet(10, 0)) {
                if (reverse) {
                    Thread.currentThread().interrupt();
                } else {
                    array = array2;
                    reverse = true;
                    index = 0;
                }
            }
            if (counter.get() % 2 == current) {
                if (counter.compareAndSet(index + 1, index)) {
                    System.out.println(Thread.currentThread().getName() + " : " + array[index]);
                    index = counter.incrementAndGet();
                } else {
                    System.out.println(Thread.currentThread().getName() + " : " + array[index]);
                    counter.incrementAndGet();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main runner = new Main();
        new Thread(() -> runner.action(0)).start();
        new Thread(() -> runner.action(1)).start();

    }
}