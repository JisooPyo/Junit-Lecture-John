package com.udemy.junit_lecture_1.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GreetingTest {
    Greeting greeting;

    @BeforeEach
    void setUp() {
        System.out.println("In Before Each....");
        greeting = new Greeting();
    }

    @Test
    void helloWorld1() {
        System.out.println(greeting.helloWorld());
    }

    @Test
    void helloWorld2() {
        System.out.println(greeting.helloWorld("John"));
    }
}
