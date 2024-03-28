package com.udemy.junit_lecture_1;

public class Money {
    protected int amount;

    public boolean equals(Object object) {
        Money money = (Money)object;
        return amount == money.amount
            && getClass().equals(object.getClass());
    }
}
