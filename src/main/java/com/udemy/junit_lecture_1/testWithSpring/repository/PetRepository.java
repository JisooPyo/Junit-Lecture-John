package com.udemy.junit_lecture_1.testWithSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.udemy.junit_lecture_1.testWithSpring.model.Pet;
import com.udemy.junit_lecture_1.testWithSpring.model.PetType;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value = "select ptype from PetType ptype order by ptype.name")
    List<PetType> findPetTypes();
}
