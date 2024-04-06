package com.udemy.junit_lecture_1.testJavaWithJunit.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Visit extends BaseEntity {

    private LocalDate date;
    private String description;
    private Pet pet;

    public Visit() {
        super(null);
    }

    public Visit(Long id) {
        super(id);
    }

    public Visit(Long id, LocalDate date) {
        super(id);
        this.date = date;
    }
}
