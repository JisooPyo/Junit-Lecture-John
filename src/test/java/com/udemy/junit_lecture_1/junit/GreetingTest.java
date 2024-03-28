package com.udemy.junit_lecture_1.junit;

import org.junit.jupiter.api.Test;

class GreetingTest {

    @Test
    void helloWorld() {
        Greeting greeting = new Greeting();
        System.out.println(greeting.helloWorld());
    }

    @Test
    void testHelloWorld() {
        Greeting greeting = new Greeting();
        System.out.println(greeting.helloWorld("John"));
    }
}
