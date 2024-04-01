package com.udemy.junit_lecture_1.testJavaWithJunit.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IndexControllerTest {
    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @Test
    void index() {
        assertEquals("index", controller.index());

        // message
        assertEquals("index", controller.index(), "Wrong View Returned");

        // lambda
        assertEquals("index", controller.index(),
            () -> "Another Expensive Message "
                + "Make me only if you have to");
    }

    @Test
    void oupsHandler() {
        assertTrue("notimplemented".equals(controller.oupsHandler()),
            () -> "This is some expensive " +
                "Message to build" +
                "for my test");
    }
}