package com.udemy.junit_lecture_1.testWithSpring.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class NamedEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return this.getName();
    }

}
