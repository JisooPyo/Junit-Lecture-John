package com.udemy.junit_lecture_1.testJavaWithJunit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Speciality extends BaseEntity {
    private String description;

    public Speciality(Long id, String description) {
        super(id);
        this.description = description;
    }
}
