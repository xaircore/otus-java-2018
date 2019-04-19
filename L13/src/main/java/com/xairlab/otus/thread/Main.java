package com.xairlab.otus.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private AtomicInteger counter = new AtomicInteger();
    int index = 0;
    boolean reverse = false;

    private void action(int current) {
        while (true) {
            if (counter.get() % 2 == current) {
                if (counter.get() % 20 == 1) {
                    reverse = !reverse;
                }
                try {
                    System.out.println(Thread.currentThread().getName() + " : " + index);
                    if (reverse) {
                        if (current == 1) {
                            index++;
                        }
                    } else {
                        if (current == 1) {
                            index--;
                        }
                    }
                    counter.getAndIncrement();
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