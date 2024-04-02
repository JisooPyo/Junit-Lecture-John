package com.udemy.junit_lecture_1.testJavaWithJunit.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

import com.udemy.junit_lecture_1.testJavaWithJunit.ModelRepeatedTests;

public class PersonRepeatedTests implements ModelRepeatedTests {
    // {displayName}이 name의 가장 앞에 오는 경우 run view 에서는 생략되어 보인다.
    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} - {totalRepetitions}")
    @DisplayName("My Repeated Test")
    void myRepeatedTest() {
        // TODO - impl
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(
        value = 3,
        name = "{displayName} cur: {currentRepetition}, total: {totalRepetitions}"
    )
    @DisplayName("My Assignment Repeated Test")
    void myAssignmentRepeated() {
        // TODO - impl
    }
}
