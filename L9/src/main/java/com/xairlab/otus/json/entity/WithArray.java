package com.xairlab.otus.json.entity;

import java.util.Arrays;

public class WithArray {

    private int[] tokens;

    @Override
    public String toString() {
        return "WithArray{" +
                "tokens=" + Arrays.toString(tokens) +
                ", cost=" + cost +
                '}';
    }

    private float cost;

    public WithArray(int[] tokens, float cost) {
        this.tokens = tokens;
        this.cost = cost;
    }
}
