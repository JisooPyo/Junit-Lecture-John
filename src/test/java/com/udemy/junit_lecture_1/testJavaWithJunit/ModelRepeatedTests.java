package com.udemy.junit_lecture_1.testJavaWithJunit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("repeated")
public interface ModelRepeatedTests {
    @BeforeEach
    // 주의 : RepetitionInfo는 반복되는 메서드(@RepeatedTest)에 한해서만 Info가 있다.
    // 반복하지 않는 메서드에 적용하면 예외를 던진다.
    default void beforeEachConsoleOutputter(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Running Test - " + testInfo.getDisplayName() + " - " +
            repetitionInfo.getCurrentRepetition() + " | " + repetitionInfo.getTotalRepetitions());
    }
}
