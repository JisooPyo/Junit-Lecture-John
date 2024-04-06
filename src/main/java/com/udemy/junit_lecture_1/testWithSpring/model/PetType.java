package com.udemy.junit_lecture_1.testWithSpring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "types")
public class PetType extends NamedEntity {
}
