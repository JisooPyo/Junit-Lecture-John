package com.udemy.junit_lecture_1.testJavaWithJunit.services.map;

import java.util.Set;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.Pet;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.PetService;

public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
