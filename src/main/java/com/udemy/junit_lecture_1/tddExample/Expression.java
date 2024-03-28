package com.udemy.junit_lecture_1.tddExample;

public interface Expression {
    Money reduce(Bank bank, String to);
    Expression plus(Expression addend);

    Expression times(int multiplier);
}
