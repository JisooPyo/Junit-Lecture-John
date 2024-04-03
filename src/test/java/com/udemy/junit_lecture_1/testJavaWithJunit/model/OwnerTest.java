package com.udemy.junit_lecture_1.testJavaWithJunit.model;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.udemy.junit_lecture_1.testJavaWithJunit.ModelTests;

class OwnerTest implements ModelTests {
    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1L, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");

        assertAll("Properties Test",
            () -> assertAll("Person Properties",
                () -> assertEquals("Joe", owner.getFirstName(), "Fist Name Did Not Match"),
                () -> assertEquals("Buck", owner.getLastName())),
            () -> assertAll("Owner Properties",
                () -> assertEquals("Key West", owner.getCity(), "Cith Did Not Match"),
                () -> assertEquals("1231231234", owner.getTelephone())
            ));

        // Hamcrest 라이브러리
        assertThat(owner.getCity(), is("Key West"));
    }

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {argumentsWithNames}")
    // short, byte, int, long, float, double, char, boolean, String, Class
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }
}
