package com.udemy.junit_lecture_1.contextPractice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = HearingInterpreterComponentScanTest.TestConfig.class)
class HearingInterpreterComponentScanTest {
    @Configuration
    @ComponentScan("com.udemy.junit_lecture_1.contextPractice")
    static class TestConfig {
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHeard() {
        String word = hearingInterpreter.whatIHeard();

        assertEquals("Laurel", word);
    }
}
