package com.udemy.junit_lecture_1.testJavaWithJunit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person extends BaseEntity {

    public Person(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String firstName;
    private String lastName;

}
