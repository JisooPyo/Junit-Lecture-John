package com.udemy.junit_lecture_1.testJavaWithJunit.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

// Edit configurations -> Add New Configuration -> JUnit -> 명명하기(ex. Model Tests) -> Tags : model 입력 -> run
@Tag("model")
class PersonTest {
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
}
