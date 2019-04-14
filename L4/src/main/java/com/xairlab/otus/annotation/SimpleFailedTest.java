package com.xairlab.otus.annotation;

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
