package com.udemy.junit_lecture_1.testJavaWithJunit.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.Vet;
import com.udemy.junit_lecture_1.testJavaWithJunit.repositories.VetRepository;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.VetService;

public class VetSDJpaService implements VetService {

    private final VetRepository vetRepository;

    public VetSDJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long aLong) {
        return vetRepository.findById(aLong).orElse(null);
    }

    @Override
    public Vet save(Vet object) {
        return vetRepository.save(object);
    }

    @Override
    public void delete(Vet object) {
        vetRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        vetRepository.deleteById(aLong);
    }
}
