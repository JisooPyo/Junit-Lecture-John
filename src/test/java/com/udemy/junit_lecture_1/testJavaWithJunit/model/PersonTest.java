package com.udemy.junit_lecture_1.testJavaWithJunit.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import com.udemy.junit_lecture_1.testJavaWithJunit.ModelTests;

// Edit configurations -> Add New Configuration -> JUnit -> 명명하기(ex. Model Tests) -> Tags : model 입력 -> run
class PersonTest implements ModelTests {
    @Test
    void groupedAssertions() {
        // given
        Person person = new Person(1L, "Joe", "Buck");

        // then
        assertAll("Test Props Set",
            () -> assertEquals("Joe", person.getFirstName()),
            () -> assertEquals("Buck", person.getLastName())
        );
    }

    @Test
    void groupedAssertionsMsgs() {
        // given
        Person person = new Person(1L, "Joe", "Buck");

        // then
        assertAll("Test Props Set",
            () -> assertEquals("Joe", person.getFirstName(), "First Name Failed"),
            () -> assertEquals("Buck", person.getLastName(), "Last Name Failed")
        );
    }

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
}
