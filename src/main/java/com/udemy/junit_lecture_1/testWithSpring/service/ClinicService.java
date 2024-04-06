package com.udemy.junit_lecture_1.testWithSpring.service;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.udemy.junit_lecture_1.testWithSpring.model.PetType;
import com.udemy.junit_lecture_1.testWithSpring.model.Vet;

public interface ClinicService {
    Collection<PetType> findPetTypes() throws DataAccessException;
    List<Vet> findVets() throws DataAccessException;

}
