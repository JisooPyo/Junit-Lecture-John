package com.udemy.junit_lecture_1.testJavaWithJunit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PetType extends BaseEntity {

    private String name;

    @Override
    public String toString() {
        return name;
    }

    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }
}
