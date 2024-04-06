package com.udemy.junit_lecture_1.testJavaWithJunit.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Pet extends BaseEntity {

    private String name;
    private PetType petType;
    private Owner owner;
    private LocalDate birthDate;
    private Set<Visit> visits = new HashSet<>();

    public Pet(Long id) {
        super(id);
    }
}
