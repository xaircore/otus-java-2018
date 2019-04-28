package com.xairlab.otus.annotation.runner;

import java.util.ArrayList;

import com.xairlab.otus.annotation.core.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runner {

    private static Logger logger = LoggerFactory.getLogger(Runner.class);

    int total = 0;
    int success = 0;
    int failed = 0;

    private ArrayList<Suite> suites = new ArrayList<>();

    public void add(String name) {
        suites.add(new Suite(name));
    }

    public void run() {
        logger.info("Running test with " + this.getClass().getName() + " runner");
        for (Suite suite : suites) {
            suite.exec();
            total += suite.getTotalCount();
            success += suite.getSuccessCount();
            failed += suite.getFailedCount();
        }
        logger.info("Total count: " + total);
        logger.info("Success count: " + success);
        logger.info("Failed count: " + failed);
    }


}
