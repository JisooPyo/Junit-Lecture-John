package com.udemy.junit_lecture_1;

public class Bank {
    Money reduce(Expression source, String toCurrency) {
        return source.reduce(toCurrency);
    }
}
