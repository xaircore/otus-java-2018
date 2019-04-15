package com.xairlab.otus.annotation;

import com.xairlab.otus.annotation.api.After;
import com.xairlab.otus.annotation.api.Before;
import com.xairlab.otus.annotation.api.Test;

public class SimpleFailedTest {

    @Before
    public void e() {
    }

    @Test
    public void f() {
        throw new AssertionError("Fail");
    }

    @After
    public void d() {
    }
}
