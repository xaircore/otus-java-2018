package com.xairlab.otus.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Case {

    static Logger logger = LoggerFactory.getLogger(Runner.class);

    private final Object instance;
    private final Method before;
    private final Method after;
    private final Method test;
    private TestStatus status;

    public Case(Constructor factory, Method before, Method after, Method test) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        this.instance = factory.newInstance();
        this.before = before;
        this.after = after;
        this.test = test;
    }

    public void exec() {
        logger.info("Run case: " + test.getName());
        try {
            if (before != null) {
                before.invoke(instance);
            }
            test.invoke(instance);
            if (after != null) {
                after.invoke(instance);
            }
            status = TestStatus.SUCCESS;
        } catch (AssertionError | InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
            logger.error("Failed test", e.getCause());
            status = TestStatus.FAILED;
        }
    }

    public TestStatus getStatus() {
        return status;
    }
}
