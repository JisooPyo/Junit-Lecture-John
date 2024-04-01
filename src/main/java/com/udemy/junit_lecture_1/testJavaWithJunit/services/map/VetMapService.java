package com.udemy.junit_lecture_1.testJavaWithJunit.services.map;

import java.util.Set;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.Speciality;
import com.udemy.junit_lecture_1.testJavaWithJunit.model.Vet;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.SpecialtyService;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.VetService;

public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {

        if (object.getSpecialities() != null && !object.getSpecialities().isEmpty()) {
            object.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null) {
                    Speciality savedSpecialty = specialtyService.save(speciality);
                    speciality.setId(savedSpecialty.getId());
                }
            });
        }

        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
