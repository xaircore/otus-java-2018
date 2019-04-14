package com.xairlab.otus.annotation;


public class Main {
    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.add("com.xairlab.otus.annotation.SimpleSuccesTest");
        runner.add("com.xairlab.otus.annotation.SimpleFailedTest");
        runner.run();
    }
}
