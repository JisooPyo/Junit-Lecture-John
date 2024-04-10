package com.udemy.junit_lecture_1.testWithSpring.service;

import java.util.Collection;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.junit_lecture_1.testWithSpring.model.Owner;
import com.udemy.junit_lecture_1.testWithSpring.model.PetType;
import com.udemy.junit_lecture_1.testWithSpring.model.Vet;
import com.udemy.junit_lecture_1.testWithSpring.repository.OwnerRepository;
import com.udemy.junit_lecture_1.testWithSpring.repository.PetRepository;
import com.udemy.junit_lecture_1.testWithSpring.repository.VetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClinicServiceImpl implements ClinicService {
    private final VetRepository vetRepository;
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<PetType> findPetTypes() throws DataAccessException {
        return petRepository.findPetTypes();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "vets")
    public List<Vet> findVets() throws DataAccessException {
        return vetRepository.findAll();
    }

    @Override
    @Transactional
    public void saveOwner(Owner owner) throws DataAccessException {
        ownerRepository.save(owner);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Owner findOwnerById(int id) throws DataAccessException {
        return ownerRepository.findById((long)id).orElseThrow();
    }

}
