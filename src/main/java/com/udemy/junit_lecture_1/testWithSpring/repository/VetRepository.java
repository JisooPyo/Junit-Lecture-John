package com.udemy.junit_lecture_1.testWithSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.junit_lecture_1.testWithSpring.model.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {
}
