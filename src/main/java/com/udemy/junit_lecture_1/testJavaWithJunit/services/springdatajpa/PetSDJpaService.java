package com.udemy.junit_lecture_1.testJavaWithJunit.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.Pet;
import com.udemy.junit_lecture_1.testJavaWithJunit.repositories.PetRepository;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.PetService;

public class PetSDJpaService implements PetService {

    private final PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}