package com.xairlab.otus.json.entity;

public class Plain {

    private int age;
    private char name;
    private boolean sex;
    private double weight;

    public Plain(int age, char name, boolean sex, double weight) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Plain{" +
                "age=" + age +
                ", name=" + name +
                ", sex=" + sex +
                ", weight=" + weight +
                '}';
    }
}
