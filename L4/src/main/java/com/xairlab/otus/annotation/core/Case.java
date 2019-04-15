package com.xairlab.otus.annotation.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.xairlab.otus.annotation.api.TestStatus;
import com.xairlab.otus.annotation.runner.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Case {

    static Logger logger = LoggerFactory.getLogger(Runner.class);

    private final Object instance;
    private final Method before;
    private final Method after;
    private final Method test;

    public Case(Constructor factory, Method before, Method after, Method test) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        this.instance = factory.newInstance();
        this.before = before;
        this.after = after;
        this.test = test;
    }

    public TestStatus exec() {
        logger.info("Run case: " + test.getName());
        try {
            if (before != null) {
                before.invoke(instance);
            }
            test.invoke(instance);
            if (after != null) {
                after.invoke(instance);
            }
            return TestStatus.SUCCESS;
        } catch (Exception e) {
            logger.error("Failed test", e.getCause());
            return TestStatus.FAILED;
        }
    }
}
