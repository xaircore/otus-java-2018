package com.xairlab.otus.gc;


/**
 * Created by tully.
 */
class Benchmark implements BenchmarkMBean {

    private volatile int size = 0;
    private final int loopCounter;

    public Benchmark(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    void run() throws InterruptedException {
        for(int idx = 0; idx < loopCounter; idx ++) {
            int local = size;
            Object[] array = new Object[local];
            for (int i = 0; i < local; i++) {
                array[i] = new Long(idx + 300);
            }
            Thread.sleep(100);
            for (int i = 100; i < 150; i++) {
                array[i] = null;
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

}
