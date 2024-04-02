package com.udemy.junit_lecture_1.testJavaWithJunit.model;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("model")
class OwnerTest {
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
}
