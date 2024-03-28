package com.udemy.junit_lecture_1;

public class Franc extends Money {

    public Franc(int amount) {
        this.amount = amount;
    }

    Franc times(int multiplier) {
        return new Franc(amount * multiplier);
    }
}
