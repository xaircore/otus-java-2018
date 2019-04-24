package com.xairlab.otus.json.entity;

public class WithString {

    private short len;
    private String name;

    @Override
    public String toString() {
        return "WithString{" +
                "len=" + len +
                ", name='" + name + '\'' +
                '}';
    }

    public WithString(short len, String name) {
        this.len = len;
        this.name = name;
    }
}
