package com.udemy.junit_lecture_1.testWithSpring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.junit_lecture_1.testWithSpring.model.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    List<Owner> findByLastName(String lastName);
}
