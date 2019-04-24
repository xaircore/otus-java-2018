package com.xairlab.otus.json.entity;

import com.xairlab.otus.json.api.JsonName;

public class WithAnnotation {

    @JsonName(name = "vova")
    private int age;
    private boolean sex;

    @Override
    public String toString() {
        return "WithAnnotation{" +
                "age=" + age +
                ", sex=" + sex +
                '}';
    }

    public WithAnnotation(int age, boolean sex) {
        this.age = age;
        this.sex = sex;
    }
}
