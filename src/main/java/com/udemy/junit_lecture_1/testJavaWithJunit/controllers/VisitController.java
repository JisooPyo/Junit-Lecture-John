package com.udemy.junit_lecture_1.testJavaWithJunit.controllers;

import java.util.Map;

import com.udemy.junit_lecture_1.testJavaWithJunit.model.Pet;
import com.udemy.junit_lecture_1.testJavaWithJunit.model.Visit;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.PetService;
import com.udemy.junit_lecture_1.testJavaWithJunit.services.VisitService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public Visit loadPetWithVisit(Long petId, Map<String, Object> model) {
        Pet pet = petService.findById(petId);
        model.put("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }
}
